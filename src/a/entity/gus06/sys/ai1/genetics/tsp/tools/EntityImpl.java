package a.entity.gus06.sys.ai1.genetics.tsp.tools;

import a.framework.*;

public class EntityImpl implements Entity, R {

	public String creationDate() {return "20170428";}


	private Service crossover_ox;


	public EntityImpl() throws Exception
	{
		crossover_ox = Outside.service(this,"gus06.sys.ai1.genetics.tsp.crossover.ox");
	}
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("crossover_ox")) return crossover_ox;
		if(key.equals("keys")) return new String[]{"crossover_ox"};
		
		throw new Exception("Unknown key: "+key);
	}
}
