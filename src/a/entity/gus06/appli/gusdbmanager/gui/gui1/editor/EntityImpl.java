package a.entity.gus06.appli.gusdbmanager.gui.gui1.editor;

import a.framework.*;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.util.Map;

public class EntityImpl implements Entity, P, I {

	public String creationDate() {return "20150613";}


	private Service connectors;
	private Service findDisplay;
	private Service repaintLabel;
	
	private Service form;
	private Service area;
	private Service button;
	
	
	private JLabel titleLabel;
	
	private JPanel panel;

	private String id;
	private Map map;
	private String display;
	
	


	public EntityImpl() throws Exception
	{
		connectors = Outside.service(this,"gus06.appli.gusdbmanager.data.connectors");
		findDisplay = Outside.service(this,"gus06.appli.gusdbmanager.data.connectors.builddisplay");
		repaintLabel = Outside.service(this,"gus06.swing.label.cust2.display");
		
		form = Outside.service(this,"*gus06.appli.gusdbmanager.gui.gui1.editor.form");
		area = Outside.service(this,"*gus06.appli.gusdbmanager.gui.gui1.editor.area");
		button = Outside.service(this,"*gus06.appli.gusdbmanager.gui.gui1.editor.button");
		
		titleLabel = new JLabel(" ");
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		titleLabel.setBorder(BorderFactory.createRaisedBevelBorder());
		
		
		JPanel panel1 = new JPanel(new BorderLayout());
		panel1.add((JComponent) form.i(),BorderLayout.NORTH);
		panel1.add((JComponent) area.i(),BorderLayout.CENTER);
		panel1.add((JComponent) button.i(),BorderLayout.SOUTH);
		
		panel = new JPanel(new BorderLayout());
		panel.add(titleLabel,BorderLayout.NORTH);
		panel.add(panel1,BorderLayout.CENTER);
	}



	public Object i() throws Exception
	{return panel;}



	
	public void p(Object obj) throws Exception
	{
		id = (String) obj;
		if(id==null) {resetEditor();return;}
		
		map = (Map) connectors.r((String) obj);
		display = (String) findDisplay.t(map);
		repaintLabel.v(display,titleLabel);
		
		area.p(id);
		button.p(id);
		form.p(map);
	}
	
	
	
	
	private void resetEditor() throws Exception
	{
		map = null;
		display = null;
		repaintLabel.v(" ",titleLabel);
		
		area.p(null);
		button.p(null);
		form.p(null);
	}
}
