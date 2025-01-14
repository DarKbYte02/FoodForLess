import { Component } from '@angular/core';
import { FooterComponent } from "../shared/footer/footer.component";
import { HeaderComponent } from '../shared/header/header.component';
import { CategoriaService } from '../services/categoria.service';
import { Categoria } from '../categoria';
import { FormGroup,FormControl,FormsModule,ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-categoria',
  imports: [FooterComponent, HeaderComponent,FormsModule, ReactiveFormsModule],
  templateUrl: './add-categoria.component.html',
  styleUrl: './add-categoria.component.css'
})
export class AddCategoriaComponent {
  private categoriaService: CategoriaService = new CategoriaService();
  private router : Router = new Router();
  categoria: Categoria = new Categoria();

  categoriaForm = new FormGroup({
    nombreCategoria: new FormControl(''),
    imagenCategoria: new FormControl(''),
  });

  createCategoria() {
    this.categoria.nombreCategoria = this.categoriaForm.value.nombreCategoria!;
    this.categoria.imagenCategoria = this.categoriaForm.value.imagenCategoria!;
    this.categoria.idCategoria = 1000;
    if(this.categoria.nombreCategoria != null && this.categoria.imagenCategoria != null){
      this.categoriaService.create(this.categoria).subscribe((response: any) => {
        alert('Categoría creada');
        this.router.navigate(['/admin']);
      });
    }
  }
}
