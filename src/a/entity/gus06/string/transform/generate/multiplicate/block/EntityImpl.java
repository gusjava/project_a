package a.entity.gus06.string.transform.generate.multiplicate.block;

import a.framework.*;
import java.util.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141112";}
	
	public final static String INDEX_N = "<n>";
	

	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		String[] n = s.split("\n",-1);
		
		int nb = number1(n[0]);
		String block = buildBlock(n);
		
		StringBuffer b = new StringBuffer();
		for(int i=0;i<nb;i++) b.append(formatN(block,i));
		return b.toString();
	}
	
	
	private String buildBlock(String[] n)
	{
		StringBuffer b = new StringBuffer();
		for(int i=1;i<n.length;i++) b.append(n[i]+"\n");
		if(b.length()>0) b.deleteCharAt(b.length()-1);
		return b.toString();
	}
	
	
	private String formatN(String s, int index)
	{return s.replaceAll(INDEX_N,""+index);}
	
	
	private int number1(String s)
	{
		try{return Integer.parseInt(s);}
		catch(Exception e){return 1;}
	}
}
