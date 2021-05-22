import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {CarCrudComponent} from "./car-crud/car-crud.component";
import {CarsFilterComponent} from "./cars-filter/cars-filter.component";
import {CarsHomeComponent} from "./cars-home/cars-home.component";
import {CarsLoginComponent} from './cars-login/cars-login.component';

const routes: Routes = [
   {path: '', redirectTo: '/cars-login', pathMatch: 'full'},
   {path: 'car-crud', component: CarCrudComponent},
   {path: 'cars-filter', component: CarsFilterComponent},
   {path: 'cars-home', component: CarsHomeComponent},
   {path: 'cars-login', component: CarsLoginComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
