package Controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import Model.Car;
import Model.Road;
import Model.TrafficLight;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.TextAlignment;

public class Main extends Application {

	public TrafficLight t1;
	public TrafficLight t2;
	public TrafficLight t3;
	public static AnchorPane loader;
	Road r1;
	Road r2;
	Road r3;
	Thread th1, th2, th3;
	Button start, pause, exit;
	Label about;
	Boolean active = false;
	int i = 0;
	Timeline tl;
	TextField tf;
	public static int numCars = 1;
	public int maxNumCars;
	public ArrayList<String> Cars = new ArrayList<String>();
	// Change the way to read name of images .....

	// new String[] { "ambulance.png", "car1.png", "car2.png", "car3.png",
	// "car4.png", "police.png",
	// "truck1.png", "truck2.png", "cars.jpg"
	// };/Traffic-Light-Simulator/src/View/images
	final File images = new File(".\\View\\images");

	@Override
	public void start(Stage primaryStage) {
		try {

			loader = new AnchorPane();
			loader.setId("anchor");
			drawTL();
			listFilesForFolder(images);

			Scene scene = new Scene(loader, 1100, 700);
			scene.getStylesheets().add(getClass().getResource("/css/road.css").toExternalForm());

			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.setTitle("Traffic Light Simulator");
			primaryStage.show();

			start.setOnMouseClicked(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent event) {
					if (!tf.getText().isEmpty()) {
						active = true;
						numCars = 1;
						maxNumCars = Integer.parseInt(tf.getText());
						CreateCars();
						control();
						start.setDisable(true);
					} else {
						Alert alert = new Alert(Alert.AlertType.ERROR);
						alert.setTitle("Error");
						alert.setContentText("Number of cars is required");
						alert.showAndWait();
					}

				}
			});

