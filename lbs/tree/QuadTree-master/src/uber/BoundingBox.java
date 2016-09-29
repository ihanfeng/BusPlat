package uber;


public class BoundingBox {
	private Point center;
	private float radius;
	private Point nwPoint;
	private Point nePoint;
	private Point swPoint;
	private Point sePoint;
	public final static int CAPACITY = 100;

	/**
	 * Used when dividing a quadTree
	 */
	public BoundingBox(Point center, float radius) {
		this.center = center;
		this.radius = radius;
	}

	@Override
	public boolean equals(Object object) {
		BoundingBox boundingBox = (BoundingBox)object;
		return (center == null) ? boundingBox.nePoint.equals(this.nwPoint) &&
				boundingBox.nePoint.equals(this.nePoint) &&
				boundingBox.nePoint.equals(this.swPoint) &&
				boundingBox.nePoint.equals(this.sePoint) :
					this.center == boundingBox.center && this.radius == boundingBox.radius;
	}

	/**
	 * used to be associated with a trip
	 */
	public BoundingBox(Point p1, Point p2) {
		if (p1.getLng() < p2.getLng() && p1.getLat() < p2.getLat()) {
			swPoint = p1;
			nePoint = p2;
			nwPoint = new Point(swPoint.getLng(), nePoint.getLat());
			sePoint = new Point(nePoint.getLng(), swPoint.getLat());
		} else if (p1.getLng() > p2.getLng() && p1.getLat() > p2.getLat()) {
			swPoint = p2;
			nePoint = p1;
			nwPoint = new Point(swPoint.getLng(), nePoint.getLat());
			sePoint = new Point(nePoint.getLng(), swPoint.getLat());
		} else if (p1.getLng() < p2.getLng() && p1.getLat() > p2.getLat()) {
			nwPoint = p1;
			sePoint = p2;
			swPoint = new Point(nwPoint.getLng(), sePoint.getLat());
			nePoint = new Point(sePoint.getLng(), nwPoint.getLat());
		}else if (p1.getLng() > p2.getLng() && p1.getLat() < p2.getLat()) {
			nwPoint = p2;
			sePoint = p1;
			swPoint = new Point(nwPoint.getLng(), sePoint.getLat());
			nePoint = new Point(sePoint.getLng(), nwPoint.getLat());
		} if (p1.equals(p2)) {
			swPoint = p1;
			nePoint = p1;
			nwPoint = p1;
			sePoint = p1;
		}
		this.center = null;
		this.radius = 0;
	}

	public float getRadius() {
		return radius;
	}

	public Point getCenter() {
		return center;
	}

	public boolean contains(Point p) {
		if (center != null) {
			return p == null ? false :
				((p.getLng() <= (center.getLng() + radius)) && (p.getLng() >= (center.getLng() - radius))) &&
				((p.getLat() <= (center.getLat() + radius)) && (p.getLat() >= (center.getLat() - radius)));
		} else {
			return p == null ? false :
				( p.getLng() >= nwPoint.getLng() && p.getLng() <= nePoint.getLng() &&
				p.getLat() >= swPoint.getLat() && p.getLat() <= nePoint.getLat());
		}
	}

	public boolean intersect(BoundingBox b) {
		return (contains(b.nwPoint) || contains(b.nePoint) || contains(b.swPoint) || contains(b.sePoint) ||
				b.contains(nwPoint) || b.contains(nePoint) || b.contains(swPoint) || b.contains(sePoint));
	}

	@Override
	public String toString() {
		return (center != null) ? "center : " + center + " radius : " + radius :
			"\nnorthWest : " + nwPoint +
			"\nnorthEast : " + nePoint +
			"\nsouthWest : " + swPoint +
			"\nsouthEast : " + sePoint;
	}
}
