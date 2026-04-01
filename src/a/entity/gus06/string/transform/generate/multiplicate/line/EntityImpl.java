package a.entity.gus06.string.transform.generate.multiplicate.line;

import a.framework.*;
import java.util.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141112";}
	
	public final static String INDEX_I = "<i>";
	public final static String INDEX_J = "<j>";
	

	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		String[] n = s.split("\n",-1);
		
		StringBuffer b = new StringBuffer();
		for(int i=0;i<n.length;i++) handle(b,n[i]);
		
		if(b.length()>0) b.deleteCharAt(b.length()-1);
		return b.toString();
	}
	
	
	private void handle(StringBuffer b, String line)
	{
		if(line.contains(" "))
		{
			String[] n = line.split(" ",2);
			int number = number(n[0]);
			if(number>0)
			{
				String line0 = n[1];
				for(int j=0;j<number;j++)
				b.append(formatJ(line0,j));
				b.append("\n");
				return;
			}
		}
		if(line.contains("\t"))
		{
			String[] n = line.split("\t",2);
			int number = number(n[0]);
			if(number>0)
			{
				String line0 = n[1];
				for(int j=0;j<number;j++)
				b.append(formatI(line0,j)+"\n");
				return;
			}
		}
		b.append(line+"\n");
	}
	
	
	private String formatI(String s, int index)
	{return s.replaceAll(INDEX_I,""+index);}
	
	private String formatJ(String s, int index)
	{return s.replaceAll(INDEX_J,""+index);}
	
	
	private int number(String s)
	{
		try{return Integer.parseInt(s);}
		catch(Exception e){return 0;}
	}
}
