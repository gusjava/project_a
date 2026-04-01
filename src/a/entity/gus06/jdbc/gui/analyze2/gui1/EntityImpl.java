package a.entity.gus06.jdbc.gui.analyze2.gui1;

import a.framework.*;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JComponent;
import java.awt.Color;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20230227";}


	private Service tableGui;
	private Service legendGui;

	private JPanel panel;


	public EntityImpl() throws Exception
	{
		tableGui = Outside.service(this,"*gus06.jdbc.gui.analyze2.gui1.table");
		legendGui = Outside.service(this,"*gus06.jdbc.gui.analyze2.gui1.legend");
		
		JTable table = (JTable) tableGui.i();

		JScrollPane scroll = new JScrollPane(table);
		scroll.getViewport().setBackground(Color.WHITE);
		scroll.getViewport().setOpaque(true);
		
		panel = new JPanel(new BorderLayout());
		panel.add(scroll, BorderLayout.CENTER);
		panel.add((JComponent) legendGui.i(), BorderLayout.SOUTH);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void p(Object obj) throws Exception
	{
		tableGui.p(obj);
		legendGui.p(obj);
	}
}