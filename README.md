cs383f2015-lab3
================

El Farol Bar multi-agent simulations in Repast Simphony.
 - For plots and data analysis, check out our [IPython notebook](http://nbviewer.ipython.org/github/hawkw/cs383f2015-lab3/blob/master/analysis.ipynb)
 - For documentation of implemented strategies, check out the [JavaDoc](http://hawkweisman.me/cs383f2015-lab3/) for our agent classes.

Implementation
--------------

We have added three additional agent types:
 - [`AgentRan`](http://hawkweisman.me/cs383f2015-lab3/elfarol/AgentRan.html), which makes a random prediction between the maximum and minimum attendance levels.
 - [`AgentAverage`](http://hawkweisman.me/cs383f2015-lab3/elfarol/AgentAverage.html), which makes a prediction based on the average of all the remembered attendence levels.
 - [`AgentMarkov`](http://hawkweisman.me/cs383f2015-lab3/elfarol/AgentMarkov.html), which uses a Markov chain predictive model to predict the next attendance.

These new agent types were implemented by extracting an interface [`Agent`](http://hawkweisman.me/cs383f2015-lab3/elfarol/Agent.html) from the public methods in the provided `Agent` class (renamed to [`AgentWeighted`](http://hawkweisman.me/cs383f2015-lab3/elfarol/AgentWeighted.html)). New agent classes were written to extend this interface, and the [`ElFarolContextBuilder`](http://hawkweisman.me/cs383f2015-lab3/elfarol/ElFarolContextBuilder.html) class was modified to create agents of the correct type depending on the parameters passed to the model. Additional parameters were added to the Repast model for controlling the distribution of agents, the maximum run time, and other characteristics.

For documentation on the functionality of each agent type, please check out the [JavaDoc](http://hawkweisman.me/cs383f2015-lab3/) for their respective Java classes.

We had intended to use Repast's batch mode for running all of the required parameters configurations in the laboratory handout, but this was determined to be impossible due to memory leak bug in Repast. Therefore, each parameters configuration was run by hand, instead.

Analysis
--------

Analysis of the various strategies is contained in this [IPython notebook](http://nbviewer.ipython.org/github/hawkw/cs383f2015-lab3/blob/master/analysis.ipynb).


Learning Take-aways
------------------

We learned more about Markov chains while implementing it. A Markov chain is a predicitve model that uses the previous two elements to predict the next element.

We learned that the effects of numAgents has an impact of the trends shown. With more Agents inside the model, you can see patterns begin to appear within the attendance graphs (see graphs).

We learned that some strategies are better at handling the threashold differences than others. The Regression model is able to even itself out at the threshhold higher than average, but when the threshold is lower it does not work.
