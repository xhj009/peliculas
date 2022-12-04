import { Component, OnInit } from '@angular/core';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { ActivatedRoute } from '@angular/router';
import { Pelicula } from '../pelicula';
import { PeliculaServiceService } from '../pelicula-service.service';

@Component({
  selector: 'app-detalles',
  templateUrl: './detalles.component.html',
  styleUrls: ['./detalles.component.css']
})
export class DetallesComponent implements OnInit {


  peliculas:Pelicula[] = [];
  safeURL!:SafeResourceUrl;
  video!:any;

  constructor(private peliculaService:PeliculaServiceService,private activatedRoute:ActivatedRoute,private sanitizer: DomSanitizer ) { }

  ngOnInit(): void {
    this.cargarDatos();
    }

  cargarDatos(){
    const id = this.activatedRoute.snapshot.params['id'];
    if(id){
      this.peliculaService.get(id).subscribe(
        data =>{ this.peliculas.push(data)
          this.video = data.video
        }
      );
    }
  }

  getVideoIframe(url:string) {
    let video;
    let results;
    let youtube = 'https://www.youtube.com/embed/';

    if (url.indexOf(youtube)) {
    results = url.match('[\\?&]v=([^&#]*)');
    video = (results === null) ? url : results[1];
      }
      return this.sanitizer.bypassSecurityTrustResourceUrl('https://www.youtube.com/embed/' + video);
    }


}
