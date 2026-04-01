package a.entity.gus06.math.perfectsqr.check;

import a.framework.*;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20231107";}

	

	public EntityImpl() throws Exception
	{
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		if(obj==null) return false;
		if(obj instanceof Long) return check((Long) obj);
		if(obj instanceof Integer) return check((Integer) obj);
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private boolean check(long v)
	{
		long r = 0;
		long r2 = 0;
		while(r2<v) {r++;r2 = r*r;}
		return v==r2;
	}
	
	private boolean check(int v)
	{
		int r = 0;
		int r2 = 0;
		while(r2<v) {r++;r2 = r*r;}
		return v==r2;
	}
}