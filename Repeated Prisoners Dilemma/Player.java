abstract class Player {
	// This procedure takes in the number of rounds elapsed so far (n), and 
	// the previous plays in the match, and returns the appropriate action.
	int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
		throw new RuntimeException("You need to override the selectAction method.");
	}
	
	// Used to extract the name of this player class.
	final String name() {
		String result = getClass().getName();
		return result.substring(result.indexOf('$')+1);
	}
}