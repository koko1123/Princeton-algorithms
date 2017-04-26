/*
 * percolation class
 */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.io.File;
import java.util.Scanner;

public class Percolation {

    private boolean[][] grid;
    private int rowNum;
    private int openSites;
    private boolean percolates;
    private WeightedQuickUnionUF unionFindStructure;
    private int virtualTop;

    public Percolation(int n) {
        this.rowNum = n;
        this.virtualTop = n * n; //index of the virtual top for UnionFind, virtualTop + 1 represents bottom index
        this.grid = new boolean[n][n];
        this.openSites = 0;
        this.percolates = false;
        this.unionFindStructure = new WeightedQuickUnionUF(this.virtualTop + 2);
    }

    public void open(int row,
                     int col) throws Exception {
        if (!isOpen(row, col)) {
            // numerical number if units are assigned left to right, top to bottom
            int currentIndex = (row - 1) * this.rowNum + (col - 1);
            //edges
            if (row == this.rowNum) {
                unionFindStructure.union(currentIndex, this.virtualTop + 1);
            }
            if (row == 1) {
                unionFindStructure.union(currentIndex, this.virtualTop);
            }
            //check surroundings
            // check top
            if (row > 1) {
                if (isOpen(row - 1, col)) {
                    int topIndex = (row - 2) * this.rowNum + (col - 1);
                    unionFindStructure.union(topIndex, currentIndex);
                }
            }
            //bottom
            if (row < this.rowNum) {
                if (isOpen(row + 1, col)) {
                    int topIndex = row * this.rowNum + (col - 1);
                    unionFindStructure.union(topIndex, currentIndex);
                }
            }
            //left
            if (col > 1) {
                if (isOpen(row, col - 1)) {
                    int topIndex = (row - 1) * this.rowNum + (col - 2);
                    unionFindStructure.union(topIndex, currentIndex);
                }
            }
            //right
            if (col < this.rowNum) {
                if (isOpen(row, col + 1)) {
                    int topIndex = (row - 1) * this.rowNum + col;
                    unionFindStructure.union(topIndex, currentIndex);
                }
            }
            //check if adjacent units are open and connect them
            if (unionFindStructure.connected(currentIndex, this.virtualTop) &&
                    unionFindStructure.connected(currentIndex, virtualTop + 1)) {
                //this newly opened unit is connected to both top and bottom
                this.percolates = true;
            }
            this.grid[row - 1][col - 1] = true;
            this.openSites += 1;
        }
    }

    public boolean isOpen(int row,
                          int col) throws Exception {
        if (row > this.rowNum || col > this.rowNum ||
                row < 0 || col < 0) {
            throw new IndexOutOfBoundsException(row + ", " + col);
        }
        //adjust to 0-based index
        return this.grid[row - 1][col - 1];
    }

    public boolean isFull(int row, int col) throws Exception {
        if (isOpen(row, col)) {
            //convert to our UF notation
            int UnionIndex = (row - 1) * this.rowNum + (col - 1);
            return unionFindStructure.connected(UnionIndex, this.virtualTop);
        }
        return false;
    }

    public int numberOfOpenSites() {
        return this.openSites;
    }

    public boolean percolates() {
        return this.percolates;
    }

    public static void main(String[] args) throws Exception {
        File inputFile = new File("C:/Users/Amlandeep Bhadra/Princeton-algorithms/percolation/greeting57.txt");
        Scanner input = new Scanner(inputFile);
        int size = input.nextInt();
        Percolation perc = new Percolation(size);
        while(input.hasNextInt()){
            int x = input.nextInt();
            int y = input.nextInt();
            perc.open(x, y);
        }
        System.out.println(perc.percolates()? "Percolates" : "does not Percolate");
    }
}
