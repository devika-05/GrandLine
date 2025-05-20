import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { StorageService } from '../../../auth/services/storage/storage.service';
import { Observable } from 'rxjs';

const BASIC_URL=["http://localhost:8080"];

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  constructor(private http:HttpClient) { }

  getAllCruises():Observable<any>{
    return this.http.get(BASIC_URL + "/cruises/all", {
      headers: this.createAuthorizationHeader()
    })
  }

  createAuthorizationHeader(): HttpHeaders {
    return new HttpHeaders({
      'Authorization': 'Bearer ' + StorageService.getToken()
    });
  }

  bookCruise(bookingRequest:any):Observable<any>{
    return this.http.post(BASIC_URL + "/bookings", bookingRequest, {
      headers: this.createAuthorizationHeader()
    })
  }

  getCruiseById(id:string):Observable<any>{
    return this.http.get(BASIC_URL + "/bookings/cruise/" + id, {
      headers: this.createAuthorizationHeader()
    })
  }

  getBookingByBookingId(bookingId:string):Observable<any>{
    return this.http.get(BASIC_URL + "/bookings/" + bookingId, {
      headers: this.createAuthorizationHeader()
    })
  }

  updateBookingByBookingId(bookingId:string):Observable<any>{
    return this.http.put(BASIC_URL + "/bookings/" + bookingId, {}, { // pass an empty object as request body
      headers: this.createAuthorizationHeader() // pass headers in options object
    })
  }

  pay(amount:number):Observable<any>{
    return this.http.get(BASIC_URL + "/payment/"+amount, {
      headers: this.createAuthorizationHeader()
    })
  }

  getAllBookingsByEmail(email:string):Observable<any>{
    return this.http.get(BASIC_URL + "/bookings/email/" + email, {
      headers: this.createAuthorizationHeader()
    })
  }

  getCruiseByCruiseId(cruiseId:string):Observable<any>{
    return this.http.get(BASIC_URL + "/cruises/cId/" + cruiseId, {
      headers: this.createAuthorizationHeader()
    })
  }
}
