import { Component,inject } from '@angular/core';
import { Location } from '@angular/common';
import { HeaderComponent } from '../../shared/header/header.component';
import { FooterComponent } from '../../shared/footer/footer.component';
import { FormsModule, ReactiveFormsModule, FormGroup,FormControl } from '@angular/forms';
import { PedidoService } from '../../services/pedido.service';
import { DetallePedidoService } from '../../services/detallePedido.service';
import { RouterModule,ActivatedRoute,Router } from '@angular/router';
import { Pedido } from '../../pedido';
import { DetallePedido } from '../../detallePedido';

@Component({
  selector: 'app-edit-order',
  imports: [HeaderComponent,FormsModule, ReactiveFormsModule,RouterModule, FooterComponent],
  templateUrl: './edit-order.component.html',
  styleUrl: './edit-order.component.css'
})
export class EditOrderComponent {
  route: ActivatedRoute = inject(ActivatedRoute);
  private pedidoService = inject(PedidoService);
  private detallePedidoService = inject(DetallePedidoService);
  private router = inject(Router);
  

  pedido: Pedido = new Pedido();
  private detallePedido = new DetallePedido();

  private routeID: number = 0;

  totalPedidoFinal: number = 0;
  precioUnitarioFinal : number = 0;

  nuevoPedidoYDetalle = new FormGroup({
    estadoPedido: new FormControl(''),
    cantidadPedido: new FormControl(''),
    totalPedido: new FormControl(''),
    precioUnitario: new FormControl(''),

  });

  constructor() { 
    this.pedidoService.get(Number(this.route.snapshot.paramMap.get('id'))).subscribe((response1: any) => {
        console.log(response1);
        this.routeID = response1.lugar.idLugar;
        this.pedido = response1;
          this.detallePedidoService.getByOrderID(Number(this.route.snapshot.paramMap.get('id'))).subscribe((response: any) => {

            this.detallePedido= response[0];
            //console.log(this.detallePedido);

            this.nuevoPedidoYDetalle.setValue({
              estadoPedido: response1.estadoPedido,
              cantidadPedido: this.detallePedido.cantidadPedido.toString(),
              totalPedido: this.pedido.totalPedido.toString(),
              precioUnitario: this.detallePedido.precioPedido.toString(),
              
            });
            this.totalPedidoFinal = this.pedido.totalPedido;
            this.precioUnitarioFinal = this.detallePedido.precioPedido;
            this.nuevoPedidoYDetalle.get('totalPedido')!.disable();
            this.nuevoPedidoYDetalle.get('precioUnitario')!.disable();
        });
    });

}

  onSubmit() {

    //console.log(this.nuevoPedidoYDetalle.value);
    //console.log(this.pedido);

    this.pedido.estadoPedido = Number(this.nuevoPedidoYDetalle.value.estadoPedido);
    this.detallePedido.cantidadPedido = Number(this.nuevoPedidoYDetalle.value.cantidadPedido);

    this.pedido.lugar = this.pedido.lugar.idLugar;
    this.pedido.user = this.pedido.user.idUser;

    this.detallePedido.pedido = this.pedido.idPedido;
    this.detallePedido.articulo = this.detallePedido.articulo.idArticulo;

    console.log(this.detallePedido);

    this.pedidoService.update(this.pedido).subscribe((response: any) => {
      this.detallePedidoService.update(this.detallePedido).subscribe((response: any) => {
        alert('Pedido modificado');
        this.router.navigate(['/']);
      });
    });
  }
}
