package a.entity.gus06.sys.filetool.perform.file.show.settingsgui;

import a.framework.*;
import java.io.File;
import java.util.Map;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20230216";}


	private Service loadProp;
	private Service mapToComp;
	private Service showComp;

	public EntityImpl() throws Exception
	{
		loadProp = Outside.service(this,"gus06.file.read.properties.autosaver.strict");
		mapToComp = Outside.service(this,"gus06.sys.filetool.main.settingsgui.maptocomp");
		showComp = Outside.service(this,"gus06.swing.frame.show");
	}
	
	public void p(Object obj) throws Exception
	{
		File file = (File) obj;
		Map prop = (Map) loadProp.t(file);
		JComponent comp = (JComponent) mapToComp.t(prop);
		
		if(comp==null) 
		{
			String message = "No setting has been designed for this tool file";
			String title = "Unavailable";
			JOptionPane.showMessageDialog(null, message, title, JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		String title = "UTIL_settings#Settings for "+file.getName();
		showComp.v(title, comp);
	}
}