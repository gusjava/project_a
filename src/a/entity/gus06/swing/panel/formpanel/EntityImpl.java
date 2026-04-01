package a.entity.gus06.swing.panel.formpanel;

import a.framework.*;
import java.awt.Font;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class EntityImpl implements Entity, I, V, E {

	public String creationDate() {return "20140801";}


	

	private FormJPanel panel;

	public EntityImpl() throws Exception
	{
		panel = new FormJPanel();
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void e() throws Exception
	{panel.removeLabels();}	
	
	
	public void v(String key, Object obj) throws Exception
	{panel.addLabel(key,(JComponent) obj);}
	
	
	public class FormJPanel extends JPanel
	{
		public FormJPanel()
		{
			super();
			setBorder(new EmptyBorder(10, 10, 10, 10));
			setLayout(new FormLayout(20, 5)); 
		}
    
		public void addLabel(String name, JComponent comp)
		{
			add(label(name));
			add(comp);
			updatePanel();
		}
    
		public void insertLabelAt(int index ,String name, JComponent comp)
		{
			add(comp,index*2);
			add(label(name),index*2);
			updatePanel();
		}
    
		public void removeLabelAt(int index)
		{
			remove(index*2);
			remove(index*2);
			updatePanel();
		}
		
		public void removeLabels()
		{
			removeAll();
			updatePanel();
		}
		
		private JLabel label(String name)
		{
			JLabel l = new JLabel(name);
			l.setFont(l.getFont().deriveFont(Font.BOLD));
			return l;
		}
		
		private void updatePanel()
		{
			synchronized(getTreeLock())
			{validateTree();}

			if(isDisplayable())
			{
				validate();
				repaint();
			}
		}
	}
}