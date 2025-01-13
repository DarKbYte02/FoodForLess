import { Component,inject } from '@angular/core';
import { HeaderComponent } from '../../shared/header/header.component';
import { FooterComponent } from '../../shared/footer/footer.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute , Router,RouterModule } from '@angular/router';
import { PlaceService } from '../../services/place.service';
import { Articulo } from '../../articulo';
import { ArticuloService } from '../../services/articulo.service';

@Component({
  selector: 'app-list-items',
  imports: [HeaderComponent, FooterComponent, FormsModule, ReactiveFormsModule,RouterModule],
  templateUrl: './list-items.component.html',
  styleUrl: './list-items.component.css'
})
export class ListItemsComponent {
    route: ActivatedRoute = inject(ActivatedRoute);
    nombreLugar: string = '';
    idLugar: number = 0;
    articulos : Articulo[] = [];
    private articuloService = inject(ArticuloService);
    private lugarService = inject(PlaceService);
    private router = inject(Router);

  constructor() {
    this.idLugar = Number(this.route.snapshot.paramMap.get('id'));
    this.lugarService.get(this.idLugar).subscribe((response: any) => {
      this.nombreLugar = response.nombreLugar;
    });
    this.articuloService.list().subscribe((response: any) => {
      console.log(response);
      for(let i = 0; i < response.length; i++){
        if(response[i].lugar.idLugar == this.idLugar){
          this.articulos.push(response[i]);
        }
      }
      console.log(this.articulos);
  });

  }

  deleteItem(id:any){
    console.log(id);
    this.articuloService.delete(id).subscribe((response: any) => {
      alert('Artículo eliminado');
      window.location.reload();
      this.router.navigate(['places/listItems/'+this.idLugar]);
    });
  }
}
