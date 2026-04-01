package a.entity.gus06.sys.quickreplace.t.byposition;

import a.framework.*;
import java.util.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160430";}

	
	
	public Object t(Object obj) throws Exception
	{
		R info = (R) obj;
		
		String textMem = (String) info.r("text");
		String selected = (String) info.r("selected");
		String replace = (String) info.r("replace");
		
		int position = (Integer) info.r("caretPos");
		int delBefore = (Integer) info.r("delBefore");
		int delAfter = (Integer) info.r("delAfter");
		
		if(position==textMem.length() || textMem.charAt(position)=='\n')
		return new ReplaceAtEndOfLine(replace, delBefore, delAfter);
		
		int d = 0;
		while(d<position && textMem.charAt(position-d-1)!='\n') d++;
		return new ReplaceAtFixedPosition(replace, delBefore, delAfter, d);
	}
	
	
	
	public class ReplaceAtEndOfLine implements T
	{
		private String replace;
		private int delBefore;
		private int delAfter;
    
		public ReplaceAtEndOfLine(String replace, int delBefore, int delAfter)
		{
			this.replace = replace;
			this.delBefore = delBefore;
			this.delAfter = delAfter;
		}
		
		public Object t(Object obj) throws Exception
		{
			String text = (String) obj;
			List ranges = new ArrayList();
			int length = text.length();
			
			for(int i=0;i<length;i++)
			if(text.charAt(i)=='\n')
			{
				int start = i-delBefore;
				int end = i+delAfter;
				
				if(start<0) start = 0;
				if(end>length) end = length;
				
				Object[] range = new Object[]{start, end, replace};
				ranges.add(range);
			}
			
			int start = length-delBefore;
			int end = length;
			
			Object[] range = new Object[]{start,end,replace};
			ranges.add(range);
			
			return ranges;
		}
	}
	
	
	
	public class ReplaceAtFixedPosition implements T
	{
		private String replace;
		private int delBefore;
		private int delAfter;
		private int d;

		public ReplaceAtFixedPosition(String replace, int delBefore, int delAfter, int d)
		{
			this.replace = replace;
			this.delBefore = delBefore;
			this.delAfter = delAfter;
			this.d = d;
		}
    

		public Object t(Object obj) throws Exception
		{
			String text = (String) obj;
			List ranges = new ArrayList();
			int d0=0;
        
			for(int i=0;i<text.length();i++)
			{
				char c = text.charAt(i);
				if(d0==d)
				{
					Object[] range = new Object[]{i-delBefore, i+delAfter, replace};
					ranges.add(range);
				}
				
				d0++;
				if(c=='\n') d0=0;
			}
			return ranges;
		}
	}
}