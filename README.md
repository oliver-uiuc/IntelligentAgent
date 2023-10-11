# IntelligentAgent
Agent Decision Making and Repeated Prisoners Dilemma

## Task 1: Agent Decision Making

<img width="517" alt="image" src="https://github.com/oliver-uiuc/IntelligentAgent/assets/143446110/1a2fa2ab-577b-465f-9a7a-72b548037e2f">

The transition model is as follows: the intended outcome occurs with probability 0.8, and
with probability 0.1 the agent moves at either right or left angle to the intended direction. If the
move would make the agent walk into a wall, the agent stays in the same place as before. The
rewards for the white squares are -0.04, for the green squares are +1, and for the brown
squares are -1. Note that there are no terminal states; the agent’s state sequence is infinite.

Assuming the known transition model and reward function listed above, find the
optimal policy and the utilities of all the (non-wall) states using both value iteration and
policy iteration. Display the optimal policy and the utilities of all the states. 
In this task, use a discount factor of 0.99.

A further question is to design a more complicated maze environment and
re-run the algorithms designed for Task 1 on it. How does the number of states and the
complexity of the environment affect convergence? How complex can you make the
environment and still be able to learn the right policy?

## Task 2: Repeated Prisoners Dilemma

As you all know, in the game the Prisoners’ Dilemma, the dominant strategy equilibrium is for agents to defect, even though both agents would be best off cooperating. 
If we move to a repeated version of the Prisoners’ Dilemma, then this lack of cooperation can possibly disappear.

In a repeated game, a given game (often thought of in normal form) is played multiple times (possibly infinitely many times) 
by the same set of players. We compute the (average) reward of a player in a repeated game, to be (rj is the player’s payoff in round j)

<img width="162" alt="image" src="https://github.com/oliver-uiuc/IntelligentAgent/assets/143446110/e931fc78-ed14-41af-9fc4-e81198dfb28e">

A strategy in a repeated game specifies what action the agent should take in each stage of the game, given all the actions taken by all players in the past. 
For example, one strategy in the Prisoner’s Dilemma is Tit-for-Tat(TfT). In this strategy, the agent starts by cooperating, and thereafter chooses in 
round j + 1 the same action that the other agent chose in round j. If both agents play TfT then we have an equilibrium (with certain additional conditions).
However, this is not the only strategy that agents might consider playing, in fact there are infinite many strategies which agents may consider. 
For example, in the Trigger strategy, an agent starts by cooperating but if the other player ever defects then the first defects forever. 
The Trigger strategy forms a Nash equilibrium both with itself and with TfT.

A strategy is developed for an agent in a three player repeated prisoners’ dilemma. In this simulation, triples of players will play each other 
repeatedly in a ‘match’. A match consists of about 100 rounds, and your score from that match is the average of the payoffs from each round of 
that match. For each round, your strategy is given a list of the previous plays (so you can remember what your opponent did) and must compute 
the next action. We represent cooperation by the integer 0, and defection by the integer 1.
