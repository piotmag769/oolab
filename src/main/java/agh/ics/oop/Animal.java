package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class Animal {
    private Vector2d position = new Vector2d(2, 2);
    private MapDirection orientation = MapDirection.NORTH;
    private IWorldMap map;
    private List<IPositionChangeObserver> observers= new ArrayList<>();

    public Animal() {}

    public Animal(IWorldMap map)
    {
        this.map = map;
        this.addObserver((IPositionChangeObserver) map);
    }

    public Animal(IWorldMap map, Vector2d initialPosition)
    {
        this.map = map;
        this.position = initialPosition;
        this.addObserver((IPositionChangeObserver) map);
    }

    public Vector2d getPosition()
    {
        return this.position;
    }

//    public String toString()
//    {
//        return this.position.toString() + ' ' + this.orientation.toString();
//    }

    @Override
    public String toString()
    {
        return this.orientation.toString();
    }

    public boolean isAt(Vector2d position)
    {
        return position.equals(this.position);
    }

    public void move(MoveDirection direction)
    {
        switch (direction) {
            case FORWARD -> {
                Vector2d newPosition = this.position.add(this.orientation.toUnitVector());
                if (this.map.canMoveTo(newPosition))
                {
                    this.positionChanged(this.position, newPosition);
                    this.position = newPosition;
                }
            }
            case BACKWARD -> {
                Vector2d newPosition = this.position.subtract(this.orientation.toUnitVector());
                if (this.map.canMoveTo(newPosition))
                {
                    this.positionChanged(this.position, newPosition);
                    this.position = newPosition;
                }
            }
            case RIGHT -> this.orientation = this.orientation.next();
            case LEFT -> this.orientation = this.orientation.previous();
        }
    }

    public void addObserver(IPositionChangeObserver observer)
    {
        this.observers.add(observer);
    }

    public void removeObserver(IPositionChangeObserver observer)
    {
        this.observers.remove(observer);
    }

    private void positionChanged(Vector2d oldPosition, Vector2d newPosition)
    {
        if (this.observers.size() != 0)
            for(IPositionChangeObserver observer: observers)
                observer.positionChanged(oldPosition, newPosition);
    }
}
