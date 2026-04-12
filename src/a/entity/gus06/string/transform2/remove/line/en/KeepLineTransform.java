/*
 * Created on 5 mars 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package a.entity.gus06.string.transform2.remove.line.en;

import a.framework.*;


public class KeepLineTransform implements T {

	
	private String selected;
	

	public KeepLineTransform(String selected)
	{this.selected = selected;}

	
	
	
	public Object t(Object obj) throws Exception
	{
		if(selected.equals("")) return obj;
		if(selected.contains("\n"))return obj;
		
		String[] lines = obj.toString().split("[\n\r]",-1);
		StringBuffer b = new StringBuffer();
		for(int i=0;i<lines.length;i++)
		{
			String line = lines[i];
			if(line.contains(selected))
			  b.append(line+"\n");	
		}
		if(b.length()>0)
		b.deleteCharAt(b.length()-1);
		return b.toString();
	}

}
