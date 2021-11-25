package agh.ics.oop;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractWorldMap implements IWorldMap {

    protected List<Animal> animals = new ArrayList<>();
    protected MapVisualizer MV = new MapVisualizer(this);
    protected Vector2d upper_corner;
    protected Vector2d lower_corner;

    abstract public boolean canMoveTo(Vector2d position);

    abstract public void update_bounds();

    public boolean place(Animal animal)
    {
        Vector2d position = animal.getPosition();

        if (this.canMoveTo(position))
        {
            this.animals.add(animal);
            return true;
        }

        return false;
    }


    public boolean isOccupied(Vector2d position)
    {
        return this.objectAt(position) != null;
    }


    public Object objectAt(Vector2d position)
    {
        if (this.animals.size() != 0)
            for(Animal animal: this.animals)
                if (animal.isAt(position))
                    return animal;

        return null;
    }

    @Override
    public String toString()
    {
        this.update_bounds();
        return this.MV.draw(this.lower_corner, this.upper_corner);
    }
}
