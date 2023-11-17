package com.example.finalproject;

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

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    Canvas canvas;
    @FXML
    Button submitButton;
    @FXML
    private Button clearButton;
    @FXML
    public BorderPane borderpane;
    public Label label;

    // Set up the event handler for the Clear button
    @FXML
    private void handleClearButtonClick() {
        clearCanvas();
    }

    private void clearCanvas() {
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }
    @FXML
    public void onSubmitButtonClick(ActionEvent actionEvent) {

        try{
//          img_dir用来存放生成canvas图片的路径
            String img_dir = "src\\main\\java\\com\\example\\finalproject\\output\\";
//          script_dir用来存放模型脚本的路径
            String script_dir = "\\src\\main\\java\\com\\example\\finalproject\\script\\";

            //这里开始将手写图片保存
            WritableImage writableImage = new WritableImage((int) canvas.getWidth(), (int) canvas.getHeight());
            canvas.snapshot(null, writableImage);
            File file = new File("canvas_image.png");
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(writableImage, null), "png", file);
                System.out.println("Canvas saved as image: " + file.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
//            getProperty方法是从System类的一个静态属性props里面获取相应的值，user.dir
//            user.dir是值用户当前的工作目录，这些属性是由JVM加载时初始化的属性
            //这里开始识别手写数字
            String path = System.getProperty("user.dir");
            String path_to_image = path + "\\" + img_dir + "canvas_image.png";
            ProcessBuilder pb=new ProcessBuilder("python",path+script_dir+"test.py",path_to_image);
            Process process=pb.start();
            process.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line=reader.readLine())!=null){
                System.out.println(line);
            }
            InputStream errorStream = process.getErrorStream();
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(errorStream));
            String errorLine;
            while ((errorLine = errorReader.readLine()) != null) {
                System.err.println(errorLine);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
# 将图片转化成模型能识别的张量，并且将像素值正态化（normalize）
        # os.path.dirname()去掉文件名，返回目录
        # cv2可以用来查看图片的样子
        # cv2.imshow("img", image)
        # cv2.waitKey(0)
        # 将模型设置为评估模式