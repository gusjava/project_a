package a.entity.gus06.swing.table.buildpanel1;

import a.framework.*;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150511";}


	private Service labelCellPosition;
	private Service tableTooltip;


	public EntityImpl() throws Exception
	{
		labelCellPosition = Outside.service(this,"gus06.swing.table.buildlabel.cellposition");
		tableTooltip = Outside.service(this,"gus.x.swing.table.cust.tooltip1");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		JTable table = (JTable) obj;
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableTooltip.p(table);
		
		JLabel label = (JLabel) labelCellPosition.t(table);
		JScrollPane scroll = new JScrollPane(table);
		
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(scroll,BorderLayout.CENTER);
		panel.add(label,BorderLayout.SOUTH);
		
		return panel;
	}
}
