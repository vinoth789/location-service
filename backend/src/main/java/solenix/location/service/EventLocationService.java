package solenix.location.service;

import solenix.location.modal.Event;
import solenix.location.modal.EventWithLocation;
import solenix.location.modal.Location;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventLocationService {
    /**
     * Retrieves a list of events with their corresponding locations.
     *
     * @param events     List of events
     * @param latitudes  List of latitude locations
     * @param longitudes List of longitude locations
     * @return List of EventWithLocation objects
     */
    public List<EventWithLocation> getEventLocations(List<Event> events, List<Location> latitudes, List<Location> longitudes) {
        return events.stream()
                .map(event -> createEventWithLocation(event, latitudes, longitudes))
                .collect(Collectors.toList());
    }
    /**
     * Retrieves the location of a specific event.
     *
     * @param event      The event
     * @param latitudes  List of latitude locations
     * @param longitudes List of longitude locations
     * @return EventWithLocation object for the specified event
     */
    public EventWithLocation getSpecificEventLocation(Event event, List<Location> latitudes, List<Location> longitudes) {
        return createEventWithLocation(event, latitudes, longitudes);
    }
    private EventWithLocation createEventWithLocation(Event event, List<Location> latitudes, List<Location> longitudes) {
        LocalDateTime closestLatTime = findClosestTime(event.getOccurrenceTime(), latitudes);
        LocalDateTime closestLonTime = findClosestTime(event.getOccurrenceTime(), longitudes);

        double latitude = latitudes.stream().filter(lat -> lat.getTimestamp().equals(closestLatTime)).findFirst().map(Location::getPosition).orElse(0.0);
        double longitude = longitudes.stream().filter(lon -> lon.getTimestamp().equals(closestLonTime)).findFirst().map(Location::getPosition).orElse(0.0);

        return new EventWithLocation(event, latitude, longitude);
    }

    private LocalDateTime findClosestTime(LocalDateTime targetTime, List<Location> positions) {
            return positions.stream()
            .min(Comparator.comparingLong(position -> Math.abs(Duration.between(targetTime, position.getTimestamp()).toMillis())))
            .map(Location::getTimestamp)
            .orElse(null);
    }
}