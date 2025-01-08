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
      path: 'categories/:id',
      component: CategoriesComponent,
      title: 'Categorías',
    }
  ];
  export default routeConfig;