package a.entity.gus06.sys.treeparser1.builder;

import a.framework.*;
import java.util.Map;
import java.util.List;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20161030";}
	
	public static final String NAME = "name";
	public static final String CHILDREN = "children";
	public static final String DATA = "data";


	private Service findP;


	public EntityImpl() throws Exception
	{
		findP = Outside.service(this,"gus06.find.p");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		P p = (P) findP.t(o[0]);
		Map map = (Map) o[1];
		
		handle(map,p,0);
		
		if(p instanceof E) ((E) p).e();
	}
	
	
	private void handle(Map map, P p, int deep) throws Exception
	{
		String name = (String) get1(map,NAME);
		String data = (String) get0(map,DATA);
		List children = (List) get0(map,CHILDREN);
		
		if(deep>0)
		{
			for(int i=0;i<deep;i++) p.p("@");
			p.p(name+"\n");
		}
		
		if(data!=null)
		{
			if(!data.equals(""))
			p.p(checkData(data));
		}
		
		if(children!=null)
		{
			for(int i=0;i<children.size();i++)
			handle((Map) children.get(i),p,deep+1);
		}
	}
	
	
	
	private Object get1(Map map, String key) throws Exception
	{
		if(!map.containsKey(key)) throw new Exception("Key not found inside map: "+key);
		return map.get(key);
	}
	
	private Object get0(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return map.get(key);
	}
	
	private String checkData(String data) throws Exception
	{
		if(!data.endsWith("\n")) return data+"\n";
		return data;
	}
}
