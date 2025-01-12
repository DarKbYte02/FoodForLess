import { Component, inject, OnInit } from '@angular/core';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { ArticuloService } from '../../services/articulo.service';
import { Articulo } from '../../articulo';
import { HeaderComponent } from "../../shared/header/header.component";
import { FooterComponent } from '../../shared/footer/footer.component';
import { CategoriaService } from '../../services/categoria.service';
import { Categoria } from '../../categoria';
import { CommonModule } from '@angular/common';
import { PlaceService } from '../../services/place.service';
import { Lugar } from '../../lugar';

@Component({
  selector: 'app-categories',
  imports: [HeaderComponent,FooterComponent,CommonModule,RouterLink],
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.css']
})
export class CategoriesComponent {
  private route: ActivatedRoute = inject(ActivatedRoute);
  private articuloService = inject(ArticuloService);
  private categoriaService = inject(CategoriaService);
    private lugaresService = inject(PlaceService);

  
  categoriaID : number = 0;
    lugares:Lugar[] = [];
  
  articulos : Articulo[] = [];
  categoria : Categoria = new Categoria();

  constructor() {
    this.categoriaID = Number(this.route.snapshot.paramMap.get('id') ?? 0);
    console.log(this.categoriaID);
    this.articuloService.listByCategory(this.categoriaID).subscribe((response: any) => {
      this.articulos = response as Articulo[];
      console.log(this.articulos);
    });
    this.categoriaService.get(this.categoriaID).subscribe((response: any) => {
      this.categoria = response as Categoria;
      console.log(this.categoria);
    });

    this.lugaresService.list().subscribe((response: any) => {
      const lugares: Lugar[] = response as Lugar[];
      console.log(lugares);
      lugares.sort((a: Lugar, b: Lugar) => b.calificacionTotal - a.calificacionTotal);
      this.lugares = lugares;
    });
  }

}
