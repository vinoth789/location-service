package solenix.location.modal;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Event {
    @JsonProperty("occurrence_time")
    private LocalDateTime occurrenceTime;
    @JsonProperty("event_name")
    private String eventName;
    private String id;
    private String severity;
}
