package a.entity.gus06.sys.store.process.find.list1;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;


public class EntityImpl implements Entity, G {

	public String creationDate() {return "20140907";}


	private Map map;

	public EntityImpl() throws Exception
	{
		map = new HashMap();
		
		init("map");
		init("obj");
		init("obj1");
		
		init("string","gus.tostring.tostring1");
		init("prop","gus.app.prop.get");
		init("entity","gus.find.entity");
		init("class","gus.find.class1");
		init("new","gus.find.obj1");
		init("cg","gus.feature.call.g");
		init("ci","gus.feature.call.i");
		
		init("c","gus.sys.store.t.string.string.customizer1");
		init("scroll","gus.swing.scrollpane.comptoscroll");
	}
	
	
	public Object g() throws Exception
	{return map;}
	
	
	
	private void init(String id)
	{map.put(id,"gus.sys.store.process.p."+id);}
	
	
	private void init(String id, String entity)
	{map.put(id,entity);}
}
