package a.entity.gus06.app.jarfile.entity.viewer.panel.doc;

import a.framework.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

public class EntityImpl implements Entity, I, P, ActionListener {

	public String creationDate() {return "20140829";}


	private Service factory;
	private Service langManager;
	private Service perform;
	private Service isGusPseudo;
	private Service editable;
	
	private String name;
	private JScrollPane scroll;
	private JTextPane textPane;
	
	
	public EntityImpl() throws Exception
	{
		factory = Outside.service(this,"gus06.swing.textpane.factory1.calibri1");
		langManager = Outside.service(this,"gus06.ling.language.manager");
		perform = Outside.service(this,"gus06.ling.localize.perform");
		isGusPseudo = Outside.service(this,"gus06.entitydev.pseudo.check.gus");
		editable = Outside.service(this,"*gus06.app.jarfile.entity.viewer.panel.doc.editable");
		
		textPane = (JTextPane) factory.i();
		scroll = new JScrollPane(textPane);
		
		langManager.addActionListener(this);
		
		if(isGusPseudo.f(null)) editable.p(textPane);
	}
	
	
	
	public Object i() throws Exception
	{return scroll;}
	
	
	public void p(Object obj) throws Exception
	{
		name = (String) obj;
		updateGui();
	}
	
	
	public void actionPerformed(ActionEvent e)
	{updateGui();}
	
	
	
	
	private void updateGui()
	{
		try
		{
			editable.v("name",name);
			if(name==null) {textPane.setText("");return;}
			perform.v(lingKey(),textPane);
		}
		catch(Exception e)
		{Outside.err(this,"updateGui()",e);}
	}
	
	
	
	private String lingKey()
	{return "doc1_entity_"+name;}
}
