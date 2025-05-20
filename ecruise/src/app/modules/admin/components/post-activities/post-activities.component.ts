import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { NgZorroImportsModuleTs } from '../../../../ng-zorro-imports-module.ts';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { AdminService } from '../../services/admin.service.js';
import { NzMessageService } from 'ng-zorro-antd/message';

@Component({
  selector: 'app-post-activities',
  standalone: true,
  imports: [CommonModule,NgZorroImportsModuleTs, ReactiveFormsModule,FormsModule],
  templateUrl: './post-activities.component.html',
  styleUrl: './post-activities.component.scss'
})
export class PostActivitiesComponent implements OnInit{

isSpinning: boolean = false;
postActivityForm!:FormGroup;

constructor(
  private fb:FormBuilder,
  private service:AdminService,
  private message:NzMessageService
){}
  ngOnInit(): void {
    this.initForm();
  }

  initForm(): void {
    this.postActivityForm = this.fb.group({
      activityId: [null, [Validators.required]],
      cruiseId: [null, [Validators.required]],
      name: [null, [Validators.required]],
      type: [null, [Validators.required]],
      location: [null, [Validators.required]],
      price: [null, [Validators.required]]
    });
  }

addActivity() {
  if (this.postActivityForm.invalid) {
    for (const i in this.postActivityForm.controls) {
      this.postActivityForm.controls[i].markAsDirty();
      this.postActivityForm.controls[i].updateValueAndValidity();
    }
    return;
  }
  this.isSpinning=true;

  this.service.addActivity(this.postActivityForm.value).subscribe((res: any) => {
    this.isSpinning=false;

      console.log(res);
      this.message.success('Activity added successfully',{nzDuration: 5000});

  },
    (error: any) => {
      console.log("Error adding activity", error);
      this.message.error('Error adding activity',{nzDuration: 5000});
      this.isSpinning=false;
    }
  );
}}
