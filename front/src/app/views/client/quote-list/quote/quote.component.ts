import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { QuoteService } from '../../../../service/quote/quote.service';
import { QuoteDetail } from '../../../../model/QuoteDetail';

declare let html2pdf: any;

@Component({
  selector: 'app-quote',
  templateUrl: './quote.component.html',
  styleUrl: './quote.component.scss',
})
export class QuoteComponent {
  quoteId: string | null = null;
  quoteDetails: QuoteDetail[] = [];
  works: any[] = [];
  loading$ = this.quoteService.loading$;
  error$ = this.quoteService.error$;
  constructor(
    private quoteService: QuoteService,
    private route: ActivatedRoute
  ) {}

  groupByWorkName() {
    return this.quoteDetails.reduce(
      (
        groups: Record<string, QuoteDetail[]>,
        element: QuoteDetail
      ): Record<string, QuoteDetail[]> => {
        const key: string = element.workName ? element.workName : ''; // Assuming workName is the property you want to group by
        if (!groups[key]) {
          groups[key] = [];
        }
        groups[key].push(element);
        return groups;
      },
      {}
    );
  }

  ngOnInit(): void {
    this.quoteId = this.route.snapshot.paramMap.get('quoteId');
    this.quoteService.fetchQuote(this.quoteId).subscribe((response) => {
      this.quoteDetails = response;
      console.log(this.groupByWorkName());
    });
  }

  getSum(work: any[]) {
    let s = 0;
    for (let each of work) {
      s += each.unitPrice * each.quantity;
    }
    return s;
  }

  exportAsPDF(divId: string) {
    let data = document.getElementById(divId);
    if (data) {
      const options = {
        filename: 'Devis.pdf',
        margin: 1,
        image: { type: 'jpeg', quality: 0.98 },
        html2canvas: { scale: 2 },
        jsPDF: { unit: 'in', format: 'letter', orientation: 'landscape' },
      };

      html2pdf().set(options).from(data).save();
    }
  }
}
