package com.example.finalproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.util.StringTokenizer;

public class DrawingCanvasApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("canvas.fxml"));
        Parent root = loader.load();
        HelloController controller=loader.getController();

        Border border = new Border(new BorderStroke(Color.RED,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));
        controller.borderpane.setBorder(border);
        GraphicsContext graphicsContext=controller.canvas.getGraphicsContext2D();
        setMouseDrawingHandlers(controller.canvas,graphicsContext);

        Scene scene = new Scene(root);

        //scene.getStylesheets().add(getClass().getResource("../../../../resources/borderPane.css").toExternalForm());
        stage.setTitle("Drawing Canvas");
        stage.setScene(scene);
        stage.show();
    }

    private void setMouseDrawingHandlers(Canvas canvas, GraphicsContext graphicsContext) {
        canvas.setOnMousePressed(mouseEvent -> {
            graphicsContext.beginPath();
            graphicsContext.moveTo(mouseEvent.getX(),mouseEvent.getY());
            graphicsContext.setStroke(Color.BLACK);
            graphicsContext.setLineWidth(8);
        });

        canvas.setOnMouseDragged(mouseEvent -> {
            graphicsContext.lineTo(mouseEvent.getX(),mouseEvent.getY());
            graphicsContext.stroke();
        });

        canvas.setOnMouseReleased(mouseEvent -> {
            graphicsContext.closePath();
        });
    }
    public class JavaCallPython{
        public static void main(String[] args) {
            launch(args);

        }

    }

}
