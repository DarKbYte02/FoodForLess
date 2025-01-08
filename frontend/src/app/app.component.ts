import { Component,inject } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { HeaderComponent } from './shared/header/header.component';
import { FooterComponent } from './shared/footer/footer.component';
import { CookieService } from 'ngx-cookie-service';
import { UserService } from './services/user.service';
import { Usuario } from './usuario';
import { RouterModule } from '@angular/router';
import {ReactiveFormsModule} from '@angular/forms';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, RouterModule, ReactiveFormsModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
  providers: [CookieService],
})
export class AppComponent {
  private cookieService = inject(CookieService);
    private userService = inject(UserService);
    userId = this.cookieService.get('userId');
      cookieValue: string;
      usuario : Usuario;
  
      constructor() {
        if(this.userId == "" || this.userId == "-1"){
          this.cookieService.set('userId', '-1');
          this.usuario = new Usuario();
          this.usuario.idUser = -1;
          console.log("No hay usuario logueado");
        }
        else{
              this.usuario = new Usuario();
              this.cookieValue = this.cookieService.get('userId');
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
  title = 'FoodForLess';
}
