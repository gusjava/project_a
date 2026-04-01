package a.entity.gus06.sys.editor16x16.c.discretize2;

import a.framework.*;
import java.awt.Color;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250416";}


	private Service decode;
	private Service encode;

	public EntityImpl() throws Exception
	{
		decode = Outside.service(this,"gus06.sys.editor16x16.c.decode2");
		encode = Outside.service(this,"gus06.sys.editor16x16.c.encode2");
	}
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		int[] v = (int[]) decode.t(obj);
		
		int r = v[0];
		int g = v[1];
		int b = v[2];
		int a = v[3];
		
		r = r<=85?0:r<=170?127:255;
		g = g<=85?0:g<=170?127:255;
		b = b<=85?0:b<=170?127:255;
		
		return encode.t(new int[]{r,g,b,a});
	}
}