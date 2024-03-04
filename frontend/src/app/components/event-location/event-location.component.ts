import { Component, OnInit } from '@angular/core';
import * as L from 'leaflet';
import { EventLocation } from '../../interfaces/eventLocation';
import { EventLocationService } from '../../service/event-location.service';

@Component({
  selector: 'app-event-location',
  standalone: true,
  imports: [],
  templateUrl: './event-location.component.html',
  styleUrl: './event-location.component.css'
})

export class EventLocationComponent implements OnInit {
  private map: any;
  
  constructor(private eventLocationService: EventLocationService) { }

  ngOnInit(): void {
    this.initMap();
    this.loadEventData();
  }

  private initMap(): void {
    const iconRetinaUrl = 'assets/images/leaflet/marker-icon-2x.png';
    const iconUrl = 'assets/images/leaflet/marker-icon.png';
    const shadowUrl = 'assets/images/leaflet/marker-shadow.png';
    const iconDefault = L.icon({
      iconRetinaUrl,
      iconUrl,
      shadowUrl,
      iconSize: [25, 41],
      iconAnchor: [12, 41],
      popupAnchor: [1, -34],
      tooltipAnchor: [16, -28],
      shadowSize: [41, 41]
    });
    L.Marker.prototype.options.icon = iconDefault;
    this.map = L.map('map', {
      center: [ 39.50, -98.35 ],
      zoom: 5
    });

    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      maxZoom: 19,
      attribution: '© OpenStreetMap contributors'
    }).addTo(this.map);
    L.control.scale().addTo(this.map); 
  }

  private loadEventData(): void {
    this.eventLocationService.getAllEvents().subscribe((eventLocations: EventLocation[]) => {
      const positions: L.LatLngLiteral[] = [];
      eventLocations.forEach(eventLocation => {
        const latLng: L.LatLngLiteral = { lat: eventLocation.latitude, lng: eventLocation.longitude };
        positions.push(latLng);
        const marker = L.marker([latLng.lat, latLng.lng]).addTo(this.map);
        
        marker.on('click', () => {
          this.displayEventDetails(eventLocation.event.id, marker);
        });
      });
      const polyline = L.polyline(positions as L.LatLngExpression[], { color: 'blue' }).addTo(this.map);
      this.map.fitBounds(polyline.getBounds());
    });
  }

  private displayEventDetails(eventId: string, marker: L.Marker): void {
    this.eventLocationService.getEventById(eventId).subscribe((eventDetails: EventLocation) => {
      console.log("eventDetails", eventDetails);
      const popupContent = `
        <div>
          <h4>${eventDetails.event.event_name}</h4>
          <p>Occurrence Time: ${eventDetails.event.occurrence_time}</p>
          <p>Severity: ${eventDetails.event.severity}</p>
          <p>Latitude: ${eventDetails.latitude}</p>
          <p>Longitude: ${eventDetails.longitude}</p>
        </div>
      `;
      marker.bindPopup(popupContent).openPopup();
    });
  }
  
}
