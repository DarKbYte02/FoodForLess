import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
    providedIn: 'root'
})
export class ArticuloService {
    private http = inject(HttpClient);

    list() {
        return this.http.get('http://localhost:8080/articulo');
    }
    get(id: number) {
        return this.http.get(`http://localhost:8080/articulo/${id}`);
    }
    create(articulo: any) {
        return this.http.post('http://localhost:8080/articulo', articulo);
    }
    update(articulo: any) {
        return this.http.put(`http://localhost:8080/articulo/${articulo.idArticulo}`, articulo);
    }
    delete(id: number) {
        return this.http.delete(`http://localhost:8080/articulo/${id}`);
    }
    listByCategory(id: number) {
        return this.http.get(`http://localhost:8080/articulo/categoria/${id}`);
    }
    
}
