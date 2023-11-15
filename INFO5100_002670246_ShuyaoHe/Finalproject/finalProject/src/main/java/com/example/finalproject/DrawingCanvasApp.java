package com.example.finalproject;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class DrawingCanvasApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Drawing Canvas");
        Canvas canvas=new Canvas(400,400);
        GraphicsContext graphicsContext=canvas.getGraphicsContext2D();
        setMouseDrawingHandlers(canvas,graphicsContext);

        StackPane root=new StackPane();
        root.getChildren().add(canvas);
        stage.setScene(new Scene(root,400,500));
        stage.show();
    }

    private void setMouseDrawingHandlers(Canvas canvas, GraphicsContext graphicsContext) {
        canvas.setOnMousePressed(mouseEvent -> {
            graphicsContext.beginPath();
            graphicsContext.moveTo(mouseEvent.getX(),mouseEvent.getY());
            graphicsContext.setStroke(Color.BLACK);
            graphicsContext.setLineWidth(2);
        });

        canvas.setOnMouseDragged(mouseEvent -> {
            graphicsContext.lineTo(mouseEvent.getX(),mouseEvent.getY());
            graphicsContext.stroke();
        });

        canvas.setOnMouseReleased(mouseEvent -> {
            graphicsContext.closePath();
        });
    }
    public static void main(String[] args) {
        launch(args);
    }
}
