package agh.ics.oop;

public class Animal {
    private Vector2d position = new Vector2d(2, 2);
    private MapDirection orientation = MapDirection.NORTH;
    private IWorldMap map;

    public Animal() {}

    public Animal(IWorldMap map)
    {
        this.map = map;
    }

    public Animal(IWorldMap map, Vector2d initialPosition)
    {
        this.map = map;
        this.position = initialPosition;
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
                Vector2d tmp1 = this.position.add(this.orientation.toUnitVector());
//                if (tmp1.upperRight(new Vector2d(4, 4)).equals(new Vector2d(4, 4)) && tmp1.lowerLeft(new Vector2d(0, 0)).equals(new Vector2d(0, 0)))
                if (this.map.canMoveTo(tmp1))
                    this.position = tmp1;
            }
            case BACKWARD -> {
                Vector2d tmp2 = this.position.subtract(this.orientation.toUnitVector());
//                if (tmp2.upperRight(new Vector2d(4, 4)).equals(new Vector2d(4, 4)) && tmp2.lowerLeft(new Vector2d(0, 0)).equals(new Vector2d(0, 0)))
                if (this.map.canMoveTo(tmp2))
                    this.position = tmp2;
            }
            case RIGHT -> this.orientation = this.orientation.next();
            case LEFT -> this.orientation = this.orientation.previous();
        }
    }
}
