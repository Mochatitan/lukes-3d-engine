import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
public class Data {
    ArrayList<Triangle> tris = new ArrayList<Triangle>();
        public void setup(){
        tris.add(new Triangle(new Vertex(100, 100, 100),
                            new Vertex(-100, -100, 100),
                            new Vertex(-100, 100, -100),
                            Color.WHITE));
        tris.add(new Triangle(new Vertex(100, 100, 100),
                            new Vertex(-100, -100, 100),
                            new Vertex(100, -100, -100),
                            Color.RED));
        tris.add(new Triangle(new Vertex(-100, 100, -100),
                            new Vertex(100, -100, -100),
                            new Vertex(100, 100, 100),
                            Color.GREEN));
        tris.add(new Triangle(new Vertex(-100, 100, -100),
                            new Vertex(100, -100, -100),
                            new Vertex(-100, -100, 100),
                            Color.BLUE));
        }
        
}
