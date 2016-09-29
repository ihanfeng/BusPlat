package uber;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import com.google.gson.Gson;

public class QuadTree {
	/**
	 *  grouped for timestamp retrieval
	 *  one instance for the main quadtree
	 */
	private static final HashMap<Integer, Trip> trips = new HashMap<Integer, Trip>();

	public static void updateTrip(Trip trip) {
		if (trip == null) {
			return;
		}
		if (trips.containsKey(trip.getTripId()) && trip.getEventType().equals(Trip.EventType.END)) {
			Trip t = trips.get(trip.getTripId());
			t.setEndTimeStamp();
			t.setEventType(Trip.EventType.END);
			t.setFare(trip.getFare());
		} else if (trip.getEventType().equals(Trip.EventType.BEGIN)) {
			trips.put(trip.getTripId(), trip);
		}
	}
	
	public static int getNbTrips() {
		return trips.size();
	}

	public int howManyTripsOccuringAt(long timeStamp) {
		Collection<Trip> set = trips.values();
		int nbTrips = 0;
		for (Trip trip : set) {
			if (trip.getBeginTimeStamp() <= timeStamp && trip.getEndTimeStamp() >= timeStamp) {
				nbTrips++;
			}
		}
		return nbTrips;
	}


	private BoundingBox boundingBox;
	private Point[] points = new Point[BoundingBox.CAPACITY];

	private QuadTree northWest = null;
	private QuadTree northEast = null;
	private QuadTree southWest = null;
	private QuadTree southEast = null;

	private QuadTree(BoundingBox boundingBox) {
		this.boundingBox = boundingBox;
	}

	public QuadTree() {
		// maximum longitude is 90 but in order to include latitude length we'll have a box of 180 by 180
		this.boundingBox = new BoundingBox(new Point((float) 0,(float) 0), (float) 180);
	}

	private boolean isLeaf() {
		return (northWest == null);
	}

	private boolean insertPoint(Point p) {
		int i = 0;


		for (; i < BoundingBox.CAPACITY && points[i] != null; i++) {
		}
		if (i < BoundingBox.CAPACITY) {
			points[i] = p;
			return true;
		}
		return false;
	}
	
	/**
	 * main method for inserting an event from a JSon message
	 */
	public boolean insert(String jsonMessage) {
		Gson gson = new Gson();
		Event event = gson.fromJson(jsonMessage, Event.class);
		Trip trip = new Trip(event.getTripId(), Trip.EventType.valueOf(event.getEvent()), event.getFare());
		Point point = new Point(event.getLng(), event.getLat(), trip);
		return insert(point);
	}

	private synchronized boolean insert(Point p) {
		if (!boundingBox.contains(p)) {
			return false;
		}
		if (insertPoint(p)) {
			return true;
		}
		subdivide();

		if (northWest.insert(p)) {
			return true;
		}
		if (northEast.insert(p)) {
			return true;
		}
		if (southWest.insert(p)) {
			return true;
		}
		if (southEast.insert(p)) {
			return true;
		}
		return false;
	}

	public boolean subdivide() {
		if (!isLeaf()) {
			return false;
		}
		float halfRadius = (float) (boundingBox.getRadius() / 2);
		northWest = new QuadTree(new BoundingBox(
				new Point((float)(boundingBox.getCenter().getLng() - halfRadius),
						(float)(boundingBox.getCenter().getLat() + halfRadius)),
						halfRadius));
		northEast = new QuadTree(new BoundingBox(
				new Point((float)(boundingBox.getCenter().getLng() + halfRadius),
						(float)(boundingBox.getCenter().getLat() + halfRadius)),
						halfRadius));
		southWest = new QuadTree(new BoundingBox(
				new Point((float)(boundingBox.getCenter().getLng() - halfRadius),
						(float)(boundingBox.getCenter().getLat() - halfRadius)), halfRadius));
		southEast = new QuadTree(new BoundingBox(
				new Point((float)(boundingBox.getCenter().getLng() + halfRadius),
						(float)(boundingBox.getCenter().getLat() - halfRadius)), halfRadius));

		return true;
	}

	public int howManyTripsPassedThrough(BoundingBox boundingBox) {
		ArrayList<Point> points = queryRange(boundingBox);
		Set<Integer> tripIds = new HashSet<Integer>();
		for (Point point : points) {
			tripIds.add(point.getTrip().getTripId());
		}
		return tripIds.size();
	}

	/**
	 * returns an array with
	 * 0 : number of trips who strated or stopped
	 * 1 : total cost of the trips
	 */
	public int[] howManyTripsStartedOrStoppedThrough(BoundingBox boundingBox) {
		ArrayList<Point> points = queryRange(boundingBox);
		Set<Integer> tripIds = new HashSet<Integer>();
		int[] res = new int[2];
		int nbTrips = 0;
		for (Point point : points) {
			if (point.getTrip().getEventType().equals(Trip.EventType.BEGIN) ||
					point.getTrip().getEventType().equals(Trip.EventType.END)) {
				if (!tripIds.contains(point.getTrip().getTripId())) {
					res[0]++;
				}
				tripIds.add(point.getTrip().getTripId());
				res[1] += point.getTrip().getFare();
			}
		}
		return res;
	}

	private ArrayList<Point> queryRange(BoundingBox boundingBox) {
		ArrayList<Point> points = new ArrayList<Point>();

		if (!this.boundingBox.intersect(boundingBox)) {
			return points;
		}
		for (Point p : this.points) {
			if (boundingBox.contains(p)) {
				points.add(p);
			}
		}

		if (northWest != null) {
			points.addAll(northWest.queryRange(boundingBox));
		}
		if (northEast != null) {
			points.addAll(northEast.queryRange(boundingBox));
		}
		if (southWest != null) {
			points.addAll(southWest.queryRange(boundingBox));
		}
		if (southEast != null) {
			points.addAll(southEast.queryRange(boundingBox));
		}

		return points;
	}

	private String printPoints() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < points.length; i++) {
			sb.append(points[i]).append(" ");
		}
		return sb.toString();
	}

	@Override
	public String toString() {
		return "bounding box : " + boundingBox +
				"\n points : " + printPoints() +
				"\n nw box : " + northWest +
				"\n ne box : " + northEast +
				"\n sw box : " + southWest + 
				"\n se box : " + southEast;
	}

}
