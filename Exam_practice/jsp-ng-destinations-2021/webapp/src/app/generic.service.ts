import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Destination} from "./destination";

@Injectable()
export class GenericService {

  constructor(private httpClient: HttpClient) { }

  // login(username: string, password: string): Observable<any>{
  //   // 'http://localhost/UsersAssets/controller.php'  //THIS IS FOR PHP
  //   //THIS IS FOR C# / Java
  //   return this.httpClient.post('http://localhost:8080/LoginServlet', {},
  //     { withCredentials: true,
  //       params: {action: 'login', user: username, password: password}
  //     }
  //   );
  // }
  login(username: string): Observable<any>{
      // 'http://localhost/UsersAssets/controller.php'  //FOR PHP
      //THIS IS FOR C# / Java
      return this.httpClient.post('http://localhost:8080/LoginServlet', {},
        { withCredentials: true,
          params: {action: 'login', user: username}
        }
      );
    }

  addBan(username: string, destination: string): Observable<any>{
    return this.httpClient.post('http://localhost:8080/ApiServlet', {},
      { withCredentials: true,
        params: {action: 'addBan', user: username, destination: destination}
      }
    );
  }

  getSearchDestinations(destination: string): Observable<Destination[]>{
    return this.httpClient.get<Destination[]>('http://localhost:8080/ApiServlet?action=getDestinations&name=' + destination
      + '&user=' + window.localStorage.getItem('user'));
  }
}
