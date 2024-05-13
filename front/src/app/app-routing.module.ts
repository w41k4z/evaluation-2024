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
import { HomeComponent } from './views/home/home.component';
import { adminGuard } from './guards/admin.guard';
import { AdminLayoutComponent } from './layout/app-layout/admin-layout/admin-layout.component';
import { ClientLayoutComponent } from './layout/app-layout/client-layout/client-layout.component';
import { clientGuard } from './guards/client.guard';

const routes: Routes = [
  {
    path: 'app',
    component: AppLayoutComponent,
    canActivate: [authGuard],
    children: [
      {
        path: 'admin',
        component: AdminLayoutComponent,
        canActivate: [adminGuard],
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
        path: 'client',
        component: ClientLayoutComponent,
        canActivate: [clientGuard],
        children: [
          {
            path: 'home',
            component: HomeComponent,
          },
        ],
      },
    ],
  },
  {
    path: 'page',
    component: FullPageLayoutComponent,
    children: [
      {
        path: 'admin/sign-in',
        component: SignInComponent,
      },
      {
        path: 'log-in',
        component: SignUpComponent,
      },
    ],
  },
  { path: '', redirectTo: '/app/admin/dashboard', pathMatch: 'full' },
  { path: '**', component: PageNotFoundComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
