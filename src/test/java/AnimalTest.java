import agh.ics.oop.MoveDirection;
import agh.ics.oop.OptionParser;
//import agh.ics.oop.Vector2d;
import agh.ics.oop.Animal;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnimalTest {
    @Test
    public void move_test()
    {
        Animal Pufu = new Animal();
        //tab wejściowa - OK, orientacja i przemieszczenie - OK, mapa - OK
        ArrayList <MoveDirection> animal_moves = OptionParser.parse(new String[]{"r", "f", "f", "g", "f", "r", "p", "b", "l"});
        ArrayList <MoveDirection> check = new ArrayList<MoveDirection>(Arrays.asList(MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.RIGHT, MoveDirection.BACKWARD, MoveDirection.LEFT));

        assertEquals(animal_moves, check);

        String[] correct = {"(2,2) Wschód", "(3,2) Wschód", "(4,2) Wschód", "(4,2) Wschód", "(4,2) Południe", "(4,3) Południe", "(4,3) Wschód"};
        for(int i = 0; i < correct.length; i++)
        {
            Pufu.move(animal_moves.get(i));
            assertEquals(correct[i], Pufu.toString());
        }
    }



    // mechanizm wykluczający 2 zwierzęta w tym samym miejscu - klasa Map pamiętająca położenia zwierząt
}
