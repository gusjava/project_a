package a.entity.gus.y.stringcase1.transmap;

import a.framework.*;
import java.util.HashMap;
import java.util.Map;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20240714";}
	
	private Service split;
	private Service toUpper;
	private Service toLower;
	private Service toCamelCase;
	private Service toPascalCase;
	private Service toUpperSnakeCase;
	private Service toLowerSnakeCase;
	private Service toUpperKebabCase;
	private Service toLowerKebabCase;
	private Service toUpperPointCase;
	private Service toLowerPointCase;
	private Service toUpperSpaceCase;
	private Service toLowerSpaceCase;

	public EntityImpl() throws Exception
	{
		split = Outside.service(this,"gus.y.stringcase1.splitcase");
		toUpper = Outside.service(this,"gus.x.transform.string.case1.uppercase");
		toLower = Outside.service(this,"gus.x.transform.string.case1.lowercase");
		toCamelCase = Outside.service(this,"gus.y.stringcase1.to.camelcase");
		toPascalCase = Outside.service(this,"gus.y.stringcase1.to.pascalcase");
		toUpperSnakeCase = Outside.service(this,"gus.y.stringcase1.to.upper.snakecase");
		toLowerSnakeCase = Outside.service(this,"gus.y.stringcase1.to.lower.snakecase");
		toUpperKebabCase = Outside.service(this,"gus.y.stringcase1.to.upper.kebabcase");
		toLowerKebabCase = Outside.service(this,"gus.y.stringcase1.to.lower.kebabcase");
		toUpperPointCase = Outside.service(this,"gus.y.stringcase1.to.upper.pointcase");
		toLowerPointCase = Outside.service(this,"gus.y.stringcase1.to.lower.pointcase");
		toUpperSpaceCase = Outside.service(this,"gus.y.stringcase1.to.upper.spacecase");
		toLowerSpaceCase = Outside.service(this,"gus.y.stringcase1.to.lower.spacecase");
	}
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String s1 = (String) o[0];
		String s2 = (String) o[1];
		
		String[] ss1 = (String[]) split.t(s1);
		String[] ss2 = (String[]) split.t(s2);
		
		Map m = new HashMap();
		
		m.put(s1,s2);
		
		m.put(toUpper.t(s1),	toUpper.t(s2));
		m.put(toLower.t(s1),	toLower.t(s2));
		
		m.put(toCamelCase.t(ss1),	toCamelCase.t(ss2));
		m.put(toPascalCase.t(ss1),	toPascalCase.t(ss2));
		
		m.put(toUpperSnakeCase.t(ss1),	toUpperSnakeCase.t(ss2));
		m.put(toUpperKebabCase.t(ss1),	toUpperKebabCase.t(ss2));
		m.put(toUpperPointCase.t(ss1),	toUpperPointCase.t(ss2));
		m.put(toUpperSpaceCase.t(ss1),	toUpperSpaceCase.t(ss2));
		
		m.put(toLowerSnakeCase.t(ss1),	toLowerSnakeCase.t(ss2));
		m.put(toLowerKebabCase.t(ss1),	toLowerKebabCase.t(ss2));
		m.put(toLowerPointCase.t(ss1),	toLowerPointCase.t(ss2));
		m.put(toLowerSpaceCase.t(ss1),	toLowerSpaceCase.t(ss2));
		
		return m;
	}
}