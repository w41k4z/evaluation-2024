import { TestBed } from '@angular/core/testing';

import { NewQuoteService } from './new-quote.service';

describe('NewQuoteService', () => {
  let service: NewQuoteService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(NewQuoteService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
