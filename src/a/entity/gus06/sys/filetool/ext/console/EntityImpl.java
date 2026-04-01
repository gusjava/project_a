package a.entity.gus06.sys.filetool.ext.console;

import a.framework.*;
import java.io.File;
import java.util.Map;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141230";}


	private Service findRoot;
	private Service newConsole;

	
	public EntityImpl() throws Exception
	{
		findRoot = Outside.service(this,"gus06.sys.filetool.findroot");
		newConsole = Outside.service(this,"factory#gus.sys.console.gui1");
	}
	
	
	
	public Object t(Object obj) throws Exception
	{
		Map map = (Map) obj;
		
		Object console = newConsole.g();
		((V) console).v("cmdBuilder",new CmdBuilder(map));
		((V) console).v("root",findRoot(map));
		
		String colorWait = get(map,"color.wait");
		String colorIn = get(map,"color.in");
		
		if(colorWait!=null) ((V) console).v("colorWait",colorWait);
		if(colorIn!=null) ((V) console).v("colorIn",colorIn);
		
		return ((I) console).i();
	}
	
	
	private File findRoot(Map map) throws Exception
	{return (File) findRoot.t(map);}
	
	
	
	private String get(Map map, String key)
	{return map.containsKey(key)?(String) map.get(key):null;}
	
	
	
	
	private class CmdBuilder implements T
	{
		private Map map;
		public CmdBuilder(Map map) {this.map = map;}

		public Object t(Object obj) throws Exception
		{
			String text = (String) obj;
			if(map.containsKey("alias."+text))
				return (String) map.get("alias."+text);
			return text;
		}
	}
}