			pause.setOnMouseClicked(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent e) {
					if (active) {
						if (i == 0) {
							active = true;
							th1.suspend();
							th2.suspend();
							th3.suspend();
							StopCars();
							pause.setText("Play");
							i++;
						} else {
							active = false;
							th1.resume();
							th2.resume();
							th3.resume();
							CreateCars();
							pause.setText("Pause");
							i--;
						}
					} else {
						Alert alert = new Alert(Alert.AlertType.ERROR);
						alert.setTitle("Error");
						alert.setContentText("Please Start the program first.");
						alert.showAndWait();
					}
				}
			});

			exit.setOnMouseClicked(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent event) {
					System.exit(0);
				}
			});

			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				@Override
				public void handle(WindowEvent t) {
					System.exit(0);
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void listFilesForFolder(final File folder) {
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				listFilesForFolder(fileEntry);
			} else {
				Cars.add(fileEntry.getName());
			}
		}
	}

	private void control() {
		th1 = new Thread(new ColorControl(t1, t2, t3));
		th2 = new Thread(new ColorControl(t1, t2, t3));
		th3 = new Thread(new ColorControl(t1, t2, t3));
		th1.start();
		th2.start();
		th3.start();
	}

	public void drawTL() {

		r1 = new Road("E");
		r2 = new Road("S");
		r3 = new Road("W");

		t1 = new TrafficLight(780, 0, 200, 100, "E");
		t2 = new TrafficLight(715, 340, 100, 200, "S");
		t3 = new TrafficLight(298, 340, 200, 100, "W");

		t1.r = r1;
		t2.r = r2;
		t3.r = r3;

		start = new Button("Start");
		start.setLayoutX(50);
		start.setLayoutY(450);
		start.setPadding(new Insets(12, 45, 12, 45));

		pause = new Button("Pause");
		pause.setLayoutX(50);
		pause.setLayoutY(540);
		pause.setPadding(new Insets(12, 41, 12, 41));

		exit = new Button("Exit");
		exit.setLayoutX(50);
		exit.setLayoutY(630);
		exit.setPadding(new Insets(12, 52, 12, 52));

		about = new Label();
		about.setLayoutX(800);
		about.setLayoutY(440);
		about.setText("Done By: \nHassan Asaad\nMohamad Naji\n\nI3341\n\n2018 -2019\n\n");
		about.setTextAlignment(TextAlignment.CENTER);
		about.setPadding(new Insets(3, 20, 3, 20));

		tf = new TextField();
		tf.setLayoutX(800);
		tf.setLayoutY(620);
		tf.setPromptText("Insert number of cars...");

		loader.getChildren().add(start);
		loader.getChildren().add(pause);
		loader.getChildren().add(exit);
		loader.getChildren().add(tf);
		loader.getChildren().add(about);

		loader.getChildren().add(t1.drawTrafficLight());
		loader.getChildren().add(t1.drawRedCircle());
		loader.getChildren().add(t1.drawYellowCircle());
		loader.getChildren().add(t1.drawGreenCircle());

		loader.getChildren().add(t2.drawTrafficLight());
		loader.getChildren().add(t2.drawRedCircle());
		loader.getChildren().add(t2.drawYellowCircle());
		loader.getChildren().add(t2.drawGreenCircle());

		loader.getChildren().add(t3.drawTrafficLight());
		loader.getChildren().add(t3.drawRedCircle());
		loader.getChildren().add(t3.drawYellowCircle());
		loader.getChildren().add(t3.drawGreenCircle());

	}

	public void manageRoad() {

		int turn = ThreadLocalRandom.current().nextInt(0, 2);

		if (numCars <= maxNumCars) {
			String image = ChooseCar();
			Car car = new Car(image);
			numCars++;
			car.AddRoad(t1.r);
			car.manageImage();
			if (turn == 0) {
				car.ELeft = true;
			} else {
				car.Forword = true;
			}
			if (t1.r.cars.size() > 0) {
				if (t1.Active) {
					while ((car = t1.r.cars.poll()) != null) {
						car.Move(car.xposition, car.yposition);
					}
					t1.r.distance[0] = 0;
					t1.r.distance[1] = 0;

				} else {
					car.Stop();
					loader.getChildren().add(car.iv1);
				}
			}
		} else {
			if (t1.r.cars.size() > 0) {
				Car car;
				if (t1.Active) {
					while ((car = t1.r.cars.poll()) != null) {
						car.Move(car.xposition, car.yposition);
					}
					t1.r.distance[0] = 0;
					t1.r.distance[1] = 0;
				}
			}
		}

		if (numCars <= maxNumCars) {
			String image = ChooseCar();
			Car car = new Car(image);
			car.AddRoad(t3.r);
			numCars++;

			if (turn == 0) {
				car.Forword = true;
			} else {
				car.Rit = true;
			}

			if (t3.r.cars.size() > 0) {
				if (t3.Active) {
					while ((car = t3.r.cars.poll()) != null) {
						car.Move(car.xposition, car.yposition);
					}
					t3.r.distance1[0] = 0;
					t3.r.distance1[1] = 0;
				} else {
					car.Stop();
					loader.getChildren().add(car.iv1);
				}
			}
		} else {
			if (t3.r.cars.size() > 0) {
				Car car;
				if (t3.Active) {
					while ((car = t3.r.cars.poll()) != null) {
						car.Move(car.xposition, car.yposition);
					}
					t3.r.distance1[0] = 0;
					t3.r.distance1[1] = 0;
				}
			}
		}

		if (numCars <= maxNumCars) {
			String image = ChooseCar();
			Car car = new Car(image);
			numCars++;
			car.AddRoad(t2.r);
			car.manageImage();
			if (turn == 0) {
				car.Left = true;
			} else {
				car.Rit = true;
			}

			if (t2.r.cars.size() > 0) {
				if (t2.Active) {
					while ((car = t2.r.cars.poll()) != null) {
						car.Move(car.xposition, car.yposition);
					}
					t2.r.distance2[0] = 0;
					t2.r.distance2[1] = 0;
				} else {
					car.Stop();
					loader.getChildren().add(car.iv1);
				}
			}
		} else {
			if (t2.r.cars.size() > 0) {
				Car car;
				if (t2.Active) {
					while ((car = t2.r.cars.poll()) != null) {
						car.Move(car.xposition, car.yposition);
					}
					t2.r.distance2[0] = 0;
					t2.r.distance2[1] = 0;
				}
			}
		}

		if (numCars > maxNumCars && active) {
			start.setDisable(false);
		}

	}

	public void CreateCars() {

		tl = new Timeline(new KeyFrame(Duration.seconds(2), ae -> {
			manageRoad();
		}));

		tl.setCycleCount(Animation.INDEFINITE);
		tl.play();

	}

	public void StopCars() {
		tl.pause();
	}

	public String ChooseCar() {

		String image;
		Random rand = new Random();
		int x = rand.nextInt(Cars.size());
		image = Cars.get(x);
		return image;
	}

	public static void main(String[] args) {
		launch(args);
	}

}
