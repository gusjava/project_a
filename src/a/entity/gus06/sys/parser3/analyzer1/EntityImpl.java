package a.entity.gus06.sys.parser3.analyzer1;

import a.framework.*;
import java.util.List;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160810";}
	
	
	private Service analyzeTag;
	private Service analyzeUnary;
	private Service analyzeList;
	
	public EntityImpl() throws Exception
	{
		analyzeTag = Outside.service(this,"gus06.sys.parser3.analyzer1.el.tag");
		analyzeUnary = Outside.service(this,"gus06.sys.parser3.analyzer1.el.unary");
		analyzeList = Outside.service(this,"gus06.sys.parser3.analyzer1.el.list");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof List)
			return analyzeList((List) obj);
		if(obj instanceof Map)
			return analyzeTag.t(new Object[]{obj,this});
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private Object analyzeList(List l) throws Exception
	{
		if(l.size()==0)		throw new Exception("Invalid empty list");
		if(l.size()==1)		return analyzeTag.t(new Object[]{l.get(0),this});
		if(l.size()==2)		return analyzeUnary.t(new Object[]{l.get(0),l.get(1),this});
		
		return analyzeList.t(new Object[]{l,this});
	}
}
