import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppLayoutComponent } from './layout/app-layout/app-layout.component';
import { FullPageLayoutComponent } from './layout/full-page-layout/full-page-layout.component';
import { DashboardComponent } from './views/dashboard/dashboard.component';
import { PageNotFoundComponent } from './views/page-not-found/page-not-found.component';
import { SignInComponent } from './views/sign-in/sign-in.component';
import { authGuard } from './guards/auth.guard';

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
