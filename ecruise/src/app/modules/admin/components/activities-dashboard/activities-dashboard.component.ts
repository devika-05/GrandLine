import { Component, OnInit } from '@angular/core';
import { AdminService } from '../../services/admin.service';
import { NzMessageService } from 'ng-zorro-antd/message';
import { NgZorroImportsModuleTs } from '../../../../ng-zorro-imports-module.ts';
import { CommonModule } from '@angular/common';
import { Activity } from '../../../../models/activity.model';
import { Router, RouterModule } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-activities-dashboard',
  standalone: true,
  imports: [NgZorroImportsModuleTs,CommonModule, RouterModule, ReactiveFormsModule],
  templateUrl: './activities-dashboard.component.html',
  styleUrl: './activities-dashboard.component.scss'
})
export class ActivitiesDashboardComponent implements OnInit {
  activityId: string = '';
  isSpinning: boolean = false;
  activities:Activity[]=[];
  constructor(private service:AdminService,
    private message:NzMessageService,
    private router:Router){}
  ngOnInit(): void {
    this.service.getActivities().subscribe((res: any) => {
      this.isSpinning=false;
      this.activities=res;
      console.log(res);
    },
    (error: any) => {
      console.log("Error fetching activities", error,{nzDuration: 5000});
      this.isSpinning=false;
      this.message.error('Error fetching activities',{nzDuration: 5000});
    })
    // throw new Error('Method not implemented.');
  }

  deleteActivity(activityId: string) {
    this.service.deleteActivityById(activityId).subscribe((res: any) => {
      console.log(res);
      this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
        this.router.navigate(['admin/activities']);
      this.message.success('Activity deleted successfully',{nzDuration: 5000});
    },
    (error: any) => {
      console.log("Error deleting activity", error,{nzDuration: 5000});
      this.message.error('Error deleting activity',{nzDuration: 5000});
    })})
  }

}
