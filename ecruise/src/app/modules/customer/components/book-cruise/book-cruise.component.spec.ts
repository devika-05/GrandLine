import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BookCruiseComponent } from './book-cruise.component';

describe('BookCruiseComponent', () => {
  let component: BookCruiseComponent;
  let fixture: ComponentFixture<BookCruiseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BookCruiseComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(BookCruiseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
