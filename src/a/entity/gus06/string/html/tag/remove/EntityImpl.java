package a.entity.gus06.string.html.tag.remove;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190713";}
	
	public static final String TAG = "<[^>]+>";


	private Service decode;


	public EntityImpl() throws Exception
	{
		decode = Outside.service(this,"gus06.string.transform.format.html.decode");
	}
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		
		StringBuffer b = new StringBuffer();
		String[] nn = s.split(TAG);
		for(String n:nn) 
		{
			String part = (String) decode.t(n);
			if(!part.equals(""))
			{
				if(b.length()>0) b.append(" ");
				b.append(part);
			}
		}
		return b.toString();
	}
}
