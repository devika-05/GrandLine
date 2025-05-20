import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormArray, Validators, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { CustomerService } from '../../services/customer.service';
import { CommonModule } from '@angular/common';
import { NgZorroImportsModuleTs } from '../../../../ng-zorro-imports-module.ts';
import { NzMessageService } from 'ng-zorro-antd/message';
import { BookingRequest } from '../../../../models/book-cruise.model';

@Component({
  selector: 'app-book-cruise',
  standalone: true,
  imports:[CommonModule, FormsModule, ReactiveFormsModule, RouterModule, NgZorroImportsModuleTs],
  templateUrl: './book-cruise.component.html',
  styleUrls: ['./book-cruise.component.scss']
})
export class BookCruiseComponent implements OnInit {
  isSpinning:boolean=false;
  id: string = '';
  cruiseId: string = '';
  email: string = '';
  userId: string = '';
  error: string = '';
  bookingForm!: FormGroup;
  bookingId:string="";
  bookingRequest: BookingRequest = {
    email: '',
    cruiseId: '',
    passengers: []
  };

  constructor(
    private route: ActivatedRoute,
    private fb: FormBuilder,
    private customerService: CustomerService,
    private message:NzMessageService,
    private router: Router
  ) { }

  createForm(): void {
    this.bookingForm = this.fb.group({
      email: ['', Validators.required],
      passengers: this.fb.array([]) // Initialize FormArray
    });
  }

  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      this.id = params['id'];
      this.getCruiseIdById(this.id);
    });

    this.createForm();
  }

  getCruiseIdById(id: string) {
    this.customerService.getCruiseById(id).subscribe((res: any) => {
      this.cruiseId = res.cruiseId;
    });
  }

  onSubmit(): void {
    if(this.bookingForm){
      this.isSpinning = true;
      let tamount:number=0;
      console.log(this.bookingForm.value);
      this.bookingRequest.email = this.bookingForm.get('email')?.value;
      this.bookingRequest.cruiseId = this.cruiseId;
      this.bookingRequest.passengers = this.bookingForm.get('passengers')?.value;
      console.log(this.bookingRequest);

      this.customerService.bookCruise(this.bookingRequest).subscribe((res)=>{
        this.isSpinning = false;
        this.router.navigate(['/customer/booking-summary/',res.bookingId]);
        // this.message.success("Booking completed Successfully",{nzDuration:5000});
        console.log(res);
      },
      error=>{
        this.isSpinning = false;
        this.message.error("Error while booking cruise",{nzDuration:5000});
        console.log(error);
      })
    }
  }

  get passengers() {
    return this.bookingForm.get('passengers') as FormArray;
  }

  addPassenger(): void {
    this.passengers.push(this.fb.group({
      name: ['', Validators.required],
      age: ['', Validators.required]
    }));
  }
}
