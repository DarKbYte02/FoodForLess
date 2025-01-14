import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
    providedIn: 'root'
})
export class DetallePedidoService {
    private http = inject(HttpClient);

    list() {
        return this.http.get('http://localhost:8080/detallePedido');
    }
    get(id: number) {
        return this.http.get(`http://localhost:8080/detallePedido/${id}`);
    }
    getByOrderID(id: number) {
        return this.http.get(`http://localhost:8080/detallePedido/pedido/${id}`);
    }
    create(detallePedido: any) {
        return this.http.post('http://localhost:8080/detallePedido', detallePedido);
    }
    update(detallePedido: any) {
        return this.http.put(`http://localhost:8080/detallePedido/${detallePedido.idDetallePedido}`, detallePedido);
    }
    delete(id: number) {
        return this.http.delete(`http://localhost:8080/detallePedido/${id}`);
    }
    
}
