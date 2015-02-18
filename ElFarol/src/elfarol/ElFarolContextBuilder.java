/*
 * Version info:
 *     $HeadURL: https://cscs-repast-demos.googlecode.com/svn/richard/ElFarol/trunk/src/elfarol/ElFarolContextBuilder.java $
 *     $LastChangedDate: 2011-08-22 21:40:45 +0200 (H, 22 aug. 2011) $
 *     $LastChangedRevision: 1123 $
 *     $LastChangedBy: richard.legendi@gmail.com $
 */
package elfarol;

import static elfarol.ParameterWrapper.*;
import repast.simphony.context.Context;
import repast.simphony.context.DefaultContext;
import repast.simphony.dataLoader.ContextBuilder;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.parameter.Parameters;

/**
 * The context builder for the <i>El Farol</i> simulation.
 * 
 * @author Richard O. Legendi (richard.legendi)
 * @author Hawk Weisman <hawk@meteorcodelabs.com>
 */
public class ElFarolContextBuilder extends DefaultContext<Object> implements
		ContextBuilder<Agent> {

	private static int agentAmount(int percent, int total) {
		return (int) ((percent / 100d) * total);
	}

	// ========================================================================
	// === Super Interface ====================================================

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * repast.simphony.dataLoader.ContextBuilder#build(repast.simphony.context
	 * .Context)
	 */
	@Override
	public Context<Agent> build(final Context<Agent> context) {
		ParameterWrapper.reinit();
		History.getInstance().init();

		for (int i = 0; i < agentAmount(getPercentRand(), getAgentsNumber()); i++) {
			final Agent agent = new AgentRan();
			context.add(agent);
		}
		for (int i = 0; i < agentAmount(getPercentAvg(), getAgentsNumber()); i++) {
			final Agent agent = new AgentAverage();
			context.add(agent);
		}
		
		// TODO: comment this back in when the smart agent is implemented 
		for (int i = 0; i < agentAmount( ParameterWrapper.getPercentAvg(),
				ParameterWrapper.getAgentsNumber() ); i++) {
			final Agent agent = new AgentMarkov(); 
			context.add(agent);
		}

		for (int i = 0; i < agentAmount(100 - (getPercentAvg()
				+ getPercentRand() + getPercentSmart()), getAgentsNumber()); i++) {
			final Agent agent = new AgentWeighted();
			context.add(agent);
		}
		return context;
	}

	// ========================================================================
	// === Schedule ===========================================================

	/**
	 * Main schedule of the simulation containing the following phases:
	 * 
	 * <ol>
	 * <li><b>First</b>, all agents determine if their attend to the bar based
	 * on their knowledge (<i>strategies</i>) and the prediction</li>
	 * <li><b>Then</b> we update the global history</li>
	 * <li><b>At the end of the turn</b> when the new attendance level is known,
	 * all of the agents update their best strategy</li>
	 * </ol>
	 */
	@ScheduledMethod(start = 1, interval = 1)
	public void schedule() {
		for (final Agent act : Utils.getAgentList()) {
			act.updateAttendance();
		}

		History.getInstance().updateHistory();

		for (final Agent act : Utils.getAgentList()) {
			act.updateBestStrategy();
		}
	}

	// ========================================================================
	// === Observer Methods ===================================================

	/**
	 * Returns the current attendance level of the bar to display in a simple
	 * time series chart on the Repast GUI.
	 * 
	 * @return the current attendance level of the bar
	 */
	public int attendance() {
		return Utils.getAttendance();
	}

}
