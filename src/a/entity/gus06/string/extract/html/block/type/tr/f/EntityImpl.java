package a.entity.gus06.string.extract.html.block.type.tr.f;

import java.util.regex.Pattern;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170205";}
	
	public static final String NAME = "tr";


	private Service build;
	private Service perform;
	private Pattern p;

	public EntityImpl() throws Exception
	{
		build = Outside.service(this,"gus06.string.extract.html.block.buildpattern");
		perform = Outside.service(this,"gus06.string.extract.match.first");
		p = (Pattern) build.t(NAME);
	}

	public Object t(Object obj) throws Exception
	{return perform.t(new Object[]{obj,p});}
}
