import { Component,inject } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ArticuloService } from '../../services/articulo.service';
import { Articulo } from '../../articulo';
import { HeaderComponent } from "../../shared/header/header.component";
import { FooterComponent } from '../../shared/footer/footer.component';

@Component({
  selector: 'app-categories',
  imports: [HeaderComponent,FooterComponent],
  templateUrl: './categories.component.html',
  styleUrl: './categories.component.css'
})
export class CategoriesComponent {
  route: ActivatedRoute = inject(ActivatedRoute);
  private articuloService = inject(ArticuloService);
  categoriaID : number = 0;

  articulos : Articulo[] = [];
  constructor() {
    this.categoriaID = Number(this.route.snapshot.paramMap.get('id'));
    this.articuloService.listByCategory(this.categoriaID).subscribe((response: any) => {
      this.articulos = response as Articulo[];
      console.log(this.articulos);
    });
  }
}
