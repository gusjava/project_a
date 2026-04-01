package a.entity.gus06.convert.stringtoinsets;

import a.framework.*;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160907";}

    
	private Pattern p_int = Pattern.compile("[0-9]+");
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		if(s.equals("")) return null;
		
		List l = extractInt(s);
		
		if(l.size()==1)
		{
			int a = int_(l.get(0));
			return new Insets(a,a,a,a);
		}
		if(l.size()==4)
		{
			int a = int_(l.get(0));
			int b = int_(l.get(1));
			int c = int_(l.get(2));
			int d = int_(l.get(3));
			return new Insets(a,b,c,d);
		}
		throw new Exception("Invalid insets definition: "+s);
	}
    
    
	private List extractInt(String s)
	{
		List l = new ArrayList();
		Matcher m = p_int.matcher(s);
		while(m.find()) l.add(m.group());
		return l;
	}
	
	private int int_(Object s)
	{return Integer.parseInt((String) s);}
}
