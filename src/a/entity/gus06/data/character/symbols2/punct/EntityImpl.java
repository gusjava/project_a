package a.entity.gus06.data.character.symbols2.punct;

import a.framework.*;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20160817";}

	public static String DELIM = "!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~";
	
	public Object g() throws Exception
	{return DELIM;}
}