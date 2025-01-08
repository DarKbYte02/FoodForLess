import { Component, inject, OnInit } from '@angular/core';
import { PlaceService } from '../services/place.service';
import { Lugar } from '../lugar';
import { HeaderComponent } from "../shared/header/header.component";
import { FooterComponent } from '../shared/footer/footer.component';
import { CommonModule } from '@angular/common';
import { Categoria } from '../categoria';
import { CategoriaService } from '../services/categoria.service';
import { ArticuloService } from '../services/articulo.service';
import { Articulo } from '../articulo';
import { RouterModule } from '@angular/router';
@Component({
  selector: 'app-place-list',
  imports: [HeaderComponent,FooterComponent,CommonModule,RouterModule],
  templateUrl: './place-list.component.html',
  styleUrl: './place-list.component.css'
})
export class PlaceListComponent implements OnInit{
  private lugaresService = inject(PlaceService);
  private categoriaService = inject(CategoriaService);
  private articuloService = inject(ArticuloService);
  index: number = 0;
  lugares:Lugar[] = [];
  categorias:Categoria[] = [];
  articulos:Articulo[] = [];

  ngOnInit():void {

    this.lugaresService.list()
    .subscribe((response: any) => {
      const lugares: Lugar[] = response as Lugar[];
      console.log(lugares);
      lugares.sort((a: Lugar, b: Lugar) => b.calificacionTotal - a.calificacionTotal);
      this.lugares = lugares;
    });

    this.categoriaService.list().subscribe((response: any) => {
      const categorias: Categoria[] = response as Categoria[];
      console.log(categorias);
      this.categorias = categorias;
    });

    this.articuloService.list().subscribe((response: any) => {
      const articulos: Articulo[] = response as Articulo[];
      console.log(articulos);
      this.articulos = articulos;
    });
    }
  }
