/*
 * Version info:
 *     $HeadURL: https://cscs-repast-demos.googlecode.com/svn/richard/ElFarol/trunk/src/elfarol/Agent.java $
 *     $LastChangedDate: 2011-08-22 21:40:45 +0200 (H, 22 aug. 2011) $
 *     $LastChangedRevision: 1123 $
 *     $LastChangedBy: richard.legendi@gmail.com $
 */
package elfarol;

import static elfarol.ParameterWrapper.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import elfarol.strategies.AStrategy;
import elfarol.strategies.RandomStrategy;

/**
 * Defines an agent of the <i>El Farol</i> simulation.
 * 
 * <p>
 * Agents have a list of strategies (see {@link AStrategy}) and each turn they
 * try to predict the attendance level of the bar for the current time step, and
 * determine if they attend to the bar or not.
 * </p>
 * 
 * @author Richard O. Legendi (richard.legendi)
 * @since 2.0-beta, 2011
 * @version $Id: Agent.java 1092 2011-08-22 08:35:16Z richard.legendi@gmail.com
 *          $
 * @see AStrategy
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
	 * Initializes a new agent instance with the
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

	private double predictAttendance(final List<Integer> subhistory) {
		return randomInRange(Collections.min(subhistory), Collections.max(subhistory));
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
