package sample;

import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.scene.image.ImageView;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

public class MyAnimation {


    void myAnamation_corona(ImageView image)
    {
        Path path = new Path();
        int row_int = (int) (Math.random() * (6000));
        int col_int = (int) (Math.random() * (700));
        System.out.println(row_int);
        System.out.println(col_int);
        MoveTo move = new MoveTo(0, 0);
        LineTo line1 = new LineTo(0, row_int);
        LineTo line2 = new LineTo(col_int, 0);
        LineTo line3 = new LineTo(0, 0);
        path.getElements().addAll(move, line2, line1, line3);
        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.seconds(15));
        pathTransition.setNode(image);
        pathTransition.setPath(path);
        pathTransition.setCycleCount(-1);
        pathTransition.setAutoReverse(true);
        pathTransition.play();
        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setDuration(Duration.seconds(1.5));
        rotateTransition.setNode(image);
        rotateTransition.setByAngle(180);
        rotateTransition.setCycleCount(-1);
        rotateTransition.setAutoReverse(true);
        rotateTransition.play();

    }
    void myAnamation_corona2(ImageView image)
    {
        Path path = new Path();
        int row_int = (int) (Math.random() * (600));
        int col_int = (int) (Math.random() * (700));
        System.out.println(row_int);
        System.out.println(col_int);
        MoveTo move = new MoveTo(0, 0);
        LineTo line1 = new LineTo(0, -row_int);
        LineTo line2 = new LineTo(-col_int, 0);
        LineTo line3 = new LineTo(0, 0);
        path.getElements().addAll(move, line2, line1, line3);
        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.seconds(15));
        pathTransition.setNode(image);
        pathTransition.setPath(path);
        pathTransition.setCycleCount(-1);
        pathTransition.setAutoReverse(true);
        pathTransition.play();
        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setDuration(Duration.seconds(1.5));
        rotateTransition.setNode(image);
        rotateTransition.setByAngle(180);
        rotateTransition.setCycleCount(-1);
        rotateTransition.setAutoReverse(true);
        rotateTransition.play();

    }

}
