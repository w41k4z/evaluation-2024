import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppLayoutComponent } from './layout/app-layout/app-layout.component';
import { FullPageLayoutComponent } from './layout/full-page-layout/full-page-layout.component';
import { DashboardComponent } from './views/dashboard/dashboard.component';
import { TestTableComponent } from './views/test-table/test-table.component';
import { PageNotFoundComponent } from './views/page-not-found/page-not-found.component';
import { SignInComponent } from './views/sign-in/sign-in.component';
import { authGuard } from './guards/auth.guard';
import { SignUpComponent } from './views/sign-up/sign-up.component';

const routes: Routes = [
  {
    path: 'app',
    component: AppLayoutComponent,
    canActivate: [authGuard],
    children: [
      {
        path: 'dashboard',
        component: DashboardComponent,
      },
      {
        path: 'test',
        component: TestTableComponent,
      },
    ],
  },
  {
    path: 'page',
    component: FullPageLayoutComponent,
    children: [
      {
        path: 'sign-in',
        component: SignInComponent,
      },
      {
        path: 'sign-up',
        component: SignUpComponent,
      },
    ],
  },
  { path: '', redirectTo: '/app/dashboard', pathMatch: 'full' },
  { path: '**', component: PageNotFoundComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
