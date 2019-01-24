import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BrowseproductComponent } from './browseproduct.component';

describe('BrowseproductComponent', () => {
  let component: BrowseproductComponent;
  let fixture: ComponentFixture<BrowseproductComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BrowseproductComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BrowseproductComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
