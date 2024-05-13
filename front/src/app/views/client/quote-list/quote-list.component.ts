import { Component } from '@angular/core';
import { Quote } from '../../../model/Quote';
import { QuoteService } from '../../../service/quote/quote.service';

@Component({
  selector: 'app-quote-list',
  templateUrl: './quote-list.component.html',
  styleUrl: './quote-list.component.scss',
})
export class QuoteListComponent {
  quotes: Quote[] = [];
  loading$ = this.quoteService.loading$;
  error$ = this.quoteService.error$;

  constructor(private quoteService: QuoteService) {}

  ngOnInit(): void {
    this.quoteService.fetchQuotes().subscribe((response) => {
      this.quotes = response;
    });
  }
}
