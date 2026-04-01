package a.entity.gus06.string.transform.html.block.empty;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190709";}

	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		
		if(!s.contains("<")) throw new Exception("Invalid tag block: "+s);
		if(!s.contains(">")) throw new Exception("Invalid tag block: "+s);
		
		StringBuffer b = new StringBuffer();
		
		String[] nn = s.split(">",2);
		if(nn.length!=2) throw new Exception("Invalid tag block: "+s);
		if(!nn[0].contains("<")) throw new Exception("Invalid tag block: "+s);
		b.append(nn[0]+">");
		
		s = nn[1];
		
		int c = s.lastIndexOf("<");
		if(c==-1) throw new Exception("Invalid tag block: "+s);
		s = s.substring(c,s.length());
		if(!s.contains(">")) throw new Exception("Invalid tag block: "+s);
		b.append(s);
		
		return b.toString();
	}
}