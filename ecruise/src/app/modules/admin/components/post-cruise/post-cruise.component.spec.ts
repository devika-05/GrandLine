import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PostCruiseComponent } from './post-cruise.component';

describe('PostCruiseComponent', () => {
  let component: PostCruiseComponent;
  let fixture: ComponentFixture<PostCruiseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PostCruiseComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PostCruiseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
