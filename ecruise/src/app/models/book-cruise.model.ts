export interface BookingRequest {
  email: string;
  cruiseId: string; // Assuming this is the cruise ID
  passengers: Passenger[];
}

export interface Passenger {
  name: string;
  age: number;
}


