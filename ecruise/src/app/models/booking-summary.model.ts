export interface BookingDetails{
  bookingId:string;
  cruiseId:string;
  email:string;
  amount:number;
  status:string;
  passengers:Passenger[];
  activities:Activity[];
  cruise:Cruise;
}

export interface Passenger {
  name: string;
  age: number;
}

export interface Activity{
  activityId:string;
  name:string;
  type:string;
  cruiseId:string;
  location:string;
  price:number;
}
export interface Cruise {
  id: string;
  cruiseId: string;
  name: string;
  departurePort: string;
  destination: string;
  departureDate: Date; // Assuming departure date is represented as a string
  price: number;
}
