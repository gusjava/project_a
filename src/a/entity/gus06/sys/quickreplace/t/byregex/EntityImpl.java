package a.entity.gus06.sys.quickreplace.t.byregex;

import a.framework.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160430";}

	private Service buildexp;

	public EntityImpl() throws Exception
	{
		buildexp = Outside.service(this,"gus06.sys.quickreplace.buildexp");
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
		
            
		String exp = buildexp(selected, delBefore, delAfter);
		return new ReplaceByRegex(exp, replace);
	}
	
	private String buildexp(String selected, int delBefore, int delAfter) throws Exception
	{return (String) buildexp.t(new String[]{selected, ""+delBefore, ""+delAfter});}
	
	
	

	public class ReplaceByRegex implements T
	{
		private String exp;
		private String newExp;

		public ReplaceByRegex(String exp, String newExp)
		{
			this.exp = exp;
			this.newExp = newExp;
		}
		
		public Object t(Object obj) throws Exception
		{
			String text = (String) obj;
			
			Pattern p = Pattern.compile(exp,Pattern.DOTALL);
              		Matcher m = p.matcher(text);
			
			List ranges = new ArrayList();
			while(m.find())
			{
				Integer start = Integer.valueOf(m.start());
				Integer end = Integer.valueOf(m.end());
				Object[] range = new Object[]{start,end,newExp};
				ranges.add(range);
			}
			return ranges;
		}
	}
}