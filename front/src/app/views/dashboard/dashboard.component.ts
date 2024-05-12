import { Component } from '@angular/core';
import { DashboardService } from '../../service/dashboard/dashboard.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.scss',
})
export class DashboardComponent {
  loading$ = this.dashboardService.loading$;
  constructor(private dashboardService: DashboardService) {}

  reinitialize() {
    this.dashboardService.reinitialize().subscribe(() => {
      alert('Database reinitialized');
    });
  }
}
