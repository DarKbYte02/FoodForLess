import { ApplicationConfig, provideZoneChangeDetection } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { provideHttpClient } from '@angular/common/http';

export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection({ eventCoalescing: true }), 
    provideHttpClient(),
    CookieService
  ]
};
