import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Genero } from './genero';

@Injectable({
  providedIn: 'root'
})
export class GeneroServiceService {

  url = environment.url + 'api/generos/';

  constructor(private http: HttpClient) { }

  getAll(page:number,size:number,order:string,asc:boolean):Observable<any>{
    return this.http.get<any>(this.url + `?page=${page}&size=${size}&order=${order}&asc=${asc}`);
  }

  get(id:number):Observable<Genero>{
    return this.http.get<Genero>(this.url + id);
  }

  create(genero:Genero):Observable<Genero>{
    return this.http.post<Genero>(this.url ,genero);
  }

  update(id:number,genero:Genero):Observable<Genero>{
    return this.http.put<Genero>(this.url + id , genero);
  }

  delete(id:number):Observable<any>{
    return this.http.delete<any>(this.url + id);
  }
}
