package a.entity.gus06.appli.gusclient1.menu.errors;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class EntityImpl implements Entity, I, ActionListener {

	public String creationDate() {return "20140726";}

	
	private Service printErr;
	private Service buildItem;
	private List errors;

	private JMenu menu;

	public EntityImpl() throws Exception
	{
		printErr = Outside.service(this,"gus06.debug.printerr");
		buildItem = Outside.service(this,"gus06.swing.menuitem.builder1");
		errors = (List) Outside.resource(this,"errors");

		JMenuItem item1 = (JMenuItem) buildItem.t(printErr);
		item1.setText("Print errors");

		menu = new JMenu("0");
		menu.add(item1);

		((S) errors).addActionListener(this);
		menu.setVisible(false);
	}
	
	
	public Object i() throws Exception
	{return menu;}




	public void actionPerformed(ActionEvent evt)
	{updateMenu();}


	private void updateMenu()
	{
		menu.setVisible(true);
		menu.setText("Error ("+errors.size()+")");
	}
}
