package a.entity.gus06.sys.git1.filehistory.handle.highlight.src;

import a.framework.*;
import javax.swing.text.JTextComponent;
import java.util.Map;
import java.util.HashMap;
import java.awt.Color;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20230301";}

	public static final String KEY_OPTIONS = "options";
	public static final String KEY_SEARCH = "search";
	public static final String KEY_PAINTER = "painter";

	public static Color COLOR = new Color(255,102,255);

	private Service perform;
	private Service splitMethod;

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.swing.textcomp.highlight.search.map1");
		splitMethod = Outside.service(this,"gus06.string.split.method2");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		JTextComponent comp = (JTextComponent) o[0];
		Map commit = (Map) o[1];
		
		if(!commit.containsKey("query")) return;
		String query = (String) commit.get("query");
		
		StringBuilder sb = new StringBuilder();
		if(query.startsWith("@"))
		{
			sb.append("@");
			query = query.substring(1);
		}
		if(query.startsWith(":"))
		{
			sb.append(":");
			query = query.substring(1);
		}
		if(query.startsWith(">"))
		{
			sb.append(">");
			query = query.substring(1);
		}
		
		if(sb.length()>0 && !sb.toString().contains(">")) return;
		
		boolean strict = false;
		if(query.startsWith("!"))
		{
			query = query.substring(1);
			strict = true;
		}
		
		boolean full = false;
		if(query.startsWith("'"))
		{
			query = query.substring(1);
			full = true;
		}
		
		if(query.equals("")) return;
		
		String options = strict ? "" : "n";
		String[] searchs = full ? new String[]{query} : (String[]) splitMethod.t(query);
		
		for(String search : searchs)
		{
			Map m = new HashMap();
			m.put(KEY_SEARCH, search);
			m.put(KEY_OPTIONS, options);
			m.put(KEY_PAINTER, COLOR);
			
			perform.p(new Object[]{comp, m});
		}
	}
}