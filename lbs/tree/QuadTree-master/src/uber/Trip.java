package uber;

public class Trip {

	public enum EventType {
		BEGIN, UPDATE, END
	}

	EventType eventType;
	int tripId;
	int fare;
	long beginTimeStamp = 0;
	long endTimeStamp = 0;

	public int getTripId() {
		return tripId;
	}
	
	public int getFare() {
		return fare;
	}
	
	public long getBeginTimeStamp() {
		return beginTimeStamp;
	}
	
	public void setEndTimeStamp() {
		this.endTimeStamp = java.lang.System.currentTimeMillis();
	}
	
	public long getEndTimeStamp() {
		return this.endTimeStamp;
	}
	
	public EventType getEventType() {
		return eventType;
	}
	
	public void setEventType(EventType evenType) {
		this.eventType = eventType;
	}
	
	public void setFare(int fare) {
		this.fare = fare;
	}
	
	public Trip(int tripId) {
		this(tripId, EventType.UPDATE, 0);
	}

	public Trip(int tripId, EventType type) {
		this(tripId, type, 0);
		if (type.equals(EventType.END));
		QuadTree.updateTrip(this);
	}

	public Trip(int tripId, EventType type, int fare) {	
		this.tripId = tripId;
		this.eventType = type;
		this.fare = fare;
		this.beginTimeStamp = java.lang.System.currentTimeMillis();
	}
	
	@Override
	public String toString() {
		return tripId + " " + eventType.toString() + " " + fare + " " +
				beginTimeStamp + " " + endTimeStamp;
	} 

}