package a.entity.gus06.file.buildfilter.global;

import a.framework.*;
import java.io.File;
import java.util.Map;
import java.util.HashMap;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220513";}


	private Service handleBiggest;
	private Service handleLatest;
	private Service handleSmallest;
	private Service handleOldest;

	public EntityImpl() throws Exception
	{
		handleBiggest = Outside.service(this,"gus06.file.buildfilter.global.biggest");
		handleLatest = Outside.service(this,"gus06.file.buildfilter.global.latest");
		handleSmallest = Outside.service(this,"gus06.file.buildfilter.global.smallest");
		handleOldest = Outside.service(this,"gus06.file.buildfilter.global.oldest");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String rule = (String) o[0];
		List roots = (List) o[1];
		
		Set results = findResults(rule, roots);
		return new Filter(results);
	}
	
	
	
	private int toInt(String s)
	{
		try{return Integer.parseInt(s);}
		catch(NumberFormatException e){}
		return -1;
	}
	
	
	
	private class Filter implements F
	{
		private Set results;
		
		public Filter(Set results) throws Exception
		{this.results = results;}
		
		public boolean f(Object obj) throws Exception
		{
			File file = (File) obj;
			return results.contains(file.getAbsolutePath());
		}
	}
	
	
	
	private Set findResults(String rule, List roots) throws Exception
	{
		String[] n = rule.split(":",2);
		String type = n[0];
		int nb = n.length>1 ? toInt(n[1]) : 1;
		if(nb<1) throw new Exception("Invalid search rule: "+rule);
		
		if(type.equals("biggest")) 
			return (Set) handleBiggest.t(new Object[]{roots,nb});
		if(type.equals("latest")) 
			return (Set) handleLatest.t(new Object[]{roots,nb});
		if(type.equals("smallest")) 
			return (Set) handleSmallest.t(new Object[]{roots,nb});
		if(type.equals("oldest")) 
			return (Set) handleOldest.t(new Object[]{roots,nb});
		
		throw new Exception("Invalid type: "+type);
	}
}