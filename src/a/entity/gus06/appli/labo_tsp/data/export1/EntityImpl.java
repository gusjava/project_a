package a.entity.gus06.appli.labo_tsp.data.export1;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20190307";}


	private Service manager;

	public EntityImpl() throws Exception
	{
		manager = Outside.service(this,"gus06.appli.labo_tsp.data.manager");
	}
	
	
	public Object g() throws Exception
	{
		int[] dim = (int[]) manager.r("dim");
		List towns = (List) manager.r("towns");
		
		StringBuilder b = new StringBuilder();
		b.append(toString(dim)+"\n");
		
		for(int i=0;i<towns.size();i++)
		{
			double[] town = (double[]) towns.get(i);
			b.append(toString(town)+"\n");
		}
		return b.toString();
	}
	
	
	
	private String toString(int[] d)
	{return d[0]+";"+d[1];}
	
	
	private String toString(double[] d)
	{return d[0]+";"+d[1];}
}
