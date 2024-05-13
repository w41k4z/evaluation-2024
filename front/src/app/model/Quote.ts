import { FinitionType } from './FinitionType';
import { HouseType } from './HouseType';

export class Quote {
  id: number | null = null;
  date: Date | undefined;
  houseType: HouseType | undefined;
  finitionType: FinitionType | undefined;
  finitionTypeMajoration: number | undefined;
  constructionStartDate: Date | undefined;
  constructionEndDate: Date | undefined;
  totalPrice: number | undefined;
}
