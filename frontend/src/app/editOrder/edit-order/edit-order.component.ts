import { Component } from '@angular/core';
import { HeaderComponent } from '../../shared/header/header.component';
import { FooterComponent } from '../../shared/footer/footer.component';
import { FormsModule, ReactiveFormsModule, FormGroup,FormControl } from '@angular/forms';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-edit-order',
  imports: [HeaderComponent,FormsModule, ReactiveFormsModule,RouterModule, FooterComponent],
  templateUrl: './edit-order.component.html',
  styleUrl: './edit-order.component.css'
})
export class EditOrderComponent {

  nuevoPedidoYDetalle = new FormGroup({
    estadoPedido: new FormControl(''),
    cantidadPedido: new FormControl(''),
    totalPedido: new FormControl(''),
    precioUnitario: new FormControl(''),

  });

  onSubmit() {}
}
