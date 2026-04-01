package a.entity.gus06.swing.panel.inv.grid;

import a.framework.*;
import javax.swing.JPanel;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Component;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20160830";}

	
	
	public Object t(Object obj) throws Exception
	{
		p(obj);
		return obj;
	}
	
	
	public void p(Object obj) throws Exception
	{
		JPanel p = (JPanel) obj;
		
		LayoutManager lm = p.getLayout();
		if(lm instanceof GridLayout)
		{
			GridLayout gl = (GridLayout) lm;
			int rows = gl.getRows();
			int columns = gl.getColumns();
			
			p.setLayout(new GridLayout(columns,rows));
		}
	}
}
