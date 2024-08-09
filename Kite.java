//imports
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

/***********************************************************************
 * A javafx program displaying a bunch of people flying kites at a park
 * 
 * @author Dawit Ashenafi Getachew
 * @date 20-02-2024
 * @version Assignment 4 - Version 1
 **********************************************************************/
 
public class Kite extends Application 
{
    @Override
    public void start(Stage primaryStage) 
    {
        Group root = new Group();

        // Draw the sun
        Circle sun = new Circle(50, Color.YELLOW);
        sun.setCenterX(100);
        sun.setCenterY(100);

        // Draw the grass
        Rectangle grass = new Rectangle(0, 250, 800, 150);
        grass.setFill(Color.GREEN);

        // Draw multiple stickmen and their kites
        draw(root, 200, 200);
        draw(root, 400, 200);
        draw(root, 600, 200);

        // Add the sun and grass to the root
        root.getChildren().add(sun);
        root.getChildren().add(grass);

        // Set the stage
        primaryStage.setTitle("Kite Flying");
        primaryStage.setScene(new Scene(root, 800, 400, Color.SKYBLUE));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private void draw(Group root, int x, int y) 
    {
        // Draw the stickman
        Circle head = new Circle(x, y, 10, Color.BLACK);
        Line body = new Line(x, y + 10, x, y + 40);
        Line arm1 = new Line(x, y + 20, x - 10, y + 30);
        Line arm2 = new Line(x, y + 20, x + 10, y + 30);
        Line leg1 = new Line(x, y + 40, x - 10, y + 60);
        Line leg2 = new Line(x, y + 40, x + 10, y + 60);

        // Draw the kite
        Rectangle kite = new Rectangle(x + 25, y - 100, 20, 20);
        kite.setFill(Color.RED);

        // Draw the kite string
        Line kiteString = new Line(x, y + 20, kite.getX() + kite.getWidth() / 2, kite.getY() + kite.getHeight());

        // Add elements to the root
        root.getChildren().addAll(head, body, arm1, arm2, leg1, leg2, kite, kiteString);

        // Animate the kite and its string at an angle
        animate(kite, kiteString);
    }

    private void animate(Rectangle kite, Line kiteString) 
    {
    // Define the range and speed of the kite's movement
    double deltaX = 50; 
    double deltaY = 30; 

    // Create a timeline for animation
    Timeline timeline = new Timeline();
    timeline.setCycleCount(Timeline.INDEFINITE);
    timeline.setAutoReverse(true);

    // KeyFrame for moving the kite right and up
    KeyFrame moveRightUp = new KeyFrame(Duration.seconds(2),
            new KeyValue(kite.translateXProperty(), deltaX),
            new KeyValue(kite.translateYProperty(), -deltaY),
            new KeyValue(kiteString.endXProperty(), kite.getX() + deltaX + kite.getWidth() / 2),
            new KeyValue(kiteString.endYProperty(), kite.getY() - deltaY + kite.getHeight() / 2));
    
    // Add the keyframe to the timeline
    timeline.getKeyFrames().add(moveRightUp);

    // Start the animation
    timeline.play();
    }

}
