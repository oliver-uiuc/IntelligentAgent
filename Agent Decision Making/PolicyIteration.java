import java.util.Arrays;
public class PolicyIteration {
	// initialize rewards array
		private static final double[][] rewards = { { 1, 0, 1, -0.04, -0.04, 1 }, { -0.04, -1, -0.04, 1, 0, -1 },
				{ -0.04, -0.04, -1, -0.04, 1, -0.04 }, { -0.04, -0.04, 0, -1, -0.04, 1 }, { -0.04, 0, 0, 0, -1, -0.04 },
				{ -0.04, -0.04, -0.04, -0.04, -0.04, -0.04 } };
		// set discount factor
		//private static final double gamma = 0.946057;
		private static final double gamma = 0.99;
		// set converge threshold
		private static final double threshold = 0.0;
		// starting position
		private static final int START_ROW = 3;
		private static final int START_COL = 2;
		
		// how action changes the row&col number
		//Action 0: Move up (row - 1, col); Action 1: Move right (row, col + 1)
		//Action 2: Move down (row + 1, col); Action 3: Move left (row, col - 1)
		private static final double[] ACTION_ROW = {-1, 0, 1, 0};
	    private static final double[] ACTION_COL = {0, 1, 0, -1};
	    
	    public static void main(String[] args) {
	    	boolean policyChanged;
	    	// Initialize the utilities of all the states to zero.
			double[][] utilities = new double[rewards.length][rewards[0].length];
			
			// Policy represented by integer
			int[][] policy = new int[rewards.length][rewards[0].length];
			
			int iterations = 0;
			
			do {
				iterations++;
				policyChanged = false;
				utilities = policyEvaluation(policy, utilities);
				
				for(int i=0; i<rewards.length; i++) {
					for(int j=0; j<rewards[0].length; j++) {
						if(isWall(i,j)) {
							continue;
						}
						int oldAction = policy[i][j];
						int bestAction = oldAction;
						double maxUtility = Double.NEGATIVE_INFINITY;
						for(int action = 0; action<4; action++) {
							double utility = computeExpectedUtility(i,j,action,utilities);
							if(utility>maxUtility) {
								maxUtility = utility;
								bestAction = action;
							}
						}
						if(oldAction != bestAction) {
							policyChanged = true;
							policy[i][j] = bestAction;
						}
					}
				}
				System.out.println("Utilities in iteration " + iterations + ": ");
				for (int row = 0; row < rewards.length; row++) {
		            for (int col = 0; col < rewards[0].length; col++) {
		                System.out.printf("(%.2f) ", utilities[row][col]);
		            }
		            System.out.println();
		        }
				System.out.println("Current policy:");
				for (int i = 0; i < rewards.length; i++) {
					for (int j = 0; j < rewards[0].length; j++) {
						if (i == START_ROW && j == START_COL) {
							System.out.print(" S ");// start
						} else if (isWall(i, j)) {
							System.out.print(" | ");// wall
						} else {
							System.out.print(" " + policytoString(policy[i][j]) + " ");
						}
					}
					System.out.println();
				}
				System.out.println();
			}while(true);
	    }
	    
	    private static double[][] policyEvaluation(int[][] policy, double[][] utilities) {
	        double[][] newUtilities = new double[rewards.length][rewards[0].length];
	        boolean converged;
	        

	        do {
	            converged = true;
	            

	            for (int row = 0; row < rewards.length; row++) {
	                for (int col = 0; col < rewards[0].length; col++) {
	                    if (isWall(row, col)) {
	                        continue;
	                    }

	                    int action = policy[row][col];
	                    newUtilities[row][col] = rewards[row][col] + gamma * computeExpectedUtility(row, col, action, utilities);

	                    if (Math.abs(newUtilities[row][col] - utilities[row][col]) > threshold) {
	                        converged = false;
	                    }
	                }
	            }

	            utilities = Arrays.copyOf(newUtilities, newUtilities.length);
	        } while (!converged);

	        return utilities;
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
					|| (row == 4 && col == 3))
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
