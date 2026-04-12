package a.entity.gus.y.quickreplace1.buildexp;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20240714";}

	private Service quote;
	
	public EntityImpl() throws Exception
	{
		quote = Outside.service(this,"gus.x.transform.string.regexp.quote");
	}
	
	public Object t(Object obj) throws Exception
	{
		String[] o = (String[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		String center = o[0];
		int delBefore = int_(o[1]);
		int delAfter = int_(o[2]);
		
		StringBuffer b = new StringBuffer();
		
		for(int i=0;i<delBefore;i++) b.append(".");
		b.append((String) quote.t(center));
		for(int i=0;i<delAfter;i++) b.append(".");
		
		return b.toString();
	}
	
	private int int_(String s)
	{return Integer.parseInt(s);}
}