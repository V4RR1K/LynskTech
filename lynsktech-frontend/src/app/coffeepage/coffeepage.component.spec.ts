import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CoffeepageComponent } from './coffeepage.component';

describe('CoffeepageComponent', () => {
  let component: CoffeepageComponent;
  let fixture: ComponentFixture<CoffeepageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CoffeepageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CoffeepageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
