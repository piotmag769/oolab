package agh.ics.oop;

import static java.lang.Math.sqrt;

public class GrassField extends AbstractWorldMap implements IWorldMap{

    public GrassField(int n)
    {
        int x, y;
        Vector2d temp;
        int bound = (int) sqrt(10*n);

        this.upper_corner = new Vector2d(0, 0);
        this.lower_corner = new Vector2d(bound, bound);

        for(int i = 0; i < n; i++)
        {
            do
            {
                x = (int)(Math.random()*(bound + 1));
                y = (int)(Math.random()*(bound + 1));
                temp = new Vector2d(x, y);
            } while(isOccupied(temp));

            this.tufts.add(new Grass(temp));
        }

        this.update_bounds();
    }


    @Override
    public boolean canMoveTo(Vector2d position)
    {
        Object obj = this.objectAt(position);
        return obj == null || obj instanceof Grass;
    }


    @Override
    public void update_bounds()
    {
        if (this.animals.size() != 0)
            for (Animal animal : this.animals)
            {
                this.lower_corner = this.lower_corner.lowerLeft(animal.getPosition());
                this.upper_corner = this.upper_corner.upperRight(animal.getPosition());
            }

        if (this.tufts.size() != 0)
            for(Grass grass: this.tufts)
            {
                this.lower_corner = this.lower_corner.lowerLeft(grass.getPosition());
                this.upper_corner = this.upper_corner.upperRight(grass.getPosition());
            }
    }
}
