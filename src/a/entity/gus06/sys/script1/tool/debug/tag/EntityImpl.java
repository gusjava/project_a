package a.entity.gus06.sys.script1.tool.debug.tag;

import a.framework.*;
import java.util.Map;
import java.util.List;
import java.util.Set;

public class EntityImpl implements Entity, V {

	public String creationDate() {return "20160330";}


	private Service display;
	private Service deepGet;
	private Service showText;

	public EntityImpl() throws Exception
	{
		display = Outside.service(this,"gus06.tostring.desc");
		deepGet = Outside.service(this,"gus06.map.deep.get");
		showText = Outside.service(this,"gus06.swing.frame.show.text");
	}


	
	public void v(String key, Object obj) throws Exception
	{
		Map tag = (Map) obj;
		StringBuffer b = new StringBuffer();
		
		String value = (String) find(tag,"value");
		String[] nn = key.split(";");
		
		p_(b,"----------------------------");
		p_(b,"DEBUG TAG: "+value);
		for(String n:nn) debug(b,tag,n);
		p_(b,"----------------------------");
		
		showText.p(b.toString());
	}
	
	
	private void debug(StringBuffer b, Map tag, String info) throws Exception
	{
		String path = formatInfo(info);
		Object data = find(tag,path);
		
		p_(b,info+":"+display(data)+"\n");
	}
	
	
	private Object find(Map tag, String n) throws Exception
	{return deepGet.t(new Object[]{tag,n});}
	
	private String display(Object obj) throws Exception
	{return (String) display.t(obj);}
	
	private void p_(StringBuffer b, String s)
	{b.append(s+"\n");}
	
	
	
	
	private String formatInfo(String n)
	{
		if(n.equals("tag")) return "stack.owner";
		if(n.equals("pool")) return "stack.pool";
		
		if(n.equals("tag2")) return "parent";
		if(n.equals("tag3")) return "parent.parent";
		if(n.equals("tag4")) return "parent.parent.parent";
		
		if(n.equals("pool2")) return "stack.pool.parent";
		if(n.equals("pool3")) return "stack.pool.parent.parent";
		if(n.equals("pool4")) return "stack.pool.parent.parent.parent";
		
		if(n.equals("stack2")) return "stack.parent";
		if(n.equals("stack3")) return "stack.parent.parent";
		if(n.equals("stack4")) return "stack.parent.parent.parent";
	
		if(n.startsWith("tag.")) return "stack.owner."+n.substring(4);
		if(n.startsWith("pool.")) return "stack."+n;
		
		if(n.startsWith("tag2.")) return "parent."+n.substring(5);
		if(n.startsWith("tag3.")) return "parent.parent."+n.substring(5);
		if(n.startsWith("tag4.")) return "parent.parent.parent."+n.substring(5);
		
		if(n.startsWith("pool2.")) return "stack.pool.parent."+n.substring(6);
		if(n.startsWith("pool3.")) return "stack.pool.parent.parent."+n.substring(6);
		if(n.startsWith("pool4.")) return "stack.pool.parent.parent.parent."+n.substring(6);
		
		if(n.startsWith("stack2.")) return "stack.parent."+n.substring(7);
		if(n.startsWith("stack3.")) return "stack.parent.parent."+n.substring(7);
		if(n.startsWith("stack4.")) return "stack.parent.parent.parent."+n.substring(7);
		
		return n;
	}
}
