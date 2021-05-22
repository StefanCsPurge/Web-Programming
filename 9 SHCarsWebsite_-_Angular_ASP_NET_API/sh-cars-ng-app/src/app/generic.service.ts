import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Car } from './car';

@Injectable({
  providedIn: 'root'
})
export class GenericService {
  private backendUrl = 'http://localhost:8080/api/Cars';  // URL to web api
  private currentUser = '';
  public httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(private http: HttpClient) {
    sessionStorage.setItem("currentUser",'');
  }

  setCurrentUser(u: string): void {
      this.currentUser = u;
  }

  getCurrentUser(): string{
    return this.currentUser;
  }

  fetchCars() : Observable<Car[]> {
    /* body of the method */
    return this.http.get<Car[]>(this.backendUrl + '/getAll')
      .pipe(catchError(this.handleError<Car[]>('fetchCars', []))
      );
  }

  fetchFuels(): Observable<string[]>{
    return this.http.get<string[]>(this.backendUrl+'/getAllFuels')
      .pipe(catchError(this.handleError<string[]>('fetchFuels', []))
      );
  }

  fetchCarsByFuel(fuel: string) : Observable<Car[]> {
    return this.http.get<Car[]>(this.backendUrl+'/getAllForFuel/'+fuel)
      .pipe(catchError(this.handleError<Car[]>('fetchCarsByFuel', []))
      );
  }

  /** POST: add a new car to the database */
  addCar(car: Car): Observable<any> {
    const body = JSON.stringify(car);
    return this.http.post<any>(this.backendUrl+'/addCar', body, this.httpOptions)
      .pipe(catchError(this.handleError<any>('addCar')));
  }

  updateCar(car: Car): Observable<any> {
    const body = JSON.stringify(car);
    return this.http.put<any>(this.backendUrl+'/Put', body, this.httpOptions)
      .pipe(catchError(this.handleError<any>('updateCar')));
  }

  deleteCar(id: number): Observable<any> {
    return this.http.delete<any>(this.backendUrl+"/Delete/"+id)
      .pipe(catchError(this.handleError<any>('deleteCar')));
  }

  /**
   * Handle Http operation that failed.
   * Let the app continue.
   * @param operation - name of the operation that failed
   * @param result - optional value to return as the observable result
   */
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      // TO DO: send the error to remote logging infrastructure
      console.error('Error: ' + error); // log to console instead
      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }


}
