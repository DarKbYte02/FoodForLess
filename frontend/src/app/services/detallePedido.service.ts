import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
    providedIn: 'root'
})
export class DetallePedidoService {
    private http = inject(HttpClient);

    list() {
        return this.http.get('https://foodforless-latest.onrender.com/detallePedido');
    }
    get(id: number) {
        return this.http.get(`https://foodforless-latest.onrender.com/detallePedido/${id}`);
    }
    getByOrderID(id: number) {
        return this.http.get(`https://foodforless-latest.onrender.com/detallePedido/pedido/${id}`);
    }
    create(detallePedido: any) {
        return this.http.post('https://foodforless-latest.onrender.com/detallePedido', detallePedido);
    }
    update(detallePedido: any) {
        return this.http.put(`https://foodforless-latest.onrender.com/detallePedido/${detallePedido.idDetallePedido}`, detallePedido);
    }
    delete(id: number) {
        return this.http.delete(`https://foodforless-latest.onrender.com/detallePedido/${id}`);
    }
    
}
