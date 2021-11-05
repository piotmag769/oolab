package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class SimulationEngine implements IEngine{
    private List<MoveDirection> directions;
    private IWorldMap map;
    private List<Animal> animals = new ArrayList<>();

    public SimulationEngine(List<MoveDirection> directions, IWorldMap map, Vector2d[] positions)
    {
        this.directions = directions;
        this.map = map;

        if (positions.length > 0)
            for (Vector2d position: positions)
            {
                Animal zwierzomtko = new Animal(map, position);
                map.place(zwierzomtko);
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
        for(int i = 0; i < this.directions.size(); i++) {
            this.animals.get(i % n).move(this.directions.get(i));
            System.out.println(this.map);
        }
    }
}
