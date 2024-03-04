import { HttpClientModule } from '@angular/common/http';
import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { EventLocationComponent } from './components/event-location/event-location.component';
import { EventLocationService } from './service/event-location.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, EventLocationComponent, HttpClientModule],
  providers: [EventLocationService],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'frontend';
}
