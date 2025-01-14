import { Component,inject } from '@angular/core';
import { HeaderComponent } from '../../shared/header/header.component';
import { FooterComponent } from '../../shared/footer/footer.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute , Router,RouterModule } from '@angular/router';
import { PlaceService } from '../../services/place.service';
import { Articulo } from '../../articulo';
import { ArticuloService } from '../../services/articulo.service';
import { PedidoService } from '../../services/pedido.service';
import { DetallePedidoService } from '../../services/detallePedido.service';
import { Pedido } from '../../pedido';
import { DetallePedido } from '../../detallePedido';

@Component({
  selector: 'app-list-items',
  imports: [HeaderComponent, FooterComponent, FormsModule, ReactiveFormsModule,RouterModule],
  templateUrl: './list-items.component.html',
  styleUrl: './list-items.component.css'
})
export class ListItemsComponent {
    route: ActivatedRoute = inject(ActivatedRoute);
    nombreLugar: string = '';
    idLugar: number = 0;
    articulos : Articulo[] = [];
    pedidos : Pedido[] = [];
    detalle : DetallePedido[] = [];
    nuevoPedido : Pedido = new Pedido();
    private articuloService = inject(ArticuloService);
    private lugarService = inject(PlaceService);
    private pedidoService = inject(PedidoService);
    private detallePedido = inject(DetallePedidoService);
    private router = inject(Router);
    private detailedMap = new Map();

  constructor() {
    this.idLugar = Number(this.route.snapshot.paramMap.get('id'));
    this.lugarService.get(this.idLugar).subscribe((response: any) => {
      this.nombreLugar = response.nombreLugar;
    });
    this.articuloService.list().subscribe((response: any) => {
      //console.log(response);
      for(let i = 0; i < response.length; i++){
        if(response[i].lugar.idLugar == this.idLugar){
          this.articulos.push(response[i]);
        }
      }
      //console.log(this.articulos);
  });

  this.pedidoService.list().subscribe((response: any) => {
    //console.log(response);    
    for(let i = 0; i < response.length; i++){
      if(response[i].lugar.idLugar == this.idLugar){
        this.pedidos.push(response[i]);
      }
    }
    //console.log(this.pedidos);
    this.detallePedido.list().subscribe((response: any) => {
      if(response.length > 0){
      console.log(response);
      this.detalle = response;
      for(let i=0; i< this.pedidos.length; i++){
        if(this.pedidos[i].idPedido == this.detalle[i].pedido.idPedido){
          console.log(this.detalle[i].pedido.idPedido);
          this.detailedMap.set(this.pedidos[i], this.detalle[i]);
        }
      }
      console.log(this.detailedMap);
    }
    });
    
  });



  }

  deleteItem(id:any){
    console.log(id);
    this.articuloService.delete(id).subscribe((response: any) => {
      alert('Artículo eliminado');
      window.location.reload();
      this.router.navigate(['places/listItems/'+this.idLugar]);
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
}
