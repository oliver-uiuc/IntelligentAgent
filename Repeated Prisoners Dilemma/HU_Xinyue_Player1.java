	/**
	 * In the Generous Tit-for-Tat strategy, the player initially cooperates and then 
	 * imitates the opponent's previous move, except if the opponent has defected twice 
	 * in a row, in which case the player defects. This behavior is implemented in the 
	 * selectAction method by checking the opponent's behavior in the previous round and 
	 * updating the opponentDefectCount and lastAction variables accordingly. If the opponent 
	 * defected twice in a row, the player switches to defecting. Otherwise, the player imitates 
	 * the opponent's previous move.
	 */
public class HU_Xinyue_Player1 extends Player {
	private int lastAction = 0; // assume cooperate on the first round
    private int opponentDefectCount = 0;

    int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
        if (n == 0) {
            lastAction = 0; // cooperate by default on the first round
            return lastAction;
        }

        // determine if opponent defected in the previous round
        boolean oppDefected = (oppHistory1[n-1] == 1 || oppHistory2[n-1] == 1);

        // update opponentDefectCount based on opponent's behavior
        if (oppDefected) {
            opponentDefectCount++;
        } else {
            opponentDefectCount = 0;
        }

        // update lastAction based on opponent's behavior
        if (opponentDefectCount >= 2) {
            lastAction = 1; // defect if opponent defected twice in a row
        } else {
            lastAction = oppHistory1[n-1]; // imitate opponent's last move
        }

        return lastAction;
    }
}


