package agh.ics.oop;

public class World {

    public static void main(String[] args)
    {
        System.out.println("system wystartował");

        Direction[] animal_move = convert(args);
        run(animal_move);

        System.out.println("system zakończył działanie");
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
