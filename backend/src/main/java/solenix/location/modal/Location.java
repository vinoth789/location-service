package solenix.location.modal;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Location {
    private LocalDateTime timestamp;
    private double position;
}
