package a.entity.gus06.swing.comp.perform.sum;

import a.framework.*;

import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Component;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160830";}

	
	
	public Object t(Object obj) throws Exception
	{
		JComponent[] cc = (JComponent[]) obj;
		JPanel panel = new JPanel(new GridLayout(1,0));
		for(int i=0;i<cc.length;i++) add(panel,cc[i]);
		return panel;
	}
	
	
	private void add(JPanel panel, Component c)
	{
		if(c instanceof JPanel)
		{
			JPanel p = (JPanel) c;
			LayoutManager lm = p.getLayout();
			if(lm instanceof GridLayout)
			{
				GridLayout gl = (GridLayout) lm;
				int rows = gl.getRows();
				int columns = gl.getColumns();
				
				if(rows==1)
				{
					Component[] cc = p.getComponents();
					for(int i=0;i<cc.length;i++) add(panel,cc[i]);
					return;
				}
			}
		}
		panel.add(c);
	}
}
