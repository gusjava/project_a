package a.entity.gus06.appli.gusclient1.gui.space.documentation.build.infopane;

import a.framework.*;
import java.awt.Insets;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140818";}


	private Service localizer;
	private Service factory;
	private Service replaceTag;
	
	
	public EntityImpl() throws Exception
	{
		localizer = Outside.service(this,"gus06.ling.localize.manager");
		factory = Outside.service(this,"gus06.swing.textpane.factory1.calibri1");
		replaceTag = Outside.service(this,"gus06.string.transform.replace.tag");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String id = (String) obj;
		String lingKey = "doc1_section_"+id;
		
		JTextPane textPane = (JTextPane) factory.i();
		
		((V) textPane).v("formater",replaceTag);
		localizer.v(lingKey,textPane);
		
		return new JScrollPane(textPane);
	}
}
