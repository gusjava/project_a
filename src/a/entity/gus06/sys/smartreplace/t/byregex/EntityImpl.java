package a.entity.gus06.sys.smartreplace.t.byregex;

import a.framework.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160430";}

	private Service buildexp;
	private Service buildTransMap;

	public EntityImpl() throws Exception
	{
		buildexp = Outside.service(this,"gus06.sys.quickreplace.buildexp");
		buildTransMap = Outside.service(this,"gus06.string.case1.transmap");
	}
	
	
	
	public Object t(Object obj) throws Exception
	{
		R info = (R) obj;
		
		String textMem = (String) info.r("text");
		String selected = (String) info.r("selected");
		String replace = (String) info.r("replace");
		
		int position = (Integer) info.r("caretPos");
		int delBefore = (Integer) info.r("delBefore");
		int delAfter = (Integer) info.r("delAfter");
		
		Map map = (Map) buildTransMap.t(new Object[]{selected, replace});
		return new ReplaceByRegex(map,delBefore,delAfter);
	}
	
	
	private String buildexp(String selected, int delBefore, int delAfter) throws Exception
	{return (String) buildexp.t(new String[]{selected,""+delBefore,""+delAfter});}
	
	
	

	public class ReplaceByRegex implements T
	{
		private Map map;
		private int delBefore;
		private int delAfter;

		public ReplaceByRegex(Map map, int delBefore, int delAfter)
		{
			this.map = map;
			this.delBefore = delBefore;
			this.delAfter = delAfter;
		}
		
		public Object t(Object obj) throws Exception
		{
			String text = (String) obj;
			Map map1 = new HashMap();
			
			Iterator it = map.keySet().iterator();
			while(it.hasNext())
			{
				String key = (String) it.next();
				String value = (String) map.get(key);
				
				String exp = buildexp(key,delBefore,delAfter);
				
				Pattern p = Pattern.compile(exp,Pattern.DOTALL);
              			Matcher m = p.matcher(text);
				
				while(m.find())
				{
					Integer start = Integer.valueOf(m.start());
					Integer end = Integer.valueOf(m.end());
					Object[] range = new Object[]{start,end,value};
					map1.put(start,range);
				}
			}
			
			List ranges = new ArrayList();
			
			List keys = new ArrayList(map1.keySet());
			Collections.sort(keys);
			
			for(int i=0;i<keys.size();i++)
			{
				Integer key = (Integer) keys.get(i);
				Object[] range = (Object[]) map1.get(key);
				ranges.add(range);
			}
			return ranges;
		}
	}
}