
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double results[];
    private double stddev;
    private double mean;
    private int trials;
    private double confidenceFactor;

    public PercolationStats(int n, int trials) {
        this.trials = trials;
        this.results = new double[n];
        // perform trials
        for(int i =0; i < trials; i++) {
            this.results[i] = doTrial(n);
        }
        this.mean = StdStats.mean(this.results);
        this.stddev = StdStats.stddev(this.results);
        this.confidenceFactor = (1.96 * this.stddev) / Math.sqrt(this.trials);
    }

    private double doTrial(int n) {
        // All hail Java garbage collector
        Percolation percolationStructure = new Percolation(n);

        return 0.0;
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
    }
}
