package a.entity.gus06.sys.ai1.genetics.tsp.towns.builder;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170424";}

	
	
	public Object t(Object obj) throws Exception
	{
		int[] infos = (int[]) obj;
		if(infos.length!=2) throw new Exception("Wrong data number: "+infos.length);
		
		int max = infos[0];
		int nb = infos[1];
		
		List list = new ArrayList();
		for(int i=0;i<nb;i++)
		{
			double x = random(max);
			double y = random(max);
			list.add(new double[]{x,y});
		}
		
		return list;
	}
	
	private double random(int max)
	{return Math.random()*max;}
}
