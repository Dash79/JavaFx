//imports
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/*****************************************************************
 * A javafx program to wish Dela a Happy Late Birthday
 * 
 * @author Dawit Ashenafi Getachew
 * @date 20-02-2024
 * @version Assignment 4 - Version 1
 ****************************************************************/

public class Birthday extends Application 
{
    @Override
    public void start(Stage primaryStage) 
    {
        Pane root = new Pane();

        // Create the sky background
        Rectangle sky = new Rectangle(0, 0, 400, 300);
        sky.setFill(Color.SKYBLUE);

        // Create the grass
        Rectangle grass = new Rectangle(0, 250, 400, 50);
        grass.setFill(Color.LIGHTGREEN);

        // Drawing the stick figure 
        
        //Head of the stick figure
        Circle head = new Circle(200, 100, 25, Color.BEIGE); 
        
        //Body of the stick figure
        Line body = new Line(200, 120, 200, 210);
        body.setStrokeWidth(6);
        
        //Left leg of the stick figure
        Line leftLeg = new Line(200, 220, 170, 270);
        leftLeg.setStrokeWidth(6);
        
        //Right leg of the stick figure
        Line rightLeg = new Line(200, 220, 230, 270);
        rightLeg.setStrokeWidth(6);
        
        //Left arm of the stick figure
        Line leftArm = new Line(200, 170, 150, 200);
        leftArm.setStrokeWidth(6);
        
        //Right arm of the stick figure
        Line rightArm = new Line(200, 170, 250, 200); 
        rightArm.setStrokeWidth(6);

        body.setStroke(Color.DARKBLUE);
        leftLeg.setStroke(Color.DARKBLUE);
        rightLeg.setStroke(Color.DARKBLUE);
        leftArm.setStroke(Color.DARKBLUE);
        rightArm.setStroke(Color.DARKBLUE);

        // Adjust the sign position
        Rectangle sign = new Rectangle(140, 160, 140, 70);
        sign.setFill(Color.LIGHTBLUE);
        sign.setStroke(Color.DARKBLUE);

        // Add text to the sign
        Text text = new Text(145, 190, "Happy Birthday!!");
        text.setFont(Font.font("Arial", 18));
        text.setFill(Color.DARKBLUE);

        // Add all elements to the root pane in order
        root.getChildren().addAll(sky, grass, body, leftLeg, rightLeg, leftArm, rightArm, head, sign, text);

        Scene scene = new Scene(root, 400, 300);
        primaryStage.setTitle("Happy Late Birthday!");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
