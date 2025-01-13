import { Component,inject } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router'
import { PlaceService } from '../../services/place.service';
import { Lugar } from '../../lugar';
import { HeaderComponent } from "../../shared/header/header.component";
import { FooterComponent } from "../../shared/footer/footer.component";
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-place-details',
  imports: [HeaderComponent, FooterComponent, FormsModule, ReactiveFormsModule],
  templateUrl: './place-details.component.html',
  styleUrl: './place-details.component.css',
  providers: [CookieService]
})
export class PlaceDetailsComponent {
  route: ActivatedRoute = inject(ActivatedRoute);
  private lugarService = inject(PlaceService);
  private router = inject(Router);
  lugarId: number = 0;
  lugar: Lugar = new Lugar();
  nuevoLugar : Lugar = new Lugar();
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

    this.lugarId = Number(this.route.snapshot.paramMap.get('id'));
    this.lugarService.get(this.lugarId).subscribe((response: any) => {
      this.lugar = response as Lugar;
      console.log(this.lugar);
      this.placeForm.setValue({
        nombre: this.lugar.nombreLugar,
        descripcion: this.lugar.descripcionLugar,
        direccion: this.lugar.direccionLugar,
        latitud: String(this.lugar.latitudLugar),
        longitud: String(this.lugar.longitudLugar),
        horaApertura: String(this.lugar.horaApertura),
        horaCierre: String(this.lugar.horaCierre),
        calificacion: String(this.lugar.calificacionTotal),
        imagen: this.lugar.imagenLugar
      });
      this.placeForm.get('calificacion')?.disable();
    });

    console.log(this.lugarId);


  }
  onSubmit() {
    this.nuevoLugar.nombreLugar = this.placeForm.value.nombre!;
    this.nuevoLugar.descripcionLugar = this.placeForm.value.descripcion!;
    this.nuevoLugar.direccionLugar = this.placeForm.value.direccion!;
    this.nuevoLugar.latitudLugar = Number(this.placeForm.value.latitud);
    this.nuevoLugar.longitudLugar = Number(this.placeForm.value.longitud);
    this.nuevoLugar.horaApertura = Number(this.placeForm.value.horaApertura);
    this.nuevoLugar.horaCierre = Number(this.placeForm.value.horaCierre);
    this.nuevoLugar.calificacionTotal = Number(this.placeForm.value.calificacion);
    this.nuevoLugar.imagenLugar = this.placeForm.value.imagen!;
    this.nuevoLugar.user = this.lugar.user.idUser;
    this.nuevoLugar.idLugar = this.lugar.idLugar;
    console.log(this.nuevoLugar);
    this.lugarService.update(this.nuevoLugar).subscribe((response: any) => {
      console.log(response);
    });
    this.router.navigate(['/places']);
  }
}
