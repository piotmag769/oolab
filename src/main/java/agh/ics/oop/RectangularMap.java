package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class RectangularMap implements IWorldMap{

    private int width, height;
    private List<Animal> animals = new ArrayList<>();
    MapVisualizer MV;

    public RectangularMap(int width, int height)
    {
        this.width = width;
        this.height = height;
        this.MV= new MapVisualizer(this);
    }

    @Override
    public boolean canMoveTo(Vector2d position)
    {
        Vector2d upper_corner = new Vector2d(this.width, this.height);
        Vector2d lower_corner = new Vector2d(0, 0);
        return !this.isOccupied(position) && position.upperRight(upper_corner).equals(upper_corner) && position.lowerLeft(lower_corner).equals(lower_corner);
    }

    @Override
    public boolean place(Animal animal)
    {
        Vector2d position = animal.tell_position();
        if (this.canMoveTo(position))
        {
            this.animals.add(animal);
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position)
    {
        if (this.animals.size() != 0)
            for(Animal animal: this.animals)
            {
                if (animal.isAt(position))
                    return true;
            }
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        if (this.animals.size() != 0)
            for(Animal animal: this.animals)
            {
                if (animal.isAt(position))
                    return animal;
            }
        return null;
    }

    @Override
    public String toString()
    {
        return this.MV.draw(new Vector2d(0,0), new Vector2d(this.width, this.height));
    }
}
