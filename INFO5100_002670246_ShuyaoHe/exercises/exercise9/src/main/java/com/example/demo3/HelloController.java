package com.example.demo3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;

public class HelloController {
    public TextField tf1, tf2;
    public Label label;
    public Button buttonPlus;
    public Button buttonMinus;
    public Button buttonMultiply;
    public Button buttonDivide;
    public Button buttonClear;

    @FXML
    public void plus() {
        try {
            Integer a = Integer.parseInt(tf1.getText());
            Integer b=Integer.parseInt(tf2.getText());
            label.setText(String.valueOf(a)+" plus "+b+" equals "+String.valueOf(a+b));
        } catch (NumberFormatException e) {
            System.out.println("Enter value First");
        }
    }

    public void minus() {
        try {
            Integer a = Integer.parseInt(tf1.getText());
            Integer b=Integer.parseInt(tf2.getText());
            label.setText(String.valueOf(a)+" minus "+b+" equals "+String.valueOf(a-b));
        } catch (NumberFormatException e) {
            System.out.println("Enter value First");
        }
    }

    public void multiply() {
        try {
            Integer a = Integer.parseInt(tf1.getText());
            Integer b=Integer.parseInt(tf2.getText());
            label.setText(String.valueOf(a)+" multiply "+b+" equals "+String.valueOf(a*b));
        } catch (NumberFormatException e) {
            System.out.println("Enter value First");
        }
    }

    public void divide() {
        try {
            Integer a = Integer.parseInt(tf1.getText());
            Integer b=Integer.parseInt(tf2.getText());
            label.setText(String.valueOf(a)+" divide "+b+" equals "+String.valueOf(a/b));
        } catch (NumberFormatException e) {
            System.out.println("Enter value First");
        }
    }

    public void clear() {
        tf1.setText("");
        tf2.setText("");
        label.setText("");
    }
}
