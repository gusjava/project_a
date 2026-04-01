package a.entity.gus06.swing.label.cust3.filedisplay2;

import a.framework.*;
import javax.swing.Icon;
import javax.swing.JLabel;
import java.io.File;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingUtilities;


public class EntityImpl implements Entity, P {

	public String creationDate() {return "20141209";}


	private Service fileIcon;
	private Service fileDisplay;
	private Service fileColor;

	public EntityImpl() throws Exception
	{
		fileIcon = Outside.service(this,"gus06.file.icon.t1");
		fileDisplay = Outside.service(this,"gus06.dirfile.display");
		fileColor = Outside.service(this,"gus06.file.findcolor1");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Invalid data number: "+o.length);
		
		final JLabel label = (JLabel) o[0];
		final File file = (File) o[1];
		
		SwingUtilities.invokeLater(new Runnable(){
			public void run() {update(label,file);}
		});
	}
	
	
	
	private void update(JLabel label, File file)
	{
		try
		{
			if(file!=null)
			{
				label.setText(display(file));
				label.setIcon(icon(file));
	        		label.setForeground(color(file));
	        		label.setFont(findFont(file,label.getFont()));
			}
			else
			{
				label.setText(" ");
				label.setIcon(null);
			}
		}
		catch(Exception e)
		{Outside.err(this,"update(JLabel,File)",e);}
	}
	
	
	
	private Icon icon(File file) throws Exception
	{return (Icon) fileIcon.t(file);}
	
	private String display(File file) throws Exception
	{return (String) fileDisplay.t(file);}
	
	private Color color(File file) throws Exception
	{return (Color) fileColor.t(file);}
	
	
	
	private Font findFont(File file, Font f)
	{
		if(!file.exists() || file.canWrite())
			return f.deriveFont(Font.PLAIN);
		return f.deriveFont(Font.ITALIC);
	}
}
