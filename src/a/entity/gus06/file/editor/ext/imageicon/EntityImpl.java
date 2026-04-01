package a.entity.gus06.file.editor.ext.imageicon;

import a.framework.*;
import javax.swing.JLabel;
import java.io.File;
import javax.swing.Icon;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20140723";}

	private Service loadIcon;
	private JLabel label;

	public EntityImpl() throws Exception
	{
		loadIcon = Outside.service(this,"gus06.file.read.image.imageio.icon");
		label = new JLabel(" ");
	}
	
	
	public Object i() throws Exception
	{return label;}
	
	
	public void p(Object obj) throws Exception
	{
		File file = (File) obj;
		Icon icon = (Icon) loadIcon.t(file);
		label.setIcon(icon);
	}
}