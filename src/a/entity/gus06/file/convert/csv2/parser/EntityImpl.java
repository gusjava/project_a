package a.entity.gus06.file.convert.csv2.parser;

import a.framework.*;

public class EntityImpl implements Entity, T
{
	public String creationDate() {return "20150702";}
	
	public static final char CHAR_DELIM = ',';

	private Parser1 parser;
	
	public EntityImpl()
	{parser = new Parser1(CHAR_DELIM);}
	
	public Object t(Object obj) throws Exception
	{return parser.parse((String) obj);}
}