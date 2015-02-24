/*
 * Version info:
 *     $HeadURL: https://cscs-repast-demos.googlecode.com/svn/richard/ElFarol/trunk/src/elfarol/ParameterWrapper.java $
 *     $LastChangedDate: 2011-08-22 21:40:45 +0200 (H, 22 aug. 2011) $
 *     $LastChangedRevision: 1123 $
 *     $LastChangedBy: richard.legendi@gmail.com $
 */
package elfarol;

import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.parameter.Parameters;

/**
 * A simple wrapper class that handles the parameters and their values specified
 * for the simulation.
 * 
 * <p>
 * During each simulation initialization (the execution of
 * {@link ElFarolContextBuilder#build(repast.simphony.context.Context)}) the
 * actual values are updated by the {@link #reinit()} method. <i>Because of this
 * behaviour, if the parameter values are modified during the run it is not
 * taken into account.</i> The parameter values may be accessed through the
 * static interface.
 * </p>
 * 
 * @author Richard O. Legendi (richard.legendi)
 * @author Hawk Weisman <hawk@meteorcodelabs.com>
 */
public final class ParameterWrapper {

	// ========================================================================
	// === Parameter Values ===================================================

	/** Specifies the number of agents in the simulation. */
	private static int agentsNumber = 100;

	/** Specifies the number of agents in the simulation. */
	private static int percentRand = 0;
	private static int percentAvg = 0;
	private static int percentSmart = 0;

	private static String agentType = "Weighted";

	/** Specifies the memory size that agents have. */
	private static int memorySize = 5;

	/** Specifies the number of strategies agents may operate with. */
	private static int strategiesNumber = 10;

	/** Specifies the threshold level. */
	private static int overcrowdingThreshold = 60;

	private static int runTicks = -1;

	// ========================================================================
	// === Getter Methods =====================================================

	/**
	 * Returns the number of agents.
	 * 
	 * @return the number of agents; should be positive
	 */
	public static int getAgentsNumber() {
		assert (agentsNumber > 0);
		return agentsNumber;
	}

	/**
	 * Get the percentage of random agents.
	 * 
	 * @return the percentage of random agents; this should be an integer
	 *         between 0 and 100 (inclusive)
	 */
	public static int getPercentRand() {
		assert (percentRand >= 0 && percentRand <= 100);
		return percentRand;
	}

	/**
	 * Get the percentage of averaging agents.
	 * 
	 * @return the percentage of averaging agents; this should be an integer
	 *         between 0 and 100 (inclusive)
	 */
	public static int getPercentAvg() {
		assert (percentAvg >= 0 && percentAvg <= 100);
		return percentAvg;
	}

	/**
	 * Get the percentage of smart (Markov chain) agents.
	 * 
	 * @return the percentage of random agents; this should be an integer
	 *         between 0 and 100 (inclusive)
	 */
	public static int getPercentSmart() {
		assert (percentSmart >= 0 && percentSmart <= 100);
		return percentSmart;
	}

	/**
	 * Get the number of ticks the model should run for. If this is less than 0,
	 * the model will run indefinitely.
	 * 
	 * @return the number of ticks for the model to run for.
	 */
	public static int getRunTicks() {
		return runTicks;
	}

	/**
	 * Returns the memory size of the agents (e.g., how much previous week they
	 * can remember).
	 * 
	 * @return the memory size of agents; should be positive
	 */
	public static int getMemorySize() {
		assert (memorySize > 0);
		return memorySize;
	}

	/**
	 * Returns the number of strategies an agent may operate with.
	 * 
	 * @return the number of strategies of an agent; should be positive
	 */
	public static int getStrategiesNumber() {
		assert (strategiesNumber > 0);
		return strategiesNumber;
	}

	/**
	 * Returns the threshold level when the El Farol Bar is declared
	 * overcrowded.
	 * 
	 * @return the overcrowding threshold value
	 */
	public static int getOvercrowdingThreshold() {
		return overcrowdingThreshold;
	}

	// ========================================================================

	/**
	 * The parameter values are queried and assigned again.
	 * 
	 * <p>
	 * It is executed at each simulation initialization, during the execution of
	 * {@link ElFarolContextBuilder#build(repast.simphony.context.Context)}).
	 * </p>
	 */
	public static void reinit() {
		final Parameters parameters = RunEnvironment.getInstance()
				.getParameters();

		agentsNumber = ((Integer) parameters.getValue("agentsNumber"))
				.intValue();
		percentRand = ((Integer) parameters.getValue("percentRand")).intValue();
		percentSmart = ((Integer) parameters.getValue("percentSmart"))
				.intValue();
		percentAvg = ((Integer) parameters.getValue("percentAvg")).intValue();
		agentType = ((String) parameters.getValue("agentType"));
		memorySize = ((Integer) parameters.getValue("memorySize")).intValue();

		strategiesNumber = ((Integer) parameters.getValue("strategiesNumber"))
				.intValue();

		overcrowdingThreshold = ((Integer) parameters
				.getValue("overcrowdingThreshold")).intValue();
		runTicks = ((Integer) parameters.getValue("runTicks")).intValue();
	}

	// ========================================================================

	/**
	 * This class should be utilized by its static interface so it should not be
	 * instantiated. For this reason the constructor is hidden.
	 */
	private ParameterWrapper() {
		;
	}

	/**
	 * Get the type of agent in this simulation. Options are 'Random' for random
	 * agents ({@link AgentRan}), 'Weighted' for regression agents (
	 * {@link AgentWeighted}), 'Markov' for Markov-chain agents (
	 * {@link AgentMarkov}), 'Average' for averaging agents (
	 * {@link AgentAverage}), and 'Mixed' for heterogenous agents (distributed
	 * according to the percentage parameters).
	 * 
	 * @see ElFarolContextBuilder#build(repast.simphony.context.Context)
	 * @return a string denoting the type of agents used in this simulation.
	 */
	public static String getAgentType() {
		return agentType;
	}

}
