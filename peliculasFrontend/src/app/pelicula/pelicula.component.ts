import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { TokenService } from '../auth/token.service';
import { Pelicula } from './pelicula';
import { PeliculaServiceService } from './pelicula-service.service';

@Component({
  selector: 'app-pelicula',
  templateUrl: './pelicula.component.html',
  styleUrls: ['./pelicula.component.css']
})
export class PeliculaComponent implements OnInit {

  peliculas:Pelicula[] = [];
  totalPages:Array<number> = [];
  page = 0;
  size = 12;
  order = 'id';
  asc = true;
  isFirst = false;
  isLast = false;
  isAdmin = false;

  constructor(private peliculaService:PeliculaServiceService,private tokenService:TokenService,private toastr: ToastrService,private activedRouter:ActivatedRoute,private router:Router) { }

  ngOnInit(): void {
    this.getPeliculas();
    this.isAdmin = this.tokenService.isAdmin();

  }

  getPeliculas(){
    this.peliculaService.getAll(this.page,this.size,this.order,this.asc).subscribe(
      data => {
        this.peliculas = data.content;
        this.isFirst = data.first;
        this.isLast = data.last;
        this.totalPages = new Array(data['totalPages']);
      }
    );
  }


  rewind():void{
    if(!this.isFirst){
      this.page--;
      this.getPeliculas();
    }
  }


  forward():void{
    if(!this.isLast){
      this.page++;
      this.getPeliculas();
    }
  }

  setPage(page:number):void{
    this.page = page;
    this.getPeliculas();
  }

  delete(pelicula:Pelicula):void{
    this.peliculaService.delete(pelicula.id).subscribe(
      data => {
        this.toastr.success('Pelicula eliminada', 'OK', {
          timeOut: 3000, positionClass: 'toast-top-center'
        });
        this.getPeliculas();
      }, err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000, positionClass: 'toast-top-center',
        });
      }
    );
  }


}
