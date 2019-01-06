package eu.faredge.dda.processors.common.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

/**
 * This class represents serializers that convert Java objects to JSON chunks.
 *
 * @param <T> The type of the objects.
 */
public class JsonSerializer<T> implements Serializer<T> {

    /**
     * The object mapper.
     */
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Constructs a new JSON serializer.
     */
    public JsonSerializer() {
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.setDateFormat(new StdDateFormat().withColonInTimeZone(true));
    }

    /**
     * Configures this JSON serializer.
     */
    public void configure(Map<String, ?> props, boolean isKey) {
        // Nothing to configure.
    }

    /**
     * Converts the specified object into a JSON chunk for the specified topic.
     */
    public byte[] serialize(String topic, T data) {
        if (data == null) {
            return null;
        }
        try {
            return objectMapper.writeValueAsBytes(data);
        } catch (Exception e) {
            throw new SerializationException("Failed to serialize.", e);
        }
    }

    /**
     * Closes this JSON serializer.
     */
    public void close() {
        // Nothing to close.
    }
}