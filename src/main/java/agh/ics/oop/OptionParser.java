package agh.ics.oop;

import java.util.ArrayList;

public class OptionParser {
    public static ArrayList<MoveDirection> parse(String[] moves)
    {
        ArrayList<MoveDirection> animal_move = new ArrayList<MoveDirection>();  // VSA - is that okay?

        for (String move: moves)
        {
            switch (move) {
                case "f", "forward" -> animal_move.add(MoveDirection.FORWARD);
                case "b", "backward" -> animal_move.add(MoveDirection.BACKWARD);
                case "r", "right" -> animal_move.add(MoveDirection.RIGHT);
                case "l", "left" -> animal_move.add(MoveDirection.LEFT);
            };
        }

        return animal_move;
    }
}
