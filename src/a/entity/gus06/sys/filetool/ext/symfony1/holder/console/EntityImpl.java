package a.entity.gus06.sys.filetool.ext.symfony1.holder.console;

import a.framework.*;
import javax.swing.JComponent;
import java.util.Map;
import java.io.File;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20150329";}

	private Service console;
	private Service findRoot;
	
	private Map map;
	private File root;

	

	public EntityImpl() throws Exception
	{
		console = Outside.service(this,"*gus06.sys.console.gui1");
		findRoot = Outside.service(this,"gus06.sys.filetool.findroot");
		
		console.v("cmdBuilder",new T(){
			public Object t(Object obj) throws Exception
			{return buildCmd((String) obj);}
		});
	}
	
	
	
	public Object i() throws Exception
	{return console.i();}
	
	
	private String get(String key)
	{return map.containsKey(key)?(String) map.get(key):null;}
	
	
	
	
	
	public void p(Object obj) throws Exception
	{
		map = (Map) obj;
		root = (File) findRoot.t(map);
		
		console.v("root",new File(root,"app"));
		
		String colorWait = get("color.wait");
		String colorIn = get("color.in");
		
		if(colorWait!=null) console.v("colorWait",colorWait);
		if(colorIn!=null) console.v("colorIn",colorIn);
	}
	
	
	
	
	
	private String buildCmd(String text)
	{
		if(text.equals("")) return "php console";
		return "php console "+handleAlias(text);
	}
	
	private String handleAlias(String text)
	{
		if(map==null) return text;
		if(!map.containsKey("alias."+text)) return text;
		return (String) map.get("alias."+text);
	}
}
