package a.entity.gus.y.stringcase1.variants;

import a.framework.*;
import java.util.HashMap;
import java.util.Map;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20240714";}
	
	public static final String KEY_UPPER = "AAA";
	public static final String KEY_LOWER = "aaa";
	
	public static final String KEY_CAMELCASE = "aAaA";
	public static final String KEY_PASCALCASE = "AaAa";
	
	public static final String KEY_UPPER_SNAKECASE = "A_A";
	public static final String KEY_UPPER_KEBABCASE = "A-A";
	public static final String KEY_UPPER_POINTCASE = "A.A";
	public static final String KEY_UPPER_SPACECASE = "A A";
	
	public static final String KEY_LOWER_SNAKECASE = "a_a";
	public static final String KEY_LOWER_KEBABCASE = "a-a";
	public static final String KEY_LOWER_POINTCASE = "a.a";
	public static final String KEY_LOWER_SPACECASE = "a a";
	
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
		String s = (String) obj;
		String[] ss = (String[]) split.t(s);
		
		Map m = new HashMap();
		
		m.put(KEY_UPPER,	toUpper.t(s));
		m.put(KEY_LOWER,	toLower.t(s));
		
		m.put(KEY_CAMELCASE,	toCamelCase.t(ss));
		m.put(KEY_PASCALCASE,	toPascalCase.t(ss));
		
		m.put(KEY_UPPER_SNAKECASE,	toUpperSnakeCase.t(ss));
		m.put(KEY_UPPER_KEBABCASE,	toUpperKebabCase.t(ss));
		m.put(KEY_UPPER_POINTCASE,	toUpperPointCase.t(ss));
		m.put(KEY_UPPER_SPACECASE,	toUpperSpaceCase.t(ss));
		
		m.put(KEY_LOWER_SNAKECASE,	toLowerSnakeCase.t(ss));
		m.put(KEY_LOWER_KEBABCASE,	toLowerKebabCase.t(ss));
		m.put(KEY_LOWER_POINTCASE,	toLowerPointCase.t(ss));
		m.put(KEY_LOWER_SPACECASE,	toLowerSpaceCase.t(ss));
		
		return m;
	}
}