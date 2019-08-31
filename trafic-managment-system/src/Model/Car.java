package Model;

import javafx.animation.PathTransition;
import javafx.animation.PathTransition.OrientationType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

public class Car {

	public Boolean Left = false, Rit = false, Forword = false, ELeft = false;
	public int xposition;
	public int yposition;
	public static int counter = 0;
	public static int counter1 = 0;
	public static int counter2 = 0;
	public String side;
	public Road r;
	public ImageView iv1;
	public String imageName;

	public Car(String imageName) {
		this.imageName = imageName;
		iv1 = new ImageView(new Image("/images/" + this.imageName));
	}

	public void manageImage() {
		if (side == "E") {
			iv1.setRotate(-180);
		} else if (side == "S") {
			iv1.setRotate(-90);
		}
	}

	public void AddRoad(Road road) {

		r = road;
		side = r.side;
		r.cars.add(this);
	}

	public void Move(int Xs, int Ys) {

		if (side == "S" && Rit) {

			Path path = new Path();
			MoveTo moveTo = new MoveTo(Xs, Ys);
			LineTo line1 = new LineTo(r.XSecond[2], r.YSecond[2]);
			LineTo line2 = new LineTo(r.XSecond[3] + r.distance2[1], r.YSecond[3]);

			path.getElements().add(moveTo);
			path.getElements().addAll(line1, line2);

			PathTransition pathTransition = new PathTransition();
			pathTransition.setDuration(Duration.seconds(4));
			pathTransition.setNode(iv1);
			pathTransition.setPath(path);
			pathTransition.setAutoReverse(false);

			pathTransition.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);
			pathTransition.play();
			r.distance2[1] -= 120;
		} else if (side == "S" && Left) {

			Path path = new Path();
			MoveTo moveTo = new MoveTo(Xs, Ys);
			LineTo line1 = new LineTo(r.XThird[2], r.YThird[2]);
			LineTo line2 = new LineTo(r.XThird[3] - r.distance2[0], r.YThird[3]);

			path.getElements().add(moveTo);
			path.getElements().addAll(line1, line2);

			PathTransition pathTransition = new PathTransition();
			pathTransition.setDuration(Duration.seconds(4));
			pathTransition.setNode(iv1);
			pathTransition.setPath(path);
			pathTransition.setAutoReverse(false);

			pathTransition.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);
			pathTransition.play();
			r.distance2[0] -= 80;

		} else if (side == "W" && Rit) {
			Path path = new Path();
			MoveTo moveTo = new MoveTo(Xs, Ys);
			LineTo line1 = new LineTo(r.XSecond[2], r.YSecond[2]);
			LineTo line2 = new LineTo(r.XSecond[3], r.YSecond[3] + r.distance1[1]);

			path.getElements().add(moveTo);
			path.getElements().addAll(line1, line2);

			PathTransition pathTransition = new PathTransition();
			pathTransition.setDuration(Duration.seconds(4));
			pathTransition.setNode(iv1);
			pathTransition.setPath(path);
			pathTransition.setAutoReverse(false);

			pathTransition.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);
			pathTransition.play();
			r.distance1[1] -= 110;

		} else if (side == "W" && Forword) {
			Path path = new Path();
			MoveTo moveTo = new MoveTo(Xs, Ys);
			LineTo line1 = new LineTo(r.XFirst[2] + r.distance1[0], r.YFirst[2]);
			path.getElements().add(moveTo);
			path.getElements().add(line1);

			PathTransition pathTransition = new PathTransition();
			pathTransition.setDuration(Duration.seconds(4));
			pathTransition.setNode(iv1);
			pathTransition.setPath(path);
			pathTransition.setAutoReverse(false);

			pathTransition.play();
			r.distance1[0] -= 110;

//			System.out.println("Distance toto: " + r.distance);
		} else if (side == "E" && Forword) {
			Path path = new Path();
			MoveTo moveTo = new MoveTo(Xs, Ys);
			LineTo line1 = new LineTo(r.XFirst[2] - r.distance[0], r.YFirst[2]);
			path.getElements().add(moveTo);
			path.getElements().add(line1);

			PathTransition pathTransition = new PathTransition();
			pathTransition.setDuration(Duration.seconds(4));
			pathTransition.setNode(iv1);
			pathTransition.setPath(path);
			pathTransition.setAutoReverse(false);

			pathTransition.play();
			r.distance[0] -= 110;

//			System.out.println("Distance toto: " + r.distance);
		} else if (side == "E" && ELeft) {
			Path path = new Path();
			MoveTo moveTo = new MoveTo(Xs, Ys);
			LineTo line1 = new LineTo(r.XSecond[2], r.YSecond[2]);
			LineTo line2 = new LineTo(r.XSecond[3], r.YSecond[3] + r.distance[1]);

			path.getElements().add(moveTo);
			path.getElements().addAll(line1, line2);

			PathTransition pathTransition = new PathTransition();
			pathTransition.setDuration(Duration.seconds(4));
			pathTransition.setNode(iv1);
			pathTransition.setPath(path);
			pathTransition.setAutoReverse(false);

			pathTransition.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);
			pathTransition.play();
			r.distance[1] -= 110;
		}
	}

	public void Stop() {

		if (side == "W" && Forword == true) {
			Path path = new Path();
			MoveTo moveTo = new MoveTo(r.XFirst[0], r.YFirst[0]);
			LineTo line1 = new LineTo(r.XFirst[1] - r.distance1[0], r.YFirst[1]);

			this.xposition = r.XFirst[1] - r.distance1[0];
			this.yposition = r.YFirst[1];

//			System.out.println("XStop: " + r.XFirst[0]);
//			System.out.println("Car.xposition: " + xposition);

			path.getElements().add(moveTo);
			path.getElements().add(line1);

			PathTransition pathTransition = new PathTransition();
			pathTransition.setDuration(Duration.millis(3000));
			pathTransition.setNode(iv1);
			pathTransition.setPath(path);
			pathTransition.setAutoReverse(false);
			pathTransition.play();

//			System.out.println("Distance: " + r.distance1[0]);
			r.distance1[0] += 110;

		} else if (side == "W" && Rit) {
			Path path = new Path();
			MoveTo moveTo = new MoveTo(r.XSecond[0], r.YSecond[0]);
			LineTo line1 = new LineTo(r.XSecond[1] - r.distance1[1], r.YSecond[1]);

			this.xposition = r.XSecond[1] - r.distance1[1];
			this.yposition = r.YSecond[1];

//			System.out.println("XStop: " + r.XSecond[0]);
//			System.out.println("Car.xposition: " + xposition);

			path.getElements().add(moveTo);
			path.getElements().add(line1);

			PathTransition pathTransition = new PathTransition();
			pathTransition.setDuration(Duration.millis(3000));
			pathTransition.setNode(iv1);
			pathTransition.setPath(path);
			pathTransition.setAutoReverse(false);
			pathTransition.play();

//			System.out.println("Distance: " + r.distance1[1]);
			r.distance1[1] += 110;
		} else if (side == "E" && Forword == true) {
			Path path = new Path();
			MoveTo moveTo = new MoveTo(r.XFirst[0], r.YFirst[0]);
			LineTo line1 = new LineTo(r.XFirst[1] + r.distance[0], r.YFirst[1]);

			this.xposition = r.XFirst[1] + r.distance[0];
			this.yposition = r.YFirst[1];

//			System.out.println("XStop: " + r.XFirst[0]);
//			System.out.println("Car.xposition: " + xposition);

			path.getElements().add(moveTo);
			path.getElements().add(line1);

			PathTransition pathTransition = new PathTransition();
			pathTransition.setDuration(Duration.millis(3000));
			pathTransition.setNode(iv1);
			pathTransition.setPath(path);
			pathTransition.setAutoReverse(false);
			pathTransition.play();

//			System.out.println("Distance: " + r.distance);
			r.distance[0] += 110;
		} else if (side == "E" && ELeft == true) {
			Path path = new Path();
			MoveTo moveTo = new MoveTo(r.XSecond[0], r.YSecond[0]);
			LineTo line1 = new LineTo(r.XSecond[1] + r.distance[1], r.YSecond[1]);

			this.xposition = r.XSecond[1] + r.distance[1];
			this.yposition = r.YSecond[1];

//			System.out.println("XStop: " + r.XSecond[0]);
//			System.out.println("Car.xposition: " + xposition);

			path.getElements().add(moveTo);
			path.getElements().add(line1);

			PathTransition pathTransition = new PathTransition();
			pathTransition.setDuration(Duration.millis(3000));
			pathTransition.setNode(iv1);
			pathTransition.setPath(path);
			pathTransition.setAutoReverse(false);
			pathTransition.play();

//			System.out.println("Distance: " + r.distance[1]);
			r.distance[1] += 110;

		} else if (side == "S" && Rit) {
			Path path = new Path();
			MoveTo moveTo = new MoveTo(r.XSecond[0], r.YSecond[0]);
			LineTo line1 = new LineTo(r.XSecond[1], r.YSecond[1] + r.distance2[1]);

			this.xposition = r.XSecond[1];
			this.yposition = r.YSecond[1] + r.distance2[1];

//			System.out.println("XStop: " + r.XSecond[0]);
//			System.out.println("Car.xposition: " + xposition);

			path.getElements().add(moveTo);
			path.getElements().add(line1);

			PathTransition pathTransition = new PathTransition();
			pathTransition.setDuration(Duration.millis(3000));
			pathTransition.setNode(iv1);
			pathTransition.setPath(path);
			pathTransition.setAutoReverse(false);
			pathTransition.play();

//			System.out.println("Distance: " + r.distance2[1]);
			r.distance2[1] += 120;

		} else if (side == "S" && Left) {
			Path path = new Path();
			MoveTo moveTo = new MoveTo(r.XThird[0], r.YThird[0]);
			LineTo line1 = new LineTo(r.XThird[1], r.YThird[1] + r.distance2[0]);

			this.xposition = r.XThird[1];
			this.yposition = r.YThird[1] + r.distance2[0];

//			System.out.println("XStop: " + r.XThird[0]);
//			System.out.println("Car.xposition: " + xposition);

			path.getElements().add(moveTo);
			path.getElements().add(line1);

			PathTransition pathTransition = new PathTransition();
			pathTransition.setDuration(Duration.millis(3000));
			pathTransition.setNode(iv1);
			pathTransition.setPath(path);
			pathTransition.setAutoReverse(false);
			pathTransition.play();

//			System.out.println("Distance: " + r.distance2[0]);
			r.distance2[0] += 120;
		}

	}
}
