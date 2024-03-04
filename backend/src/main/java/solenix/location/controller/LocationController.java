package solenix.location.controller;

import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import solenix.location.modal.Event;
import solenix.location.modal.EventWithLocation;
import solenix.location.modal.Location;
import solenix.location.service.EventLocationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import solenix.location.util.JsonDataParser;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class LocationController {

    @Autowired
    private EventLocationService eventLocationService;
    @Autowired
    private JsonDataParser jsonDataParser;

    private List<Event> events;
    private List<Location> latitudes;
    private List<Location> longitudes;

    @PostConstruct
    public void init() {
        try {
            events = jsonDataParser.readEvents("data/events.json");
            latitudes = jsonDataParser.readLocations("data/latitudes.json");
            longitudes = jsonDataParser.readLocations("data/longitudes.json");
        } catch (IOException e) {
            throw new RuntimeException("Failed to read location data", e);
        }
    }

    /**
     * Retrieves all events with their corresponding locations.
     *
     * @return ResponseEntity containing a list of EventWithLocation objects
     */
    @GetMapping("/get-all-events")
    public ResponseEntity<List<EventWithLocation>> getAllEventsWithLocations() {
        List<EventWithLocation> combinedData = eventLocationService.getEventLocations(events, latitudes, longitudes);

        return ResponseEntity.ok(combinedData);
    }

    /**
     * Retrieves the location of a specific event.
     *
     * @param eventId The ID of the event
     * @return ResponseEntity containing the EventWithLocation object for the specified event
     */
    @GetMapping("/get-event-location/{eventId}")
    public ResponseEntity<EventWithLocation> getEventLocation(@PathVariable String eventId) {
        Optional<Event> particularEvent = events.stream()
                .filter(event -> event.getId().equals(eventId))
                .findFirst();

        if (particularEvent.isPresent()) {
            EventWithLocation eventLocation = eventLocationService.getSpecificEventLocation(particularEvent.get(), latitudes, longitudes);
            return ResponseEntity.ok(eventLocation);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
