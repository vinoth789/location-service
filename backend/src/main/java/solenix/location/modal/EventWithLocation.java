package solenix.location.modal;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class EventWithLocation {
    private Event event;
    private double latitude;
    private double longitude;

    public EventWithLocation(Event event, double latitude, double longitude) {
        this.event = event;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
