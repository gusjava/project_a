package a.entity.gus06.file.convert.json.parser;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141010";}

	public static final boolean DEBUG = false;

	private Parser2 parser;
	

	public EntityImpl() throws Exception
	{parser = new Parser2(DEBUG);}


	public Object t(Object obj) throws Exception
	{
		String in = (String) obj;
		return parser.parse(clean(in));
	}
	
	private String clean(String in)
	{return in.trim().replace("\n","").replace("\t","").replace("\r","").replace("\b","").replace("\f","");}
}
