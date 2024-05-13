import { Component } from '@angular/core';
import { HouseType } from '../../../model/HouseType';
import { FinitionType } from '../../../model/FinitionType';
import { NewQuoteService } from '../../../service/new-quote/new-quote.service';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-new-quote',
  templateUrl: './new-quote.component.html',
  styleUrl: './new-quote.component.scss',
})
export class NewQuoteComponent {
  newQuoteForm: FormGroup = new FormGroup({
    finitionTypeId: new FormControl('', Validators.required),
    constructionStartDate: new FormControl('', Validators.required),
  });
  houseTypes: HouseType[] = [];
  finitionTypes: FinitionType[] = [];
  selectedHouseType: HouseType | null = null;
  loading$ = this.newQuoteService.loading$;
  error$ = this.newQuoteService.error$;
  submitted = false;

  constructor(
    private newQuoteService: NewQuoteService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.newQuoteService
      .fetchHouseTypesAndFinitionTypes()
      .subscribe((response) => {
        this.houseTypes = response.houseTypes;
        this.finitionTypes = response.finitionTypes;
      });
  }

  chooseHouseType(index: number) {
    this.selectedHouseType = this.houseTypes[index];
  }

  submit() {
    this.submitted = true;
    if (this.newQuoteForm.invalid) {
      return;
    }
    let finitionType: FinitionType | null = null;
    this.finitionTypes.map((_) => {
      if (_.id == this.newQuoteForm.get('finitionTypeId')?.value) {
        finitionType = _;
      }
    });
    console.log(
      finitionType,
      this.selectedHouseType,
      this.newQuoteForm.get('constructionStartDate')?.value
    );
    this.newQuoteService
      .newQuote(
        finitionType,
        this.selectedHouseType,
        this.newQuoteForm.get('constructionStartDate')?.value
      )
      .subscribe(() => {
        // reset
        this.selectedHouseType = null;
        this.newQuoteForm.reset();
        this.submitted = false;
        this.router.navigate(['/app/client/quote-list']);
      });
  }
}
