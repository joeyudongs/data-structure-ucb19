package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] grid;
    private int size;
    private int top;
    private int bottom;
    private WeightedQuickUnionUF unionSet;
    private WeightedQuickUnionUF unionSetTopOnly;
    private int numOpenSites = 0;

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        grid = new boolean[N][N];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j] = false;
            }
        }
        size = N;
        top = 0;
        bottom = N*N + 1;       // virtual bottom site
        unionSet = new WeightedQuickUnionUF(N*N + 2);
        unionSetTopOnly = new WeightedQuickUnionUF(N*N + 1);
    }
    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);
        if (!isOpen(row, col)) {
            grid[row][col] = true;
            numOpenSites += 1;
        }
        // first row in the grid, connect to the head
        if (row == 0) {
            unionSet.union(xyTo1D(row, col), top);
            unionSetTopOnly.union(xyTo1D(row, col), top);
        }
        // bottom row in the grid, connect to the tail
        if (row == size - 1) {
            unionSet.union(xyTo1D(row, col), bottom);
        }
        // Check current site's neighbours is opened or not, Union the opened sites
        if (row > 0 && isOpen(row-1, col)) {
            unionSet.union(xyTo1D(row, col), xyTo1D(row-1, col));
            unionSetTopOnly.union(xyTo1D(row, col), xyTo1D(row-1, col));
        }
        if (row > 0 && isOpen(row+1, col)) {
            unionSet.union(xyTo1D(row, col), xyTo1D(row+1, col));
            unionSetTopOnly.union(xyTo1D(row, col), xyTo1D(row+1, col));
        }
        if (row > 0 && isOpen(row, col-1)) {
            unionSet.union(xyTo1D(row, col), xyTo1D(row, col-1));
            unionSetTopOnly.union(xyTo1D(row, col), xyTo1D(row, col-1));
        }
        if (row > 0 && isOpen(row, col+1)) {
            unionSet.union(xyTo1D(row, col), xyTo1D(row, col+1));
            unionSetTopOnly.union(xyTo1D(row, col), xyTo1D(row, col+1));
        }
    }
    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return grid[row][col];
    }
    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row, col);
        return unionSetTopOnly.connected(xyTo1D(row, col), top);
    }
    // number of open sites
    public int numberOfOpenSites() {
        return numOpenSites;
    }
    // does the system percolate?
    public boolean percolates() {
        return unionSet.connected(top, bottom);
    }

    private void validate(int r, int c) {
        if (r < 0 || r >= size || c < 0 || c >= size) {
            throw new IndexOutOfBoundsException();
        }
    }
    private int xyTo1D(int r, int c) {
        return r*size + c + 1;
    }
    // use for unit testing (not required, but keep this here for the autograder)
    public static void main(String[] args) {
//        Percolation test = new Percolation(5);
//        test.open(3, 4);
//        test.open(2, 4);
//        test.open(2, 2);
//        test.open(2, 3);
//        test.open(0, 2);
//        test.open(1, 2);
//        test.open(4, 4);
//        System.out.println(test.isFull(2, 2));
//        System.out.println(test.percolates());
    }
}
