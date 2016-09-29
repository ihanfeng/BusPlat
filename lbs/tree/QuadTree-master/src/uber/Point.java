package uber;


public class Point {
	/**
	 * -180 to 180
	 */
	private float lng;
	/**
	 * -90 to 90
	 */
	private float lat;
	private Trip trip;

	public float getLng() {
		return lng;
	}

	public float getLat() {
		return lat;
	}

	public Trip getTrip() {
		return trip;
	}

	public Point(float lng, float lat) {
		this(lng, lat, null, false);
	}
	
	public Point(float lng, float lat, Trip trip) {
		this(lng, lat, trip, true);
	}
	
	private Point(float lng, float lat, Trip trip, boolean tripPoint) {
		if (tripPoint) {
			if (lng < -180 || lng > 180) {
				System.err.println("longitude must be between -180 and 180");
				lng = lng < -180 ? -180 : 180;
			}
			if (lat < -90 || lat > 90) {
				System.err.println("latitude must be between -90 and 90");
				lat = lat < -90 ? -90 : 90;
			}
		}
		this.lng = lng;
		this.lat = lat;
		this.trip = trip;
		QuadTree.updateTrip(trip);
	}
	
	@Override
	public boolean equals(Object obj) {
		Point pt = (Point)obj;
		return (this.getLng() == pt.getLng() && this.getLat() == pt.getLat());
	}
	
	@Override
	public String toString() {
		return trip != null ? "trip Id :" + trip.getTripId() +  " lng : " + lng + " lat : " + lat :
			"lng : " + lng + " lat : " + lat;
	}
}
