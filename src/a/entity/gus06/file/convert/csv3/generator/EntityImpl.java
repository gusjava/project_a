package a.entity.gus06.file.convert.csv3.generator;

import a.framework.*;

public class EntityImpl implements Entity, T
{
	public String creationDate() {return "20160723";}
	
	public static final char CHAR_DELIM = '\t';

	private Generator1 generator;
	
	public EntityImpl()
	{generator = new Generator1(CHAR_DELIM);}
	
	public Object t(Object obj) throws Exception
	{return generator.generate((String[][])obj);}
}