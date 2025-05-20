import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AdminService } from '../../services/admin.service';
import { Activity } from '../../../../models/activity.model';
import { NgZorroImportsModuleTs } from '../../../../ng-zorro-imports-module.ts';
import { CommonModule } from '@angular/common';
import { NzMessageService } from 'ng-zorro-antd/message';

@Component({
  selector: 'app-update-activity',
  standalone: true,
  imports: [NgZorroImportsModuleTs, CommonModule, ReactiveFormsModule],
  templateUrl: './update-activity.component.html',
  styleUrls: ['./update-activity.component.scss']
})
export class UpdateActivityComponent implements OnInit {
  activityId: string = '';
  updateActivityForm!: FormGroup;
  isSpinning: boolean = false;

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private service: AdminService,
    private message:NzMessageService
  ) { }

  ngOnInit(): void {
    this.updateActivityForm = this.formBuilder.group({
      activityId: [{value: '', disabled: true}, Validators.required], // Set disabled to true here
      cruiseId: ['', Validators.required],
      type: ['', Validators.required],
      location: ['', Validators.required],
      price: ['', Validators.required],
      name: ['', Validators.required]
    });

    this.activityId = this.route.snapshot.params['id'];

    this.service.getActivityByActivityId(this.activityId).subscribe((res: Activity) => {
      this.updateActivityForm.patchValue({
        activityId: res.activityId,
        cruiseId: res.cruiseId,
        type: res.type,
        location: res.location,
        price: res.price,
        name: res.name
      });
    }, (err) => {
      console.error("Error fetching activity", err);
    });
  }

  updateActivity() {
    this.isSpinning = true;
    this.service.updateActivity(this.activityId,this.updateActivityForm.value).subscribe((res) => {
      this.isSpinning = false;
      this.message.success('Activity updated successfully',{nzDuration: 5000});
      this.router.navigate(['/admin/activities']);
    }, (err) => {
      console.error("Error updating activity", err);
    });
  }
}
