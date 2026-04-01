package a.entity.gus06.sys.store.t.map.comp.list;

import a.framework.*;
import java.util.Map;
import java.util.Vector;
import javax.swing.JList;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140908";}

	public static final String KEY_ICONID = "iconid";
	public static final String KEY_DATA = "data";
	public static final String KEY_SELECTION = "selection";



	private Service findObj;
	private Service buildJList;
	private Service findVector;
	private Service selectionHandler;
	
	public EntityImpl() throws Exception
	{
		findObj = Outside.service(this,"gus06.sys.store.obj.find");
		buildJList = Outside.service(this,"gus06.swing.list.build.fromicon");
		findVector = Outside.service(this,"gus06.find.vector");
		selectionHandler = Outside.service(this,"gus06.swing.list.cust3.selectionhandler");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Map map = (Map) obj;
		
		String iconId = get(map,KEY_ICONID);
		String data = get(map,KEY_DATA);
		String selection = get(map,KEY_SELECTION);
		
		JList list = new JList();
		
		if(iconId!=null)
		list = (JList) buildJList.t(iconId);
		
		if(data!=null)
		list.setListData(findData(data));
		
		if(selection!=null)
		selectionHandler.p(new Object[]{list,obj(selection)});
		
		return list;
	}
	
	
	
	
	private String get(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return (String) map.get(key);
	}
	
	
	private Vector findData(String rule) throws Exception
	{return (Vector) findVector.t(obj(rule));}
	
	
	private Object obj(String rule) throws Exception
	{return findObj.t(rule);}
}
