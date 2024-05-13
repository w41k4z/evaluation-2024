import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientSideMenuComponent } from './client-side-menu.component';

describe('ClientSideMenuComponent', () => {
  let component: ClientSideMenuComponent;
  let fixture: ComponentFixture<ClientSideMenuComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ClientSideMenuComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ClientSideMenuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
