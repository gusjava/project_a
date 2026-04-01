package a.entity.gus06.time.date.int3.display1.fr;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160618";}


	private Service dayOfWeek;


	public EntityImpl() throws Exception
	{
		dayOfWeek = Outside.service(this,"gus06.time.date.int3.dayofweek.fr");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		int[] n = (int[]) obj;
		if(n.length!=3) throw new Exception("Invalid int array length: "+n.length);
		
		String week = (String) dayOfWeek.t(n);
		return week+" "+n[2]+"/"+n[1]+"/"+n[0];
	}
}
