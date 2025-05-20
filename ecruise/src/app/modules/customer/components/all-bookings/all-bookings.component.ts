import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CustomerService } from '../../services/customer.service';
import { BookingDetails } from '../../../../models/booking-summary.model';
import { CommonModule } from '@angular/common';
import { Cruise } from '../../../../models/cruise.model';

@Component({
  selector: 'app-all-bookings',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './all-bookings.component.html',
  styleUrl: './all-bookings.component.scss'
})
export class AllBookingsComponent implements OnInit{
  email:string='';
  bookings:BookingDetails[]=[];
  cruiseDetails:Cruise={
    cruiseId: '',
    name: '',
    departurePort: '',
    destination: '',
    departureDate: new Date(),
    price: 0
  }
  cid:string='';
  constructor(private route: ActivatedRoute,
    private service: CustomerService) { }
  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      this.email = params['email'];
      this.service.getAllBookingsByEmail(this.email).subscribe((res: BookingDetails[]) => {
        console.log(res);
        this.bookings=res;

        this.bookings.forEach((booking: BookingDetails) => {
          this.service.getCruiseByCruiseId(booking.cruiseId).subscribe((cruise: Cruise) => {
            this.cruiseDetails = cruise;
          })
        })
      },
        (error) => {
          console.log("Error fetching bookings", error);
        })
    });

  }


}
