/**
	 * the GrimPlayer object keeps track of whether it is currently defecting or not using 
	 * the isDefecting boolean variable. On the first move, it cooperates by default. 
	 * On all subsequent moves, it checks whether the opponent has defected in the 
	 * previous round, and switches to defecting if so. Once it starts defecting, 
	 * it will continue to do so for the rest of the game.
	 */
public class HU_Xinyue_Player2 extends Player {
	 private boolean isDefecting; // true if the player is defecting, false otherwise

	    HU_Xinyue_Player2() {
	        this.isDefecting = false; // start off cooperating
	    }

	    int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
	        if (n == 0) {
	            // cooperate on the first move
	            return 0;
	        } else {
	            if (isDefecting) {
	                // defect for the rest of the game if the opponent has defected
	                return 1;
	            } else if (oppHistory1[n-1] == 1 || oppHistory2[n-1] == 1) {
	                // switch to defecting if the opponent defects
	                isDefecting = true;
	                return 1;
	            } else {
	                // cooperate otherwise
	                return 0;
	            }
	        }
	    }
}
