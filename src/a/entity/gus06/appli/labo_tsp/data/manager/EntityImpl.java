package a.entity.gus06.appli.labo_tsp.data.manager;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl extends S1 implements Entity, E, R, V {

	public String creationDate() {return "20190306";}
	
	
	private int[] dim;
	private List towns;


	public EntityImpl() throws Exception
	{
		dim = new int[]{1000,1000};
		towns = new ArrayList();
	}
	
	
	public void e() throws Exception
	{reset();}
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("towns")) return towns;
		if(key.equals("dim")) return dim;
		if(key.equals("keys")) return new String[]{"towns","dim"};
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("towns")) {setTowns((List) obj);return;}
		if(key.equals("dim")) {setDim((int[]) obj);return;}
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	
	private void setTowns(List towns)
	{
		this.towns = towns;
		modified();
	}
	
	private void setDim(int[] dim)
	{
		this.dim = dim;
		modified();
	}
	
	private void reset()
	{
		dim = new int[]{1000,1000};
		towns = new ArrayList();
		modified();
	}
	
	private void modified()
	{send(this,"modified()");}
}
