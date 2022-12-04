import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './auth/login.component';
import { RegistroComponent } from './auth/registro.component';
import { FormularioGeneroComponent } from './genero/formulario-genero/formulario-genero.component';
import { GeneroComponent } from './genero/genero.component';
import { GuardService } from './guards/guard.service';
import { DetallesComponent } from './pelicula/detalles/detalles.component';
import { FormularioPeliculaComponent } from './pelicula/formulario-pelicula/formulario-pelicula.component';
import { PeliculaComponent } from './pelicula/pelicula.component';

const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'login', component: LoginComponent },
  { path: 'registro', component: RegistroComponent},
  { path: 'generos', component: GeneroComponent,canActivate:[GuardService], data: { expectedRol: ['admin'] } },
  { path: 'generos/formulario', component: FormularioGeneroComponent,canActivate:[GuardService], data: { expectedRol: ['admin'] } },
  { path: 'generos/formulario/:id', component: FormularioGeneroComponent,canActivate:[GuardService], data: { expectedRol: ['admin'] } },
  { path: 'peliculas', component: PeliculaComponent,canActivate:[GuardService], data: { expectedRol: ['admin','user'] } },
  { path: 'peliculas/formulario', component: FormularioPeliculaComponent,canActivate:[GuardService], data: { expectedRol: ['admin'] } },
  { path: 'peliculas/formulario/:id', component: FormularioPeliculaComponent,canActivate:[GuardService], data: { expectedRol: ['admin'] } },
  { path: 'peliculas/detalles/:id', component: DetallesComponent,canActivate:[GuardService], data: { expectedRol: ['admin','user'] } },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
