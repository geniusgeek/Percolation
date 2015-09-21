public class Percolation {
    private int size;
    private byte[][] status;
    private boolean percolates = false;
    private byte full = 2;

    public Percolation(int N) {
	if (N <= 0)
	    throw new IllegalArgumentException("Given N<= 0");
	size = N + 1;
	status = new byte[size][size];
    }

    private boolean isValid(int i, int j) {
	if (i < 1 || i > size || j < 1 || j > size)
	    throw new IndexOutOfBoundsException("row index  out of bounds");
	return true;
    }

    public void open(int i, int j) {
	if (!isValid(i, j) || isOpen(i, j))
	    return;
	status[i][j] = (byte) (full - 1); // set the position to open
	recursiveUpdate(i, j);

    }

    private void recursiveUpdate(int i, int j) {
	if (i < 1 || j > size - 1 || j < 1 || i > size - 1)
	    return;

	if (status[i][j] != full - 1) // return if closed or full
	    return;

	byte largestNeigh = getLargestNeighbour(i, j);

	if (i == 1)
	    status[i][j] = full;
	else {
	    if (largestNeigh == (byte) full)
		status[i][j] = (byte) (full); // update
	    else
		return;
	}

	if (i == size - 1 && status[i][j] == full) {
	    percolates = true;
	}

	if (largestNeigh == full) {
	    recursiveUpdate(i + 1, j);
	    recursiveUpdate(i, j + 1);
	    recursiveUpdate(i, j - 1);
	    recursiveUpdate(i - 1, j);
	}

    }

    public boolean isOpen(int i, int j) {
	if (isValid(i, j) && status[i][j] >= full - 1)
	    return true;
	return false;
    }

    private byte getLargestNeighbour(int i, int j) {
	byte toReturn = (byte) 0;
	if (i == 1)
	    return full;
	// top
	if (i > 1 && isOpen(i - 1, j))
	    if (status[i - 1][j] > toReturn)
		toReturn = status[i - 1][j];

	// left
	if (j > 1 && isOpen(i, j - 1))
	    if (status[i][j - 1] > toReturn)
		toReturn = status[i][j - 1];

	// right
	if (j < size - 1 && isOpen(i, j + 1))
	    if (status[i][j + 1] > toReturn)
		toReturn = status[i][j + 1];

	// bottom
	if (i < size - 1 && isOpen(i + 1, j))
	    if (status[i + 1][j] > toReturn)
		toReturn = status[i + 1][j];

	return toReturn;

    }

    public boolean isFull(int i, int j) {
	if (isValid(i, j) && status[i][j] == full)
	    return true;
	return false;
    }

    public boolean percolates() {
	// does the system percolate?
	return percolates;
    }

    public static void main(String[] args) {
	/*
	 * // test client (optional) Stopwatch stopWatch= new Stopwatch();
	 * 
	 * Percolation percolation= new Percolation(5); percolation.open(3, 2);
	 * percolation.open(2, 3); percolation.open(4, 3); percolation.open(3,
	 * 4); percolation.open(3, 3); percolation.open(1, 3);
	 * percolation.open(5, 3);
	 * 
	 * for(int i=1; i<=5; i++) for(int j=1; j<=5; j++)
	 * System.out.println(percolation.findIndex(i,
	 * j)+" is opened"+percolation.isOpen(i, j));
	 * 
	 * System.out.println("percolation is full  1,1"+percolation.isFull(1,
	 * 1));
	 * System.out.println("Does the system percolates?"+percolation.percolates
	 * ());
	 * 
	 * System.out.println("elapsed time is: "+ stopWatch.elapsedTime());
	 */
    }

}
