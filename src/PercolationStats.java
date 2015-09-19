/*import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;
*/
public class PercolationStats {
	private double[] threshold;
	private Percolation percolation;

	public PercolationStats(int N, int T) {
		if (N <= 0 || T <= 0) {
			throw new IllegalArgumentException("Given N <= 0 || T <= 0");
		}

		// perform T independent experiments on an N-by-N grid
		threshold = new double[T];
		int i, j, opensites = 0;

		for (int k = 0; k < T; k++) {
			percolation = new Percolation(N);

			do {
				i = StdRandom.uniform(1, N + 1);
				j = StdRandom.uniform(1, N + 1);

				if (!percolation.isOpen(i, j)) {
					percolation.open(i, j);
					opensites++;
				}
			} while (!percolation.percolates());

			threshold[k] = (double) opensites / (N * N);// opensites/totalsites
			System.out.println("total ope sites is: " + opensites
					+ "   threshold is :" + threshold[k]);

		}

	}

	public double mean() {
		// sample mean of percolation threshold
		return StdStats.mean(threshold);
	}

	public double stddev() {
		// sample standard deviation of percolation threshold
		return StdStats.stddev(threshold);
	}

	public double confidenceLo() {
		// low endpoint of 95% confidence interval
		return mean() - 1.96 * stddev() / Math.sqrt(threshold.length);

	}

	public double confidenceHi() {
		// high endpoint of 95% confidence interval
		return mean() + 1.96 * stddev() / Math.sqrt(threshold.length);

	}

	public static void main(String[] args) {
		int N = Integer.parseInt(args[0]);
		int T = Integer.parseInt(args[1]);

		System.out.println("starting program N is:" + N + " t is: " + T);

		Stopwatch sw = new Stopwatch();

		PercolationStats percol = new PercolationStats(N, T);

		double t = sw.elapsedTime();

		System.out.println("time used               = " + t + "s");
		System.out.println("mean                    = " + percol.mean());
		System.out.println("stddev                  = " + percol.stddev());
		System.out.println("95% confidence interval = " + percol.confidenceLo()
				+ "," + percol.confidenceHi());

	}
}