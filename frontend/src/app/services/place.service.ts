import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
    providedIn: 'root'
})
export class PlaceService {
    private http = inject(HttpClient);

    list() {
        return this.http.get('https://foodforless-latest.onrender.com/lugar');
    }
    get(id: number) {
        return this.http.get(`https://foodforless-latest.onrender.com/lugar/${id}`);
    }
    create(place: any) {
        return this.http.post('https://foodforless-latest.onrender.com/lugar', place);
    }
    update(place: any) {
        return this.http.put(`https://foodforless-latest.onrender.com/lugar/${place.idLugar}`, place);
    }
    delete(id: number) {
        return this.http.delete(`https://foodforless-latest.onrender.com/lugar/${id}`);
    }
    
}
