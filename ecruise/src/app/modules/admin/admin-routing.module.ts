import { PostCruiseComponent } from './components/post-cruise/post-cruise.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminDashboardComponent } from './components/admin-dashboard/admin-dashboard.component';
import { UpdateCruiseComponent } from './components/update-cruise/update-cruise.component';
import { PostActivitiesComponent } from './components/post-activities/post-activities.component';
import { ActivitiesDashboardComponent } from './components/activities-dashboard/activities-dashboard.component';
import { UpdateActivityComponent } from './components/update-activity/update-activity.component';
import { ReservationsComponent } from './components/reservations/reservations.component';

const routes: Routes = [
  {path:"dashboard", component:AdminDashboardComponent},
  {path:"cruises", component:PostCruiseComponent},
  {path:"cruises/:id", component:UpdateCruiseComponent},
  {path:"postActivities", component:PostActivitiesComponent},
  {path:"activities", component:ActivitiesDashboardComponent},
  {path:"activities/:id", component:UpdateActivityComponent},
  {path:"reservations",component:ReservationsComponent}

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
