package eu.faredge.dda.processors.common.serialization;

import eu.faredge.dda.processors.common.model.Observation;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

/**
 * Factory for creating serializers / deserializers.
 * <p>
 * This factory extends {@link org.apache.kafka.common.serialization.Serdes}.
 */
public class Serdes extends org.apache.kafka.common.serialization.Serdes {

    /**
     * Gets a serde that converts observations to and from JSON chunks.
     *
     * @return the serde.
     */
    public static Serde<Observation> Observation() {
        return new ObservationSerde();
    }

    /**
     * Gets a serde for the specified type.
     *
     * @param type the type to get a serde for.
     *
     * @return the serde.
     */
    @SuppressWarnings("unchecked")
    public static <T> Serde<T> serdeFrom(Class<T> type) {
        if (Observation.class.isAssignableFrom(type)) {
            return (Serde<T>) Observation();
        }
        return org.apache.kafka.common.serialization.Serdes.serdeFrom(type);
    }

    /**
     * Serializers / deserializers that convert observations to and from JSON chunks.
     */
    public static final class ObservationSerde implements Serde<Observation> {

        /**
         * The underlying serializer.
         */
        private final Serializer<Observation> serializer = new ObservationSerializer();

        /**
         * The underlying deserializer.
         */
        private final Deserializer<Observation> deserializer = new ObservationDeserializer();

        /**
         * Configures the underlying serializer and deserializer.
         */
        public void configure(Map<String, ?> configs, boolean isKey) {
            this.serializer.configure(configs, isKey);
            this.deserializer.configure(configs, isKey);
        }

        /**
         * Closes the underlying serializer and deserializer.
         */
        public void close() {
            this.serializer.close();
            this.deserializer.close();
        }

        /**
         * Gets the underlying serializer.
         */
        public Serializer<Observation> serializer() {
            return serializer;
        }

        /**
         * Gets the underlying deserializer.
         */
        public Deserializer<Observation> deserializer() {
            return deserializer;
        }
    }
}
