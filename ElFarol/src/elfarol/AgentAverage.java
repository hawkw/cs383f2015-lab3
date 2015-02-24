package elfarol;

import static elfarol.ParameterWrapper.getOvercrowdingThreshold;

import java.util.List;

/**
 * Agent which predicts attendance by averaging it's current memory
 * 
 * @author Hawk Weisman <hawk@meteorcodelabs.com>
 *
 */
public class AgentAverage implements Agent {
	private boolean attend = false;

	@Override
	public boolean isAttending() {
		return attend;
	}

	/**
	 * Predicts attendance by averaging the values in the agent's remembered
	 * subhistory.
	 * 
	 * @param subhistory
	 * @return a prediction for the next attendance.
	 */
	private double predictAttendance(final List<Integer> subhistory) {
		int sum = 0;
		for (Integer i : subhistory) {
			sum += i;
		}
		return sum / subhistory.size();
	}

	/**
	 * Update this agent's attendance by making a new average prediction.
	 */
	@Override
	public void updateAttendance() {
		final double prediction = predictAttendance(History.getInstance()
				.getMemoryBoundedSubHistory());

		attend = (prediction <= getOvercrowdingThreshold());
	}

	/**
	 * Does nothing as this agent does not require beliefs updating.
	 */
	@Override
	public void updatePredictions() {
	}

}
