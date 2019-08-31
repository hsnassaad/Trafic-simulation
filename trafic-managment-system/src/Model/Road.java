package Model;

import java.util.LinkedList;
import java.util.Queue;

public class Road {

	public Queue<Car> cars;

	public int[] XFirst;
	public int[] YFirst;

	public int[] XSecond;
	public int[] YSecond;

	public int[] XThird;
	public int[] YThird;

	public int[] distance = { 0, 0 };
	public int[] distance1 = { 0, 0 };
	public int[] distance2 = { 0, 0 };

	public String side;

	public Road(String s) {

		side = s;

		cars = new LinkedList<Car>();

		if (side == "E") {
//	edit all start point to be out of scene
			XFirst = new int[] { 1307, 790, 0 };
			YFirst = new int[] { 80, 80, 80 };

			XSecond = new int[] { 1307, 790, 500,500 };
			YSecond = new int[] { 150, 150, 150 ,706};

//			System.out.println("side E");

		} else if (side == "W") {

			XFirst = new int[] { -200, 313, 1107 };
			YFirst = new int[] { 223, 223, 223 };

			XSecond = new int[] { -200, 313, 464, 464 };
			YSecond = new int[] { 292, 292, 292, 706 };

//			System.out.println("side W");

		} else {

			XThird = new int[] { 590, 590, 590, 0 };
			YThird = new int[] { 906, 530, 115, 115 };

			XSecond = new int[] { 670, 670, 670, 1200 };
			YSecond = new int[] { 906, 530, 260, 260 };
			
//			System.out.println("side S");
		}

	}

}
