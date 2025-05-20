import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterModule, RouterOutlet } from '@angular/router';
//NG Zorro IMPORTS
import{NzMessageService} from 'ng-zorro-antd/message';
import {NzSpinModule} from 'ng-zorro-antd/spin';
import {NzFormModule} from 'ng-zorro-antd/form';
import {NzButtonModule} from 'ng-zorro-antd/button';
import {NzInputModule} from 'ng-zorro-antd/input';
import {NzLayoutModule} from 'ng-zorro-antd/layout';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../services/auth/auth.service';
import { StorageService } from '../../services/storage/storage.service';


@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule,RouterOutlet, RouterModule, FormsModule,NzSpinModule,NzFormModule,NzButtonModule,NzInputModule,NzLayoutModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})

export class LoginComponent {
  eadd:string="";
  isSpinning:boolean=false;
  loginForm!:FormGroup;

  constructor(private fb:FormBuilder,
    private authService:AuthService,
    private router:Router,
    private message:NzMessageService){}

  ngOnInit() {
    this.loginForm=this.fb.group({
      email:[null,[Validators.email, Validators.required]],
      password:[null,[ Validators.required]]
    })

  }

  login() {
    console.log(this.loginForm.value);
    const email = this.loginForm.get('email')?.value; // Get the entered email
    localStorage.setItem('userEmail', email);
    const userEmail = localStorage.getItem('userEmail');

if (userEmail) {
  // Email is saved in localStorage
  console.log('Email saved:', userEmail);
} else {
  // Email is not saved in localStorage
  console.log('No email saved in localStorage');
}
    this.authService.login(this.loginForm.value).subscribe((res) => {
      console.log(res);

      if (res.userId != null) {
        const user = {
          id: res.id,
          role: res.role
        };
        StorageService.saveUser(user);
        console.log(StorageService.getUser());
        StorageService.saveToken(res.jwt);
        if (StorageService.isAdminLoggedIn()) {
          this.router.navigateByUrl("/admin/dashboard");
        } else if (StorageService.isCustomerLoggedIn()) {
          this.router.navigateByUrl("/customer/dashboard");
        } else {
          // console.log(error);
          this.message.error("Bad Credentials", { nzDuration: 5000 });
        }
      } else {
        this.message.error("Invalid Credentials", { nzDuration: 5000 });
      }
    });
  }

}
