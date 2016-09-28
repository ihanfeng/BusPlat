package clusterPoints;
import javax.swing.JPanel;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class clusterPanel extends JPanel {
    public clusterMachine clusterMachine;
    List<Double[]> points;
    List<double[]> clusterPoints;
    int count;
    int size = 10;
    int spacing = 30;
    
    public clusterPanel(int x, int y) throws IOException {
        setBackground(Color.white);
        int width = x;
        int height = y;
        setPreferredSize(new Dimension(width, height)); 
        clusterMachine = new clusterMachine();
        clusterMachine.run("2dPoints.txt");
        points = new ArrayList<>();
        clusterPoints = new ArrayList<>();
        count = 0;
    }
    
    @Override
    public void paintComponent(Graphics page) {
        super.paintComponent(page);
        points = clusterMachine.getPoints();
        clusterPoints = clusterMachine.getClusterPoints();
        page.setColor(Color.blue);
        for (int i = 0; i < points.size(); i++) {
            int x = points.get(i)[0].intValue() + spacing;
            int y = points.get(i)[1].intValue() + spacing;
            
            page.drawLine(x-1, y-1, x+1, y+1);
            page.drawLine(x-1, y+1, x+1, y-1);
        }
        
        page.setColor(Color.red);
        for(int i = 0; i < clusterPoints.size(); i++) {
            int x = (int) clusterPoints.get(i)[0] + spacing;
            int y = (int) clusterPoints.get(i)[1] + spacing;
            page.fillOval(x-size,y-size,size,size);
        }
        
        page.setColor(Color.black);
        page.drawString("Refinements: " + count, 10, 10);
    }
    
    public void refine() {
        clusterMachine.refine();
        count++;
        repaint();
    }
    
    

    
}
