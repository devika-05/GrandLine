import { Injectable } from '@angular/core';

const TOKEN = "token";
const USER = "user";

@Injectable({
  providedIn: 'root'
})
export class StorageService {

  constructor() { }

  static saveToken(token: string): void {
    localStorage.removeItem(TOKEN);
    localStorage.setItem(TOKEN, token);
  }

  static saveUser(user: any): void {
    if(typeof window !=="undefined"){
      window.localStorage.removeItem(USER);
      window.localStorage.setItem(USER, JSON.stringify(user));}
  }

  static getToken() {
    if(typeof window !=="undefined"){
      return window.localStorage.getItem(TOKEN);}
      return null;
  }

  static getUser(): any {
    if(typeof window !=="undefined"){
    const value = window.localStorage.getItem(USER);
    return value ? JSON.parse(value) : null;}
  }

  static getRole(): string {
    const user = this.getUser();
    if (user == null) return "";
    return user.role;
  }

  static isAdminLoggedIn(): boolean {
    if (this.getToken() == null) return false;
    const roleA: string = this.getRole();
    return roleA === "ADMIN";
  }

  static isCustomerLoggedIn(): boolean {
    if (this.getToken() == null) return false;
    const roleC: string = this.getRole();
    return roleC === "CUSTOMER";
  }

  static logout():void{
    if(typeof window !=="undefined"){
      window.localStorage.removeItem(TOKEN);
      window.localStorage.removeItem(USER);
      window.localStorage.clear;
    }
  }
}
