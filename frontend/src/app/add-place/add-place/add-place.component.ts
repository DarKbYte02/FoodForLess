import { Component } from '@angular/core';
import { HeaderComponent } from "../../shared/header/header.component";
import { FooterComponent } from "../../shared/footer/footer.component";
import { FormsModule, ReactiveFormsModule } from '@angular/forms';


@Component({
  selector: 'app-add-place',
  imports: [HeaderComponent, FooterComponent, FormsModule, ReactiveFormsModule],
  templateUrl: './add-place.component.html',
  styleUrl: './add-place.component.css'
})
export class AddPlaceComponent {

}
