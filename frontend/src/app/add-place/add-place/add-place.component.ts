import { Component,inject } from '@angular/core';
import { HeaderComponent } from "../../shared/header/header.component";
import { FooterComponent } from "../../shared/footer/footer.component";
import { FormsModule, ReactiveFormsModule,FormControl, FormGroup } from '@angular/forms';
import { Lugar } from '../../lugar';
import { CookieService } from 'ngx-cookie-service';
import { PlaceService } from '../../services/place.service';
import {Router} from '@angular/router';


@Component({
  selector: 'app-add-place',
  imports: [HeaderComponent, FooterComponent, FormsModule, ReactiveFormsModule],
  templateUrl: './add-place.component.html',
  styleUrl: './add-place.component.css',
  providers: [CookieService]
})
export class AddPlaceComponent {
    nuevoLugar : Lugar = new Lugar();
    private cookieService = inject(CookieService);
    private lugarService = inject(PlaceService);
    private router = inject(Router);


    placeForm = new FormGroup({
      nombre: new FormControl(''),
      descripcion: new FormControl(''),
      direccion: new FormControl(''),
      latitud: new FormControl(''),
      longitud: new FormControl(''),
      horaApertura: new FormControl(''),
      horaCierre: new FormControl(''),
      calificacion: new FormControl(''),
      imagen: new FormControl(''),
    });


    constructor() {
      this.placeForm.get('calificacion')?.disable();
    }
    onSubmit() {
      this.nuevoLugar.nombreLugar = this.placeForm.value.nombre!;
      this.nuevoLugar.descripcionLugar = this.placeForm.value.descripcion!;
      this.nuevoLugar.direccionLugar = this.placeForm.value.direccion!;
      this.nuevoLugar.latitudLugar = Number(this.placeForm.value.latitud);
      this.nuevoLugar.longitudLugar = Number(this.placeForm.value.longitud);
      this.nuevoLugar.horaApertura = Number(this.placeForm.value.horaApertura);
      this.nuevoLugar.horaCierre = Number(this.placeForm.value.horaCierre);
      this.nuevoLugar.calificacionTotal = Math.random() * 5;
      while(this.nuevoLugar.calificacionTotal < 3){
        this.nuevoLugar.calificacionTotal = Math.random() * 5;
      }
      this.nuevoLugar.calificacionTotal = Number(this.nuevoLugar.calificacionTotal.toFixed(1));
      this.nuevoLugar.imagenLugar = this.placeForm.value.imagen!;
      this.nuevoLugar.user = Number(this.cookieService.get('userId'));
      this.nuevoLugar.idLugar = 0;
      console.log(this.nuevoLugar);
      if(this.nuevoLugar.nombreLugar != null && this.nuevoLugar.descripcionLugar != null && this.nuevoLugar.direccionLugar != null && this.nuevoLugar.latitudLugar != null && this.nuevoLugar.longitudLugar != null && this.nuevoLugar.horaApertura != null && this.nuevoLugar.horaCierre != null && this.nuevoLugar.calificacionTotal != null && this.nuevoLugar.imagenLugar != null){
        if(this.nuevoLugar.nombreLugar.length >= 4 && this.nuevoLugar.descripcionLugar.length >= 10 && this.nuevoLugar.direccionLugar.length >= 10  && this.nuevoLugar.imagenLugar.length >= 4){
        this.lugarService.create(this.nuevoLugar).subscribe((response: any) => {
          console.log(response);
          alert('Lugar creado');
          this.router.navigate(['places']);
          });
      }
      else{
        alert("Por favor, llene todos los campos correctamente");
      }
    }



    }
}
