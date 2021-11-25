package agh.ics.oop;

import java.util.ArrayList;

public class World {

    public static void main(String[] args)
    {
        System.out.println("system wystartował");

//        Direction[] animal_move = convert(args);
//        run(animal_move);
//
//        Vector2d position1 = new Vector2d(1,2);
//        System.out.println(position1);
//        Vector2d position2 = new Vector2d(-2,1);
//        System.out.println(position2);
//        System.out.println(position1.add(position2));

/** doesn't work anymore after modyfying move with canMove usage - in need to run try: "git checkout tags/lab3 -b lab3_branch"**/
//        Animal Pufu = new Animal();
//
//        ArrayList <MoveDirection> animal_moves = OptionParser.parse(new String[]{"r", "f", "f", "g", "f"});
//        for (MoveDirection a_move: animal_moves)
//            Pufu.move(a_move);
//
//        System.out.println(Pufu);

        ArrayList<MoveDirection> directions = OptionsParser.parse(args);
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();

        GrassField GF = new GrassField(5);
        GF.place(new Animal(GF, new Vector2d(-1, 6)));
        System.out.println(GF);

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
