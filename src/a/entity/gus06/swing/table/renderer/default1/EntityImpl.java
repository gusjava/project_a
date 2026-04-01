package a.entity.gus06.swing.table.renderer.default1;

import a.framework.*;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import java.awt.Component;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20191113";}


	private Service valueToComp;
	
	public EntityImpl() throws Exception
	{
		valueToComp = Outside.service(this,"gus06.data.build.viewcomp");
	}
	
	public Object g() throws Exception
	{return new TableCellRenderer1();}
	
	
	private class TableCellRenderer1 extends JPanel implements TableCellRenderer
	{
		boolean failed = false;
		
		public TableCellRenderer1()
		{
			super(new BorderLayout());
			setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
		}
		
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int raw, int column)
		{
			removeAll();
			if(failed) return new JLabel("###");
			
			Component comp = valueToComp(this,value);
			if(comp!=null) add(comp,BorderLayout.CENTER);
			
			setBackground(bg(isSelected));
			return this;
		}
		
		private Color bg(boolean isSelected)
		{return isSelected ? Color.LIGHT_GRAY : Color.WHITE;}
	}
	
	
	
	private Component valueToComp(TableCellRenderer1 r, Object value)
	{
		try{return (Component) valueToComp.t(value);}
		catch(Exception e)
		{
			Outside.err(this,"valueToComp(Object)",e);
			r.failed = true;
			return new JLabel("### "+e.getMessage());
		}
	}
}
