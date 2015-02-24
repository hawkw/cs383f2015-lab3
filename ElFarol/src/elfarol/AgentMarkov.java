package elfarol;

import static elfarol.ParameterWrapper.getOvercrowdingThreshold;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * Agent that predicts bar attendance using a Markov Chain, mapping previous
 * attendance to expected attendance
 * @author Willem Yarbrough <yarbroughw@allegheny.edu>
 * @author Luke Smith <smithl4@allegheny.edu>
 *
 */
public class AgentMarkov implements Agent {
	private boolean attend = false;
	private Map<Integer,Double> chain = new HashMap<Integer,Double>();
	
	private double getChainValue(int attendance) {
		int bin = attendance / 10;
		Double prediction = chain.get(bin);
		if (prediction == null) {
			return 0.0;
		} else { 
			return prediction;
		}
	}
	private void updateProbs(int lastAttendance, int attendance){		
		// update our prediction for that bin
		double previousPrediction = getChainValue(lastAttendance);
		chain.put(lastAttendance/10,(previousPrediction + attendance) / 2);
	}
		
	private double predictAttendance(final List<Integer> subhistory) {
		int size = subhistory.size();
		int yesterday;
		if (size > 0) {
			yesterday = subhistory.get(size - 1);
		} else {
			yesterday = 0;
		}
		return getChainValue(yesterday);
	}
	
	@Override
	public boolean isAttending() {
		return attend;
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
	public void updatePredictions() {
		List<Integer> subhistory = History
									.getInstance()
									.getMemoryBoundedSubHistory();
		int size = subhistory.size();
		if (subhistory.size() > 1) {
			updateProbs(subhistory.get(size-2),
						subhistory.get(size-1));
		}
	}
}
