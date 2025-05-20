import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError } from 'rxjs';
import { StorageService } from '../../../auth/services/storage/storage.service';

const BASIC_URL =["http://localhost:8080"]

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http:HttpClient) { }

  addCruise(cruiseDto:any):Observable<any>{
    return this.http.post(BASIC_URL + "/cruises/add", cruiseDto, {
      headers: this.createAuthorizationHeader()
    });
  }

  createAuthorizationHeader(): HttpHeaders {
    return new HttpHeaders({
      'Authorization': 'Bearer ' + StorageService.getToken()
    });
  }

  getAllCruises(): Observable<any> {
    if (!BASIC_URL) {
      throw new Error('BASIC_URL is not defined');
    }

    return this.http.get(BASIC_URL[0] + "/cruises/all", {
      headers: this.createAuthorizationHeader()
    }).pipe(
      catchError(err => {
        throw new Error(`Error getting all cruises: ${err}`);
      })
    );
  }

  deleteCruise(id:string):Observable<any>{
    return this.http.delete(BASIC_URL + "/cruises/" + id, {
      headers: this.createAuthorizationHeader()
    })
  }

  getCruiseById(id:string):Observable<any>{
    return this.http.get(BASIC_URL + "/cruises/" + id, {
      headers: this.createAuthorizationHeader()
    })
  }

  updateCruise(id:string, cruiseDto:any):Observable<any>{
    return this.http.put(BASIC_URL + "/cruises/" + id, cruiseDto, {
      headers: this.createAuthorizationHeader()
    })
  }

  addActivity(activityDto:any):Observable<any>{
    return this.http.post(BASIC_URL + "/activities", activityDto, {
      headers: this.createAuthorizationHeader()
    })
  }

  getActivities():Observable<any>{
    return this.http.get(BASIC_URL + "/activities", {
      headers: this.createAuthorizationHeader()
    })
  }

  deleteActivityById(id:string):Observable<any>{
    return this.http.delete(BASIC_URL + "/activities/" + id, {
      headers: this.createAuthorizationHeader()
    })
  }

  getActivityByActivityId(activityId:string):Observable<any>{
    return this.http.get(BASIC_URL + "/activities/" + activityId, {
      headers: this.createAuthorizationHeader()
    })
  }

  updateActivity(id:string,activityDto:any):Observable<any>{
    return this.http.put(BASIC_URL + "/activities/"+id, activityDto, {
      headers: this.createAuthorizationHeader()
    })
  }

  getReservations():Observable<any>{
    return this.http.get(BASIC_URL + "/reservations", {
      headers: this.createAuthorizationHeader()
    })
  }

}
