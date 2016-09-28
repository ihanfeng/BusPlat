package clusterPoints;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class clusterMachine {
    private List<Double[]> points = new ArrayList<>(); 
    private List<double[]> clusterPoints = new ArrayList<>();
    double[][] normalizations;
    double[][] distanceArray;
    private int maxClusters;
    private boolean hasFile = false;
    private File input = null;
    String fileName;
    private int rows;
    private int cols;

    public clusterMachine() {
        maxClusters = 100;
    }
    
    public List<Double[]> getPoints() {
        return points;
    }
    
    public List<double[]> getClusterPoints() {
        return clusterPoints;
    }
    
    public void setMaxClusters(int number) throws IOException{
        maxClusters = number;
        if(input != null) run(fileName); 
    }
    
    private boolean accessFile(String nameOfFile) {
        //System.out.println("Accessing File.");
        fileName = nameOfFile;
        boolean fileFound = false;
        input = new File(fileName);
        if (input.exists()) {
            fileFound = true;
            hasFile = true;
        }
        return fileFound;
    }
    
    private void acquireData() throws FileNotFoundException, IOException {
        //System.out.println("Acquiring Data.");
        BufferedReader inputSize = new BufferedReader(new FileReader(input));
        BufferedReader inputContents = new BufferedReader(new FileReader(input));
        String[] readLine;
       
        //Find the data dimensions
        readLine = inputSize.readLine().split(",");
        cols = readLine.length;
        rows = 1;
        while (inputSize.readLine() != null) {
        rows++;
        }
        inputSize.close();
        
        for(int i = 0; i < rows; i++) {
            Double[] point = new Double[cols];
            readLine = inputContents.readLine().split(",");
            for(int j = 0; j < cols; j++) {
                point[j] = Double.parseDouble(readLine[j]);
            }
            points.add(point);
        }       
    }
    
    private double[] calcMean() {
        //System.out.println("\tCalculating Means.");
        double[] mean = new double[cols];
        double[] sum = new double[cols];
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                sum[j] += points.get(i)[j];
            }
        }
        
        for(int i = 0; i < cols; i++) {
            mean[i] = sum[i] / rows;
        }
        
        return mean;
    }
    
    private double[] calcStdDev() {
        //System.out.println("\tCalculating Standard Deviations.");
        double[] mean = calcMean();
        double[] variance = new double[cols];
        double[] stdDev = new double[cols];
        
        //Variances
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                variance[j] += Math.pow((points.get(i)[j] - mean[j]),2);
            }
        }
        
        //stdDevs
        for(int i = 0; i < cols; i++) {
            stdDev[i] = Math.sqrt(variance[i]);
        }
        
        return stdDev;
    }
    
    private double[][] calcNormals() {
        //System.out.println("Calculating Normals: ");
        double[] mean = calcMean();
        double[] stdDev = calcStdDev();
        double[][] normalizations = new double[rows][cols];
        
        //Find normalizations. If the stdDev was 0 then the normal is zero anyways
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                if(stdDev[j] != 0) {
                    normalizations[i][j] = (points.get(i)[j] - mean[j] / stdDev[j]);                   
                } else normalizations[i][j] = 0;
            }
        }
        return normalizations;
    }
    
    private void selectInitialClusters() {       
        //System.out.println("Selecting Initial Clusters");
        normalizations = calcNormals();
        Set<double[]> hashSet = new LinkedHashSet<>();
        List<double[]> uniquePoints = new ArrayList<>();
        List<double[]> initialClusterPoints = new ArrayList<>();
        for(int i = 0; i < rows; i++) {
            if(!hashSet.contains(normalizations[i])) {             
                hashSet.add((normalizations[i]));
                uniquePoints.add(normalizations[i]);
            }
        }
        //System.out.println("Number of unique points: " + uniquePoints.size());
        if(uniquePoints.size() < maxClusters) {
            System.out.println("Number of Clusters exceeds number of unique points");
            maxClusters = 10;
        }
        
        Random random = new Random();
        int counter = 0;
        while(counter < maxClusters) {
            int next = random.nextInt(uniquePoints.size());
            if(!clusterPoints.contains(uniquePoints.get(next))) {
                clusterPoints.add(uniquePoints.get(next));
                counter++;
            }
        }
        //System.out.println("The number of chosen Cluster Points: " + clusterPoints.size());
        //System.out.println("Initial Clusters: ");
        for(int i = 0; i < clusterPoints.size(); i++) {
            //System.out.println(clusterPoints.get(i)[0] + "," + clusterPoints.get(i)[1]); 
        }
    }
    
    private double distance(double[] pointA, double[] pointB) {
        double distance = 0;
        for (int i = 0; i < cols; i++) {
            distance += Math.pow((pointA[i] - pointB[i]), 2);
        }
        distance = Math.sqrt(distance);
        return distance;
    }
    
    private void getDistances() {
        int clusterRows = clusterPoints.size();
        distanceArray = new double[rows][clusterRows];

        for (int i = 0; i < clusterRows; i++) {      
            for (int j = 0; j < rows; j++) {         
                distanceArray[j][i] = distance(clusterPoints.get(i), normalizations[j]);      
            }
        }
    }
    
    private int[][] determineCluster2() {
        getDistances();
        int numClusters = clusterPoints.size();
        int[][] inCluster = new int[rows][2];
        for(int i = 0; i < rows; i++) {
            double leastDistance = distanceArray[i][0];
            int cluster = 0;
            for(int j = 0; j < numClusters; j++) {
                if(distanceArray[i][j] < leastDistance) {
                    leastDistance = distanceArray[i][j];
                    cluster = j;
                }
            }
            inCluster[i][1] = cluster;
        }
        return inCluster;
    }
    
    public void refine() {
        List<double[]> newClusterPoints = new ArrayList<>();
        int numClusters = clusterPoints.size();
        int[][] inCluster = determineCluster2();
        int[] numPerCluster = new int[numClusters];
        
        
        for(int i = 0; i < numClusters; i++) {
            double[] newClusterPoint = new double[cols];
            for(int j = 0; j < rows; j++) {
                if(inCluster[j][1] == i) {
                    numPerCluster[i]++;
                    for(int k = 0; k < cols; k++) {
                        newClusterPoint[k] += normalizations[j][k];                       
                    }
                }
            }
            if(numPerCluster[i] > 0) {
                for(int m = 0; m < cols; m++) {
                    newClusterPoint[m] /= numPerCluster[i];
                }
            }
            newClusterPoints.add(newClusterPoint);
        }
        
        clusterPoints = newClusterPoints;
    }
    
    public void refine(int runs) {
        for(int i = 0; i < runs; i++) {
            refine();
        }
    }
    
    public void run(String fileName) throws IOException {
        accessFile(fileName);
        acquireData();
        selectInitialClusters();
    }
}
