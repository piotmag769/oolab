package agh.ics.oop;

public class World {

    public static void main(String[] args)
    {
        System.out.println("system wystartował");

        Direction[] animal_move = convert(args);
        run(animal_move);

        System.out.println("system zakończył działanie");

        Vector2d position1 = new Vector2d(1,2);
        System.out.println(position1);
        Vector2d position2 = new Vector2d(-2,1);
        System.out.println(position2);
        System.out.println(position1.add(position2));

        Animal Pufu = new Animal();

        Pufu.move(MoveDirection.RIGHT);
        Pufu.move(MoveDirection.FORWARD);

        Pufu.move(MoveDirection.FORWARD);
        Pufu.move(MoveDirection.FORWARD);

        System.out.println(Pufu);
    }


    static Direction[] convert(String[] args)
    {
        Direction[] animal_move = new Direction[args.length];  // VSA - is that okay?

        for (int i = 0; i < args.length; i++)
        {
            Direction move = switch (args[i]) {
                case "f" -> Direction.FORWARD;
                case "b" -> Direction.BACKWARD;
                case "r" -> Direction.RIGHT;
                case "l" -> Direction.LEFT;
                default -> Direction.ERROR;
            };

            animal_move[i] = move;
        }

        return animal_move;
    }

    static void run(Direction[] animal_move)
    {

        for (Direction move : animal_move)
        {
            String message = switch (move) {
                case FORWARD -> "idzie do przodu";
                case BACKWARD -> "idzie do tyłu";
                case RIGHT -> "idzie w prawo";
                case LEFT -> "idzie w lewo";
                case ERROR -> "";
            };

            if (!message.equals(""))
                System.out.println("Zwierzak " + message);
        }
    }
}
