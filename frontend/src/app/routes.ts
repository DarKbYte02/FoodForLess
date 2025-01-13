import {Routes} from '@angular/router';
import { LandingPageComponent } from "./landing-page/landing-page/landing-page.component";
import {PlaceListComponent} from './place-list/place-list.component';
import { LoginComponent } from './login/login/login.component';
import { RegisterComponent } from './register/register/register.component';
import { ListingDetailsComponent } from './listing-details/listing-details/listing-details.component';
import { ProfileComponent } from './profile/profile/profile.component';
import { OrdersComponent } from './orders/orders/orders.component';
import { PlaceService } from './services/place.service';
import { PlacesComponent } from './places/places/places.component';
import { CategoriesComponent } from './categories/categories/categories.component';
import { PlaceDetailsComponent } from './place-details/place-details/place-details.component';
import { AdminPanelComponent } from './admin-panel/admin-panel/admin-panel.component';
import { AddPlaceComponent } from './add-place/add-place/add-place.component';
import { AddItemComponent } from './add-item/add-item/add-item.component';

const routeConfig: Routes = [
    {
      path: '',
      component: LandingPageComponent,
      title: 'Inicio',
    },
    {
      path: 'listing',
      component: PlaceListComponent,
      title: 'Explorar',
    },
    {
      path: 'listing/details/:id',
      component: ListingDetailsComponent,
      title: 'Detalles de artículo',
    },
    {
      path: 'login',
      component: LoginComponent,
      title: 'Iniciar sesión',
    },
    {
      path: 'register',
      component: RegisterComponent,
      title: 'Registrarse',
    },
    {
      path: 'profile',
      component: ProfileComponent,
      title: 'Perfil',
    },
    {
      path: 'orders',
      component: OrdersComponent,
      title: 'Órdenes',
    },
    {
      path: 'places',
      component: PlacesComponent,
      title: 'Lugares',
    },
    {
      path: 'places/edit/:id',
      component: PlaceDetailsComponent,
      title: 'Detalles de lugar',
    },
    {
      path: 'places/add',
      component: AddPlaceComponent,
      title: 'Agregar lugar',
    },
    {
      path: 'places/addItem/:id',
      component: AddItemComponent,
      title: 'Agregar lugar',
    },
    {
      path: 'categories/:id',
      component: CategoriesComponent,
      title: 'Categorías',
    },
    {
      path: 'admin',
      component: AdminPanelComponent,
      title: 'Panel de administrador',
    }
  ];
  export default routeConfig;