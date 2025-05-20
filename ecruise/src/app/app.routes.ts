import { Routes } from '@angular/router';
import { SignupComponent } from './auth/components/signup/signup.component';
import { LoginComponent } from './auth/components/login/login.component';
import { LandingPageComponent } from './landing-page/landing-page.component';

export const routes: Routes = [
  {path:"", component:LandingPageComponent},
  {path:"register", component:SignupComponent},
  {path:"login", component:LoginComponent},
  // { path: '**', redirectTo: '' },
  {path:"admin", loadChildren: () => import("./modules/admin/admin.module").then(m => m.AdminModule)},
  {path:"customer", loadChildren: () => import("./modules/customer/customer.module").then(m => m.CustomerModule)}
];
