package a.entity.gus06.string.split.words4.delim;

import a.framework.*;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20201222";}

	public static String DELIM = "\"`'Ф\\ \n\r\t/[](){}<>|!?:,;=-+*%@&$згд^и";
	
	public Object g() throws Exception
	{return DELIM;}
}