package a.entity.gus06.swing.label.hold.file.v;

import java.io.File;
import a.framework.*;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.BorderFactory;

public class EntityImpl extends S1 implements Entity, I, P, G {

	public String creationDate() {return "20200331";}

	private Service dnd;
	private Service getIcon;
	private Service initCopyPaste;
	private Service focusOnClick;
	
	private JLabel label;
	private File file;

	public EntityImpl() throws Exception
	{
		dnd = Outside.service(this,"gus06.awt.dnd");
		getIcon = Outside.service(this,"gus06.file.icon.t1");
		initCopyPaste = Outside.service(this,"gus06.swing.comp.cust3.copypaste.file");
		focusOnClick = Outside.service(this,"gus06.swing.comp.cust.focusonclicked");
		
		label = new JLabel(" ");
		label.setBorder(BorderFactory.createRaisedBevelBorder());
		
		focusOnClick.p(label);
		
		P p = (P) this::receive;
		initCopyPaste.p(new Object[]{label,p,this});
		dnd.p(new Object[]{label,p,this});
	}
	
	
	public Object g() throws Exception
	{return file;}
	
	
	public Object i() throws Exception
	{return label;}
	
	
	
	public void p(Object obj) throws Exception
	{
		file = (File) obj;
		refresh();
		
	}
	
	private void receive(Object obj) throws Exception
	{
		file = (File) obj;
		refresh();
		received();
	}
	
	
	
	private void refresh() throws Exception
	{
		if(file==null)
		{
			label.setIcon(null);
			label.setToolTipText(null);
			label.setText(" ");
		}
		else
		{
			label.setIcon(icon(file));
			label.setText(file.getName());
			label.setToolTipText(file.getAbsolutePath());
		}
	}

	
	
	private Icon icon(File file) throws Exception
	{return (Icon) getIcon.t(file);}
	
	
	
	private void received()
	{send(this,"received()");}
}