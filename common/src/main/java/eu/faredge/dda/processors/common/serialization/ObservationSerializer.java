package eu.faredge.dda.processors.common.serialization;

import eu.faredge.dda.processors.common.model.Observation;

/**
 * This class represents observation serializers, i.e. objects that convert observations to JSON chunks.
 *
 * @see Observation
 */
public class ObservationSerializer extends JsonSerializer<Observation> {

    /**
     * Constructs a new observation serializer.
     */
    public ObservationSerializer() {

    }
}
