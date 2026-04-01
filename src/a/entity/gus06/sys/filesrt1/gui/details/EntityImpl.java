package a.entity.gus06.sys.filesrt1.gui.details;

import a.framework.*;
import javax.swing.JPanel;

public class EntityImpl extends S1 implements Entity, I, P {

	public String creationDate() {return "20230106";}


	private JPanel panel;
	
	
	private Object[] data;
	

	public EntityImpl() throws Exception
	{
		panel = new JPanel();
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void p(Object obj) throws Exception
	{
		data = (Object[]) obj;
	}
}