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
    // temporary fields to simplify the code
    private Vector2d lower_corner;
    private Vector2d upper_corner;

    private int x_len;
    private int y_len;

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

            // temporary fields to simplify the code
            this.lower_corner = map.get_lower_corner();
            this.upper_corner = map.get_upper_corner();

            this.x_len = upper_corner.x - lower_corner.x + 2;
            this.y_len = upper_corner.y - lower_corner.y + 2;

            //gui part

            Label[][] labels = this.create_labels(map);

            GridPane grid = new GridPane();
            grid.setGridLinesVisible(true);

            this.add_labels_to_grid(grid, labels);

            this.set_col_row_sizes(grid);

            this.center_labels(labels);
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

    private Label[][] create_labels(IWorldMap map)
    {
        Label[][] labels = new Label[x_len][y_len];

        // x\y label
        labels[0][0] = new Label("y\\x");

        // labeling y axis
        for(int i = 1; i < y_len; i++)
            labels[0][i] = new Label(Integer.toString(upper_corner.y - i + 1));


        // labeling x axis
        for(int i = 1; i < x_len; i++)
            labels[i][0] = new Label(Integer.toString(i - 1 + lower_corner.x));


        // filling map
        for(int i = 1; i < x_len; i++)
            for(int j = 1; j < y_len; j++)
            {
                // labels[i][j] refers to position (i - 1 + lower_corner.x, j - 1 + lower_corner.y) on the map
                Object obj = map.objectAt(new Vector2d(lower_corner.x + i - 1, lower_corner.y + j - 1));
                labels[i][j] = new Label((obj == null) ? " " : obj.toString());
            }

        return labels;
    }

    private void add_labels_to_grid(GridPane grid, Label[][] labels)
    {
        // adding x\y label
        grid.add(labels[0][0], 0, 0);


        // adding y axis
        for(int i = 1; i < y_len; i++)
            grid.add(labels[0][i], 0, i);

        // adding x axis
        for(int i = 1; i < x_len; i++)
            grid.add(labels[i][0], i, 0);

        // adding map
        for(int i = 1; i < x_len; i++)
            for(int j = 1; j < y_len; j++)
                // labels[i][j] refers to position (i, y_len - j) on the grid
                grid.add(labels[i][j], i, y_len - j);
    }

    private void set_col_row_sizes(GridPane grid)
    {
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
    }

    private void center_labels(Label[][] labels)
    {
        for(int i = 0; i < x_len; i++)
            for(int j = 0; j < y_len; j++)
                GridPane.setHalignment(labels[i][j], HPos.CENTER);
    }
}
