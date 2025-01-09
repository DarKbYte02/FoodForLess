import { Component, inject, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ArticuloService } from '../../services/articulo.service';
import { Articulo } from '../../articulo';
import { HeaderComponent } from "../../shared/header/header.component";
import { FooterComponent } from '../../shared/footer/footer.component';

@Component({
  selector: 'app-categories',
  imports: [HeaderComponent,FooterComponent],
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.css']
})
export class CategoriesComponent {
  private route: ActivatedRoute = inject(ActivatedRoute);
  private articuloService = inject(ArticuloService);
  categoriaID : number = 0;
  articulos : Articulo[] = [];
  constructor() {
    this.categoriaID = Number(this.route.snapshot.paramMap.get('id') ?? 0);
    console.log(this.categoriaID);
    this.articuloService.listByCategory(this.categoriaID).subscribe((response: any) => {
      this.articulos = response as Articulo[];
      console.log(this.articulos);
    });

  }
}
