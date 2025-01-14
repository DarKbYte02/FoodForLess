import { Component,inject } from '@angular/core';
import { HeaderComponent } from '../../shared/header/header.component';
import { FooterComponent } from '../../shared/footer/footer.component';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Usuario } from '../../usuario';
import { UserService } from '../../services/user.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-view-profile',
  imports: [HeaderComponent, FooterComponent, FormsModule, ReactiveFormsModule],
  templateUrl: './view-profile.component.html',
  styleUrl: './view-profile.component.css'
})
export class ViewProfileComponent {
  private usuarioService: UserService = new UserService();
  route: ActivatedRoute = inject(ActivatedRoute);  
  userID: number = 0;
  usuario: Usuario = new Usuario();

    profileForm = new FormGroup({
      name: new FormControl(''),       // Campo para el nombre del usuario
      email: new FormControl(''),      // Campo para el correo electrónico
      password: new FormControl(''),   // Campo para la contraseña
      image: new FormControl(''),      // Campo para el enlace de la imagen
    });


    onSubmit() {

    }
    onImageLinkChange(event:any) {

    }

    constructor() { 
      this.userID = Number(this.route.snapshot.paramMap.get('id'));
      this.usuarioService.get(this.userID).subscribe((response: any) => {
        this.usuario = response as Usuario;
      });

    }
}
