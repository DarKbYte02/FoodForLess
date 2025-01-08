import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
    providedIn: 'root'
})
export class PedidoService {
    private http = inject(HttpClient);

    list() {
        return this.http.get('http://localhost:8080/pedido');
    }
    get(id: number) {
        return this.http.get(`http://localhost:8080/pedido/${id}`);
    }
    create(pedido: any) {
        return this.http.post('http://localhost:8080/pedido', pedido);
    }
    update(pedido: any) {
        return this.http.put(`http://localhost:8080/lugar/${pedido.id}`, pedido);
    }
    delete(id: number) {
        return this.http.delete(`http://localhost:8080/lugar/${id}`);
    }
    
}
