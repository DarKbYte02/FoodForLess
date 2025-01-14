import { Component,inject } from '@angular/core';
import { HeaderComponent } from '../../shared/header/header.component';
import { FooterComponent } from '../../shared/footer/footer.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { Usuario } from '../../usuario';
import { UserService } from '../../services/user.service';
import { CookieService } from 'ngx-cookie-service';
import { Lugar } from '../../lugar';
import { PlaceService } from '../../services/place.service';
import { Categoria } from '../../categoria';
import { CategoriaService } from '../../services/categoria.service';

@Component({
  selector: 'app-admin-panel',
  imports: [HeaderComponent,FormsModule, ReactiveFormsModule,RouterModule, FooterComponent],
  templateUrl: './admin-panel.component.html',
  styleUrl: './admin-panel.component.css',
  providers: [CookieService]
})
export class AdminPanelComponent {
  private cookieService = inject(CookieService);
  usuarios: Usuario[] = [];
  lugares : Lugar[] = [];
  categorias: Categoria[] = [];
  userId: number=5;

  private usuarioService: UserService = new UserService();
  private lugarService: PlaceService = new PlaceService();
  private categoriaService: CategoriaService = new CategoriaService();


  constructor() {
    this.userId = Number(this.cookieService.get('userId'));
    this.usuarioService.list().subscribe((response: any) => {
      this.usuarios = response as Usuario[];
    });

    this.lugarService.list().subscribe((response: any) => {
      this.lugares = response as Lugar[];
    });

    this.categoriaService.list().subscribe((response: any) => {
      this.categorias = response as Categoria[];
      console.log(this.categorias);  
    });
}

deletePlace(id: number) {
  this.lugarService.delete(id).subscribe((response: any) => {
    alert('Lugar eliminado');
    window.location.reload();
  });
}

deleteUser(id: number) {
  this.usuarioService.delete(id).subscribe((response: any) => {
    alert('Usuario eliminado');
    window.location.reload();
  });
}
deleteCategory(id: number) {
  this.categoriaService.delete(id).subscribe((response: any) => {});
  alert('Categoría eliminada');
  window.location.reload();
}
}
