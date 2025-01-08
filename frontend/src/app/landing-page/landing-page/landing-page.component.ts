import { Component, inject,Input } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { Usuario } from '../../usuario'
import { CommonModule } from '@angular/common';
import { FooterComponent } from "../../shared/footer/footer.component";
import { HeaderComponent } from "../../shared/header/header.component";



@Component({
  selector: 'app-landing-page',
  imports: [CommonModule, RouterModule, FooterComponent, HeaderComponent],
  templateUrl: './landing-page.component.html',
  styleUrl: './landing-page.component.css',
  providers: [CookieService],
})
export class LandingPageComponent {
  @Input() parentMessage: Usuario;
  private cookieService = inject(CookieService);
}
