package a.entity.gus06.string.transform.format.uniline;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201126";}
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		if(s==null) return null;
		
		s = s.replace("\n"," ");
		s = s.replace("\t"," ");
		s = s.replaceAll(" +"," ");
		return s.trim();
	}
}