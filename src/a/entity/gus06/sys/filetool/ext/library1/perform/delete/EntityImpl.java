package a.entity.gus06.sys.filetool.ext.library1.perform.delete;

import a.framework.*;
import java.util.Map;
import java.util.List;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20200311";}

	public static final String STRUCT = "struct";
	public static final String DISPLAY = "display";
	public static final String CONTENT = "content";
	
	
	private Service confirm;

	public EntityImpl() throws Exception
	{
		confirm = Outside.service(this,"gus06.input.confirm.dialog");
	}
	
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map map = (Map) o[0];
		List keys0 = (List) o[1];
		
		if(keys0==null || keys0.isEmpty()) return false;
		
		String struct = get0(map,STRUCT);
		if(struct==null) return false;
		
		boolean ok = confirm.f(deleteMessage(keys0));
		if(!ok) return false;
		
		String[] nn = struct.split(";");
		int nb = nn.length;
		
		StringBuffer b = new StringBuffer();
		for(int i=0;i<nb;i++)
		{
			if(!keys0.contains(nn[i]))
			b.append(nn[i]+";");
		}
		
		if(b.length()>0) b.deleteCharAt(b.length()-1);
		map.put(STRUCT,b.toString());
		
		for(int i=0;i<keys0.size();i++)
		{
			String key0 = (String) keys0.get(i);
			map.remove(DISPLAY+"."+key0);
			map.remove(CONTENT+"."+key0);
		}
		return true;
	}
	
	
	
	private String deleteMessage(List keys0)
	{
		if(keys0.size()==1) return "You are about to delete 1 entry";
		return "You are about to delete "+keys0.size()+" entries";
	}

	
	
	private String get0(Map map, String key) throws Exception
	{
		if(map==null) throw new Exception("Map not initialized yet");
		if(!map.containsKey(key)) return null;
		return (String) map.get(key);
	}
}
