import { Component, inject } from '@angular/core';
import {CookieService} from "ngx-cookie-service";
import {PedidoService} from "../../services/pedido.service";
import {Pedido} from "../../pedido";
import {FooterComponent} from "../../shared/footer/footer.component";
import {HeaderComponent} from "../../shared/header/header.component";
import {CommonModule} from '@angular/common';
import {RouterModule, Router} from '@angular/router';

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
    private router = inject(Router);
    userId:string = this.cookieService.get('userId');
    pedidos: Pedido[] = [];

    constructor(){
        this.pedidoService.list().subscribe((data: any) => {
        for(let i = 0; i < data.length; i++){
            if(data[i].user.idUser == this.userId){
            this.pedidos.push(data[i]);
            }
        }
        console.log(this.pedidos);
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
