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
	
	/**
        * Predicts attendance by looking at the previous days attendance
	*
	* @param yesterdays attendance
	* @return a prediction for the next attendance.
	*/
	private double getChainValue(int attendance) {
		int bin = attendance / 10;
		Double prediction = chain.get(bin);
		if (prediction == null) {
			return 0.0;
		} else { 
			return prediction;
		}
	}
	
	/**
        * Updates the prediction for that bin
	*
	* @param Two days ago attendance
	* @param last attendance
	*/
	private void updateProbs(int lastAttendance, int attendance){		
		double previousPrediction = getChainValue(lastAttendance);
		chain.put(lastAttendance/10,(previousPrediction + attendance) / 2);
	}
		
	/**
        * Takes last element in history and calls {@link #getChainValue(int)} with that value.
	*
	* @param Subhistory
	* @return {@link #getChainValue(int)}
	*/
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


	/**
        * Updates Attendance 
	*
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
	
	/**
        * Updates prediction of next attendance
	*
	*/
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
