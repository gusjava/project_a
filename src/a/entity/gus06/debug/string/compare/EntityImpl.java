package a.entity.gus06.debug.string.compare;

import a.framework.*;
import java.io.PrintStream;

public class EntityImpl implements Entity, F, P {

	public String creationDate() {return "20190314";}

	private PrintStream out;

	public EntityImpl() throws Exception
	{
		out = (PrintStream) Outside.resource(this,"sysout");
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		String[] s = (String[]) obj;
		if(s.length!=2) throw new Exception("Wrong data number: "+s.length);
		
		String s1 = s[0];
		String s2 = s[1];
		
		int len1 = s1.length();
		int len2 = s2.length();
		
		if(len1!=len2)
		{
			out.println("different lengths: "+len1+" & "+len2);
			return false;
		}
		for(int i=0;i<len1;i++)
		{
			char c1 = s1.charAt(i);
			char c2 = s2.charAt(i);
			
			if(c1!=c2)
			{
				out.println("different characters at position "+i+"+: ["+c1+"] & ["+c2+"]");
				return false;
			}
		}
		return true;
	}
	
	
	public void p(Object obj) throws Exception
	{f(obj);}
}
