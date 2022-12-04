import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule } from 'ngx-toastr';
import { LoginComponent } from './auth/login.component';
import { RegistroComponent } from './auth/registro.component';
import { MenuComponent } from './menu/menu.component';
import { FooterComponent } from './footer/footer.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { GeneroComponent } from './genero/genero.component';
import { FormularioGeneroComponent } from './genero/formulario-genero/formulario-genero.component';
import { PeliculaComponent } from './pelicula/pelicula.component';
import { FormularioPeliculaComponent } from './pelicula/formulario-pelicula/formulario-pelicula.component';
import { DetallesComponent } from './pelicula/detalles/detalles.component';
import { interceptorProvider } from './interceptors/interceptor.service';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegistroComponent,
    MenuComponent,
    FooterComponent,
    GeneroComponent,
    FormularioGeneroComponent,
    PeliculaComponent,
    FormularioPeliculaComponent,
    DetallesComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot(),
    HttpClientModule,
    FormsModule
  ],
  providers: [interceptorProvider],
  bootstrap: [AppComponent]
})
export class AppModule { }
