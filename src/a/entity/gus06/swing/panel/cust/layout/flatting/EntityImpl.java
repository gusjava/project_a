package a.entity.gus06.swing.panel.cust.layout.flatting;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.JPanel;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20170923";}


	public void p(Object obj) throws Exception
	{new PanelHolder((JPanel) obj);}

	
	
	private class PanelHolder implements ComponentListener
	{
		private JPanel panel;
		
		private Component c1;
		private Component c2;
		private GridLayout gridLayout;
		private BorderLayout borderLayout;
		
		public PanelHolder(JPanel panel) throws Exception
		{
			this.panel = panel;
			
			if(panel.getComponentCount()!=2)
				throw new Exception("Component number for panel is expected to be 2");
			
			c1 = panel.getComponent(0);
			c2 = panel.getComponent(1);
			gridLayout = new GridLayout(2,1);
			borderLayout = new BorderLayout();
			
			updatePanel();
			panel.addComponentListener(this);
		}
		
		public void componentHidden(ComponentEvent e) {}
		public void componentMoved(ComponentEvent e) {}
		public void componentShown(ComponentEvent e) {}
		public void componentResized(ComponentEvent e)
		{updatePanel();}
		
		private boolean isBorderLayout()
		{return panel.getLayout() instanceof BorderLayout;}
		
		private boolean isGridLayout()
		{return panel.getLayout() instanceof GridLayout;}
		
		
		private void updatePanel()
		{
			int size1 = c1.getMinimumSize().width+c2.getMinimumSize().width;
			int size2 = panel.getWidth();
			
			if(size2>=size1 && !isBorderLayout())
			{
				panel.removeAll();
				panel.setLayout(borderLayout);
				panel.add(c1,BorderLayout.WEST);
				panel.add(c2,BorderLayout.CENTER);
				panel.revalidate();
				return;
			}
			if(size2<size1 && !isGridLayout())
			{
				panel.removeAll();
				panel.setLayout(gridLayout);
				panel.add(c1);
				panel.add(c2);
				panel.revalidate();
				return;
			}
		}
	}
}
