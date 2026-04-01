package a.entity.gus06.string.extract.html.tag.type.link.k;

import java.util.regex.Pattern;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190427";}
	
	public static final String NAME = "link";


	private Service build;
	private Service perform;
	private Pattern p;

	public EntityImpl() throws Exception
	{
		build = Outside.service(this,"gus06.string.extract.html.tag.buildpattern");
		perform = Outside.service(this,"gus06.string.extract.match.full");
		p = (Pattern) build.t(NAME);
	}

	public Object t(Object obj) throws Exception
	{return perform.t(new Object[]{obj,p});}
}