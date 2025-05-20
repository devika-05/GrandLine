import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { NavigationEnd, Router, RouterModule, RouterOutlet } from '@angular/router';
import { NgZorroImportsModuleTs } from './ng-zorro-imports-module.ts';
import { StorageService } from './auth/services/storage/storage.service.js';
import { FormsModule } from '@angular/forms';

//NG Zorro IMPORTS


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, RouterModule,NgZorroImportsModuleTs, CommonModule, FormsModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent implements OnInit{
  title = 'ecruise';
  email:string="";
  isCustomerLoggedIn:boolean=StorageService.isCustomerLoggedIn();
  isAdminLoggedIn:boolean=StorageService.isCustomerLoggedIn();

  constructor(private router:Router){}

  ngOnInit(): void {

    this.email = localStorage.getItem('userEmail') || '';

    // Check if email has been retrieved
    if (this.email) {
      console.log("Email retrieved successfully:", this.email);
    } else {
      console.log("Email not found in local storage.");
    }

    this.router.events.subscribe(event => {
      if (event.constructor.name=== "NavigationEnd") {
        this.isAdminLoggedIn = StorageService.isAdminLoggedIn();
        this.isCustomerLoggedIn = StorageService.isCustomerLoggedIn();
      }
    });
  }

  logout(){
    StorageService.logout();
    this.router.navigateByUrl("/login");
  }
}
