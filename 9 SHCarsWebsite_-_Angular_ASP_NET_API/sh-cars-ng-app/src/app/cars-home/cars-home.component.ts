import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {GenericService} from '../generic.service';

@Component({
  selector: 'app-cars-home',
  templateUrl: './cars-home.component.html',
  styleUrls: ['./cars-home.component.css']
})
export class CarsHomeComponent implements OnInit {

  constructor(private genericService: GenericService, private router: Router) { }

  ngOnInit(): void {
  }

  userLogged(): boolean{
    return this.genericService.getCurrentUser()!='';
  }

  goToFilter(): void{
    this.router.navigate(['/cars-filter']).then();
  }

  goToCrud() {
    this.router.navigate(['/car-crud']).then();
  }
}
