import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { CustomerService } from '../../services/customer.service';
import { NgZorroImportsModuleTs } from '../../../../ng-zorro-imports-module.ts';

@Component({
  selector: 'app-customer-dashboard',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule, NgZorroImportsModuleTs, ReactiveFormsModule],
  templateUrl: './customer-dashboard.component.html',
  styleUrls: ['./customer-dashboard.component.scss'],
  providers: [CustomerService] // Ensure the service is provided
})
export class CustomerDashboardComponent implements OnInit {

  cruise: any[] = [];

  constructor(private customerService: CustomerService) {}

  ngOnInit(): void {
    this.getAllCruises();
  }

  getAllCruises() {
    this.customerService.getAllCruises().subscribe({
      next: (res) => {
        if (res && res.length > 0) {
          console.log(res);
          res.forEach((element: any) => {
            if (element.returnedImage) {
              element.processedImg = 'data:image/jpeg;base64,' + element.returnedImage;
              this.cruise.push(element);
            }
          });
        } else {
          console.error('Response is undefined, null, or empty array');
        }
      },
      error: (error) => {
        console.error('Error fetching cruises:', error);
      }
    });
  }
}
