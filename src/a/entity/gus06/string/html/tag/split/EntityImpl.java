package a.entity.gus06.string.html.tag.split;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190704";}
	
	public static final String TAG = "<[^>]+>";


	private Service decode;


	public EntityImpl() throws Exception
	{
		decode = Outside.service(this,"gus06.string.transform.format.html.decode");
	}
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		
		List list = new ArrayList();
		String[] nn = s.split(TAG);
		for(String n:nn) 
		{
			String part = (String) decode.t(n);
			if(!part.equals("")) list.add(part);
		}
		return list;
	}
}
