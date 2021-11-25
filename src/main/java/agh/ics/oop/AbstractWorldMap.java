package agh.ics.oop;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {

    protected Map<Vector2d, Animal> animals_hash = new HashMap<>();
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
            this.animals_hash.put(position, animal);
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
        return this.animals_hash.get(position);
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition)
    {
        Animal animal = this.animals_hash.get(oldPosition);
        this.animals_hash.remove(oldPosition);
        this.animals_hash.put(newPosition, animal);
    }

    @Override
    public String toString()
    {
        this.update_bounds();
        return this.MV.draw(this.lower_corner, this.upper_corner);
    }
}
