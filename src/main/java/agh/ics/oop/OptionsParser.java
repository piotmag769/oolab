package agh.ics.oop;

import java.util.ArrayList;

public class OptionsParser {
    public static ArrayList<MoveDirection> parse(String[] moves)
    {
        ArrayList<MoveDirection> animal_moves = new ArrayList<MoveDirection>();

        for (String move: moves)
        {
            switch (move) {
                case "f", "forward" -> animal_moves.add(MoveDirection.FORWARD);
                case "b", "backward" -> animal_moves.add(MoveDirection.BACKWARD);
                case "r", "right" -> animal_moves.add(MoveDirection.RIGHT);
                case "l", "left" -> animal_moves.add(MoveDirection.LEFT);
            };
        }

        return animal_moves;
    }
}
