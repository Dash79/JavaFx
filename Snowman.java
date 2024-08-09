//Imports
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*****************************************************************
 * Is a javafx application that makes a snowman. 
 * It changes between day and night
 * 
 * @author Dawit Ashenafi Getachew
 * @date 20-02-2024
 * @version Assignment 4 - Version 1
 ****************************************************************/

public class Snowman extends Application 
{

    //Instance datas
    private boolean isDay = true;
    private Canvas canvas;
    private GraphicsContext gc;
    private Random random;
    private List<Star> stars;
    private Timeline blinkAnimation;

    @Override
    public void start(Stage primaryStage) 
    {
        //Setting up the main window
        StackPane root = new StackPane();

        canvas = new Canvas(600, 400);
        gc = canvas.getGraphicsContext2D();
        random = new Random();
        stars = new ArrayList<>();
        
        //Button to toggle between day and night
        Button toggleButton = new Button("Day/Night");
        toggleButton.setOnAction(e -> 
        {
            isDay = !isDay;
            if (!isDay) {
                blinkAnimation.play();
            } else {
                blinkAnimation.stop();
            }
            drawScene();
        });
        
        // Arrange the toggle button in a VBox and set its position.
        VBox buttonBox = new VBox(10);
        buttonBox.getChildren().addAll(toggleButton);
        buttonBox.setAlignment(Pos.BOTTOM_CENTER);

        root.getChildren().addAll(canvas, buttonBox);
        
        // Create and set the scene, then display the primary stage.
        Scene scene = new Scene(root, 600, 450);
        primaryStage.setTitle("Snowman");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        
        //Methods
        initializeStars();
        setupBlinkAnimation();
        drawScene();
    }
    
    //Method to initialize the stars
    private void initializeStars() 
    {
        for (int i = 0; i < 50; i++) 
        {
            double x = random.nextDouble() * canvas.getWidth();
            double y = random.nextDouble() * canvas.getHeight() * 0.7;
            Star star = new Star(x, y);
            stars.add(star);
        }
    }

    //Method for the blinking animation
    private void setupBlinkAnimation() 
    {
        blinkAnimation = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue[0]),
                new KeyFrame(Duration.seconds(1), e -> 
                {
                    for (Star star : stars) 
                    {
                        if (random.nextDouble() < 0.5) 
                        {
                            star.setOpacity(0.3);
                        } 
                        else 
                        {
                            star.setOpacity(1.0);
                        }
                    }
                    drawScene();
                })
        );
        blinkAnimation.setCycleCount(Timeline.INDEFINITE);
    }

    private void drawScene() 
    {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // Draw background
        if (isDay) 
        {
            gc.setFill(Color.LIGHTBLUE);
        } 
        else 
        {
            gc.setFill(Color.DARKBLUE);
        }
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // Draw grass
        gc.setFill(Color.LIGHTGREEN);
        gc.fillRect(0, canvas.getHeight() * 0.7, canvas.getWidth(), canvas.getHeight());

        // Draw stars
        if (!isDay) 
        {
            for (Star star : stars) 
            {
                gc.setFill(Color.WHITE);
                gc.setGlobalAlpha(star.getOpacity());
                gc.fillOval(star.getX(), star.getY(), 2, 2);
            }
            gc.setGlobalAlpha(1.0); 
        }

        // Draw snowman
        drawCircle(300, 320, 50, Color.WHITE); 
        drawCircle(300, 250, 40, Color.WHITE); 
        drawCircle(300, 190, 30, Color.WHITE); 

        // Draw eyes
        drawCircle(290, 180, 3, Color.BLACK);
        drawCircle(310, 180, 3, Color.BLACK);

        // Draw nose
        gc.setFill(Color.ORANGE);
        gc.fillPolygon(new double[]{300, 295, 305}, new double[]{195, 185, 185}, 3);

        // Draw buttons
        drawCircle(300, 240, 3, Color.BLACK);
        drawCircle(300, 260, 3, Color.BLACK);
        drawCircle(300, 280, 3, Color.BLACK);

        // Draw arms
        gc.setStroke(Color.BROWN);
        gc.setLineWidth(3);
        gc.strokeLine(260, 240, 230, 220);
        gc.strokeLine(340, 240, 370, 220);
    }

    //Method to draw circles for the snowman
    private void drawCircle(double centerX, double centerY, double radius, Color color) 
    {
        gc.setFill(color);
        gc.fillOval(centerX - radius, centerY - radius, radius * 2, radius * 2);
    }

    
    //Class to represent the stars
    private class Star 
    {
        //Instance data
        private double x;
        private double y;
        private double opacity;

        //Constructor
        public Star(double x, double y) 
        {
            this.x = x;
            this.y = y;
            this.opacity = 1.0;
        }

        //Methods
        //Accessors
        public double getX() 
        {
            return x;
        }

        public double getY() 
        {
            return y;
        }

        public double getOpacity() 
        {
            return opacity;
        }

        public void setOpacity(double opacity) 
        {
            this.opacity = opacity;
        }
    }
}
