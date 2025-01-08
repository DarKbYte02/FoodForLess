import { Component,inject,OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Articulo } from '../../articulo';
import { ArticuloService } from '../../services/articulo.service';
import { HeaderComponent } from "../../shared/header/header.component";
import { FooterComponent } from "../../shared/footer/footer.component";
import { CommonModule,Location } from '@angular/common';
import { Lugar } from '../../lugar';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Pedido } from '../../pedido';
import { CookieService } from 'ngx-cookie-service';
import { PedidoService } from '../../services/pedido.service';

@Component({
  selector: 'app-listing-details',
  imports: [HeaderComponent, FooterComponent,CommonModule,FormsModule,ReactiveFormsModule],
  templateUrl: './listing-details.component.html',
  styleUrl: './listing-details.component.css',
  providers: [CookieService]
})
export class ListingDetailsComponent implements OnInit {
  route: ActivatedRoute = inject(ActivatedRoute);
  private articuloService = inject(ArticuloService);
  private cookieService = inject(CookieService);
  private pedidoService = inject(PedidoService);
  articuloId: number = 0;
  lugarId: number = 0;
  articulo: Articulo = new Articulo();
  lugar: Lugar = new Lugar();

  bookingForm = new FormGroup({
    cantidad : new FormControl(''),
  });



  constructor(private _location: Location) 
  {}

  ngOnInit():void {
    
    this.articuloId = Number(this.route.snapshot.paramMap.get('id'));
    const x = this.articuloService.get(this.articuloId).subscribe((response: any) => {
      console.log(response.lugar);
      this.lugar = response.lugar;
      this.articulo = response as Articulo;
      this.articulo.tiempoInicial = new Date(this.articulo.tiempoInicial);
      this.articulo.tiempoInicial = this.articulo.tiempoInicial.toLocaleDateString('es-MX',{
        year: 'numeric',
        month: 'long',
        day: 'numeric',
      });
      this.articulo.tiempoFinal = new Date(this.articulo.tiempoFinal);
      this.articulo.tiempoFinal = this.articulo.tiempoFinal.toLocaleDateString('es-MX',{
        year: 'numeric',
        month: 'long',
        day: 'numeric',
      });
      this.lugarId = response.lugar.idLugar;
      console.log(this.lugarId);
    });

  }
  goBack() {
    this._location.back();
  }
  onSubmit(){
    let pedido = new Pedido();
    pedido.idPedido=1000;
    const pedidoCantidad = Number(this.bookingForm.value.cantidad ?? 0);
    if(pedidoCantidad > 0 && pedidoCantidad <= this.articulo.stock){
      pedido.totalPedido = this.articulo.precioArticulo * pedidoCantidad;
    }
    pedido.estadoPedido = 1;
    pedido.user.idUser = Number(this.cookieService.get('userId'));
    pedido.lugar.idLugar= this.lugar.idLugar;
    console.log(pedido);
    if(pedido.totalPedido > 0){
      this.pedidoService.create(pedido).subscribe((response: any) => {
        console.log(response);
      });
    }

  }

}
