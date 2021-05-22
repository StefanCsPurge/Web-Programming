import { Component, OnInit } from '@angular/core';
import {Car} from "../car";
import { GenericService } from '../generic.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-car-crud',
  templateUrl: './car-crud.component.html',
  styleUrls: ['./car-crud.component.css']
})
export class CarCrudComponent implements OnInit {
  cars: Car[] = [];
  selectedCar: Car;

  constructor(private genericService: GenericService, private router: Router) { }

  ngOnInit(): void {
    console.log("ngOnInit called for CarCrudComponent");
    this.getCars();
  }

  getCars(): void{
    this.genericService.fetchCars()
      .subscribe(cars => this.cars = cars);
  }

  onAdd(car: Car): void{
    this.selectedCar = car;
    (<HTMLInputElement>document.getElementById("model1")).value = car.model;
    (<HTMLInputElement>document.getElementById("hp1")).value = String(car.hp);
    (<HTMLInputElement>document.getElementById("fuel1")).value = car.fuel;
    (<HTMLInputElement>document.getElementById("price1")).value = String(car.price);
    (<HTMLInputElement>document.getElementById("color1")).value = car.color;
    (<HTMLInputElement>document.getElementById("age1")).value = String(car.age);
    let displayVal:string = document.getElementById('add_form').style.display;
    if (displayVal === "none")
      document.getElementById('add_form').style.display = "inline";
    else document.getElementById('add_form').style.display = "none";
  }

  onUpdate(car: Car): void{
    (<HTMLInputElement>document.getElementById("id2")).value = String(car.id);
    (<HTMLInputElement>document.getElementById("model2")).value = car.model;
    (<HTMLInputElement>document.getElementById("hp2")).value = String(car.hp);
    (<HTMLInputElement>document.getElementById("fuel2")).value = car.fuel;
    (<HTMLInputElement>document.getElementById("price2")).value = String(car.price);
    (<HTMLInputElement>document.getElementById("color2")).value = car.color;
    (<HTMLInputElement>document.getElementById("age2")).value = String(car.age);
    let displayVal:string = document.getElementById('update_form').style.display;
    if (displayVal === "none")
      document.getElementById('update_form').style.display = "inline";
    else document.getElementById('update_form').style.display = "none";
  }

  onDelete(carId: number): void{
    if(confirm("Are you sure you want to delete this car?"))
      this.genericService.deleteCar(carId).subscribe(r => {
        this.ngOnInit()
      });
  }

  addCar(newModel, newHp, newFuel, newPrice, newColor, newAge) {
      let errors: string = this.validateCarInputs(newModel.value, newHp.value, newFuel.value, newPrice.value, newColor.value, newAge.value);
      console.log(errors);
      if(errors.length > 0){
        alert(errors);
        return;
      }
      let newCar: Car = {id: +this.selectedCar.id,
                          model: newModel.value,
                          hp: +newHp.value,
                          fuel: newFuel.value,
                          price: +newPrice.value,
                          color: newColor.value,
                          age: +newAge.value
                        };
      this.genericService.addCar(newCar).subscribe(r => {
        document.getElementById('add_form').style.display = "none";
        this.ngOnInit();
      });
  }

  updateCar(uid, newModel, newHp, newFuel, newPrice, newColor, newAge) {
    let errors: string = this.validateCarInputs(newModel.value, newHp.value, newFuel.value, newPrice.value, newColor.value, newAge.value);
    if(errors.length > 0){
      alert(errors);
      return
    }
    let updatedCar: Car = {id: +uid.value,
      model: newModel.value,
      hp: +newHp.value,
      fuel: newFuel.value,
      price: +newPrice.value,
      color: newColor.value,
      age: +newAge.value
    };
    this.genericService.updateCar(updatedCar).subscribe(r => {
      document.getElementById('update_form').style.display = "none";
      this.ngOnInit();
    });
  }

  goHome() {
    this.router.navigate(['/cars-home']).then();
  }

  validateCarInputs(newModel, newHp, newFuel, newPrice, newColor, newAge){
    let errors = '';
    if(newModel.length==0)
      errors += 'Invalid model\n'
    if(newFuel.length==0)
      errors += 'Invalid fuel\n'
    if(newColor.length==0)
      errors += 'Invalid color\n'
    if(newHp < 0)
      errors += 'Invalid HP\n'
    if(newPrice < 0)
      errors += 'Invalid price\n'
    if(newAge < 0)
      errors += 'Invalid age\n'
    return errors;
  }
}
