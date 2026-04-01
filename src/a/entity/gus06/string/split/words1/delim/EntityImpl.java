package a.entity.gus06.string.split.words1.delim;

import a.framework.*;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20160503";}

	public static String DELIM = "\"`'�\\ \n\r\t/[](){}<>|!?:.,;=_-+*%@#&$���^�";
	
	public Object g() throws Exception
	{return DELIM;}
}