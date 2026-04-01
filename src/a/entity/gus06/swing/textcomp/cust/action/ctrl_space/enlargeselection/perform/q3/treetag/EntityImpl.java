package a.entity.gus06.swing.textcomp.cust.action.ctrl_space.enlargeselection.perform.q3.treetag;

import a.framework.*;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20201222";}


	
	public void p(Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		String s = comp.getText();
		
		int start = comp.getSelectionStart();
		int end = comp.getSelectionEnd();
		
		String s0 = s.substring(start);
		String[] lines = s0.split("\n",-1);
		
		int pos = start+lines[0].length();
		int level0 = findLevel(lines[0]);
		
		int level = level0;
		for(int i=1;i<lines.length;i++)
		{
			String line = lines[i];
			int newLevel = findLevel(line);
			if(newLevel>0)
			{
				if(newLevel>level+1) break;
				else if(newLevel<=level0) break;
				
				level = newLevel;
			}
			pos += line.length()+1;
		}
		
		if(pos>end) comp.select(start,pos);
		else comp.selectAll();
	}
	
	
	private int findLevel(String tag)
	{
		if(tag==null) return -1;
		int level = 0;
		for(int i=0;i<tag.length();i++)
		{
			char c = tag.charAt(i);
			if(c!='@') return level;
			level++;
		}
		return level;
	}
}