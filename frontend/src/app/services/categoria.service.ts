import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
    providedIn: 'root'
})
export class CategoriaService {
    private http = inject(HttpClient);

    list() {
        return this.http.get('http://localhost:8080/categoria');
    }
    get(id: number) {
        return this.http.get(`http://localhost:8080/categoria/${id}`);
    }
    create(categoria: any) {
        return this.http.post('http://localhost:8080/categoria', categoria);
    }
    update(categoria: any) {
        return this.http.put(`http://localhost:8080/categoria/${categoria.id}`, categoria);
    }
    delete(id: number) {
        return this.http.delete(`http://localhost:8080/categoria/${id}`);
    }
    
}
