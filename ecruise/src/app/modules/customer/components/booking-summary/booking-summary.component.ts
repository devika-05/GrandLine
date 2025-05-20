import { Cruise } from './../../../../models/cruise.model';
import { Component, HostListener, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterModule, RouterOutlet } from '@angular/router';
import { CustomerService } from '../../services/customer.service';

import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgZorroImportsModuleTs } from '../../../../ng-zorro-imports-module.ts';
import { BrowserModule } from '@angular/platform-browser';
import { BookingDetails } from '../../../../models/booking-summary.model';
// import{Razorpay} from 'razorpay';

declare var Razorpay: any;
@Component({
  selector: 'app-booking-summary',
  standalone: true,
  imports: [RouterModule, RouterOutlet, CommonModule, ReactiveFormsModule, NgZorroImportsModuleTs, FormsModule],
  templateUrl: './booking-summary.component.html',
  styleUrl: './booking-summary.component.scss'
})
export class BookingSummaryComponent implements OnInit {
  bookingId: string = '';
  bookingDetails:BookingDetails|undefined;
  tAmount:number=0;
  constructor(
    private route: ActivatedRoute,
    private service:CustomerService,
    private router:Router
  ) { }

  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      this.bookingId = params['bookingId'];
      this.getBookingDetails(this.bookingId);
    });


  }

  getBookingDetails(bookingId: string): void {
    this.service.getBookingByBookingId(bookingId).subscribe(
      (details: BookingDetails) => {
        this.bookingDetails = details;
        this.tAmount=this.bookingDetails?.amount;
        console.log(this.bookingDetails);
      },
      (error) => {
        console.error('Error fetching booking details:', error);
      }
    );
  }

  initiatePayment(amount:number): void {
    this.service.pay(amount).subscribe((response)=>{
          console.log(response);
          this.openTranscationModel(response)
        },
        (error)=>{
          console.log(error);
        }
      );
  }

  openTranscationModel(response:any): void {
    var options={
      order_id:response.orderId,
      key:response.key,
      amount:response.amount,
      currency:response.currency,

      name:"Devu",
      description: "Payment for cruise booking",
      handler: (response: any)=>{
        var event = new CustomEvent("payment.success",
        {
          // detail: response,
          // bubbles: true,
          // cancelable: true
        }
      );
      window.dispatchEvent(event);        //what is the action to be performed will be given in the handler
        this.processResponse(response);
      },
      prefill: {
        name:'LPY',
        email: 'abc@gmail.com',
        contact: '9090909090'
      },
      notes:{
        addresss:'Online booking'
      },
      theme:{
        color: '#F37254'
      }
    };

    const razorpayObj = new Razorpay(options);
    razorpayObj.open();

  }


  processResponse(response:any): void {
    console.log(response);
  }

  @HostListener('window:payment.success', ['$event'])
  onPaymentSuccess(event: { detail: any; }): void {
    console.log('Payment successful:', event.detail);
    this.service.updateBookingByBookingId(this.bookingId).subscribe(
      (response) => {
        console.log(response);
        this.router.navigate(['/customer/all-bookings/',response.email]);
      },
      (error) => {
        console.log(error);
      }
    )
  }

  cancelBooking() {
    // Navigate back to the booking component
    this.router.navigate(['/customer/cruises/',this.bookingDetails?.cruise.id]);
  }

}

