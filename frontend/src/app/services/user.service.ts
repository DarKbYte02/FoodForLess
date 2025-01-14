import { Injectable, inject } from '@angular/core';
import { HttpClient,HttpHeaders } from '@angular/common/http';
import { Usuario as User} from '../usuario'

@Injectable({
    providedIn: 'root'
})
export class UserService {
    private http = inject(HttpClient);
    private headers = new HttpHeaders({
        'Content-Type': 'application/json'
      });

    list() {
        return this.http.get('https://foodforless-latest.onrender.com/user');
    }
    get(id: number) {
        return this.http.get(`https://foodforless-latest.onrender.com/user/${id}`);
    }
    getHashedPassword(correo: any) {
        return this.http.get(`https://foodforless-latest.onrender.com/user/correo/${correo}`);
    }
    create(user: User) {
        console.log(user);
        return this.http.post('https://foodforless-latest.onrender.com/user', user);
    }
    update(user: any) {
        return this.http.put(`https://foodforless-latest.onrender.com/user/${user.idUser}`, user);
    }
    delete(id: number) {
        return this.http.delete(`https://foodforless-latest.onrender.com/user/${id}`);
    }
    
}
