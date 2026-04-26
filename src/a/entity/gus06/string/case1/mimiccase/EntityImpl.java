package a.entity.gus06.string.case1.mimiccase;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160820";}
	
	
	
	private Service toLower;
	private Service toUpper;
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
		toLower = Outside.service(this,"gus06.string.transform.str.lower");
		toUpper = Outside.service(this,"gus.x.transform.string.case1.uppercase");
		toCamelCase = Outside.service(this,"gus06.string.case1.to.camelcase");
		toPascalCase = Outside.service(this,"gus06.string.case1.to.pascalcase");
		toUpperSnakeCase = Outside.service(this,"gus06.string.case1.to.upper.snakecase");
		toLowerSnakeCase = Outside.service(this,"gus06.string.case1.to.lower.snakecase");
		toUpperKebabCase = Outside.service(this,"gus06.string.case1.to.upper.kebabcase");
		toLowerKebabCase = Outside.service(this,"gus06.string.case1.to.lower.kebabcase");
		toUpperPointCase = Outside.service(this,"gus06.string.case1.to.upper.pointcase");
		toLowerPointCase = Outside.service(this,"gus06.string.case1.to.lower.pointcase");
		toUpperSpaceCase = Outside.service(this,"gus06.string.case1.to.upper.spacecase");
		toLowerSpaceCase = Outside.service(this,"gus06.string.case1.to.lower.spacecase");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String[] o = (String[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String s1 = o[0];
		String s2 = o[1];
		
		return mimicCase(s1,s2);
	}
	
	private String mimicCase(String s, String mimicked) throws Exception
	{
		if(isLowerAlphaNum(mimicked)) return (String) toLower.t(s);
		if(isUpperAlphaNum(mimicked)) return (String) toUpper.t(s);
		if(isCamelCase(mimicked)) return (String) toCamelCase.t(s);
		if(isPascalCase(mimicked)) return (String) toPascalCase.t(s);
		if(isLowerSnakeCase(mimicked)) return (String) toLowerSnakeCase.t(s);
		if(isUpperSnakeCase(mimicked)) return (String) toUpperSnakeCase.t(s);
		if(isUpperSnakeCase(mimicked)) return (String) toUpperSnakeCase.t(s);
		if(isLowerKebabCase(mimicked)) return (String) toLowerKebabCase.t(s);
		if(isUpperKebabCase(mimicked)) return (String) toUpperKebabCase.t(s);
		if(isUpperKebabCase(mimicked)) return (String) toUpperKebabCase.t(s);
		if(isLowerPointCase(mimicked)) return (String) toLowerPointCase.t(s);
		if(isUpperPointCase(mimicked)) return (String) toUpperPointCase.t(s);
		if(isUpperPointCase(mimicked)) return (String) toUpperPointCase.t(s);
		if(isLowerSpaceCase(mimicked)) return (String) toLowerSpaceCase.t(s);
		if(isUpperSpaceCase(mimicked)) return (String) toUpperSpaceCase.t(s);
		if(isUpperSpaceCase(mimicked)) return (String) toUpperSpaceCase.t(s);
		return s;
	}
	
	private boolean isLowerAlphaNum(String s)
	{
		return s!=null && s.matches("[a-z0-9]+");
	}
	
	private boolean isUpperAlphaNum(String s)
	{
		return s!=null && s.matches("[A-Z0-9]+");
	}
	
	private boolean isCamelCase(String s)
	{
		return s!=null && s.matches("[a-z][a-zA-Z0-9]*");
	}
	
	private boolean isPascalCase(String s)
	{
		return s!=null && s.matches("[A-Z][a-zA-Z0-9]*");
	}
	
	private boolean isLowerSnakeCase(String s)
	{
		return s!=null && s.matches("[a-z][a-z0-9]*(_[a-z0-9]*)*");
	}
	
	private boolean isUpperSnakeCase(String s)
	{
		return s!=null && s.matches("[A-Z][A-Z0-9]*(_[A-Z0-9]*)*");
	}
	
	private boolean isLowerKebabCase(String s)
	{
		return s!=null && s.matches("[a-z][a-z0-9]*(-[a-z0-9]*)*");
	}
	
	private boolean isUpperKebabCase(String s)
	{
		return s!=null && s.matches("[A-Z][A-Z0-9]*(-[A-Z0-9]*)*");
	}
	
	private boolean isLowerPointCase(String s)
	{
		return s!=null && s.matches("[a-z][a-z0-9]*(\\.[a-z0-9]*)*");
	}
	
	private boolean isUpperPointCase(String s)
	{
		return s!=null && s.matches("[A-Z][A-Z0-9]*(\\.[A-Z0-9]*)*");
	}
	
	private boolean isLowerSpaceCase(String s)
	{
		return s!=null && s.matches("[a-z][a-z0-9]*( [a-z0-9]*)*");
	}
	
	private boolean isUpperSpaceCase(String s)
	{
		return s!=null && s.matches("[A-Z][A-Z0-9]*( [A-Z0-9]*)*");
	}
}