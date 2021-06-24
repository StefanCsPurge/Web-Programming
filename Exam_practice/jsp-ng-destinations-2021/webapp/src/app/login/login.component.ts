import { Component, OnInit } from '@angular/core';
import {GenericService} from "../generic.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  username: string;
  password: string;

  constructor(private service: GenericService, private router: Router) { }

  ngOnInit() {
  }

  login():void {
    // this.service.login(this.username, this.password).subscribe(result =>{
    //   window.localStorage.setItem('userId', result['userId']);
    //   console.log(window.localStorage.getItem('userId'));
    //   this.router.navigate(['home']).then();
    // });
    this.service.login(this.username).subscribe(_ =>{
      window.localStorage.setItem('user', this.username);
      console.log(window.localStorage.getItem('user'));
      this.router.navigate(['home']).then();
    });
  }

}
