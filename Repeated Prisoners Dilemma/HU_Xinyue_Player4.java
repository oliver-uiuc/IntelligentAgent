/**
	 * This strategy starts out playing tit-for-tat, but as soon as its opponent defects, 
	 * it switches to always defecting in return.
	 */
public class HU_Xinyue_Player4 extends Player{
	 boolean isSpiteful = false;

	    int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
	        if (n == 0) return 0; // cooperate by default
	        if (isSpiteful) {
	            return 1; // defect if opponent ever defected
	        } else {
	            if (oppHistory1[n-1] == 1 || oppHistory2[n-1] == 1) {
	                isSpiteful = true; // switch to spiteful if opponent defected
	            }
	            return oppHistory1[n-1]; // tit-for-tat otherwise
	        }
	    }
}
