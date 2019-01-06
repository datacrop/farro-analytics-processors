package eu.faredge.dda.processors.common.model;

/**
 * This class represents locations.
 */
public class Location implements Cloneable {

    /**
     * The geographical location that this location represents.
     */
    private GeoLocation geoLocation;

    /**
     * The virtual location that this location represents.
     */
    private String virtualLocation;

    /**
     * Constructs a new location.
     */
    public Location() {

    }

    public GeoLocation getGeoLocation() {
        return geoLocation;
    }

    public void setGeoLocation(GeoLocation geoLocation) {
        this.geoLocation = geoLocation;
        if (geoLocation != null) {
            this.virtualLocation = null;
        }
    }

    public String getVirtualLocation() {
        return virtualLocation;
    }

    public void setVirtualLocation(String virtualLocation) {
        this.virtualLocation = virtualLocation;
        if (virtualLocation != null) {
            this.geoLocation = null;
        }
    }

    /**
     * Constructs a new location as a clone of the specified one.
     *
     * @param location the location to clone.
     *
     * @return the clone.
     */
    public static final Location from(Location location) {
        final Location clone = new Location();
        if (location.geoLocation != null) {
            clone.geoLocation = GeoLocation.from(location.geoLocation);
        }
        clone.virtualLocation = location.virtualLocation;
        return clone;
    }

    /**
     * This class represents geographical locations.
     */
    public static class GeoLocation {

        /**
         * The latitude of this geographical location.
         */
        private double latitude;

        /**
         * The longitude of this geographical location.
         */
        private double longitude;

        /**
         * Constructs a new geographical location.
         */
        public GeoLocation() {

        }

        /**
         * Constructs a new geographical location with the specified coordinates.
         *
         * @param latitude  the latitude of the new geographical location.
         * @param longitude the longitude of the new geographical location.
         */
        public GeoLocation(double latitude, double longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        /**
         * Constructs a new geographical location as a clone of the specified one.
         *
         * @param geoLocation the geographical location to clone.
         *
         * @return the clone.
         */
        public static final GeoLocation from(GeoLocation geoLocation) {
            return new GeoLocation(geoLocation.latitude, geoLocation.longitude);
        }
    }
}
