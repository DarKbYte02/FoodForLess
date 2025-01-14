import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
    providedIn: 'root'
})
export class PedidoService {
    private http = inject(HttpClient);

    list() {
        return this.http.get('https://foodforless-latest.onrender.com/pedido');
    }
    get(id: number) {
        return this.http.get(`https://foodforless-latest.onrender.com/pedido/${id}`);
    }
    create(pedido: any) {
        return this.http.post('https://foodforless-latest.onrender.com/pedido', pedido);
    }
    update(pedido: any) {
        return this.http.put(`https://foodforless-latest.onrender.com/pedido/${pedido.idPedido}`, pedido);
    }
    delete(id: number) {
        return this.http.delete(`https://foodforless-latest.onrender.com/pedido/${id}`);
    }
    
}
