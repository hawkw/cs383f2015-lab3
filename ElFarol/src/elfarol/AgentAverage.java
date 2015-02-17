package elfarol;

import static elfarol.ParameterWrapper.getOvercrowdingThreshold;

import java.util.List;

/**
 * Agent which predicts attendance by averaging it's current memory
 * @author Hawk Weisman <hawk@meteorcodelabs.com>
 *
 */
public class AgentAverage implements Agent {
	private boolean attend = false;

	@Override
	public boolean isAttending() {
		return attend;
	}
	
	private double predictAttendance(final List<Integer> subhistory) {
		return subhistory
				.stream()
				.mapToInt(i -> i)
				.average()
				.orElse(0);
	}

	@Override
	public void updateAttendance() {
		final double prediction = predictAttendance(
				History
				.getInstance()
				.getMemoryBoundedSubHistory()
				);

		attend = (prediction <= getOvercrowdingThreshold());
	}
	
	@Override
	public void updateBestStrategy() {
		// Swallow and do nothing (this agent always uses the same strategy)	
	}

}
