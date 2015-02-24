package elfarol;

/**
 * An interface representing an agent in the El Farol Bar model. Defines a
 * common set of public methods for agents in the simulation.
 * 
 * This interface was extracted from the <code>Agent</code> class written by
 * Richard O. Legendi.
 * 
 * @author Hawk Weisman
 *
 */
public interface Agent {

	/**
	 * Returns the value of <code>attend</code>.
	 * 
	 * @return <code>true</code> if the agent attends the bar in the current
	 *         time step (<i>if</i> called after the {@link #updateAttendance()}
	 *         function); <code>false</code> otherwise
	 */
	public abstract boolean isAttending();

	/**
	 * Scheduled update method for the agent's predictions. This is called by
	 * the model every tick prior to {@link #updateAttendance()} and can be used
	 * to update the agent's beliefs prior to making predictions. Agents which
	 * do not require scheduled beliefs updating do nothing when this method is
	 * called.
	 */
	public abstract void updatePredictions();

	/**
	 * Makes the agent update its attendance level based on its attendance
	 * prediction. This is called by the model after
	 * {@link #updatePredictions()} and prior to {@link #isAttending()}
	 */
	public abstract void updateAttendance();
}