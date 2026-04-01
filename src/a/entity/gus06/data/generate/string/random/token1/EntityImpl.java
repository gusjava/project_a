package a.entity.gus06.data.generate.string.random.token1;

import a.framework.*;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Iterator;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20160306";}
	
	public static final int LENGTH = 20;
	
	
	public Object g() throws Exception
	{return generate(LENGTH);}
	
	
	
	private String generate(int length)
	{
		StringBuffer b = new StringBuffer();
		for(int i=0;i<length;i++) b.append(randomChar());
		return b.toString();
	}
	
	
	private char randomChar()
	{
		int r = random(63); //26+26+10+1
		if(r<10) return (char)(48+r);
		if(r<36) return (char)(65+r-10);
		if(r<62) return (char)(97+r-36);
		return '_';
	}
	
	
	private int random(int limit)
	{return (int) (Math.random()*limit);}
}
