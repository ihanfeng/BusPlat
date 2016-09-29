package uber;

public class Event {
	private String event;
	private int tripId;
	private float lat;
	private float lng;
	private int fare;

	public Event() {

	}

	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public int getTripId() {
		return tripId;
	}
	public void setTripId(int tripId) {
		this.tripId = tripId;
	}
	public float getLat() {
		return lat;
	}
	public void setLat(float lat) {
		this.lat = lat;
	}
	public float getLng() {
		return lng;
	}
	public void setLng(float lng) {
		this.lng = lng;
	}
	public int getFare() {
		return fare;
	}
	public void setFare(int fare) {
		this.fare = fare;
	}
	
	@Override
	public String toString() {
		return event + " " + tripId + " lng : " + lng + " lat : " + lat + " " + fare;
	}
}
