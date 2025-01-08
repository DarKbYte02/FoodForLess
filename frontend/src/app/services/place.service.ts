import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
    providedIn: 'root'
})
export class PlaceService {
    private http = inject(HttpClient);

    list() {
        return this.http.get('http://localhost:8080/lugar');
    }
    get(id: number) {
        return this.http.get(`http://localhost:8080/lugar/${id}`);
    }
    create(place: any) {
        return this.http.post('http://localhost:8080/lugar', place);
    }
    update(place: any) {
        return this.http.put(`http://localhost:8080/lugar/${place.id}`, place);
    }
    delete(id: number) {
        return this.http.delete(`http://localhost:8080/lugar/${id}`);
    }
    
}
