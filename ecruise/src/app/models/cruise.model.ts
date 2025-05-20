export interface Cruise {
  cruiseId: string;
  name: string;
  departurePort: string;
  destination: string;
  departureDate: Date; // Assuming departure date is represented as a string
  price: number;
}
