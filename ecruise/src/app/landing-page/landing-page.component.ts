import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { Observable } from 'rxjs/internal/Observable';
const BASIC_URL=["http://localhost:8080"];
@Component({
  selector: 'app-landing-page',
  standalone: true,
  imports: [FormsModule, CommonModule,ReactiveFormsModule,RouterModule],
  templateUrl: './landing-page.component.html',
  styleUrl: './landing-page.component.scss'
})
export class LandingPageComponent {
  cruise:any = [];
  constructor(private http:HttpClient) { }
  ngOnInit(): void {
    //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
    //Add 'implements OnInit' to the class.
    this.getAll();
  }

  getAllCruises():Observable<any>{
    return this.http.get(BASIC_URL + "/cruises/all", {
    })
  }


  getAll(){
    this.getAllCruises().subscribe({
      next: (res) => {
        if (res && res.length > 0) { // Check if res is defined and not empty
          //console.log(res);
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
}




