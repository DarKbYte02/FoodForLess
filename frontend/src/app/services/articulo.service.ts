import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
    providedIn: 'root'
})
export class ArticuloService {
    private http = inject(HttpClient);

    list() {
        return this.http.get('https://foodforless-latest.onrender.com/articulo');
    }
    get(id: number) {
        return this.http.get(`https://foodforless-latest.onrender.com/articulo/${id}`);
    }
    create(articulo: any) {
        return this.http.post('https://foodforless-latest.onrender.com/articulo', articulo);
    }
    update(articulo: any) {
        return this.http.put(`https://foodforless-latest.onrender.com/articulo/${articulo.idArticulo}`, articulo);
    }
    delete(id: number) {
        return this.http.delete(`https://foodforless-latest.onrender.com/articulo/${id}`);
    }
    listByCategory(id: number) {
        return this.http.get(`https://foodforless-latest.onrender.com/articulo/categoria/${id}`);
    }
    
}
