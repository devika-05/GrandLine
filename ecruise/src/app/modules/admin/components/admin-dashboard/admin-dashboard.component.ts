import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AdminService } from '../../services/admin.service';
import { NgZorroImportsModuleTs } from '../../../../ng-zorro-imports-module.ts';
import { NzMessageService } from 'ng-zorro-antd/message';
import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-admin-dashboard',
  standalone: true,
  imports: [CommonModule,FormsModule, NgZorroImportsModuleTs,RouterModule],
  templateUrl: './admin-dashboard.component.html',
  styleUrl: './admin-dashboard.component.scss'
})
export class AdminDashboardComponent {

  cruise:any = [];
  constructor( private adminService:AdminService,
    private message:NzMessageService,
    private router:Router) {}

  ngOnInit(): void {
    //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
    //Add 'implements OnInit' to the class.
    this.getAllCruises();

  }

  getAllCruises(){
    this.adminService.getAllCruises().subscribe({
      next: (res) => {
        if (res && res.length > 0) { // Check if res is defined and not empty
          console.log(res);
          res.forEach((element: any) => { // Iterate over res directly
            if (element.returnedImage) { // Ensure that returnedImage property exists
              element.processedImg = 'data:image/jpeg;base64,' + element.returnedImage;
              this.cruise.push(element);
            }
          });
        } else {
          console.error('Response is undefined, null, or empty array');
        }
      },
      error: (error) => {
        console.error('Error fetching cruises:', error);
      }
    });
  }


  deleteCruise(id:string){
    console.log(id);
    this.adminService.deleteCruise(id).subscribe((res)=>{
        // Reload the current route
    this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
      this.router.navigate(['admin/dashboard']);
      this.message.success("Cruise deleted successfully", {nzDuration: 5000});
    })})
  }
}


