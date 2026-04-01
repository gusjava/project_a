package a.entity.gus06.string.html.block.inner;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250821";}
	

	private Service beforeLast;
	
	public EntityImpl() throws Exception
	{
		beforeLast = Outside.service(this,"gus06.data.perform.substr.before.last");
	}
	
	public Object t(Object obj) throws Exception
	{
		String block = ((String) obj).trim();
		if(block.length()<7) throw new Exception("Invalid block: "+block);
		if(!block.startsWith("<")) throw new Exception("Invalid block: "+block);
		
		String[] n = block.substring(1).split(">",2);
		if(n.length!=2) throw new Exception("Invalid block: "+block);
		
		String head = n[0];
		String tail = n[1];
		
		String[] nn = head.split("[ \t\n]+",2);
		String name = nn[0].trim();
		
		String endTag = "</"+name+">";
		String inner = (String) beforeLast.t(new Object[]{tail,endTag});
		
		return inner;
	}
}