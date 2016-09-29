package uber;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

public class Tests {

	private static QuadTree qt;
	private static BoundingBox box1;
	private static BoundingBox box2;
	private static BoundingBox box3;
	private static long timeStamp1;
	private static long timeStamp2;

	static {
		try {
			qt = new QuadTree();
			initTestCaseJson();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void initTestCaseJson() {
		qt.insert("{\"event\":\"BEGIN\",\"tripId\":1,\"lng\":-2.0,\"lat\":-1.0}");
		qt.insert("{\"event\":\"BEGIN\",\"tripId\":2,\"lng\":-2.0,\"lat\":-4.0}");
		qt.insert("{\"event\":\"UPDATE\",\"tripId\":1,\"lng\":-1.0,\"lat\":0.0}");
		qt.insert("{\"event\":\"UPDATE\",\"tripId\":1,\"lng\":-1.0,\"lat\":1.0}");
		qt.insert("{\"event\":\"UPDATE\",\"tripId\":1,\"lng\":-2.0,\"lat\":2.0}");
		timeStamp1 = java.lang.System.currentTimeMillis();
		qt.insert("{\"event\":\"UPDATE\",\"tripId\":1,\"lng\":-2.0,\"lat\":3.0}");
		qt.insert("{\"event\":\"UPDATE\",\"tripId\":1,\"lng\":-2.0,\"lat\":4.0}");
		qt.insert("{\"event\":\"UPDATE\",\"tripId\":1,\"lng\":-3.0,\"lat\":4.0}");
		qt.insert("{\"event\":\"UPDATE\",\"tripId\":1,\"lng\":-4.0,\"lat\":4.0}");
		qt.insert("{\"event\":\"END\",\"tripId\":1,\"lng\":-5.0,\"lat\":4.0,\"fare\":10}");

		
		qt.insert("{\"event\":\"UPDATE\",\"tripId\":2,\"lng\":-1.0,\"lat\":-4.0}");
		qt.insert("{\"event\":\"UPDATE\",\"tripId\":2,\"lng\":0.0,\"lat\":-4.0}");
		qt.insert("{\"event\":\"UPDATE\",\"tripId\":2,\"lng\":0.0,\"lat\":-3.0}");
		qt.insert("{\"event\":\"UPDATE\",\"tripId\":2,\"lng\":0.0,\"lat\":-2.0}");
		qt.insert("{\"event\":\"UPDATE\",\"tripId\":2,\"lng\":1.0,\"lat\":-1.0}");
		qt.insert("{\"event\":\"UPDATE\",\"tripId\":2,\"lng\":2.0,\"lat\":0.0}");
		qt.insert("{\"event\":\"UPDATE\",\"tripId\":2,\"lng\":2.0,\"lat\":1.0}");
		qt.insert("{\"event\":\"UPDATE\",\"tripId\":2,\"lng\":3.0,\"lat\":1.0}");
		qt.insert("{\"event\":\"UPDATE\",\"tripId\":2,\"lng\":4.0,\"lat\":1.0}");
		qt.insert("{\"event\":\"UPDATE\",\"tripId\":2,\"lng\":4.0,\"lat\":2.0}");
		qt.insert("{\"event\":\"END\",\"tripId\":2,\"lng\":5.0,\"lat\":2.0,\"fare\":5}");

		qt.insert("{\"event\":\"BEGIN\",\"tripId\":3,\"lng\":4.0,\"lat\":-5.0}");
		qt.insert("{\"event\":\"UPDATE\",\"tripId\":3,\"lng\":4.0,\"lat\":-4.0}");
		qt.insert("{\"event\":\"UPDATE\",\"tripId\":3,\"lng\":4.0,\"lat\":-3.0}");
		qt.insert("{\"event\":\"UPDATE\",\"tripId\":3,\"lng\":4.0,\"lat\":-2.0}");
		qt.insert("{\"event\":\"UPDATE\",\"tripId\":3,\"lng\":4.0,\"lat\":-1.0}");
		qt.insert("{\"event\":\"UPDATE\",\"tripId\":3,\"lng\":3.0,\"lat\":-1.0}");
		qt.insert("{\"event\":\"END\",\"tripId\":3,\"lng\":2.0,\"lat\":-1.0,\"fare\":12}");

		qt.insert("{\"event\":\"BEGIN\",\"tripId\":4,\"lng\":-6.0,\"lat\":-4.0}");
		qt.insert("{\"event\":\"UPDATE\",\"tripId\":4,\"lng\":-5.0,\"lat\":-3.0}");
		qt.insert("{\"event\":\"UPDATE\",\"tripId\":4,\"lng\":-5.0,\"lat\":-2.0}");
		qt.insert("{\"event\":\"UPDATE\",\"tripId\":4,\"lng\":-6.0,\"lat\":-2.0}");
		qt.insert("{\"event\":\"UPDATE\",\"tripId\":4,\"lng\":-7.0,\"lat\":-2.0}");
		qt.insert("{\"event\":\"UPDATE\",\"tripId\":4,\"lng\":-6.0,\"lat\":-1.0}");
		timeStamp2 = java.lang.System.currentTimeMillis();
		qt.insert("{\"event\":\"UPDATE\",\"tripId\":4,\"lng\":-6.0,\"lat\":0.0}");
		qt.insert("{\"event\":\"UPDATE\",\"tripId\":4,\"lng\":-6.0,\"lat\":1.0}");
		qt.insert("{\"event\":\"UPDATE\",\"tripId\":4,\"lng\":-6.0,\"lat\":2.0}");
		qt.insert("{\"event\":\"UPDATE\",\"tripId\":4,\"lng\":-7.0,\"lat\":2.0}");
		qt.insert("{\"event\":\"END\",\"tripId\":4,\"lng\":-7.0,\"lat\":3.0,\"fare\":13}");

		qt.insert("{\"event\":\"BEGIN\",\"tripId\":5,\"lng\":0.0,\"lat\":1.0}");
		qt.insert("{\"event\":\"UPDATE\",\"tripId\":5,\"lng\":0.0,\"lat\":2.0}");
		qt.insert("{\"event\":\"UPDATE\",\"tripId\":5,\"lng\":0.0,\"lat\":3.0}");
		qt.insert("{\"event\":\"UPDATE\",\"tripId\":5,\"lng\":0.0,\"lat\":4.0}");
		qt.insert("{\"event\":\"UPDATE\",\"tripId\":5,\"lng\":1.0,\"lat\":4.0}");
		qt.insert("{\"event\":\"UPDATE\",\"tripId\":5,\"lng\":1.0,\"lat\":3.0}");
		qt.insert("{\"event\":\"UPDATE\",\"tripId\":5,\"lng\":1.0,\"lat\":2.0}");
		qt.insert("{\"event\":\"END\",\"tripId\":5,\"lng\":1.0,\"lat\":1.0,\"fare\":6}");

		qt.insert("{\"event\":\"BEGIN\",\"tripId\":6,\"lng\":2.0,\"lat\":-5.0}");
		qt.insert("{\"event\":\"UPDATE\",\"tripId\":6,\"lng\":3.0,\"lat\":-4.0}");
		qt.insert("{\"event\":\"UPDATE\",\"tripId\":6,\"lng\":4.0,\"lat\":-4.0}");
		qt.insert("{\"event\":\"UPDATE\",\"tripId\":6,\"lng\":5.0,\"lat\":-3.0}");
		qt.insert("{\"event\":\"END\",\"tripId\":6,\"lng\":6.0,\"lat\":-2.0,\"fare\":4}");

		qt.insert("{\"event\":\"BEGIN\",\"tripId\":7,\"lng\":-8.0,\"lat\":-5.0}");
		qt.insert("{\"event\":\"UPDATE\",\"tripId\":7,\"lng\":-7.0,\"lat\":-5.0}");
		qt.insert("{\"event\":\"UPDATE\",\"tripId\":7,\"lng\":-6.0,\"lat\":-5.0}");
		qt.insert("{\"event\":\"UPDATE\",\"tripId\":7,\"lng\":-5.0,\"lat\":-5.0}");
		qt.insert("{\"event\":\"UPDATE\",\"tripId\":7,\"lng\":-4.0,\"lat\":-5.0}");
		qt.insert("{\"event\":\"UPDATE\",\"tripId\":7,\"lng\":-3.0,\"lat\":-5.0}");
		qt.insert("{\"event\":\"UPDATE\",\"tripId\":7,\"lng\":-2.0,\"lat\":-5.0}");
		qt.insert("{\"event\":\"END\",\"tripId\":7,\"lng\":-1.0,\"lat\":-5.0,\"fare\":8}");

		box1 = new BoundingBox(new Point(-3, -2), new Point(3, 2));
		box2 = new BoundingBox(new Point(3, -7), new Point(5, -3));
		box3 = new BoundingBox(new Point(-7, 2), new Point(-2, 7));

	}

	@Test
	/**
	 * QUESTION1
	 */
	public void testHowManyTripsPassedThroughRect1() {
		assertEquals(4, qt.howManyTripsPassedThrough(box1));
	}
	@Test
	/**
	 * QUESTION1
	 */
	public void testHowManyTripsPassedThroughRect2() {
		assertEquals(2, qt.howManyTripsPassedThrough(box2));
	}
	@Test
	/**
	 * QUESTION1
	 */
	public void testHowManyTripsPassedThroughRect3() {
		assertEquals(2, qt.howManyTripsPassedThrough(box3));
	}
	@Test
	/**
	 * QUESTION2
	 */
	public void testHowManyTripsStartedOrStoppedThroughRect1() {
		assertEquals(3, qt.howManyTripsStartedOrStoppedThrough(box1)[0]);
	}
	@Test
	/**
	 * QUESTION2
	 */
	public void testHowManyTripsStartedOrStoppedThroughRect1Fare() {
		assertEquals(34, qt.howManyTripsStartedOrStoppedThrough(box1)[1]);
	}
	@Test
	/**
	 * QUESTION2
	 */
	public void testHowManyTripsStartedOrStoppedThroughRect2() {
		assertEquals(1, qt.howManyTripsStartedOrStoppedThrough(box2)[0]);
	}
	@Test
	/**
	 * QUESTION2
	 */
	public void testHowManyTripsStartedOrStoppedThroughRect2Fare() {
		assertEquals(12, qt.howManyTripsStartedOrStoppedThrough(box2)[1]);
	}
	@Test
	/**
	 * QUESTION2
	 */
	public void testHowManyTripsStartedOrStoppedThroughRect3() {
		assertEquals(2, qt.howManyTripsStartedOrStoppedThrough(box3)[0]);
	}
	@Test
	/**
	 * QUESTION2
	 */
	public void testHowManyTripsStartedOrStoppedThroughRect3Fare() {
		assertEquals(23, qt.howManyTripsStartedOrStoppedThrough(box3)[1]);
	}
	
	@Test
	/**
	 * QUESTION 3
	 */
	public void testHowMAnyTripsOccuring1() {
		assertEquals(2, qt.howManyTripsOccuringAt(timeStamp1));
	}
	
	@Test
	/**
	 * QUESTION 3
	 */
	public void testHowMAnyTripsOccuring2() {
		assertEquals(1, qt.howManyTripsOccuringAt(timeStamp2));
	}

	@Test
	public void testEqualBoxes() {
		BoundingBox box1 = new BoundingBox(new Point(-3, -2), new Point(3, 2));
		BoundingBox box2 = new BoundingBox(new Point(3, 2), new Point(-3, -2));
		BoundingBox box3 = new BoundingBox(new Point(-3, 2), new Point(3, 2));
		BoundingBox box4 = new BoundingBox(new Point(-3, -2), new Point(3, -2));
		boolean result = true;
		result = box1.equals(box2) && box3.equals(box4);
		assertEquals(result, box1.equals(box2));
	}

	@Test
	/**
	 * BOUNDING QUESTION1
	 */
	public void MultiThreadingTest() {
		// re-init
		qt = new QuadTree();
		ExecutorService executor = Executors.newFixedThreadPool(500);
		for (int i = 1; i <= 500; i++) {
			try {
				Runnable worker = new GenThread(qt, i, new Point(0, 0));
				executor.execute(worker);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		executor.shutdown();
		while (!executor.isTerminated()) {
		}
		assertEquals(500, qt.getNbTrips());
	}

	/**
	 * let's check if a query within the entire quadTree with 50000 points takes less than 100ms
	 */
	@Test
	/**
	 * BOUNDING QUESTION2
	 */
	public void QueryPerfTest() {
		BoundingBox box = new BoundingBox(new Point(-180, 180), new Point(180,-180));
		long t1 = java.lang.System.currentTimeMillis();
		qt.howManyTripsPassedThrough(box);
		long t2 = java.lang.System.currentTimeMillis();
		boolean perfRes = (t2 - t1) < 100;
		assertEquals(true, perfRes);
	}

}
