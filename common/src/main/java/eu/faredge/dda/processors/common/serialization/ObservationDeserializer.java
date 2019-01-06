package eu.faredge.dda.processors.common.serialization;

import eu.faredge.dda.processors.common.model.Observation;

/**
 * This class represents observation deserializers, i.e. objects that convert JSON chunks to observations.
 *
 * @see Observation
 */
public class ObservationDeserializer extends JsonDeserializer<Observation> {

    /**
     * Constructs a new observation deserializer.
     */
    public ObservationDeserializer() {
        super(Observation.class);
    }
}
