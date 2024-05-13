import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FullPageLayoutComponent } from './layout/full-page-layout/full-page-layout.component';
import { AppLayoutComponent } from './layout/app-layout/app-layout.component';
import { SideMenuComponent } from './layout/app-layout/admin-layout/side-menu/side-menu.component';
import { DashboardComponent } from './views/admin/dashboard/dashboard.component';
import { PageNotFoundComponent } from './views/page-not-found/page-not-found.component';
import { RouterModule } from '@angular/router';
import { SignInComponent } from './views/sign-in/sign-in.component';
import { ReactiveFormsModule } from '@angular/forms';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { TestTableComponent } from './views/test-table/test-table.component';
import { TestDialogComponent } from './views/test-table/test-dialog/test-dialog.component';
import { DeleteDialogComponent } from './components/delete-dialog/delete-dialog.component';
import { PaginationComponent } from './components/pagination/pagination.component';
import { TokenInterceptor } from './interceptor/TokenInterceptor';
import { FiltersComponent } from './views/test-table/filters/filters.component';
import { SignUpComponent } from './views/sign-up/sign-up.component';
import { ClientSideMenuComponent } from './layout/app-layout/client-layout/client-side-menu/client-side-menu.component';
import { HomeComponent } from './views/client/home/home.component';
import { AdminLayoutComponent } from './layout/app-layout/admin-layout/admin-layout.component';
import { ClientLayoutComponent } from './layout/app-layout/client-layout/client-layout.component';
import { NewQuoteComponent } from './views/client/new-quote/new-quote.component';

@NgModule({
  declarations: [
    AppComponent,
    FullPageLayoutComponent,
    AppLayoutComponent,
    SideMenuComponent,
    DashboardComponent,
    PageNotFoundComponent,
    SignInComponent,
    TestTableComponent,
    TestDialogComponent,
    DeleteDialogComponent,
    PaginationComponent,
    FiltersComponent,
    SignUpComponent,
    ClientSideMenuComponent,
    HomeComponent,
    AdminLayoutComponent,
    ClientLayoutComponent,
    NewQuoteComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    RouterModule,
    ReactiveFormsModule,
    HttpClientModule,
  ],
  providers: [
    provideAnimationsAsync(),
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true,
    },
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
