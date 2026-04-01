package a.entity.gus06.file.mp3.gui.metadata.form1;

import a.framework.*;
import java.io.File;
import java.io.FileInputStream;
import javazoom.jl.player.Player;
import java.util.Map;

public class EntityImpl implements Entity, P, I {

	public String creationDate() {return "20250523";}

	private Service extractProp;
	private Service formPanel;
	
	public EntityImpl() throws Exception
	{
		extractProp = Outside.service(this,"gus06.file.mp3.extract.prop");
		formPanel = Outside.service(this,"*gus06.swing.panel.formpanel.map");
	}

	public Object i() throws Exception
	{return formPanel.i();}
	
	
	public void p(Object obj) throws Exception
	{
		File file = (File) obj;
		Map prop = (Map) extractProp.t(file);
		formPanel.p(prop);
	}
}