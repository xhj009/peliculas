import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { Genero } from './genero';
import { GeneroServiceService } from './genero-service.service';

@Component({
  selector: 'app-genero',
  templateUrl: './genero.component.html',
  styleUrls: ['./genero.component.css']
})
export class GeneroComponent implements OnInit {

  generos:Genero[] = [];
  totalPages:Array<number> = [];
  page = 0;
  size = 5;
  order = 'id';
  asc = true;
  isFirst = false;
  isLast = false;
  errMsj!: string;

  constructor(private generoService:GeneroServiceService,private toastr:ToastrService) { }

  ngOnInit(): void {
    this.getGeneros();
  }

  getGeneros(){
    this.generoService.getAll(this.page,this.size,this.order,this.asc).subscribe(
      data =>{
        this.generos = data.content;
        this.isFirst = data.first;
        this.isLast = data.last;
        this.totalPages = new Array(data['totalPages']);
      }
    );
  }


  rewind():void{
    if(!this.isFirst){
      this.page--;
      this.getGeneros();
    }
  }


  forward():void{
    if(!this.isLast){
      this.page++;
      this.getGeneros();
    }
  }

  setPage(page:number):void{
    this.page = page;
    this.getGeneros();
  }

  delete(genero:Genero){
    this.generoService.delete(genero.id).subscribe(
      data => {
        this.toastr.success('Genero eliminado', 'OK', {
          timeOut: 3000, positionClass: 'toast-top-center'
        });
        this.getGeneros();
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000, positionClass: 'toast-top-center',
        });
      }
    );
  }

}
