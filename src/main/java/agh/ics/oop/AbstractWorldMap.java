package agh.ics.oop;

import java.util.List;

public abstract class AbstractWorldMap implements IWorldMap {

    protected List<Grass> tufts;
    protected List<Animal> animals;
    protected MapVisualizer MV;
    protected Vector2d upper_corner;

    abstract public boolean canMoveTo(Vector2d position);

    public boolean place(Animal animal)
    {
        Vector2d position = animal.tell_position();

        if (this.canMoveTo(position))
        {
            this.animals.add(animal);
            if (this instanceof GrassField)
                this.upper_corner = upper_corner.upperRight(position);
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

        if (this instanceof GrassField)
            if (this.tufts.size() != 0)
                for(Grass grass: this.tufts)
                    if (grass.getPosition().equals(position))
                        return grass;

        return null;
    }

    @Override
    public String toString()
    {
        return this.MV.draw(new Vector2d(0,0), this.upper_corner);
    }
}
