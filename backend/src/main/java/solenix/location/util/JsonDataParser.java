package solenix.location.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.core.io.ResourceLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import solenix.location.modal.Event;
import solenix.location.modal.Location;

import java.io.IOException;
import java.util.List;

@Component
public class JsonDataParser {

    private final ObjectMapper objectMapper;
    private final ResourceLoader resourceLoader;

    @Autowired
    public JsonDataParser(ResourceLoader resourceLoader) {
        this.objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        this.resourceLoader = resourceLoader;
    }

    public List<Event> readEvents(String filePath) throws IOException {
        return objectMapper.readValue(resourceLoader.getResource("classpath:" + filePath).getInputStream(), new TypeReference<List<Event>>(){});
    }

    public List<Location> readLocations(String filePath) throws IOException {
        return objectMapper.readValue(resourceLoader.getResource("classpath:" + filePath).getInputStream(), new TypeReference<List<Location>>(){});
    }
}