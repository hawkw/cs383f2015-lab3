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
 * @since 2.0-beta, 2011
 * @version $Id: ParameterWrapper.java 514 2011-07-08 14:50:51Z
 *          richard.legendi@gmail.com $
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
		return agentsNumber;
	}
	public static int getPercentRand() {
		return percentRand;
	}
	public static int getPercentAvg() {
		return percentAvg;
	}
	public static int getPercentSmart() {
		return percentSmart;
	}

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
		return memorySize;
	}

	/**
	 * Returns the number of strategies an agent may operate with.
	 * 
	 * @return the number of strategies of an agent; should be positive
	 */
	public static int getStrategiesNumber() {
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
		percentRand = ((Integer) parameters.getValue("percentRand"))
				.intValue();
		percentSmart = ((Integer) parameters.getValue("percentSmart"))
				.intValue();
		percentAvg = ((Integer) parameters.getValue("percentAvg"))
				.intValue();
		agentType = ((String) parameters.getValue("agentType"));
		memorySize = ((Integer) parameters.getValue("memorySize")).intValue();

		strategiesNumber = ((Integer) parameters.getValue("strategiesNumber"))
				.intValue();

		overcrowdingThreshold = ((Integer) parameters
				.getValue("overcrowdingThreshold")).intValue();
		runTicks = ((Integer) parameters
				.getValue("runTicks")).intValue();
	}

	// ========================================================================

	/**
	 * This class should be utilized by its static interface so it should not be
	 * instantiated. For this reason the constructor is hidden.
	 */
	private ParameterWrapper() {
		;
	}
	public static String getAgentType() {
		return agentType;
	}

}
