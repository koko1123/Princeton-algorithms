package UnionFind;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

import java.util.ArrayList;
import java.util.List;

class Pair {
    int i;
    int j;

    Pair(int i, int j) {
        this.i = i;
        this.j = j;
    }
}

public class PercolationStats {
    private double stddev;
    private double mean;
    private double confidenceFactor;

    public PercolationStats(int n, int trials) {
        double[] results = new double[trials];
        // perform trials
        for (int i = 0; i < trials; i++) {
            results[i] = doTrial(n);
        }
        this.mean = StdStats.mean(results);
        this.stddev = StdStats.stddev(results);
        this.confidenceFactor = (1.96 * this.stddev) / Math.sqrt(trials);
    }

    private double doTrial(int n) {
        // All hail Java garbage collector
        Percolation percolationStructure = new Percolation(n);
        List<Pair> openSlots = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                openSlots.add(new Pair(i, j));
            }
        }
        while (!percolationStructure.percolates()) {
            int randomOpenPair = (int) (StdRandom.uniform() * openSlots.size());
            try {
                percolationStructure.open(openSlots.get(randomOpenPair).i, openSlots.get(randomOpenPair).j);
                openSlots.remove(randomOpenPair);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
        return (double) percolationStructure.numberOfOpenSites() / (double) (n * n);
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
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        if(n < 1 || trials < 1) {
            throw new IllegalArgumentException(n +", " + trials);
        }
        PercolationStats perc = new PercolationStats(n, trials);
        System.out.println("mean                    = " + perc.mean());
        System.out.println("stddev                  = " + perc.stddev());
        System.out.println("95% confidence interval = " + "[" + perc.confidenceLo() + ", " + perc.confidenceHi() + "]");
    }
}
