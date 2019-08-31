package Controller;

import Model.TrafficLight;
import javafx.scene.paint.Color;

public class ColorControl implements Runnable {

	public static int i = 0;
	public static int onColor = 0;
	public static int traffic = 0;
	public static int threadNumber = 0, invokeThreadNumber = 0;
	public static final Object myLock = new Object();

	private int threadID;
	private static final long PAUSE = 2000;
	private int MAX_THREADS = 3;
	private boolean isStopped = false;

	public static TrafficLight t1;
	public static TrafficLight t2;
	public static TrafficLight t3;

	ColorControl(TrafficLight T1, TrafficLight T2, TrafficLight T3) {
		threadID = threadNumber++;
		setTrafficLight(T1, T2, T3);
	}

	void reset() {
		threadNumber = 0;
		invokeThreadNumber = 0;
	}

	public void setTrafficLight(TrafficLight T1, TrafficLight T2, TrafficLight T3) {
		t1 = T1;
		t2 = T2;
		t3 = T3;
	}

	@Override
	public void run() {
		synchronized (myLock) {

			while (!isStopped) {
				while (threadID != invokeThreadNumber) {
					try {
						myLock.wait();
					} catch (InterruptedException e) {
					}
				}
				update();
				try {
					Thread.sleep(PAUSE);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				invokeThreadNumber++;

				myLock.notifyAll();
				if (invokeThreadNumber >= MAX_THREADS) {
					traffic++;
					// traffic = ThreadLocalRandom.current().nextInt(0, 2+1);
					reset();
				}

				if (traffic == 3) {
					traffic = 0;
				}
			}
		}
	}

	public void update() {

		if (ColorControl.traffic == 0) {

			if (onColor == 0) {
				t1.green.setFill(Color.GREEN);
				t1.ActiveTL(true);
			} else {
				t1.green.setFill(Color.rgb(2, 46, 2));
				t1.Active = false;
			}
			t1.yellow.setFill((onColor == 1) ? Color.YELLOW : Color.rgb(48, 34, 0));

			if (onColor == 2) {
				t1.ActiveTL(false);
			} else {
				t1.red.setFill(Color.rgb(71, 0, 2));
			}

			// System.out.println("T1: "+t1.Active);
			// System.out.println("T2: "+t2.Active);
			// System.out.println("T3: "+t3.Active);

			onColor = ((onColor + 1) >= 3) ? 0 : onColor + 1;

			t2.ActiveTL(false);
			t3.ActiveTL(false);
		}

		if (ColorControl.traffic == 1) {

			if (onColor == 0) {
				t2.green.setFill(Color.GREEN);
				t2.ActiveTL(true);

			} else {
				t2.green.setFill(Color.rgb(2, 46, 2));
				t2.Active = false;
			}

			t2.yellow.setFill((onColor == 1) ? Color.YELLOW : Color.rgb(48, 34, 0));

			if (onColor == 2) {
				t2.ActiveTL(false);
			} else {
				t2.red.setFill(Color.rgb(71, 0, 2));
			}

			// System.out.println("T1: "+t1.Active);
			// System.out.println("T2: "+t2.Active);
			// System.out.println("T3: "+t3.Active);

			onColor = ((onColor + 1) >= 3) ? 0 : onColor + 1;

			t1.ActiveTL(false);
			t3.ActiveTL(false);

		}

		if (ColorControl.traffic == 2) {

			if (onColor == 0) {
				t3.green.setFill(Color.GREEN);
				t3.ActiveTL(true);
			} else {
				t3.green.setFill(Color.rgb(2, 46, 2));
				t3.Active = false;
			}

			t3.yellow.setFill((onColor == 1) ? Color.YELLOW : Color.rgb(48, 34, 0));

			if (onColor == 2) {
				t3.ActiveTL(false);
			} else {
				t3.red.setFill(Color.rgb(71, 0, 2));
			}

			// System.out.println("T1: "+t1.Active);
			// System.out.println("T2: "+t2.Active);
			// System.out.println("T3: "+t3.Active);

			onColor = ((onColor + 1) >= 3) ? 0 : onColor + 1;

			t2.ActiveTL(false);
			t1.ActiveTL(false);
		}

	}

}
