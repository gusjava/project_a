package a.entity.gus06.swing.filechooser.holder;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JFileChooser;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20140817";}

	private Service custFileView;
	private Service persister;
	
	private JFileChooser fc;
	
	public EntityImpl() throws Exception
	{
		custFileView = Outside.service(this,"gus06.swing.filechooser.cust.fileview.os");
		persister = Outside.service(this,"gus06.swing.filechooser.persister.directory");
		
		fc = new JFileChooser();
		custFileView.p(fc);
		persister.v(getClass().getName()+"_fc",fc);
	}
	
	
	public Object i() throws Exception
	{return fc;}
}
