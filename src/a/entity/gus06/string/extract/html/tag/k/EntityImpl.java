package a.entity.gus06.string.extract.html.tag.k;

import java.util.regex.Pattern;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190427";}

	private Service perform;
	private Pattern p;

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.string.extract.match.full");
		p = Pattern.compile("(?s)<[^>]+>");
	}



	public Object t(Object obj) throws Exception
	{return perform.t(new Object[]{obj,p});}
}
