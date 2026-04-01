package a.entity.gus06.file.editor.ext.jar.viewer.manifest;

import a.framework.*;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Insets;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20170223";}


	private Service getManifest;

	private JTextArea area;
	private JScrollPane scroll;

	public EntityImpl() throws Exception
	{
		getManifest = Outside.service(this,"gus06.file.jar.manifest.tostring");
		
		area = new JTextArea();
		area.setEditable(false);
		area.setMargin(new Insets(3,3,3,3));
		
		scroll = new JScrollPane(area);
	}
	
	
	public Object i() throws Exception
	{return scroll;}
	
	
	public void p(Object obj) throws Exception
	{
		String s = (String) getManifest.t(obj);
		area.setText(s!=null ? s : "");
	}
}