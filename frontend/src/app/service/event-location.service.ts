import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import { EventLocation } from '../interfaces/eventLocation';
import { EventDetails } from '../interfaces/eventDetails';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class EventLocationService {

  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient) { }

  getAllEvents(): Observable<EventLocation[]> {
    return this.http.get<EventLocation[]>(`${this.apiUrl}/get-all-events`);
  }
  getEventById(eventId: string): Observable<EventLocation> {
    return this.http.get<EventLocation>(`${this.apiUrl}/get-event-location/${eventId}`);
  }
}
