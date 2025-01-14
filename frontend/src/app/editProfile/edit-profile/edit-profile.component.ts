import { Component,inject } from '@angular/core';
import { HeaderComponent } from '../../shared/header/header.component';
import { FooterComponent } from '../../shared/footer/footer.component';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Usuario } from '../../usuario';
import { UserService } from '../../services/user.service';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';

@Component({
  selector: 'app-edit-profile',
  imports: [HeaderComponent, FooterComponent, FormsModule, ReactiveFormsModule],
  templateUrl: './edit-profile.component.html',
  styleUrl: './edit-profile.component.css'
})
export class EditProfileComponent {
    private usuarioService: UserService = new UserService();
    route: ActivatedRoute = inject(ActivatedRoute);
    router = inject(Router);  
    userID: number = 0;
    usuario: Usuario = new Usuario();
    usuarioActualizado: Usuario = new Usuario();
      profileForm = new FormGroup({
        name: new FormControl(''),       // Campo para el nombre del usuario
        email: new FormControl(''),      // Campo para el correo electrónico
        password: new FormControl(''),   // Campo para la contraseña
        image: new FormControl(''),      // Campo para el enlace de la imagen
      });
  
  
      onSubmit() {
        this.usuarioActualizado.idUser =  Number(this.route.snapshot.paramMap.get('id'));
        this.usuarioActualizado.nombreUsuario = this.profileForm.get('name')!.value!;
        this.usuarioActualizado.correoUsuario = this.profileForm.get('email')!.value!;
        this.usuarioActualizado.contrasenaUsuario = this.profileForm.get('password')!.value!;
        this.usuarioActualizado.imagenUsuario = this.profileForm.get('image')!.value!;
        console.log(this.usuarioActualizado);

        if(this.usuarioActualizado.nombreUsuario != null && this.usuarioActualizado.correoUsuario != null && this.usuarioActualizado.contrasenaUsuario != null){
          this.usuarioService.update(this.usuarioActualizado).subscribe((response: any) => {
            alert("Usuario actualizado");
            this.router.navigate(['/admin']);
            //console.log(response);
          });
        }
      }

  
      constructor() { 
        this.userID = Number(this.route.snapshot.paramMap.get('id'));
        this.usuarioService.get(this.userID).subscribe((response: any) => {
          this.usuario = response as Usuario;
          this.profileForm.setValue({
            name: this.usuario.nombreUsuario,
            email: this.usuario.correoUsuario,
            password: this.usuario.contrasenaUsuario,
            image: this.usuario.imagenUsuario
          });
        });
  
      }
}
