package a.entity.gus06.string.transform.html.block.inside;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190519";}

	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		
		if(!s.contains("<")) throw new Exception("Invalid tag block: "+s);
		if(!s.contains(">")) throw new Exception("Invalid tag block: "+s);
		
		String[] nn = s.split("<",2);
		if(nn.length!=2) throw new Exception("Invalid tag block: "+s);
		s = nn[1];
		
		nn = s.split(">",2);
		if(nn.length!=2) throw new Exception("Invalid tag block: "+s);
		s = nn[1];
		
		int c = s.lastIndexOf(">");
		if(c==-1) throw new Exception("Invalid tag block: "+s);
		s = s.substring(0,c);
		
		c = s.lastIndexOf("<");
		if(c==-1) throw new Exception("Invalid tag block: "+s);
		s = s.substring(0,c);
		
		return s.trim();
	}
}