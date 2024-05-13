export class HouseType {
  id: number | null = null;
  name: string | undefined;
  duration: number | undefined;
  totalPrice: number | undefined;
  houseTypeDetails:
    | {
        quantity: number;
        name: string;
      }[]
    | undefined;
}
