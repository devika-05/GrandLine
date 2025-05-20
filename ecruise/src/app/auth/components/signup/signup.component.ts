import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
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

@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule,RouterOutlet, RouterModule, FormsModule,NzSpinModule,NzFormModule,NzButtonModule,NzInputModule,NzLayoutModule],
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.scss'
})
export class SignupComponent implements OnInit {
  isSpinning: boolean = false;
  signupForm!: FormGroup;

  constructor(private fb: FormBuilder,
    private authService:AuthService,
    private message:NzMessageService,
    private router:Router){}

  ngOnInit(){
    this.signupForm=this.fb.group({
      userId:[null, [Validators.required]],
      userName:[null, [Validators.required]],
      email:[null,[Validators.required, Validators.email]],
      password:[null, [Validators.required]],
      checkPassword:[null,[Validators.required, this.confirmationValidate]]
    })
  }

  confirmationValidate = (control: FormControl): { [s: string]: boolean } | null => {
    if (!control.value) {
      return { required: true };
    } else if (control.value !== this.signupForm.controls['password'].value) {
      return { confirm: true, error: true };
    }
    return null;
  };

  register() {
    console.log(this.signupForm.value);
    this.authService.register(this.signupForm.value).subscribe(
      (res) => {
        console.log(res);
        if (res.id!=null) {
          this.message.success("Signup successful", { nzDuration: 5000 });
          this.router.navigateByUrl("/login");
        } else {
          this.message.error("Something went wrong", { nzDuration: 5000 });
        }
      },
      (error) => {
        this.message.error("This email address has a registered account <br>with us.<br>Please try to login once!", { nzDuration: 5000 });
        console.error("Error during registration:", error);
      }
    );
  }
}
