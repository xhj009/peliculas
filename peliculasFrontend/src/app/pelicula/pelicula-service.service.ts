import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Pelicula } from './pelicula';
import { PeliculaSave } from './pelicula-save';

@Injectable({
  providedIn: 'root'
})
export class PeliculaServiceService {
  url = environment.url + 'api/peliculas/';

  constructor(private http:HttpClient) { }

  getAll(page:number,size:number,order:string,asc:boolean):Observable<any>{
    return this.http.get<any>(this.url + `?page=${page}&size=${size}&order=${order}&asc=${asc}`);
  }

  get(id:number):Observable<any>{
    return this.http.get<any>(this.url + id);
  }

  create(pelicula:PeliculaSave):Observable<PeliculaSave>{
    return this.http.post<PeliculaSave>(this.url, pelicula)
  }

  update(id:number,pelicula:PeliculaSave):Observable<PeliculaSave>{
    return this.http.put<PeliculaSave>(this.url + id , pelicula);
  }

  delete(id:number):Observable<any>{
    return this.http.delete<any>(this.url + id);
  }
}
