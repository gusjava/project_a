package a.entity.gus.y.stringcase1.to.pascalcase;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20240714";}

	private Service splitCase;

	public EntityImpl() throws Exception
	{
		splitCase = Outside.service(this,"gus.y.stringcase1.splitcase");
	}
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof String[]) return build((String[]) obj);
		if(obj instanceof String) return build((String[]) splitCase.t(obj));
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private String build(String[] nn)
	{
		StringBuilder sb = new StringBuilder();
		int len = nn.length;
		for(int i=0;i<len;i++) {
			sb.append(titled(nn[i]));
		}
		return sb.toString();
	}
	
	private String titled(String s)
	{
		if(s==null || s.equals("")) return "";
		return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
	}
}