package a.entity.gus06.sys.labelholder1.file.or.text;

import java.io.File;
import a.framework.*;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.BorderFactory;

public class EntityImpl extends S1 implements Entity, I, P, G {

	public String creationDate() {return "20210717";}

	private Service dnd;
	private Service getIcon;
	private Service initCopyPaste;
	private Service focusOnClick;
	
	private JLabel label;
	private Icon iconText;
	private Object content;

	public EntityImpl() throws Exception
	{
		dnd = Outside.service(this,"gus06.awt.dnd");
		getIcon = Outside.service(this,"gus06.file.icon.t1");
		initCopyPaste = Outside.service(this,"gus06.swing.comp.cust3.copypaste.file.or.text");
		focusOnClick = Outside.service(this,"gus06.swing.comp.cust.focusonclicked");
		iconText = (Icon) Outside.resource(this,"icon#UTIL_text");
		
		label = new JLabel(" ");
		label.setBorder(BorderFactory.createRaisedBevelBorder());
		
		focusOnClick.p(label);
		
		P p = (P) this::receive;
		initCopyPaste.p(new Object[]{label,p,this});
		dnd.p(new Object[]{label,p,this});
	}
	
	
	public Object g() throws Exception
	{return content;}
	
	
	public Object i() throws Exception
	{return label;}
	
	
	
	public void p(Object obj) throws Exception
	{
		content = obj;
		refresh();
		
	}
	
	private void receive(Object obj) throws Exception
	{
		content = obj;
		refresh();
		received();
	}
	
	
	
	private void refresh() throws Exception
	{
		if(content==null)
		{
			label.setIcon(null);
			label.setToolTipText(null);
			label.setText(" ");
		}
		else if(content instanceof File)
		{
			File file = (File) content;
			
			label.setIcon(icon(file));
			label.setText(file.getName());
			label.setToolTipText(file.getAbsolutePath());
		}
		else if(content instanceof String)
		{
			String s = (String) content;
			
			label.setIcon(iconText);
			label.setText("text");
			label.setToolTipText(null);
		}
	}

	
	
	private Icon icon(File file) throws Exception
	{return (Icon) getIcon.t(file);}
	
	
	
	private void received()
	{send(this,"received()");}
}