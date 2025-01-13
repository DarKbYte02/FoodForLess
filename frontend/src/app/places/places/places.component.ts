import { Component, inject } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { PlaceService } from '../../services/place.service';
import { Lugar } from '../../lugar';
import { HeaderComponent } from "../../shared/header/header.component";
import { FooterComponent } from "../../shared/footer/footer.component";
import { CommonModule } from '@angular/common';
import { RouterModule, Router} from '@angular/router';

@Component({
  selector: 'app-places',
  imports: [HeaderComponent, FooterComponent,CommonModule,RouterModule],
  templateUrl: './places.component.html',
  styleUrl: './places.component.css',
  providers: [CookieService]
})
export class PlacesComponent {
  private cookieService = inject(CookieService);
  private lugarService = inject(PlaceService);
  private router = inject(Router);
  userId:string = this.cookieService.get('userId');
  lugares: Lugar[] = [];

  constructor(){
    this.lugarService.list().subscribe((data: any) => {
      for(let i = 0; i < data.length; i++){
        if(data[i].user.idUser == this.userId){
          this.lugares.push(data[i]);
        }
      }
      console.log(this.lugares);
    });
  }
  
deletePlace(id:any){
  this.lugarService.delete(id).subscribe((response: any) => {
    alert('Lugar eliminado');
    window.location.reload();
    this.router.navigate(['places']);

  });
}
}
