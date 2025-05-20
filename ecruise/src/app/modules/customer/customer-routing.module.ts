import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CustomerDashboardComponent } from './components/customer-dashboard/customer-dashboard.component';
import { BookCruiseComponent } from './components/book-cruise/book-cruise.component';
import { BookingSummaryComponent } from './components/booking-summary/booking-summary.component';
import { AllBookingsComponent } from './components/all-bookings/all-bookings.component';

const routes: Routes = [
  {path:"dashboard", component:CustomerDashboardComponent},
  {path:"cruises/:id", component:BookCruiseComponent},
  {path:"booking-summary/:bookingId",component:BookingSummaryComponent},
  {path:"all-bookings/:email",component:AllBookingsComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CustomerRoutingModule { }
