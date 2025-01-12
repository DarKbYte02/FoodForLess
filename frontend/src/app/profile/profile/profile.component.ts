import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HeaderComponent } from "../../shared/header/header.component";
import { FooterComponent } from "../../shared/footer/footer.component";
import { CookieService } from 'ngx-cookie-service';
import { Router } from '@angular/router';
import { UserService } from '../../services/user.service';
import { Usuario } from '../../usuario';

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [HeaderComponent, FooterComponent, FormsModule, ReactiveFormsModule],
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css'],
  providers: [CookieService],
})
export class ProfileComponent implements OnInit {
  userId: string = '';
  user: any;
  profileForm: FormGroup;
  defaultImage: string = '/profile.png'; 
  newUser : Usuario = new Usuario();

  constructor(
    private cookieService: CookieService,
    private router: Router,
    private userService: UserService
  ) {
    // Inicializar el formulario reactivo con los campos necesarios
    this.profileForm = new FormGroup({
      name: new FormControl(''),       // Campo para el nombre del usuario
      email: new FormControl(''),      // Campo para el correo electrónico
      password: new FormControl(''),   // Campo para la contraseña
      image: new FormControl(''),      // Campo para el enlace de la imagen
    });
  }

  ngOnInit(): void {
    // Validar si el usuario está logueado antes de cargar el perfil
    if (!this.cookieService.check('userId')) {
      // Redirigir al login si no está logueado
      this.router.navigate(['login']);
    } else {
      // Obtener el ID del usuario desde la cookie
      this.userId = this.cookieService.get('userId');
      this.loadUserData();
    }
  }

  // Cargar los datos del usuario desde el servicio
  loadUserData() {
    this.userService.get(Number(this.userId)).subscribe(
      (data: any) => {
        this.user = data;
        this.profileForm.setValue({
          name: this.user.nombreUsuario,
          email: this.user.correoUsuario,
          password: '', // Dejar vacío para que el usuario lo llene solo si desea cambiarlo
          image: this.user.imagenUsuario || this.defaultImage,
        });
      },
      (error: any) => {
        console.error('Error al cargar los datos del usuario:', error);
      }
    );
  }

  // Manejar el envío del formulario
  onSubmit() {
    const updatedUser = this.profileForm.value;
    this.newUser.idUser = Number(this.userId);
    this.newUser.nombreUsuario = updatedUser.name;
    this.newUser.correoUsuario = updatedUser.email;
    this.newUser.contrasenaUsuario = updatedUser.password;
    this.newUser.imagenUsuario = updatedUser.image;

    if(this.newUser.contrasenaUsuario != null && this.newUser.idUser != null && this.newUser.nombreUsuario != null && this.newUser.correoUsuario != null && this.newUser.imagenUsuario != null){
      if(this.newUser.contrasenaUsuario.length >= 4 && this.newUser.nombreUsuario.length >= 4 && this.newUser.correoUsuario.length >= 4){
          //console.log(this.profileForm.value)

          this.userService.update(this.newUser).subscribe(
            (data: any) => {
              
              alert('Perfil actualizado correctamente');
              this.router.navigate(['listing']);
            },
            (error: any) => {
              alert('Error al actualizar el perfil.');
            }
          );
    }
  }
  else{
    alert('Error al actualizar el perfil');
  }
}
    /*this.userService.update(updatedUser).subscribe(
      (data: any) => {
        
        alert('Perfil actualizado correctamente');
        this.router.navigate(['listing']);
      },
      (error: any) => {
        alert('Error al actualizar el perfil.');
      }
    );*/
  

  // Manejar el cambio en el enlace de la imagen
  onImageLinkChange(event: any) {
    const imageUrl = event.target.value;
    this.profileForm.patchValue({ image: imageUrl });
  }

}
