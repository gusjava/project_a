package a.entity.gus06.data.string.replace1.smart;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180226";}



	private Service titled;
	private Service titledInv;
	private Service upper;
	private Service lower;
	private Service upperSnakecase;
	private Service lowerSnakecase;
	private Service upperKebabcase;
	private Service lowerKebabcase;
	private Service upperPointcase;
	private Service lowerPointcase;

	public EntityImpl() throws Exception
	{
		titled = Outside.service(this,"gus06.string.transform.str.titled");
		titledInv = Outside.service(this,"gus06.string.transform.str.titled.inv");
		upper = Outside.service(this,"gus.x.transform.string.case1.uppercase");
		lower = Outside.service(this,"gus06.string.transform.str.lower");
		upperSnakecase = Outside.service(this,"gus06.string.case1.to.upper.snakecase");
		lowerSnakecase = Outside.service(this,"gus06.string.case1.to.lower.snakecase");
		upperKebabcase = Outside.service(this,"gus06.string.case1.to.upper.kebabcase");
		lowerKebabcase = Outside.service(this,"gus06.string.case1.to.lower.kebabcase");
		upperPointcase = Outside.service(this,"gus06.string.case1.to.upper.pointcase");
		lowerPointcase = Outside.service(this,"gus06.string.case1.to.lower.pointcase");
	}
	
	public Object t(Object obj) throws Exception
	{
		String[] o = (String[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		String line = o[0];
		String s1 = o[1];
		String s2 = o[2];
		
		line = line.replace(s1,s2);
		
		line = replace(line,s1,s2,titled);
		line = replace(line,s1,s2,titledInv);
		
		line = replace(line,s1,s2,upper);
		line = replace(line,s1,s2,lower);
		
		line = replace(line,s1,s2,upperSnakecase);
		line = replace(line,s1,s2,lowerSnakecase);
		
		line = replace(line,s1,s2,upperKebabcase);
		line = replace(line,s1,s2,lowerKebabcase);
		
		line = replace(line,s1,s2,upperPointcase);
		line = replace(line,s1,s2,lowerPointcase);
		
		return line;
	}
	
	
	
	private String replace(String line, String s1, String s2, T t) throws Exception
	{
		String s1_ = (String) t.t(s1);
		String s2_ = (String) t.t(s2);
		return line.replace(s1_,s2_);
	}
}