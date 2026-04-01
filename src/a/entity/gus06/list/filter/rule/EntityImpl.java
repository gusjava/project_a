package a.entity.gus06.list.filter.rule;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231116";}
	
	
	private Service ruleAll;
	private Service ruleOne;
	
	private Service ruleEn;
	private Service ruleEnI;
	private Service ruleEnN;
	
	private Service ruleEq;
	private Service ruleEqI;
	private Service ruleEqN;
	
	private Service ruleSt;
	private Service ruleStI;
	private Service ruleStN;
	
	public EntityImpl() throws Exception
	{
		ruleAll = Outside.service(this,"gus06.list.filter.rule.all");
		ruleOne = Outside.service(this,"gus06.list.filter.rule.one");
		
		ruleEn = Outside.service(this,"gus06.list.filter.rule.build.endswith");
		ruleEnI = Outside.service(this,"gus06.list.filter.rule.build.endswith_i");
		ruleEnN = Outside.service(this,"gus06.list.filter.rule.build.endswith_n");
		
		ruleEq = Outside.service(this,"gus06.list.filter.rule.build.equals");
		ruleEqI = Outside.service(this,"gus06.list.filter.rule.build.equals_i");
		ruleEqN = Outside.service(this,"gus06.list.filter.rule.build.equals_n");
		
		ruleSt = Outside.service(this,"gus06.list.filter.rule.build.startswith");
		ruleStI = Outside.service(this,"gus06.list.filter.rule.build.startswith_i");
		ruleStN = Outside.service(this,"gus06.list.filter.rule.build.startswith_n");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		List list = (List) o[0];
		String query = (String) o[1];
		String mode = (String) o[2];
		
		T builder = findBuilder(mode);
		return builder.t(new Object[]{list, query});
	}
	
	
	private T findBuilder(String mode) throws Exception
	{
		if(mode.equals("all")) return ruleAll;
		if(mode.equals("one")) return ruleOne;
		
		if(mode.equals("en")) return ruleEn;
		if(mode.equals("en_i")) return ruleEnI;
		if(mode.equals("en_n")) return ruleEnN;
		
		if(mode.equals("eq")) return ruleEq;
		if(mode.equals("eq_i")) return ruleEqI;
		if(mode.equals("eq_n")) return ruleEqN;
		
		if(mode.equals("st")) return ruleSt;
		if(mode.equals("st_i")) return ruleStI;
		if(mode.equals("st_n")) return ruleStN;
		
		throw new Exception("Unknown filter mode: "+mode);
	}
}