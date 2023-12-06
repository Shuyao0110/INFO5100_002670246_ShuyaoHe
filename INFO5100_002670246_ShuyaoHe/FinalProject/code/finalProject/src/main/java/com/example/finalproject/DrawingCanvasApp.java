// Package declaration
package com.example.finalproject;

// Import statements for JavaFX classes and other utilities

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

// Main class of the application, extending from JavaFX's Application class
public class DrawingCanvasApp extends Application {

    // Override the start method from Application class
    @Override
    public void start(Stage stage) throws Exception {
        // Load the FXML file and get the root node
        FXMLLoader loader = new FXMLLoader(getClass().getResource("canvas.fxml"));
        Parent root = loader.load();

        // Get the controller associated with the FXML file
        HelloController controller = loader.getController();

        // Set a red border for the border pane in the UI
        Border border = new Border(new BorderStroke(Color.RED,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));
        controller.borderpane.setBorder(border);

        // Get the graphics context from the canvas for drawing
        GraphicsContext graphicsContext = controller.canvas.getGraphicsContext2D();

        // Set up mouse event handlers for drawing on the canvas
        setMouseDrawingHandlers(controller.canvas, graphicsContext);

        // Create a scene with the root node and set it on the stage
        Scene scene = new Scene(root);

        // Set the title of the window and show it
        stage.setTitle("Drawing Canvas");
        stage.setScene(scene);
        stage.show();
    }

    // Private helper method to set mouse event handlers on the canvas
    private void setMouseDrawingHandlers(Canvas canvas, GraphicsContext graphicsContext) {
        // Handle mouse pressed event for starting a path
        canvas.setOnMousePressed(mouseEvent -> {
            graphicsContext.beginPath();
            graphicsContext.moveTo(mouseEvent.getX(), mouseEvent.getY());
            graphicsContext.setStroke(Color.BLACK);
            graphicsContext.setLineWidth(8);
        });

        // Handle mouse dragged event for drawing the path
        canvas.setOnMouseDragged(mouseEvent -> {
            graphicsContext.lineTo(mouseEvent.getX(), mouseEvent.getY());
            graphicsContext.stroke();
        });

        // Handle mouse released event for closing the path
        canvas.setOnMouseReleased(mouseEvent -> {
            graphicsContext.closePath();
        });
    }

    // Inner class with main method to launch the application
    public class JavaCallPython {
        public static void main(String[] args) {
            launch(args);
        }
    }
}