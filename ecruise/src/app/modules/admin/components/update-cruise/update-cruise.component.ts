import { Component } from '@angular/core';
import { AdminService } from '../../services/admin.service';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { NgZorroImportsModuleTs } from '../../../../ng-zorro-imports-module.ts';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule, formatDate } from '@angular/common';
import { NzMessageService } from 'ng-zorro-antd/message';

@Component({
  selector: 'app-update-cruise',
  standalone: true,
  imports: [NgZorroImportsModuleTs,FormsModule,RouterModule,CommonModule,ReactiveFormsModule],
  templateUrl: './update-cruise.component.html',
  styleUrl: './update-cruise.component.scss'
})
export class UpdateCruiseComponent {
  id:string = this.activatedRoute.snapshot.params['id'];
  existingImage:string|null=null;
  imgChanged:boolean=false;
  selectedFile:any|File|null;
  imagePreview:any|string| ArrayBuffer| null;
  updateCruiseForm!:FormGroup;
  isSpinning:boolean=false;
  constructor(private adminService:AdminService,
    private activatedRoute:ActivatedRoute,
    private fb:FormBuilder,
    private message:NzMessageService,
    private router:Router
    ){}

   ngOnInit(): void {
    //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
    //Add 'implements OnInit' to the class.
    this.getCruiseById(this.id);
    this.updateCruiseForm=this.fb.group({
      cruiseId:[null, Validators.required],
      name:[null, Validators.required],
      departurePort:[null, Validators.required],
      destination:[null, Validators.required],
      departureDate:[null, Validators.required],
      price:[null, Validators.required],
      availableRooms:[null, Validators.required]
    })
   }

   getCruiseById(id:string){
    this.isSpinning=true;
    this.adminService.getCruiseById(id).subscribe((res)=>{
      //console.log(res);
      this.isSpinning=false;
      const cruiseDto=res;
      this.existingImage='data:image/jpeg;base64,' + res.returnedImage;
      console.log(cruiseDto);
      console.log(this.existingImage);
      this.updateCruiseForm.patchValue(cruiseDto);
    })
   }

   onFileSelected(event:any){
    this.selectedFile = event.target.files[0];
    this.imgChanged=true;
    this.existingImage=null;
    const reader = new FileReader();
    reader.onload = () => {
      this.imagePreview = reader.result;
    };
    reader.readAsDataURL(this.selectedFile);
   }

   updateCruise(){
    this.isSpinning = true; // Start the spinner
      const formData: FormData= new FormData();
      if(this.imgChanged && this.selectedFile){
        formData.append('image', this.selectedFile);
      }
      //formData.append('image', this.selectedFile);
      formData.append('cruiseId', this.updateCruiseForm.get('cruiseId')?.value);
      formData.append('name', this.updateCruiseForm.get('name')?.value);
      formData.append('departurePort', this.updateCruiseForm.get('departurePort')?.value);
      formData.append('destination', this.updateCruiseForm.get('destination')?.value);
      // Format departureDate
    const departureDate = formatDate(this.updateCruiseForm.get('departureDate')?.value, 'yyyy-MM-dd', 'en-US');
    // Append formatted departureDate to form data
    formData.append('departureDate', departureDate);
      formData.append('price', this.updateCruiseForm.get('price')?.value);
      formData.append('availableRooms', this.updateCruiseForm.get('availableRooms')?.value);
      console.log(formData);
      this.adminService.updateCruise(this.id,formData).subscribe((res)=>{
        this.isSpinning = false; // Stop the spinner
        this.message.success("Cruise Updated Successfully",{nzDuration:5000});
        this.router.navigateByUrl("/admin/dashboard");
        console.log(res);
      }, error=>{
        this.message.error("Error while updating cruise",{nzDuration:5000});
        this.router.navigateByUrl("/admin/dashboard");
      })
   }
}
