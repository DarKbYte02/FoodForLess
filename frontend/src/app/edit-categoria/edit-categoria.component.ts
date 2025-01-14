import { Component,inject } from '@angular/core';
import { FooterComponent } from "../shared/footer/footer.component";
import { HeaderComponent } from '../shared/header/header.component';
import { CategoriaService } from '../services/categoria.service';
import { Categoria } from '../categoria';
import { FormGroup,FormControl,FormsModule,ReactiveFormsModule } from '@angular/forms';
import { Router,ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-edit-categoria',
  imports: [FooterComponent, HeaderComponent,FormsModule, ReactiveFormsModule],
  templateUrl: './edit-categoria.component.html',
  styleUrl: './edit-categoria.component.css'
})
export class EditCategoriaComponent {
  private categoriaService: CategoriaService = new CategoriaService();
  private router : Router = new Router();
  private route: ActivatedRoute = inject(ActivatedRoute);
  
  categoria: Categoria = new Categoria();
  nuevaCategoria: Categoria = new Categoria();

  categoriaForm = new FormGroup({
    nombreCategoria: new FormControl(''),
    imagenCategoria: new FormControl(''),
  });

  editarCategoria() {
    this.categoria.nombreCategoria = this.categoriaForm.value.nombreCategoria!;
    this.categoria.imagenCategoria = this.categoriaForm.value.imagenCategoria!;
    this.categoria.idCategoria = Number(this.route.snapshot.paramMap.get('id'));
    if(this.categoria.nombreCategoria != null && this.categoria.imagenCategoria != null){
      this.categoriaService.update(this.categoria).subscribe((response: any) => {
        alert('Categoría actualizada');
        this.router.navigate(['/admin']);
      });
    }
  }

  constructor() {
    this.categoriaService.get(Number(this.route.snapshot.paramMap.get('id'))).subscribe((response: any) => {
      this.categoria = response as Categoria;
      console.log(this.categoria);
      this.categoriaForm.setValue({
        nombreCategoria: this.categoria.nombreCategoria,
        imagenCategoria: this.categoria.imagenCategoria,
      });
    });

  }

}
