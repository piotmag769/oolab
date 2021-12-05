package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.util.ArrayList;

public class App extends Application {
    @Override
    public void start(Stage primaryStage)
    {
        try
        {
            ArrayList<MoveDirection> directions = OptionsParser.parse(getParameters().getRaw());
            GrassField map = new GrassField(10);
            Vector2d[] positions = {new Vector2d(4, 4), new Vector2d(7, 7)};
            IEngine engine = new SimulationEngine(directions, map, positions);

            System.out.println(map);

            //gui part
            Vector2d lower_corner = map.get_lower_corner();
            Vector2d upper_corner = map.get_upper_corner();

            int x_len = upper_corner.x - lower_corner.x + 2;
            int y_len = upper_corner.y - lower_corner.y + 2;

            GridPane grid = new GridPane();
            grid.setGridLinesVisible(true);

            Label[][] labels = new Label[x_len][y_len];

            // axis label
            labels[0][0] = new Label("y\\x");
            grid.add(labels[0][0], 0 , 0);
            GridPane.setHalignment(labels[0][0], HPos.CENTER);

            // labeling y axis
            for(int i = 1; i < y_len; i++)
            {
                labels[0][i] = new Label(Integer.toString(upper_corner.y - i + 1));
                grid.add(labels[0][i], 0 , i);
                GridPane.setHalignment(labels[0][i], HPos.CENTER);
            }

            // labeling x axis
            for(int i = 1; i < x_len; i++)
            {
                labels[i][0] = new Label(Integer.toString(i - 1 + lower_corner.x));
                grid.add(labels[i][0], i, 0);
                GridPane.setHalignment(labels[i][0], HPos.CENTER);
            }

            // filling map
            for(int i = 1; i < x_len; i++)
                for(int j = 1; j < y_len; j++)
                {
                    Object obj = map.objectAt(new Vector2d(i - 1, j - 1));
                    labels[i][j] = new Label((obj == null) ? " " : obj.toString());
                }

            // adding map to grid
            for(int i = 1; i < x_len; i++)
                for(int j = 1; j < y_len; j++)
                {
                    grid.add(labels[i][j], i, y_len - j);
                    GridPane.setHalignment(labels[i][j], HPos.CENTER);
                }

            //setting rows' sizes
            for(int i = 0; i < x_len; i++)
            {
                ColumnConstraints col1 = new ColumnConstraints();
                col1.setPercentWidth(100);
                grid.getColumnConstraints().add(col1);
            }

            // setting columns' sizes
            for(int i = 0; i < y_len; i++)
            {
                RowConstraints row1 = new RowConstraints();
                row1.setPercentHeight(100);
                grid.getRowConstraints().add(row1);
            }

//            Image hedgehog = new Image("https://thumbs.dreamstime.com/z/je%C5%BC-wype%C5%82niaj%C4%85ca-kontur-ikona-123518185.jpg");
//
//            ImageView view = new ImageView(hedgehog);
//            view.fitHeightProperty().bind(labels[5][5].heightProperty());
//            view.fitWidthProperty().bind(labels[5][5].widthProperty());
//            labels[5][5].setGraphic(view);
//            labels[5][5].setText("");
//
//            ImageView view2 = new ImageView(hedgehog);
//            view2.fitHeightProperty().bind(labels[8][8].heightProperty());
//            view2.fitWidthProperty().bind(labels[8][8].widthProperty());
//            labels[8][8].setGraphic(view2);
//            labels[8][8].setText("");


            Scene scene = new Scene(grid, 500, 500);

            primaryStage.setScene(scene);
            primaryStage.show();
        }

        catch (IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
