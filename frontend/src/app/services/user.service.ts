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
        return this.http.get('http://localhost:8080/user');
    }
    get(id: number) {
        return this.http.get(`http://localhost:8080/user/${id}`);
    }
    getHashedPassword(correo: any) {
        return this.http.get(`http://localhost:8080/user/correo/${correo}`);
    }
    create(user: User) {
        console.log(user);
        return this.http.post('http://localhost:8080/user', user);
    }
    update(user: any) {
        return this.http.put(`http://localhost:8080/user/${user.id}`, user);
    }
    delete(id: number) {
        return this.http.delete(`http://localhost:8080/user/${id}`);
    }
    
}
