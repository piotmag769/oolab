package agh.ics.oop;

import java.util.ArrayList;

public class RectangularMap extends AbstractWorldMap implements IWorldMap{

    public RectangularMap(int width, int height)
    {
        this.upper_corner = new Vector2d(width, height);
        this.MV = new MapVisualizer(this);
        this.animals = new ArrayList<>();
    }

    @Override
    public boolean canMoveTo(Vector2d position)
    {
        Vector2d lower_corner = new Vector2d(0, 0);
        return !this.isOccupied(position) && position.upperRight(upper_corner).equals(upper_corner) && position.lowerLeft(lower_corner).equals(lower_corner);
    }
}
