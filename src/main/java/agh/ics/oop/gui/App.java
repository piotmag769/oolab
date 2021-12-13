package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import java.util.ArrayList;

public class App extends Application{
    // temporary fields to simplify the code
    private Vector2d lower_corner;
    private Vector2d upper_corner;

    private int x_len;
    private int y_len;

    private IWorldMap map;
    private int moveDelay = 2000;
    private SimulationEngine engine;
    public GridPane grid;
    public Stage primaryStage;

    @Override
    public void init() throws Exception
    {
        try
        {
            ArrayList<MoveDirection> directions = OptionsParser.parse(getParameters().getRaw());
            GrassField map = new GrassField(10);
            Vector2d[] positions = {new Vector2d(4, 4), new Vector2d(7, 7)};

            this.engine = new SimulationEngine(directions, map, positions);
            this.engine.setApp(this);
            this.map = map;

            // temporary fields to simplify the code
            this.lower_corner = map.get_lower_corner();
            this.upper_corner = map.get_upper_corner();

            this.x_len = upper_corner.x - lower_corner.x + 1;
            this.y_len = upper_corner.y - lower_corner.y + 1;


        }
        catch (IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void start(Stage primaryStage) throws InterruptedException {
        //gui part
        this.primaryStage = primaryStage;

        this.grid = new GridPane();

        grid.setGridLinesVisible(true);

        this.create_and_add_axis_labels(grid);

        this.create_and_add_elements(grid);

        this.set_col_row_sizes(grid);

        Scene scene = new Scene(grid, 500, 500);

        primaryStage.setScene(scene);
        primaryStage.show();

        Thread engineThread = new Thread(engine);

        engineThread.setDaemon(true);
        engineThread.start();
    }

    public void create_and_add_axis_labels(GridPane grid)
    {
        // y/x label
        Label label1 = new Label("y\\x");
        GridPane.setHalignment(label1, HPos.CENTER);
        grid.add(label1, 0, 0);

        // labeling y axis
        for(int i = 0; i < y_len + 1; i++)
        {
            Label label = new Label(Integer.toString(upper_corner.y - i));
            GridPane.setHalignment(label, HPos.CENTER);
            grid.add(label, 0, i + 1);
        }

        // labeling x axis
        for(int i = 0; i < x_len + 1; i++)
        {
            Label label = new Label(Integer.toString(i + lower_corner.x));
            GridPane.setHalignment(label, HPos.CENTER);
            grid.add(label, i + 1, 0);
        }
    }

    public void create_and_add_elements(GridPane grid)
    {
        // filling map
        for(int i = 0; i < x_len; i++)
            for(int j = 0; j < y_len; j++)
            {
                // buttons[i][j] refers to position (i + lower_corner.x, j + lower_corner.y) on the map
                Object obj = map.objectAt(new Vector2d(lower_corner.x + i, lower_corner.y + j));

                if (obj != null)
                {
                    Labeled node = GuiElementButton.createElement((IMapElement) obj);
                    GridPane.setHalignment(node, HPos.CENTER);
                    grid.add(node, i + 1, y_len - j);
                }
            }
    }

    public void set_col_row_sizes(GridPane grid)
    {
        //setting rows' sizes
        for(int i = 0; i < x_len + 1; i++)
        {
            ColumnConstraints col1 = new ColumnConstraints();
            col1.setPercentWidth(100);
            col1.setFillWidth(Boolean.TRUE);
            grid.getColumnConstraints().add(col1);
        }

        // setting columns' sizes
        for(int i = 0; i < y_len + 1; i++)
        {
            RowConstraints row1 = new RowConstraints();
            row1.setPercentHeight(100);
//            row1.setFillHeight(Boolean.TRUE);
            grid.getRowConstraints().add(row1);
        }
    }
}
