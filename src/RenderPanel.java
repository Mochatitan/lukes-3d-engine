import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

public class RenderPanel extends JPanel implements MouseMotionListener{

    public static final boolean SHADING = false;
    Data data = new Data();

    int x = 0;
    int y = 0;

    public RenderPanel(){
        addMouseMotionListener(this);
        data.setup();
    }
    public void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                    g2.setColor(Color.BLACK);
                    g2.fillRect(0, 0, getWidth(), getHeight());

                    ArrayList<Triangle> tris = new ArrayList<Triangle>();
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

                                          double heading = Math.toRadians(x);
                                        Matrix3 headingTransform = new Matrix3(new double[]{
                                                    Math.cos(heading), 0, -Math.sin(heading),
                                                    0, 1, 0,
                                                    Math.sin(heading), 0, Math.cos(heading)
                                            });
                                         double pitch = Math.toRadians(y);
                                            Matrix3 pitchTransform = new Matrix3(new double[]{
                                                    1, 0, 0,
                                                    0, Math.cos(pitch), Math.sin(pitch),
                                                    0, -Math.sin(pitch), Math.cos(pitch)
                                            });
                                        // Merge matrices in advance
                                        Matrix3 transform = headingTransform.multiply(pitchTransform);

                                          g2.translate(getWidth() / 2, getHeight() / 2);
                                          g2.setColor(Color.WHITE);
                                          for (Triangle t : tris) {
                                              Vertex v1 = transform.transform(t.v1);
                                              Vertex v2 = transform.transform(t.v2);
                                              Vertex v3 = transform.transform(t.v3);
                                              Path2D path = new Path2D.Double();
                                              path.moveTo(v1.x, v1.y);
                                              path.lineTo(v2.x, v2.y);
                                              path.lineTo(v3.x, v3.y);
                                              path.closePath();
                                              g2.draw(path);
                                          }
                }
            


        
        public Color getShade(Color color, double shade) {
            double redLinear = Math.pow(color.getRed(), 2.4) * shade;
            double greenLinear = Math.pow(color.getGreen(), 2.4) * shade;
            double blueLinear = Math.pow(color.getBlue(), 2.4) * shade;
    
            int red = (int) Math.pow(redLinear, 1/2.4);
            int green = (int) Math.pow(greenLinear, 1/2.4);
            int blue = (int) Math.pow(blueLinear, 1/2.4);
    
            return new Color(red, green, blue);
        }
        public ArrayList<Triangle> inflate(ArrayList<Triangle> tris) {
            ArrayList<Triangle> result = new ArrayList<>();
            for (Triangle t : tris) {
                Vertex m1 = new Vertex((t.v1.x + t.v2.x)/2, (t.v1.y + t.v2.y)/2, (t.v1.z + t.v2.z)/2);
                Vertex m2 = new Vertex((t.v2.x + t.v3.x)/2, (t.v2.y + t.v3.y)/2, (t.v2.z + t.v3.z)/2);
                Vertex m3 = new Vertex((t.v1.x + t.v3.x)/2, (t.v1.y + t.v3.y)/2, (t.v1.z + t.v3.z)/2);
                result.add(new Triangle(t.v1, m1, m3, t.color));
                result.add(new Triangle(t.v2, m1, m2, t.color));
                result.add(new Triangle(t.v3, m2, m3, t.color));
                result.add(new Triangle(m1, m2, m3, t.color));
            }
            for (Triangle t : result) {
                for (Vertex v : new Vertex[] { t.v1, t.v2, t.v3 }) {
                    double l = Math.sqrt(v.x * v.x + v.y * v.y + v.z * v.z) / Math.sqrt(30000);
                    v.x /= l;
                    v.y /= l;
                    v.z /= l;
                }
            }
            return result;
        }
        @Override
        public void mouseDragged(MouseEvent e) {
            System.out.println("mouse dragged");
            double yi = 180.0 / this.getHeight();
                double xi = 180.0 / this.getWidth();
                x = (int) (e.getX() * xi);
                y = -(int) (e.getY() * yi);
                this.repaint();

        }
        @Override
        public void mouseMoved(MouseEvent e) {
            System.out.println("mouse moved");
        }
}
