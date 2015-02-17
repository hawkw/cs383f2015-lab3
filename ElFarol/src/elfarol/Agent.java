package elfarol;

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
	 * Makes the agent evaluate all the strategies and if any of them is better
	 * than the previously used one it is updated. A threshold level of
	 * <code>memorySize * agentsNumber + 1</code> is also considered.
	 */
	public abstract void updateBestStrategy();

	/**
	 * Makes the agent update its attendance level based on its attendance
	 * prediction by its best evaluated strategy.
	 */
	public abstract void updateAttendance();
}