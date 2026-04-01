package a.entity.gus06.file.editor.ext.zip.entryviewer.label;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.Icon;


public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20140807";}


	public static final String DELIM = "/";


	private Service findIcon;

	private JLabel label;
	private String entry;


	public EntityImpl() throws Exception
	{
		findIcon = Outside.service(this,"gus06.file.name.icon.os");
	
		label = new JLabel(" ");
		label.setBorder(BorderFactory.createRaisedBevelBorder());
	}
	
	
	public Object i() throws Exception
	{return label;}
	
	
	public void p(Object obj) throws Exception
	{
		entry = (String) obj;
		if(entry==null) resetGui();
		else updateGui();
	}
	
	
	private void resetGui()
	{
		label.setIcon(null);
		label.setToolTipText(null);
		label.setText(" ");
	}
	
	
	private void updateGui() throws Exception
	{
		label.setIcon(getIcon(entry));
		label.setToolTipText(entry);
		label.setText(getName(entry));
	}
	
	
	
	private Icon getIcon(String entry) throws Exception
	{return (Icon) findIcon.t(entry);}
	
	private String getName(String entry)
	{
		String[] n = entry.split(DELIM);
		return n[n.length-1];
	}
}
