package a.entity.gus06.data.transform.string.sequence.parser.comma;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150902";}

	public static final String DELIM = ",";


	private Service parserBuilder;
	private T parser;
	
	public EntityImpl() throws Exception
	{
		parserBuilder = Outside.service(this,"gus06.data.transform.string.sequence.parser");
		parser = (T) parserBuilder.t(DELIM);
	}

	public Object t(Object obj) throws Exception
	{return parser.t(obj);}
}