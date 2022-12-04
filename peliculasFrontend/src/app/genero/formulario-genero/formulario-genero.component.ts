import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Genero } from '../genero';
import { GeneroServiceService } from '../genero-service.service';

@Component({
  selector: 'app-formulario-genero',
  templateUrl: './formulario-genero.component.html',
  styleUrls: ['./formulario-genero.component.css']
})
export class FormularioGeneroComponent implements OnInit {

  constructor(private generoService:GeneroServiceService,private router:Router,private activatedRoute:ActivatedRoute,private toastr: ToastrService) { }

  genero:Genero = new Genero();

  ngOnInit(): void {
    this.cargarDatos();
  }

  cargarDatos(){
    const id = this.activatedRoute.snapshot.params['id'];

    if(id){
      this.generoService.get(id).subscribe(
        e => this.genero = e
      );
    }
  }

  create():void{
    this.generoService.create(this.genero).subscribe(
      data => {
        this.toastr.success('Categoria creada', 'OK', {
          timeOut: 3000, positionClass: 'toast-top-center'
        });
        this.router.navigate(['generos'])
      },err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000, positionClass: 'toast-top-center',
        });
      }
    )
  }

  update():void{
    this.generoService.update(this.genero.id,this.genero)
    .subscribe(
      data => {
        this.toastr.success('Genero actualizado', 'OK', {
          timeOut: 3000, positionClass: 'toast-top-center'
        });
        this.router.navigate(['generos'])
      },err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000, positionClass: 'toast-top-center',
        });
      }
    );

  }

}
