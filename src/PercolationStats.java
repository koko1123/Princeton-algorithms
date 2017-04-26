
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double stddev;
    private double mean;
    private double confidenceFactor;

    public PercolationStats(int n, int trials) {
        double[] results = new double[n];
        // perform trials
        for(int i =0; i < trials; i++) {
            results[i] = doTrial(n);
        }
        System.out.println("Here! trials done!");
        this.mean = StdStats.mean(results);
        this.stddev = StdStats.stddev(results);
        this.confidenceFactor = (1.96 * this.stddev) / Math.sqrt(trials);
    }

    private double doTrial(int n) {
        // All hail Java garbage collector
        Percolation percolationStructure = new Percolation(n);
        while(!percolationStructure.percolates()) {
            System.out.println(percolationStructure.percolates());
            int i = (int) (1 + (StdRandom.uniform() * (n - 1)));
            int j = (int) (1 + (StdRandom.uniform() * (n - 1)));
            try {
                if(!percolationStructure.isOpen(i, j)){
                    percolationStructure.open(i,j);
                }
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
        return percolationStructure.numberOfOpenSites()/ (n*n);
    }

    public double mean() {
        return this.mean;
    }

    public double stddev() {
        return this.stddev;
    }

    public double confidenceLo() {
        return this.mean - this.confidenceFactor;
    }

    public double confidenceHi() {
        return this.mean + this.confidenceFactor;
    }

    public static void main(String[] args) {
        int n = 200;//Integer.parseInt(args[0]);
        int trials = 100;//Integer.parseInt(args[1]);
        PercolationStats perc = new PercolationStats(n, trials);
        System.out.println("completed!");
        System.out.println("mean                    = " + perc.mean());
        System.out.println("stddev                  = " + perc.stddev());
        System.out.println("95% confidence interval = " + "[" + perc.confidenceLo() +", " + perc.confidenceHi() +"]");
    }
}
