import { Component, OnInit } from '@angular/core';
import {Car} from "../car";
import {GenericService} from "../generic.service";

@Component({
  selector: 'app-cars-filter',
  templateUrl: './cars-filter.component.html',
  styleUrls: ['./cars-filter.component.css']
})
export class CarsFilterComponent implements OnInit {

  selectedOption: string;
  fuels: string[] = [];
  f_cars: Car[] = [];

  optionHistory: string[] = ["choose fuel"];
  prevOption: string;
  constructor(private genericService: GenericService) { }

  ngOnInit(): void {
    this.getFuels();
  }

  getFuels(): void{
    this.genericService.fetchFuels()
      .subscribe(fuels => this.fuels = fuels);
  }

  getFilteredCars(): void{
    this.prevOption = this.optionHistory[this.optionHistory.length-1];
    this.optionHistory.push(this.selectedOption);
    this.genericService.fetchCarsByFuel(this.selectedOption)
      .subscribe(cars => {
        this.f_cars = cars;

      });
  }

}
