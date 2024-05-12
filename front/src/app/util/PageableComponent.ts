export class PageableComponent {
  constructor(
    public currentPage: number,
    public totalPages: number,
    public filters: string[] = [],
    public filtersValue: string[] = []
  ) {}

  nextPage() {
    return this.currentPage + 1;
  }

  previousPage() {
    return this.currentPage - 1 < 1 ? this.currentPage : this.currentPage - 1;
  }
}
