import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FullPageLayoutComponent } from './layout/full-page-layout/full-page-layout.component';
import { AppLayoutComponent } from './layout/app-layout/app-layout.component';
import { SideMenuComponent } from './layout/app-layout/side-menu/side-menu.component';
import { DashboardComponent } from './views/dashboard/dashboard.component';
import { PageNotFoundComponent } from './views/page-not-found/page-not-found.component';
import { RouterModule } from '@angular/router';
import { SignInComponent } from './views/sign-in/sign-in.component';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
    FullPageLayoutComponent,
    AppLayoutComponent,
    SideMenuComponent,
    DashboardComponent,
    PageNotFoundComponent,
    SignInComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    RouterModule,
    ReactiveFormsModule,
    HttpClientModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
