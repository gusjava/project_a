package a.entity.gus06.sys.filemanagement1.gui.md5set.panel.show;

import a.framework.*;
import java.util.Set;
import java.awt.Dimension;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20201108";}

	public static final Dimension DIM = new Dimension(1400,600);

	private Service factory;
	private Service perform;


	public EntityImpl() throws Exception
	{
		factory = Outside.service(this,"factory#gus.sys.filemanagement1.gui.md5set.panel");
		perform = Outside.service(this,"gus06.swing.frame.show2");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Object engine = o[0];
		Set md5Set = (Set) o[1];
		String title = (String) o[2];
		
		Object viewer = factory.g();
		((P) viewer).p(new Object[]{engine,md5Set});
		
		perform.p(new Object[]{viewer,DIM,title});
	}
}