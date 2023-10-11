/**
	 * The defect boolean variable keeps track of whether the player should defect or cooperate 
	 * in the current round. If the current round is the first round, the player starts with 
	 * cooperation (defect is set to false). If either opponent defected in the previous round, 
	 * the player remembers that and defects in the current round and in all subsequent rounds 
	 * until one of the opponents cooperates again.
	 */
public class HU_Xinyue_Player3 extends Player{
	 boolean defect = false;
	    
	    int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
	        if (n == 0) {
	            defect = false; // start with cooperation
	            return 0;
	        }
	        if (oppHistory1[n-1] == 1 || oppHistory2[n-1] == 1) {
	            defect = true; // opponent defected, so remember and defect next round
	        }
	        if (defect) {
	            return 1; // defect
	        }
	        return 0; // cooperate
	    }
}
