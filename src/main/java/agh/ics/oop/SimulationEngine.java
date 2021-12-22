package agh.ics.oop;

import agh.ics.oop.gui.App;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

public class SimulationEngine implements IEngine, Runnable{
    private List<MoveDirection> directions;
    private IWorldMap map;
    private List<Animal> animals;
    private App app;

    public SimulationEngine(List<MoveDirection> directions, IWorldMap map, Vector2d[] positions)
    {
        this.directions = directions;
        this.map = map;
        this.animals = new ArrayList<>();

        if (positions.length > 0)
            for (Vector2d position: positions)
            {
                Animal zwierzomtko = new Animal(map, position);
                this.map.place(zwierzomtko);
                this.animals.add(zwierzomtko);
            }
        System.out.println(map);
    }

    public List<Animal> list_animals()
    {
        return this.animals;
    }

    @Override
    public void run()
    {
        int n = this.animals.size();

        for(int i = 0; i < this.directions.size(); i++)
        {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }

            int finalI = i;
            Platform.runLater(() -> {
                this.animals.get(finalI % n).move(this.directions.get(finalI));

                app.grid.setGridLinesVisible(false);
                app.grid.getChildren().clear();

                app.grid.setGridLinesVisible(true);
                app.create_and_add_axis_labels(app.grid);

                app.create_and_add_elements(app.grid);

                System.out.println(this.map);}
            );
        }
    }

    public void setApp(App app)
    {
        this.app = app;
    }

    public int getDirectionLength()
    {
        return this.directions.size();
    }

    public void setDirections(List<MoveDirection> directions)
    {
        this.directions = directions;
    }
}
