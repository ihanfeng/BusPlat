package clusterPoints;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;

public class ClusterPointsGUI {
    public static void main(String[] args) throws IOException, InterruptedException {
        
        JFrame frame = new JFrame("2d Cluster Points");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        clusterPanel panel = new clusterPanel(700, 700);
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
        
        for(int i = 0; i < 10000; i++) {
            TimeUnit.MILLISECONDS.sleep(100);
            panel.refine();
        }
        
    }
}
