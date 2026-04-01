package a.entity.gus06.convert.stringtopoint;

import a.framework.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.Point;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170306";}

    
	private Pattern p_int = Pattern.compile("[0-9]+");
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		
		if(s==null) return null;
		if(s.equals("")) return null;
		
		List l = extractInt(s);
		
		if(l.size()==2)
		{
			int a = int_(l.get(0));
			int b = int_(l.get(1));
			return new Point(a,b);
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