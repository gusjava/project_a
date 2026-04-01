package a.entity.gus06.appli.labo_tsp.data.import1;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20190307";}


	private Service manager;

	public EntityImpl() throws Exception
	{
		manager = Outside.service(this,"gus06.appli.labo_tsp.data.manager");
	}
	
	public void p(Object obj) throws Exception
	{
		String s = (String) obj;
		
		String[] nn = s.split("[\n\r]");
		if(nn.length==0) throw new Exception("Invalid text: "+s);
		
		try
		{
			int[] dim = toIntArray(nn[0]);
			manager.v("dim",dim);
		}
		catch(Exception e){}
		
		List towns = new ArrayList();
		for(int i=1;i<nn.length;i++)
		{
			try{towns.add(toDoubleArray(nn[i]));}
			catch(Exception e){}
		}
		manager.v("towns",towns);
	}
	
	
	
	
	private int[] toIntArray(String line) throws Exception
	{
		String[] k = line.trim().split(";");
		if(k.length!=2) throw new Exception("Invalid line: "+line);
		return new int[]{i(k[0]),i(k[1])};
	}
	
	private int i(String s) throws Exception
	{return Integer.parseInt(s);}
	
	
	
	
	
	private double[] toDoubleArray(String line) throws Exception
	{
		String[] k = line.trim().split(";");
		if(k.length!=2) throw new Exception("Invalid line: "+line);
		return new double[]{d(k[0]),d(k[1])};
	}
	
	private double d(String s) throws Exception
	{return Double.parseDouble(s);}
}
