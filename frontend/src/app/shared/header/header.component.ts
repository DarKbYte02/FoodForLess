import { Component,inject } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { UserService } from '../../services/user.service';
import { Usuario } from '../../usuario';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  imports: [],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css',
  providers: [CookieService],
})
export class HeaderComponent {
  private cookieService = inject(CookieService);
  private userService = inject(UserService);
  private router = inject(Router);
  userId = this.cookieService.get('userId');
    cookieValue: string;
    usuario : Usuario;

    constructor() {
      if(this.userId == "" || this.userId == "-1"){
        this.cookieService.set('userId', '-1');
        this.usuario = new Usuario();
        this.usuario.idUser = -1;
      }
      else{
            this.usuario = new Usuario();
            this.cookieValue = this.cookieService.get('userId');
            this.usuario.nombreUsuario = this.cookieService.get('userName');
            console.log(this.cookieValue);
            /*
            this.userService.get(parseInt(this.cookieValue))
                .subscribe((response: any) => {
                  const usuario: Usuario = response as Usuario;
                  console.log(usuario);
                  this.usuario = usuario;
                });
            */
      }

    }

    logOut(){
      console.log("Logout");
      this.cookieService.set('userId', '-1');
      this.cookieService.set('userName', '');
      this.router.navigate(['/']);

    }
}
