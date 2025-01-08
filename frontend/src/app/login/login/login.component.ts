import { Component,inject } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { UserService } from '../../services/user.service';
import { Usuario } from '../../usuario';
import {Md5} from 'ts-md5';
import { CookieService } from 'ngx-cookie-service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-login',
  imports: [FormsModule, ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
  providers: [CookieService],
})
export class LoginComponent {
  private cookieService = inject(CookieService);
  private router = inject(Router);
  private userServices = inject(UserService);

  email :FormControl;
  password: FormControl;
  hashedPassword: string = '';
  user: Usuario = new Usuario();
  userId:string = this.cookieService.get('userId');

  loginForm = new FormGroup({
    email: new FormControl(''),
    password: new FormControl('')
  });



  constructor(){
  if(this.userId != "" && this.userId != "-1"){
      this.router.navigate(['listing']);
    }
  }



  onSubmit(){
    //console.log(this.loginForm.value);
    if(this.loginForm.value.email?.length == 0 || this.loginForm.value.password?.length == 0){
      (this.loginForm.value.email?.length == 0) ? console.log("Campo de correo vacío"): null;
      (this.loginForm.value.password?.length == 0) ? console.log("Campo de contraseña vacío"): null;
    }
    else{
    this.userServices.getHashedPassword(this.loginForm.value.email!).subscribe((data: any) => {
      if(data != null){
        this.user = data;
        console.log(this.user.contrasenaUsuario);
        console.log(Md5.hashStr(this.loginForm.value.password!).toString());
        if(Md5.hashStr(this.loginForm.value.password!).toString() == this.user.contrasenaUsuario){
          console.log("Contraseña correcta");
          this.cookieService.set('userId', this.user.idUser.toString());
          this.cookieService.set('userName', this.user.nombreUsuario);
          this.router.navigate(['listing']);
        }
        else{
          alert("Contraseña incorrecta");
          console.log("Contraseña incorrecta");
          this.router.navigate(['login']);
        }
      }
      else{
        alert("Usuario no encontrado, revisa tus credenciales");
        console.log("Usuario no encontrado, revisa tus credenciales");
        this.router.navigate(['login']);
      }
    });
    }
  }
}
