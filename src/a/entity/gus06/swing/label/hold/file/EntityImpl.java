package a.entity.gus06.swing.label.hold.file;

import java.io.File;
import a.framework.*;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.BorderFactory;

public class EntityImpl implements Entity, I, P, G {

	public String creationDate() {return "20140723";}

	private Service dnd;
	private Service getIcon;
	private Service initCopy;
	private Service focusOnClick;
	
	private JLabel label;
	private File file;

	public EntityImpl() throws Exception
	{
		dnd = Outside.service(this,"gus06.awt.dnd");
		getIcon = Outside.service(this,"gus06.file.icon.t1");
		initCopy = Outside.service(this,"gus06.swing.comp.cust3.filecopy");
		focusOnClick = Outside.service(this,"gus06.swing.comp.cust.focusonclicked");
		
		label = new JLabel(" ");
		label.setBorder(BorderFactory.createRaisedBevelBorder());
		
		focusOnClick.p(label);
		initCopy.p(new Object[]{label,this});
		dnd.p(new Object[]{label,null,this});
	}
	
	
	public Object g() throws Exception
	{return file;}
	
	
	public Object i() throws Exception
	{return label;}
	
	
	
	public void p(Object obj) throws Exception
	{
		file = (File) obj;
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
}
