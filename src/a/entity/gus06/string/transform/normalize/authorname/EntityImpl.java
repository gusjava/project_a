package a.entity.gus06.string.transform.normalize.authorname;

import a.framework.*;
import java.util.Arrays;
import java.util.Collections;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220904";}


	private Service norm1;
	private Service sortWords;

	public EntityImpl() throws Exception
	{
		norm1 = Outside.service(this,"gus06.string.transform.normalize.diacritics.lower");
		sortWords = Outside.service(this,"gus06.string.transform.words.order.sort");
	}


	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		String s = (String) norm1.t(obj);
		return sortWords.t(s);
	}
}
