import { Component,inject } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { Router } from '@angular/router';
import { UserService } from '../../services/user.service';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Usuario } from '../../usuario';


@Component({
  selector: 'app-register',
  imports: [FormsModule, ReactiveFormsModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css',
  providers: [CookieService]
})
export class RegisterComponent {
    private cookieService = inject(CookieService);
    private router = inject(Router);
    private userServices = inject(UserService);

    email :FormControl;
    password: FormControl;
    name: FormControl;
    image: string = "/food.svg"; //Default image
    user: Usuario = new Usuario();

    userId:string = this.cookieService.get('userId');

    registerForm = new FormGroup({
      email: new FormControl(''),
      password: new FormControl(''),
      name: new FormControl('')
    });

    constructor(){
      if(this.userId != "" && this.userId != "-1"){
          this.router.navigate(['listing']);
        }
      }

    onSubmit(){
        if(this.registerForm.value.email?.length == 0 || this.registerForm.value.password?.length == 0 || this.registerForm.value.name?.length == 0){
            (this.registerForm.value.email?.length == 0) ? console.log("Campo de correo vacío"): null;
            (this.registerForm.value.password?.length == 0) ? console.log("Campo de contraseña vacío"): null;
            (this.registerForm.value.name?.length == 0) ? console.log("Campo de nombre vacío"): null;
        }
        if(this.registerForm.value.email!.length >= 4 && this.registerForm.value.password!.length >= 4 && this.registerForm.value.name!.length >= 4){
            this.user.nombreUsuario = this.registerForm.value.name!;
            this.user.correoUsuario = this.registerForm.value.email!;
            this.user.contrasenaUsuario = this.registerForm.value.password!;
            this.user.imagenUsuario = "https://static.vecteezy.com/system/resources/thumbnails/024/983/914/small/simple-user-default-icon-free-png.png";
            this.user.idUser = 0;
            this.userServices.create(this.user).subscribe((data: any) => {
                console.log(data);
            });
            this.router.navigate(['login']);

        }
        console.log(this.registerForm.value);
    }
  
}
