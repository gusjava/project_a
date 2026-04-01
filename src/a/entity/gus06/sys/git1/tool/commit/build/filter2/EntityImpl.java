package a.entity.gus06.sys.git1.tool.commit.build.filter2;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20230228";}
	
	
	private Service allOfThem_n;
	private Service allOfThem;
	private Service contains_n;
	private Service contains;
	
	public EntityImpl() throws Exception
	{
		allOfThem_n = Outside.service(this,"gus06.filter.string.build.allofthem_n");
		allOfThem = Outside.service(this,"gus06.filter.string.build.allofthem");
		contains_n = Outside.service(this,"gus06.filter.string.build.contains_n");
		contains = Outside.service(this,"gus06.filter.string.build.contains");
	}
	
	public Object t(Object obj) throws Exception
	{
		String query = (String) obj;
		if(query==null || query.equals("")) return null;
		
		StringBuilder options = new StringBuilder();
		if(query.startsWith("@"))
		{
			options.append("@");
			query = query.substring(1);
		}
		if(query.startsWith(":"))
		{
			options.append(":");
			query = query.substring(1);
		}
		if(query.startsWith(">"))
		{
			options.append(">");
			query = query.substring(1);
		}
		if(options.length()==0) options.append("@:>");
		
		if(query.equals("")) return null;
		
		F filter = findFilter(query);
		return new Object[]{filter, options.toString()};
	}
	
	
	private F findFilter(String query) throws Exception
	{
		boolean strict = false;
		if(query.startsWith("!"))
		{
			query = query.substring(1);
			strict = true;
		}
		
		boolean full = false;
		if(query.startsWith("'"))
		{
			query = query.substring(1);
			full = true;
		}
		
		T builder = full ? (strict ? contains : contains_n) : (strict ? allOfThem : allOfThem_n);
		return (F) builder.t(query);
	}
}