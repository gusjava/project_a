package a.entity.gus06.string.extract.html.block.buildpattern;

import a.framework.*;
import java.util.regex.Pattern;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170117";}


	public Object t(Object obj) throws Exception
	{
		String name = (String) obj;
		return Pattern.compile("(?si)<"+name+"( [^>]*)?>(.*?)</"+name+">");
	}
}