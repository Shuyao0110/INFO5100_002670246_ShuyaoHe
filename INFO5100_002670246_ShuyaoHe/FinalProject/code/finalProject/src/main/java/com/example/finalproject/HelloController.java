package com.example.finalproject;

// JavaFX and other necessary imports

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;

import javax.imageio.ImageIO;
import java.io.*;

// Main controller class for the JavaFX application
public class HelloController {
    // Annotations to link FXML components with the controller
    @FXML
    Canvas canvas;
    @FXML
    Button submitButton;
    @FXML
    private Button clearButton;
    @FXML
    public BorderPane borderpane;
    @FXML
    public Label label;

    // Set up the event handler for the Clear button
    @FXML
    private void handleClearButtonClick() {
        clearCanvas();
    }

    // Method to clear the canvas
    private void clearCanvas() {
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    // Event handler for the submit button
    @FXML
    public void onSubmitButtonClick(ActionEvent actionEvent) {
        try {
            // Get the current working directory
            String path = System.getProperty("user.dir");

            // Directory paths for saving the image and running the script
            String img_dir = "src/main/java/com/example/finalproject/output/";
            String script_dir = "src/main/java/com/example/finalproject/script/";

            // Save the canvas as an image
            WritableImage writableImage = new WritableImage((int) canvas.getWidth(), (int) canvas.getHeight());
            canvas.snapshot(null, writableImage);
            File file = new File(img_dir + "canvas_image.png");
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(writableImage, null), "png", file);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Run the Python script for processing the image
            String pythonPath = "python3";     //Replace pythonPath with your own python path
            String path_to_image = path + "/" + img_dir + "canvas_image.png";
            ProcessBuilder pb = new ProcessBuilder(pythonPath, script_dir + "test.py", path_to_image);

            Process process = pb.start();

            // Read and display the output from the Python script
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                label.setText("Result: " + line);
            }

            // Wait for the process to complete and display the exit code
            int exitCode = process.waitFor();
            System.out.println("Exited with code : " + exitCode);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
