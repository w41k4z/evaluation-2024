import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-pagination',
  templateUrl: './pagination.component.html',
  styleUrl: './pagination.component.css',
})
export class PaginationComponent {
  @Input()
  public currentPage: number = 1;
  @Input()
  public totalPages: number = 1;
  @Input()
  public onNext: () => void = () => {
    return;
  };
  @Input()
  public onPrevious: () => void = () => {
    return;
  };
  @Input()
  public onPage: (pageNumber: number) => void = () => {
    return 1;
  };
  public pages: number[] = [];

  ngOnInit(): void {
    for (let index = 1; index <= this.totalPages; index++) {
      this.pages.push(index);
    }
  }
}
