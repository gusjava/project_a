package a.entity.gus06.java.srccode.extract.entity.dependencies1;

import a.framework.*;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150823";}


	private Service calls;
	private Service depRegex;


	public EntityImpl() throws Exception
	{
		calls = Outside.service(this,"gus06.java.srccode.extract.entity.calls");
		depRegex = Outside.service(this,"gus06.java.srccode.extract.entity.dependencies");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		Set set = new HashSet();
		
		List list = (List) calls.t(obj);
		for(int i=0;i<list.size();i++)
		{
			String call = (String) list.get(i);
			set.addAll(extract(call));
		}
		return set;
	}
	
	
	private Set extract(String s) throws Exception
	{return (Set) depRegex.t(s);}
}
