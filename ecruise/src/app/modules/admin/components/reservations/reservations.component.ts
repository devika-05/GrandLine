import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgZorroImportsModuleTs } from '../../../../ng-zorro-imports-module.ts';
import { RouterModule } from '@angular/router';
import { AdminService } from '../../services/admin.service.js';



@Component({
  selector: 'app-reservations',
  standalone: true,
  imports: [FormsModule,CommonModule, NgZorroImportsModuleTs, RouterModule, ReactiveFormsModule],
  templateUrl: './reservations.component.html',
  styleUrl: './reservations.component.scss'
})
export class ReservationsComponent implements OnInit {
reser:any="";
  constructor(
    private service:AdminService
  ) { }
  ngOnInit(): void {


    this.service.getReservations().subscribe(
      (res) => {
        this.reser=res;
        console.log("Reservations fetched successfully", res);
      },
      (error) => {
        console.log("Error fetching reservations", error);
      }
    )
  }

}
