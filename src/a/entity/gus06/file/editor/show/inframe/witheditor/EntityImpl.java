package a.entity.gus06.file.editor.show.inframe.witheditor;

import a.framework.*;
import javax.swing.JFrame;
import java.io.File;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20191031";}


	private Service show;


	public EntityImpl() throws Exception
	{
		show = Outside.service(this,"gus06.swing.frame.show");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		Object editor = o[1];
		
		((P)editor).p(file);
		Object comp = ((I)editor).i();
		JFrame frame = (JFrame) show.t(comp);
		frame.setTitle(file.getName());
		frame.setAlwaysOnTop(true);
	}
}
