import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Genero } from 'src/app/genero/genero';
import { GeneroServiceService } from 'src/app/genero/genero-service.service';
import { PeliculaSave } from '../pelicula-save';
import { PeliculaServiceService } from '../pelicula-service.service';

@Component({
  selector: 'app-formulario-pelicula',
  templateUrl: './formulario-pelicula.component.html',
  styleUrls: ['./formulario-pelicula.component.css']
})
export class FormularioPeliculaComponent implements OnInit {
  pelicula : PeliculaSave = new PeliculaSave();
  generos : Genero[] = [];
  page = 0;
  size = 5;
  order = 'id';
  asc = true

  constructor(private peliculaService:PeliculaServiceService,private generoService:GeneroServiceService,private router:Router,private activatedRoute:ActivatedRoute,private toastr: ToastrService) { }

  ngOnInit(): void {
    this.cargarDatos();
    this.cargarGeneros();
  }

  cargarDatos():void{
    const id = this.activatedRoute.snapshot.params['id'];

    if(id){
      this.peliculaService.get(id).subscribe(
        data => this.pelicula = data
      );
    }
  }

  cargarGeneros():void{
    this.generoService.getAll(this.page, this.size, this.order, this.asc).subscribe(
      data =>  {
        this.generos = data.content
      }
    )
  }

  create():void{
    this.peliculaService.create(this.pelicula).subscribe(
      data => {
        this.toastr.success('Pelicula creada', 'OK', {
          timeOut: 3000, positionClass: 'toast-top-center'
        });
        this.router.navigate(['peliculas'])
      },err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000, positionClass: 'toast-top-center',
        });

      }
    )
  }

  update():void{
    this.peliculaService.update(this.pelicula.id,this.pelicula)
    .subscribe(
      data => {
        this.toastr.success('Pelicula actualizada', 'OK', {
          timeOut: 3000, positionClass: 'toast-top-center'
        });
        this.router.navigate(['peliculas'])
      },err => {
        this.toastr.error("Error al actualizar", 'Fail', {
          timeOut: 3000, positionClass: 'toast-top-center',
        });
      }
    );

  }
}
