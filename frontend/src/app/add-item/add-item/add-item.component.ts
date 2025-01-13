import { Component,inject } from '@angular/core';
import { HeaderComponent } from '../../shared/header/header.component';
import { FooterComponent } from '../../shared/footer/footer.component';
import { FormsModule, ReactiveFormsModule,FormGroup,FormControl } from '@angular/forms';
import { CookieService } from 'ngx-cookie-service';
import { ActivatedRoute,Router } from '@angular/router';
import { Articulo } from '../../articulo';
import { ArticuloService } from '../../services/articulo.service';
import { CategoriaService } from '../../services/categoria.service';
import { Categoria } from '../../categoria';

@Component({
  selector: 'app-add-item',
  imports: [HeaderComponent, FooterComponent, FormsModule, ReactiveFormsModule],
  templateUrl: './add-item.component.html',
  styleUrl: './add-item.component.css',
  providers: [CookieService]
})
export class AddItemComponent {
  lugarId: number = 0;
  articulo : Articulo = new Articulo();
  route: ActivatedRoute = inject(ActivatedRoute);
  categorias : Categoria[] = [];
  private categoriaService = inject(CategoriaService);
  private articuloService = inject(ArticuloService);
  private router = inject(Router);

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
    //console.log(this.itemForm.value);
    this.articulo.idArticulo = 0;
    this.articulo.nombreArticulo = this.itemForm.value.nombreArticulo!;
    this.articulo.descripcionArticulo = this.itemForm.value.descripcionArticulo!;
    this.articulo.stock = Number(this.itemForm.value.stock);
    this.articulo.precioArticulo = Number(this.itemForm.value.precioArticulo);
    this.articulo.imagenArticulo = this.itemForm.value.imagenArticulo!;
    this.articulo.tiempoInicial = this.itemForm.value.tiempoInicial!;
    this.articulo.tiempoFinal = this.itemForm.value.tiempoFinal!;
    this.articulo.categoria = Number(this.itemForm.value.categoria!);
    this.articulo.lugar = this.lugarId;
    if(this.articulo.nombreArticulo != null && this.articulo.descripcionArticulo != null && this.articulo.stock != null && this.articulo.precioArticulo != null && this.articulo.imagenArticulo != null && this.articulo.tiempoInicial != null && this.articulo.tiempoFinal != null && this.articulo.categoria != null && this.articulo.lugar != null){
        if(this.articulo.nombreArticulo.length >= 4 && this.articulo.descripcionArticulo.length >= 4 && this.articulo.imagenArticulo.length >= 4){
        console.log(this.articulo);
        this.articuloService.create(this.articulo).subscribe((response: any) => {
          alert('Articulo creado');
          this.router.navigate(['places/listItems/'+this.lugarId]);
        });
      }
      else{
        console.log(this.articulo);

        alert('Datos incorrectos, revisa las entradas');
      }
      }
    }

  constructor() { 
    this.lugarId = Number(this.route.snapshot.paramMap.get('id'));
    this.categoriaService.list().subscribe((data: any) => {
      this.categorias = data;
      console.log(this.categorias);
    } );
  }

}
