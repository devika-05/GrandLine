export interface reservations{
  reservationId:string;
  cruiseId:string;
  status:string;
  amount:number;
  totalPaid:number;
  passengers:Passenger[];
  activities:Activity[];
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
