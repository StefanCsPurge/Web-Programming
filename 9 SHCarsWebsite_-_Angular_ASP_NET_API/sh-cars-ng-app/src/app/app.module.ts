import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CarCrudComponent } from './car-crud/car-crud.component';
import { CarsFilterComponent } from './cars-filter/cars-filter.component';
import { CarsHomeComponent } from './cars-home/cars-home.component';
import { HttpClientModule} from "@angular/common/http";
import { FormsModule } from "@angular/forms";
import { CarsLoginComponent } from './cars-login/cars-login.component';

@NgModule({
  declarations: [
    AppComponent,
    CarCrudComponent,
    CarsFilterComponent,
    CarsHomeComponent,
    CarsLoginComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
