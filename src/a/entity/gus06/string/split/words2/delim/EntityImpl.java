package a.entity.gus06.string.split.words2.delim;

import a.framework.*;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20160505";}

	public static String DELIM = "\"`'�\\ \n\r\t/[](){}<>|!?:.,;=-+*%@#&$���^�";
	
	public Object g() throws Exception
	{return DELIM;}
}