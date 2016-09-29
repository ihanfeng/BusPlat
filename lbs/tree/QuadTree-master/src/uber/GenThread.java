package uber;


public class GenThread implements Runnable {
	QuadTree qt;
	int tripId;
	Point point;

	private static float random(float min, float max) {
		return (float) (Math.random() * (max-min) + min);
	}
	
	public GenThread(QuadTree qt, int tripId, Point point) {
		this.qt = qt;
		this.tripId = tripId;
		this.point = point;
	}

	public void run() {
		float rnd = 0;
		float lngFact = 0;
		for (int i = 0; i < 100; i++) {
			rnd += random((float)-2, (float)2);
			lngFact = random(0, 2);
			Trip trip;
			if (i == 0) {
				trip = new Trip(tripId, Trip.EventType.BEGIN);
			} else if (i == 9) {
				trip = new Trip(tripId, Trip.EventType.END, (int) random(10,20));
			} else {
				trip = new Trip(tripId);
			}

			Point p = null;
				p = new Point((point.getLng() + rnd) * lngFact, point.getLat() + rnd, trip);
			try {
				qt.insert("{\"event\":\"" + trip.getEventType() + "\",\"tripId\":" + trip.getTripId() +
						",\"lng\":" + p.getLng() + ",\"lat\":" + p.getLat() + "}");
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}
}