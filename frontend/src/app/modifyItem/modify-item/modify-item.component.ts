import { Component,inject } from '@angular/core';
import { HeaderComponent } from '../../shared/header/header.component';
import { FooterComponent } from '../../shared/footer/footer.component';
import { FormsModule, ReactiveFormsModule, FormGroup, FormControl } from '@angular/forms';
import { RouterModule,ActivatedRoute,Router } from '@angular/router';
import { Categoria } from '../../categoria';
import { CategoriaService } from '../../services/categoria.service';
import { Articulo } from '../../articulo';
import { ArticuloService } from '../../services/articulo.service';


function formatTimestampToDate(timestamp: number): string {
  const date = new Date(timestamp); // Crear un objeto Date
  const year = date.getFullYear();
  const month = String(date.getMonth() + 1).padStart(2, '0'); // Los meses empiezan en 0
  const day = String(date.getDate()).padStart(2, '0'); // Día del mes

  return `${year}-${month}-${day}`;
}

@Component({
  selector: 'app-modify-item',
  imports: [HeaderComponent, FooterComponent,FormsModule, ReactiveFormsModule,RouterModule],
  templateUrl: './modify-item.component.html',
  styleUrl: './modify-item.component.css'
})


export class ModifyItemComponent {

  

  lugarId: number = 0;
  articuloId: number = 0;
  articulo : Articulo = new Articulo();
  route: ActivatedRoute = inject(ActivatedRoute);
  categorias : Categoria[] = [];
  private categoriaService = inject(CategoriaService);
  private router = inject(Router);
  private articuloService = inject(ArticuloService);


    itemForm = new FormGroup({
      nombreArticulo: new FormControl(''),
      descripcionArticulo: new FormControl(''),
      stock: new FormControl(''),
      precioArticulo: new FormControl(''),
      imagenArticulo: new FormControl(''),
      tiempoInicial: new FormControl(''),
      tiempoFinal: new FormControl(''),
      categoria: new FormControl(''),
      lugar: new FormControl(''),
    });


    onSubmit() { 
      this.articulo.idArticulo = this.articuloId;
      this.articulo.categoria = Number(this.itemForm.value.categoria);
      this.articulo.lugar = this.lugarId;
      this.articulo.nombreArticulo = this.itemForm.value.nombreArticulo!;
      this.articulo.descripcionArticulo = this.itemForm.value.descripcionArticulo!;
      this.articulo.stock = Number(this.itemForm.value.stock!);
      this.articulo.precioArticulo = Number(this.itemForm.value.precioArticulo!);
      this.articulo.imagenArticulo = this.itemForm.value.imagenArticulo!;
      this.articulo.tiempoInicial = this.itemForm.value.tiempoInicial;
      this.articulo.tiempoFinal = this.itemForm.value.tiempoFinal;
      console.log(this.articulo);

      if(this.articulo.nombreArticulo != null && this.articulo.descripcionArticulo != null && this.articulo.stock != null && this.articulo.precioArticulo != null && this.articulo.imagenArticulo != null && this.articulo.tiempoInicial != null && this.articulo.tiempoFinal != null && this.articulo.categoria != null && this.articulo.lugar != null){

        if(this.articulo.nombreArticulo.length >= 4 && this.articulo.descripcionArticulo.length >= 4 && this.articulo.imagenArticulo.length >= 4){
      this.articuloService.update(this.articulo).subscribe((response: any) => {
        alert('Artículo modificado');
        this.router.navigate(['places/listItems/'+this.lugarId]);
        //window.location.reload();
      });
    }
    }
    else{
      alert('Datos incorrectos, revisa las entradas');
    }
    }


    constructor() { 
      this.lugarId = Number(this.route.snapshot.paramMap.get('id'));
      this.categoriaService.list().subscribe((data: any) => {
        this.categorias = data;
        console.log(this.categorias);
      } );
      this.articuloId = Number(this.route.snapshot.paramMap.get('itemID'));

      this.articuloService.get(this.articuloId).subscribe((response: any) => {
        this.articulo = response;
        console.log(response);        
        this.itemForm.setValue({
          nombreArticulo: response.nombreArticulo,
          descripcionArticulo: response.descripcionArticulo,
          stock: response.stock,
          precioArticulo: response.precioArticulo,
          imagenArticulo: response.imagenArticulo,
          tiempoInicial: formatTimestampToDate(response.tiempoInicial),
          tiempoFinal: formatTimestampToDate(response.tiempoFinal),
          categoria: response.categoria.idCategoria,
          lugar: response.lugar
        });
      });
    }

}
