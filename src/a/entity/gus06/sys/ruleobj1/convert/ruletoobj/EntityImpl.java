package a.entity.gus06.sys.ruleobj1.convert.ruletoobj;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170117";}


	private Service performUrl;
	private Service performFile;
	private Service performIcon;
	private Service performString;
	private Service performF1;

	public EntityImpl() throws Exception
	{
		performUrl = Outside.service(this,"gus06.sys.ruleobj1.build.url");
		performFile = Outside.service(this,"gus06.sys.ruleobj1.build.file");
		performIcon = Outside.service(this,"gus06.sys.ruleobj1.build.icon");
		performString = Outside.service(this,"gus06.sys.ruleobj1.build.string");
		performF1 = Outside.service(this,"gus06.sys.ruleobj1.build.f1");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String rule = (String) obj;
		
		String[] n = rule.split(":",2);
		if(n.length!=2) throw new Exception("Invalid rule: "+rule);
		
		String type = n[0];
		String info = n[1];
		
		T t = findTrans(type);
		return t.t(info);
	}
	
	
	private T findTrans(String type) throws Exception
	{
		if(type.equals("url")) return performUrl;
		if(type.equals("file")) return performFile;
		if(type.equals("icon")) return performIcon;
		if(type.equals("string")) return performString;
		if(type.equals("f1")) return performF1;
		
		//class, entity, color, font, date, integer, float, double, long, short, boolean, new, cx
		//exp
		
		throw new Exception("Unknown rule type: "+type);
	}
}
