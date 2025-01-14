import { Component, inject } from '@angular/core';
import {CookieService} from "ngx-cookie-service";
import {PedidoService} from "../../services/pedido.service";
import {Pedido} from "../../pedido";
import {FooterComponent} from "../../shared/footer/footer.component";
import {HeaderComponent} from "../../shared/header/header.component";
import {CommonModule} from '@angular/common';
import {RouterModule, Router} from '@angular/router';
import {DetallePedido} from "../../detallePedido";
import { DetallePedidoService } from '../../services/detallePedido.service';
import { Articulo } from '../../articulo';
import { ArticuloService } from '../../services/articulo.service';


@Component({
  selector: 'app-orders',
  imports: [
    FooterComponent,
    HeaderComponent,
    CommonModule,
    RouterModule
  ],
  templateUrl: './orders.component.html',
  styleUrl: './orders.component.css',
  providers:[CookieService]
})
export class OrdersComponent {
    private cookieService = inject(CookieService);
    private pedidoService = inject(PedidoService);
    private detallePedidoService = inject(DetallePedidoService);
    private articuloService = inject(ArticuloService);
    private router = inject(Router);
    userId:string = this.cookieService.get('userId');
    pedidos: Pedido[] = [];
    nuevoPedido : Pedido = new Pedido();
    detalle : DetallePedido[] = [];
    articulos : Articulo[] = [];

    constructor(){
        this.pedidoService.list().subscribe((data: any) => {
        for(let i = 0; i < data.length; i++){
            if(data[i].user.idUser == this.userId){
            this.pedidos.push(data[i]);
            }
        }
        console.log(this.pedidos);
        });
        this.detallePedidoService.list().subscribe((response: any) => {
          if(response.length > 0){
            console.log(response);
            this.detalle = response;
            for(let i=0; i< this.pedidos.length; i++){
              if(this.pedidos[i].idPedido == this.detalle[i].pedido.idPedido){
                console.log(this.detalle[i].pedido.idPedido);
              }
            }
          }
        });

        this.articuloService.list().subscribe((response: any) => {
          this.articulos = response;
      });

    }


    cancelOrder(id:any){
      console.log(id);
      this.pedidoService.get(id).subscribe((response: any) => {
        this.nuevoPedido = response;
        this.nuevoPedido.estadoPedido = 2;
        this.nuevoPedido.lugar = response.lugar.idLugar;
        this.nuevoPedido.user = response.user.idUser;
        console.log(this.nuevoPedido);
        this.pedidoService.update(this.nuevoPedido).subscribe((response: any) => {
          alert('Pedido cancelado');
          window.location.reload();
        });
      });
    }

    finishOrder(id:any){
      console.log(id);
      this.pedidoService.get(id).subscribe((response: any) => {
        this.nuevoPedido = response;
        this.nuevoPedido.estadoPedido = 1;
        this.nuevoPedido.lugar = response.lugar.idLugar;
        this.nuevoPedido.user = response.user.idUser;
        console.log(this.nuevoPedido);
        this.pedidoService.update(this.nuevoPedido).subscribe((response: any) => {
          alert('Pedido finalizado');
          window.location.reload();
        });
      });
    }

    deleteOrder(id:any){
        this.pedidoService.delete(id).subscribe((response: any) => {
        alert('Pedido eliminado');
        window.location.reload();
        this.router.navigate(['orders']);
        });
    }

}
