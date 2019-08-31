package Model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class TrafficLight {

	public Rectangle trafficLight;
	public Circle red, yellow, green;
	Color color;
	public int x, y, width, height;
	public String side;
	boolean solution = true;
	public Boolean Active = false;

	public Road r;

	public TrafficLight() {
		trafficLight = new Rectangle();
		red = new Circle();
		yellow = new Circle();
		green = new Circle();

	}

	public TrafficLight(int x, int y, int width, int height, String side) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.side = side;

		trafficLight = new Rectangle();
		red = new Circle();
		yellow = new Circle();
		green = new Circle();
		this.ActiveTL(false);

	}

	public Rectangle drawTrafficLight() {
		trafficLight.setX(x);
		trafficLight.setY(y);
		trafficLight.setWidth(width / 2);
		trafficLight.setHeight(height / 2);
		trafficLight.setStroke(Color.GRAY);
		trafficLight.setFill(Color.BLACK);
		trafficLight.setStrokeWidth(5);
		trafficLight.setArcHeight(15);
		trafficLight.setArcWidth(15);

		return trafficLight;
	}

	public Circle drawRedCircle() {
		if (width < height) {
			red.setCenterX(trafficLight.getX() + (trafficLight.getHeight() / 4));
			red.setCenterY(trafficLight.getY() + (trafficLight.getHeight() / 4));
			red.setRadius(trafficLight.getHeight() / 9);
			if (solution) {
				red.setStroke(Color.BLACK);
				red.setStrokeWidth(2);
				side = "N";
				color = Color.rgb(71, 0, 2);
			} else {
				red.setStroke(Color.BLACK);
				red.setStrokeWidth(2);
				side = "S";
				color = Color.rgb(71, 0, 2);
			}
		} else {
			red.setCenterX(trafficLight.getX() + (trafficLight.getHeight() / 2));
			red.setCenterY(trafficLight.getY() + (trafficLight.getHeight() / 2));
			red.setRadius(trafficLight.getWidth() / 9);
			if (solution) {
				red.setStroke(Color.BLACK);
				red.setStrokeWidth(2);
				side = "W";
				color = Color.rgb(71, 0, 2);
			} else {
				red.setStroke(Color.BLACK);
				red.setStrokeWidth(2);
				side = "E";
				color = Color.rgb(71, 0, 2);
			}
		}
		return red;
	}

	public Circle drawYellowCircle() {
		if (width < height) {
			yellow.setCenterX(red.getCenterX());
			yellow.setCenterY(red.getCenterY() + 25);
			yellow.setRadius(trafficLight.getHeight() / 9);
			if (solution) {
				yellow.setStroke(Color.BLACK);
				yellow.setStrokeWidth(2);
				color = Color.YELLOW;
			} else {
				yellow.setStroke(Color.BLACK);
				yellow.setStrokeWidth(2);
				color = Color.TRANSPARENT;
			}
		} else {
			yellow.setCenterX(red.getCenterX() + 25);
			yellow.setCenterY(red.getCenterY());
			yellow.setRadius(trafficLight.getWidth() / 9);
			if (solution) {
				yellow.setStroke(Color.BLACK);
				yellow.setStrokeWidth(2);
				color = Color.rgb(48, 34, 0);
			} else {
				yellow.setStroke(Color.BLACK);
				yellow.setStrokeWidth(2);
				color = Color.TRANSPARENT;
			}
		}
		return yellow;
	}

	public Circle drawGreenCircle() {
		if (width < height) {
			green.setCenterX(red.getCenterX());
			green.setCenterY(red.getCenterY() + 50);
			green.setRadius(trafficLight.getHeight() / 9);
			if (solution) {
				green.setStroke(Color.BLACK);
				green.setStrokeWidth(2);
				color = Color.GREEN;
			} else {
				green.setStroke(Color.BLACK);
				green.setStrokeWidth(2);
				color = Color.GREEN;
			}
		} else {
			green.setCenterX(red.getCenterX() + 50);
			green.setCenterY(red.getCenterY());
			green.setRadius(trafficLight.getWidth() / 9);
			if (solution) {
				green.setStroke(Color.BLACK);
				green.setStrokeWidth(2);
				color = Color.rgb(2, 46, 2);
			} else {
				green.setStroke(Color.BLACK);
				green.setStrokeWidth(2);
				color = Color.rgb(2, 46, 2);
			}
		}
		return green;
	}

	public String getSide() {
		return side;
	}

	public Color getColor() {
		return color;
	}

	public Rectangle getRectangle() {
		return trafficLight;
	}

	public void ActiveTL(boolean a) {
		Active = a;
		if (a == false) {
			red.setFill(Color.RED);
			green.setFill(Color.rgb(2, 46, 2));
			yellow.setFill(Color.rgb(48, 34, 0));
		}
		// else {
		// red.setFill(Color.rgb(71, 0, 2));
		// yellow.setFill(Color.rgb(48, 34, 0));
		// green.setFill(Color.GREEN);
		// }
	}

}
