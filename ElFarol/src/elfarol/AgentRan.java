/*
 * Version info:
 *     $HeadURL: https://cscs-repast-demos.googlecode.com/svn/richard/ElFarol/trunk/src/elfarol/Agent.java $
 *     $LastChangedDate: 2011-08-22 21:40:45 +0200 (H, 22 aug. 2011) $
 *     $LastChangedRevision: 1123 $
 *     $LastChangedBy: richard.legendi@gmail.com $
 */
package elfarol;

import static elfarol.ParameterWrapper.*;

import java.util.Collections;
import java.util.List;

import elfarol.strategies.AStrategy;

/**
 * An Agent that predicts attendance by picking a random
 * number between the highest and lowest attendance values
 * that it remembers.
 * 
 * @see Agent
 * 
 * @author Hawk Weisman <hawk@meteorcodelabs.com>
 * @author Luke Smith
 */
public class AgentRan implements Agent {
	
	/**
	 * Returns a random int between min and max
	 * @param min
	 * @param max
	 * @return a random int between min and max
	 */
	public static int randomInRange(int min, int max) {
		assert (min < max);
	    return min + (int)(Math.random() * (max - min + 1));
	}
	
	/**
	 * A boolean flag that shows if the agent is attending the bar in the
	 * current time step.
	 */
	private boolean attend = false;

	/**
	 * Initializes a new Random agent
	 */
	public AgentRan() {
	}

	/**
	 * Returns the value of <code>attend</code>.
	 * 
	 * @return <code>true</code> if the agent attends the bar in the current
	 *         time step (<i>if</i> called after the {@link #updateAttendance()}
	 *         function); <code>false</code> otherwise
	 */
	public boolean isAttending() {
		return attend;
	}

	/**
	 * Predicts attendance based on the agent's memory
	 * by picking a random number between the highest
	 * and lowest remembered attendance.
	 * @param subhistory a List<Integer> containing the previous attendance values
	 * @return a predicted attendance
	 */
	private double predictAttendance(final List<Integer> subhistory) {
		return (double) randomInRange(Collections.min(subhistory), Collections.max(subhistory));
	}

	// ========================================================================
	// === Public Interface ===================================================

	/**
	 * Makes the agent evaluate all the strategies and if any of them is better
	 * than the previously used one it is updated. A threshold level of
	 * <code>memorySize * agentsNumber + 1</code> is also considered.
	 */
	public void updateBestStrategy() {
		// Do nothing (this agent type always chooses randomly)
	}

	/**
	 * Makes the agent update its attendance level based on its attendance
	 * prediction by its best evaluated strategy.
	 */
	@Override
	public void updateAttendance() {
		final double prediction = predictAttendance(
				History
				.getInstance()
				.getMemoryBoundedSubHistory()
				);

		attend = (prediction <= getOvercrowdingThreshold());
	}

}
