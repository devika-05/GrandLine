import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateCruiseComponent } from './update-cruise.component';

describe('UpdateCruiseComponent', () => {
  let component: UpdateCruiseComponent;
  let fixture: ComponentFixture<UpdateCruiseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UpdateCruiseComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(UpdateCruiseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
