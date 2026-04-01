package a.entity.gus06.sys.parser1.engine1.impl.brackets.curly;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150829";}

	public final static String DELIM = "{}";

	private Service engine1;
	private T parser;
	

	public EntityImpl() throws Exception
	{
		engine1 = Outside.service(this,"gus06.sys.parser1.engine1");
		parser = (T) engine1.t(DELIM);
	}
	
	public Object t(Object obj) throws Exception
	{return parser.t(obj);}
}
