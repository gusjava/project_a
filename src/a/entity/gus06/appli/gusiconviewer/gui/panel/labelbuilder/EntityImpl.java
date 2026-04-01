package a.entity.gus06.appli.gusiconviewer.gui.panel.labelbuilder;

import a.framework.*;
import java.io.File;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160628";}

	private Service labelHandler;
	private Service readIcon;

	public EntityImpl() throws Exception
	{
		labelHandler = Outside.service(this,"gus06.appli.gusiconviewer.gui.panel.labelhandler");
		readIcon = Outside.service(this,"gus06.file.read.icon.from.gif");
	}
	
	
	public Object t(Object obj) throws Exception
	{return buildLabel((File) obj);}
	
	
	private JLabel buildLabel(File file) throws Exception
	{
		Icon icon = (Icon) readIcon.t(file);
		JLabel label = new JLabel1(file,icon);
		labelHandler.p(new Object[]{label,file});
		return label;
	}
	
	
	
	private class JLabel1 extends JLabel
	{
		private File file;
		private Icon icon;
		
		public JLabel1(File file, Icon icon)
		{
			super(icon,SwingConstants.CENTER);
			setOpaque(true);
			setToolTipText(file.getName());
			
			this.file = file;
			this.icon = icon;
		}
	}
}
