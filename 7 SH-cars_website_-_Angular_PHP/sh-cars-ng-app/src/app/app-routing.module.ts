import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {CarCrudComponent} from "./car-crud/car-crud.component";
import {CarsFilterComponent} from "./cars-filter/cars-filter.component";
import {CarsHomeComponent} from "./cars-home/cars-home.component";

const routes: Routes = [
   {path: '', redirectTo: '/cars-home', pathMatch: 'full'},
   {path: 'car-crud', component: CarCrudComponent},
   {path: 'cars-filter', component: CarsFilterComponent},
   {path: 'cars-home', component: CarsHomeComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
