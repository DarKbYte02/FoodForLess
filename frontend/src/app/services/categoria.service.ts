import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
    providedIn: 'root'
})
export class CategoriaService {
    private http = inject(HttpClient);

    list() {
        return this.http.get('https://foodforless-latest.onrender.com/categoria');
    }
    get(id: number) {
        return this.http.get(`https://foodforless-latest.onrender.com/categoria/${id}`);
    }
    create(categoria: any) {
        return this.http.post('https://foodforless-latest.onrender.com/categoria', categoria);
    }
    update(categoria: any) {
        return this.http.put(`https://foodforless-latest.onrender.com/categoria/${categoria.idCategoria}`, categoria);
    }
    delete(id: number) {
        return this.http.delete(`https://foodforless-latest.onrender.com/categoria/${id}`);
    }
    
}
