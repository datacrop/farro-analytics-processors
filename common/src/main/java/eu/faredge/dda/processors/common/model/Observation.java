package eu.faredge.dda.processors.common.model;

import java.util.Date;

/**
 * This class represents observations.
 */
public class Observation {

    /**
     * The ID of this observation.
     */
    private String id;

    /**
     * The ID of the edge gateway where this observation comes from.
     */
    private String edgeGatewayReferenceID;

    /**
     * The ID of the data source where this observation comes from.
     */
    private String dataSourceManifestReferenceID;

    /**
     * The ID of data kind that this observation is about.
     */
    private String dataKindReferenceID;

    /**
     * When this observation was collected.
     */
    private Date collectionTimestamp;

    /**
     * When this observation was received.
     */
    private Date acquisitionTimestamp;

    /**
     * Where this observation was made.
     */
    private Location location;

    /**
     * The value in this observation.
     */
    private Object value;

    /**
     * Constructs a new observation.
     */
    public Observation() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEdgeGatewayReferenceID() {
        return edgeGatewayReferenceID;
    }

    public void setEdgeGatewayReferenceID(String edgeGatewayReferenceID) {
        this.edgeGatewayReferenceID = edgeGatewayReferenceID;
    }

    public String getDataSourceManifestReferenceID() {
        return dataSourceManifestReferenceID;
    }

    public void setDataSourceManifestReferenceID(String dataSourceManifestReferenceID) {
        this.dataSourceManifestReferenceID = dataSourceManifestReferenceID;
    }

    public String getDataKindReferenceID() {
        return dataKindReferenceID;
    }

    public void setDataKindReferenceID(String dataKindReferenceID) {
        this.dataKindReferenceID = dataKindReferenceID;
    }

    public Date getCollectionTimestamp() {
        return collectionTimestamp;
    }

    public void setCollectionTimestamp(Date collectionTimestamp) {
        this.collectionTimestamp = collectionTimestamp;
    }

    public Date getAcquisitionTimestamp() {
        return acquisitionTimestamp;
    }

    public void setAcquisitionTimestamp(Date acquisitionTimestamp) {
        this.acquisitionTimestamp = acquisitionTimestamp;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    /**
     * Constructs a new observation as a clone of the specified one.
     *
     * @param observation the observation to clone.
     *
     * @return the clone.
     */
    public static final Observation from(final Observation observation) {
        final Observation clone = new Observation();
        clone.id = observation.id;
        clone.edgeGatewayReferenceID = observation.edgeGatewayReferenceID;
        clone.dataSourceManifestReferenceID = observation.dataSourceManifestReferenceID;
        clone.dataKindReferenceID = observation.dataKindReferenceID;
        if (observation.collectionTimestamp != null) {
            clone.collectionTimestamp = new Date(observation.collectionTimestamp.getTime());
        }
        if (observation.acquisitionTimestamp != null) {
            clone.acquisitionTimestamp = new Date(observation.acquisitionTimestamp.getTime());
        }
        if (observation.location != null) {
            clone.location = Location.from(observation.location);
        }
        // NOTE: Let's assume that the value is something immutable.
        clone.value = observation.value;
        return clone;
    }
}
