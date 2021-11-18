import agh.ics.oop.*;
import org.junit.jupiter.api.Test;

import javax.swing.text.html.Option;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertTrue;

public class SimulationEngineTest {

    @Test
    void walk_to_edges_test()
    {
        List<MoveDirection> directions = OptionsParser.parse(new String[]{"f", "r", "f", "r", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f"});
        IWorldMap map = new RectangularMap(4, 5);
        Vector2d[] positions = {new Vector2d(0,0), new Vector2d(4, 5)};
        SimulationEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();

        Vector2d[] test_vectors = {new Vector2d(0, 5), new Vector2d(4, 0)};
        List<Animal> animals = engine.list_animals();

        for(int i = 0; i < test_vectors.length; i++)
            assertTrue(animals.get(i).isAt(test_vectors[i]));
    }

    @Test
    void walk_to_corners_test()
    {
        List<MoveDirection> directions = OptionsParser.parse(new String[]{"f", "f", "b", "b", "f", "f", "b", "b", "l", "r", "l", "r", "f", "f", "f", "f", "f", "f", "f", "f"});
        IWorldMap map = new RectangularMap(5, 5);
        Vector2d[] positions = {new Vector2d(2, 3), new Vector2d(3, 3), new Vector2d(2, 2), new Vector2d(3, 2)};
        SimulationEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();

        Vector2d[] test_vectors = {new Vector2d(0, 5), new Vector2d(5, 5), new Vector2d(0, 0), new Vector2d(5, 0)};
        List<Animal> animals = engine.list_animals();

        for(int i = 0; i < test_vectors.length; i++)
            assertTrue(animals.get(i).isAt(test_vectors[i]));
    }

    @Test
    void confront_animals_test()
    {
        List<MoveDirection> directions = OptionsParser.parse(new String[]{"r", "l", "f", "f", "f", "f", "f", "f", "f", "f", "f"});
        IWorldMap map = new RectangularMap(8, 7);
        Vector2d[] positions = {new Vector2d(0,5), new Vector2d(8, 5)};
        SimulationEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();

        Vector2d[] test_vectors = {new Vector2d(4, 5), new Vector2d(5, 5)};
        List<Animal> animals = engine.list_animals();

        for(int i = 0; i < test_vectors.length; i++)
            assertTrue(animals.get(i).isAt(test_vectors[i]));
    }
}
