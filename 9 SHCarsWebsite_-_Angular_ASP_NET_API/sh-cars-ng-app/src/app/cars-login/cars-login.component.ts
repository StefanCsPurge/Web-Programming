import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Router} from '@angular/router';
import {GenericService} from '../generic.service';

@Component({
  selector: 'app-cars-login',
  templateUrl: './cars-login.component.html',
  styleUrls: ['./cars-login.component.css']
})
export class CarsLoginComponent implements OnInit {

  constructor(private http: HttpClient, private router: Router, private service: GenericService) { }

  ngOnInit(): void {
    console.log("logged out");
    sessionStorage.setItem("currentUser", '');
  }


  login(username: string, password: string) {
    const body = JSON.stringify({"user":username, "pwd":password});

    this.http.post<any>('http://localhost:8080/api/Authentication/Login', body,
      {headers: new HttpHeaders({'Content-Type': 'application/json'})})
      .subscribe(r => {
        if(r){
          sessionStorage.setItem("currentUser", username);
          this.service.setCurrentUser(username);
          this.router.navigate(['/cars-home']).then();
        }
        else alert("Bad credentials")
      });
  }
}
