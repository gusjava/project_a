package a.entity.gus06.sys.ai1.genetics.engine1;

import a.framework.*;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, V, R, E {

	public String creationDate() {return "20170429";}


	private Set population;
	private T fitnessMeasure;
	private T mating;
	
	private Map fitnessMap;
	

	public EntityImpl() throws Exception
	{
		fitnessMap = new HashMap();
	}
	
	
	public Object r(String key) throws Exception
	{
		return null;
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("population")) {population = (Set) obj;return;}
		if(key.equals("fitnessMeasure")) {fitnessMeasure = (T) obj;return;}
		if(key.equals("mating")) {mating = (T) obj;return;}
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	public void e() throws Exception
	{
		if(population==null) throw new Exception("Population not initialized yet");
		
		//1: Create an initial population of P chromosomes (generation 0).
		
		//2: Evaluate the fitness of each chromosome
		
		//3: Select P parents from the current population via proportional selection (i.e.,the selection probability is proportional to the fitness).
		
		//4: Choose at random a pair of parents for mating. Exchange bit strings with the one-point crossover to create two offspring.
		
		//5: Process each offspring by the mutation operator, and insert the resulting offspring in the new population.
		
		//6: Repeat steps 4 and 5 until all parents are selected and mated (P offspring are created).
		
		//7: Replace the old population of chromosomes by the new one.
		
		//8: Evaluate the fitness of each chromosome in the new population.
		
		//9: Go back to step 3 if the number of generations is less than some upper bound. Otherwise, the final result is the best chromosome created during the search.
	}
	
	
	private Object findFitness(Object individual) throws Exception
	{
		if(!fitnessMap.containsKey(individual))
			fitnessMap.put(individual,fitnessMeasure.t(individual));
		return fitnessMap.get(individual);
	}
}
