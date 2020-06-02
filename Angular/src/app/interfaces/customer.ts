import {Person} from './person';
import {Address} from './address';

export interface Customer {
  address: Address;
  person: Person;
}
