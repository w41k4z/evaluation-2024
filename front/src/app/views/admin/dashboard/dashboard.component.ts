import { Component } from '@angular/core';
import { DashboardService } from '../../../service/dashboard/dashboard.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.scss',
})
export class DashboardComponent {
  loading$ = this.dashboardService.loading$;
  constructor(
    private dashboardService: DashboardService,
    private router: Router
  ) {}

  reinitialize() {
    this.dashboardService.reinitialize().subscribe(() => {
      alert('Database reinitialized');
    });
  }

  logout() {
    localStorage.removeItem('token');
    this.router.navigateByUrl('/page/admin/sign-in');
  }
}
