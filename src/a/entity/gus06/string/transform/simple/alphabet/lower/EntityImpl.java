package a.entity.gus06.string.transform.simple.alphabet.lower;

import a.framework.*;

public class EntityImpl implements Entity, G, T {

	public String creationDate() {return "20150926";}
	
	public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
	
	public Object t(Object obj) throws Exception
	{return ALPHABET;}
	
	public Object g() throws Exception
	{return ALPHABET;}
}
