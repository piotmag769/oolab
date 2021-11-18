package agh.ics.oop;
import java.util.ArrayList;
import java.util.List;


import static java.lang.Math.max;
import static java.lang.Math.sqrt;

public class GrassField extends AbstractWorldMap implements IWorldMap{

    public GrassField(int n)
    {
        this.upper_corner = new Vector2d(0, 0);
        int x, y;
        Vector2d temp;
        this.MV= new MapVisualizer(this);
        this.animals = new ArrayList<>();
        this.tufts = new ArrayList<>();
        int bound = (int) sqrt(10*n);

        for(int i = 0; i < n; i++)
        {
            do
            {
                x = (int)(Math.random()*(bound + 1));
                y = (int)(Math.random()*(bound + 1));
                temp = new Vector2d(x, y);
            } while(isOccupied(temp));

            this.tufts.add(new Grass(temp));
            this.upper_corner = this.upper_corner.upperRight(temp);
        }
    }


    @Override
    public boolean canMoveTo(Vector2d position)
    {
        Object obj = this.objectAt(position);
        return obj == null || obj instanceof Grass;
    }
}
