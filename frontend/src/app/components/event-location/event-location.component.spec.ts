import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EventLocationComponent } from './event-location.component';

describe('EventLocationComponent', () => {
  let component: EventLocationComponent;
  let fixture: ComponentFixture<EventLocationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EventLocationComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(EventLocationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
