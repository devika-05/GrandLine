import { AdminService } from './../../services/admin.service';
import { CommonModule, formatDate } from '@angular/common';
import { Component } from '@angular/core';
import { Router, RouterModule, RouterOutlet } from '@angular/router';
import { NgZorroImportsModuleTs } from '../../../../ng-zorro-imports-module.ts';
import { FormGroup, FormsModule, FormBuilder, Validators, ReactiveFormsModule } from '@angular/forms';
import { NzMessageService } from 'ng-zorro-antd/message';

@Component({
  selector: 'app-post-cruise',
  standalone: true,
  imports: [RouterModule, RouterOutlet,NgZorroImportsModuleTs, CommonModule, FormsModule, ReactiveFormsModule],
  templateUrl: './post-cruise.component.html',
  styleUrl: './post-cruise.component.scss'
})
export class PostCruiseComponent {
  postCruiseForm!:FormGroup;
  isSpinning:boolean=false;
  selectedFile:any|File|null;
  imagePreview:any|string| ArrayBuffer| null;

  constructor(private fb:FormBuilder,
      private adminService:AdminService,
      private message:NzMessageService,
      private router:Router
    ){}

  ngOnInit(): void {
    //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
    //Add 'implements OnInit' to the class.
    this.postCruiseForm =this.fb.group({
      cruiseId:[null, Validators.required],
      name:[null, Validators.required],
      departurePort:[null, Validators.required],
      destination:[null, Validators.required],
      departureDate:[null, Validators.required],
      price:[null, Validators.required],
      availableRooms:[null, Validators.required]
    })
  }

  addCruise(): void{
    if(this.postCruiseForm){
      console.log(this.postCruiseForm.value);
      this.isSpinning = true; // Start the spinner
      const formData: FormData= new FormData();
      formData.append('image', this.selectedFile);
      formData.append('cruiseId', this.postCruiseForm.get('cruiseId')?.value);
      formData.append('name', this.postCruiseForm.get('name')?.value);
      formData.append('departurePort', this.postCruiseForm.get('departurePort')?.value);
      formData.append('destination', this.postCruiseForm.get('destination')?.value);
      // Format departureDate
    const departureDate = formatDate(this.postCruiseForm.get('departureDate')?.value, 'yyyy-MM-dd', 'en-US');
    // Append formatted departureDate to form data
    formData.append('departureDate', departureDate);
    console.log(departureDate);
      formData.append('price', this.postCruiseForm.get('price')?.value);
      formData.append('availableRooms', this.postCruiseForm.get('availableRooms')?.value);
      console.log(formData);
      this.adminService.addCruise(formData).subscribe((res)=>{
        this.isSpinning = false; // Stop the spinner
        this.message.success("Cruise Posted Successfully",{nzDuration:5000});
        this.router.navigateByUrl("/admin/dashboard");
        console.log(res);
      }, error=>{
        this.message.error("Error while posting cruise",{nzDuration:5000});
        this.router.navigateByUrl("/admin/dashboard");
      })
    }else{
      console.error("postCruiseForm is null");
    }
  }

  onFileSelected(event: any) {
    const file = event.target.files[0];
    this.selectedFile = file;
    const reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = () => {
        this.imagePreview = reader.result;
    };

}
}
