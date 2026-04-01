package a.entity.gus06.sys.expression1.apply.op._show_smart;

import a.framework.*;
import java.awt.Image;
import java.awt.image.RenderedImage;
import java.io.File;
import java.net.URL;
import javax.swing.Icon;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220613";}


	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.swing.frame.show.smart");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		return new E1(obj);
	}
	
	
	private class E1 implements E
	{
		private Object o;
		public E1(Object o) {this.o = o;}
		
		public void e() throws Exception
		{perform.p(o);}
	}
}