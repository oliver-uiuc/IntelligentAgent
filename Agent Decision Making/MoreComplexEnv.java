import java.util.Arrays;

public class MoreComplexEnv {
	// initialize rewards array 10*10
	private static final double[][] rewards = { 
			{ 1, 0, 1, -0.04, -0.04, 1, 0, -0.04, -0.04, -0.04}, 
			{ -0.04, -1, -0.04, 1, 0, -1, -0.04, 1, 0, 1 },
			{ -0.04, -0.04, -1, -0.04, 1, -0.04, -0.04, -0.04, 1, -0.04 }, 
			{ -0.04, -0.04, 0, -1, -0.04, 1, 0, 0, 0 , 1 }, 
			{ -0.04, 0, 0, 0, -1, -0.04, -1, 1, -1, -0.04 },
			{ -0.04, -0.04, -0.04, -0.04, -0.04, -0.04, -0.04, -0.04, 1, -0.04 },
			{ -0.04, 1, -0.04, -0.04, -0.04, -0.04, -0.04, -0.04, -0.04, -0.04 },
			{-0.04, 0, 0, 0, -1, -0.04, 1, 1, 1, -0.04 },
			{-0.04, -0.04, -1, -1, -0.04, -1, 0, 0, 0 , -1 },
			{ -0.04, 1, -1, -0.04, 1, -0.04, -0.04, -0.04, 1, -0.04 }, 
			};
	// set discount factor
	//private static final double gamma = 0.946057;
	private static final double gamma = 0.99;
	// set converge threshold
	private static final double threshold = 0.0;
	// starting position
	private static final int START_ROW = 3;
	private static final int START_COL = 2;

	// how action changes the row&col number
	// Action 0: Move up (row - 1, col); Action 1: Move right (row, col + 1)
	// Action 2: Move down (row + 1, col); Action 3: Move left (row, col - 1)
	private static final double[] ACTION_ROW = { -1, 0, 1, 0 };
	private static final double[] ACTION_COL = { 0, 1, 0, -1 };
	
	public static void main(String[] args) {

		// Initialize the utilities of all the states to zero.
		double[][] utilities = new double[rewards.length][rewards[0].length];
		// The utilities update at the end of the current iteration
		double[][] newUtilities = new double[rewards.length][rewards[0].length];
		// Optimal policy represented by integer
		int[][] optimalPolicy = new int[rewards.length][rewards[0].length];

		// Apply the value iteration algorithm until convergence
		boolean converged = false;
		int iterations = 0;

		while (!converged) {

			System.out.println("Utilities after " + iterations + " iterations:");
			for (int i = 0; i < rewards.length; i++) {
				for (int j = 0; j < rewards[0].length; j++) {
					System.out.printf("(%.6f) ", utilities[i][j]);
				}
				System.out.println();
			}

			iterations++;
			double delta = 0;
			//compute max utility
			for (int i = 0; i < rewards.length; i++) {
				for (int j = 0; j < rewards[0].length; j++) {
					if (isWall(i, j)) {
						continue;
					}
					double maxUtility = Double.NEGATIVE_INFINITY;
					for (int action = 0; action < 4; action++) {
						double utility = computeExpectedUtility(i, j, action, utilities);
						if (utility > maxUtility) {
							maxUtility = utility;
							optimalPolicy[i][j] = action;
						}
					}
					newUtilities[i][j] = rewards[i][j] + gamma * maxUtility;
				}
			}
			for (int i = 0; i < rewards.length; i++) {
				for (int j = 0; j < rewards[0].length; j++) {
					delta = Math.max(delta, Math.abs(newUtilities[i][j] - utilities[i][j]));
				}
			}

			converged = delta < threshold;
			utilities = Arrays.copyOf(newUtilities, newUtilities.length);

			System.out.println("Optimal policy:");
			for (int i = 0; i < rewards.length; i++) {
				for (int j = 0; j < rewards[0].length; j++) {
					if (i == START_ROW && j == START_COL) {
						System.out.print(" S ");// start
					} else if (isWall(i, j)) {
						System.out.print(" | ");// wall
					} else {
						System.out.print(" " + policytoString(optimalPolicy[i][j]) + " ");
					}
				}
				System.out.println();
			}
			System.out.println();
		}
	}


	private static double computeExpectedUtility(int row, int col, int action, double[][] utilities) {
		double expectedUtility = 0;

		for (int i = -1; i <= 1; i++) {
			int adjustedAction = (action + i + 4) % 4;
			int newRow = row + (int) ACTION_ROW[adjustedAction];
			int newCol = col + (int) ACTION_COL[adjustedAction];

			if (isValidState(newRow, newCol)) {
				expectedUtility += (i == 0 ? 0.8 : 0.1) * utilities[newRow][newCol];
			} else {
				expectedUtility += (i == 0 ? 0.8 : 0.1) * utilities[row][col];
			}
		}

		return expectedUtility;
	}
	

	private static boolean isValidState(int newRow, int newCol) {
		return newRow >= 0 && newRow < rewards.length && newCol >= 0 && newCol < rewards[0].length
				&& !isWall(newRow, newCol);
	}

	private static boolean isWall(int row, int col) {
		if ((row == 0 && col == 1) || (row == 1 && col == 4) || (row == 4 && col == 1) || (row == 4 && col == 2)
				|| (row == 4 && col == 3) || (row == 0 && col == 6) || (row == 1 && col ==8) ||
				(row==3 && col==8) || (row==3 && col==7) || (row==3 && col==6) || (row==7 && col==1) ||
				(row==7 && col==2) || (row==7 && col==3) || (row==8 && col==6) || (row==8 && col==7) ||
				(row==8 && col==8))
			return true;
		else
			return false;
	}

	private static String policytoString(int num) {
		String toreturn = "";
		switch (num) {
		case 0:
			toreturn = "^";
			break;
		case 1:
			toreturn = ">";
			break;
		case 2:
			toreturn = "_"; // down
			break;
		case 3:
			toreturn = "<";
			break;
		}
		return toreturn;
	}
}
