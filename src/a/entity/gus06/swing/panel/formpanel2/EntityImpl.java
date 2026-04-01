package a.entity.gus06.swing.panel.formpanel2;

import a.framework.*;
import java.awt.Font;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class EntityImpl implements Entity, I, V, E, P {

	public String creationDate() {return "20221105";}


	private FormJPanel panel;

	public EntityImpl() throws Exception
	{panel = new FormJPanel();}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void e() throws Exception
	{panel.removeLabels();}	
	
	
	
	public void p(Object obj) throws Exception
	{
		String s = (String) obj;
		if(s.equals("removeAll")) {panel.removeLabels();return;}
		if(s.equals("repaint")) {panel.updatePanel();return;}
		
		throw new Exception("Unknown command: "+s);
	}
	
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("add"))
		{
			Object[] o = (Object[]) obj;
			if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
			JLabel label = (JLabel) o[0];
			JComponent comp = (JComponent) o[1];
			panel.addLabel(label,comp);
			return;
		}
		throw new Exception("Unknown key: "+key);
	}
	
	
	public class FormJPanel extends JPanel
	{
		public FormJPanel()
		{
			super();
			setBorder(new EmptyBorder(10, 10, 10, 10));
			setLayout(new FormLayout(20, 5)); 
		}
    
		public void addLabel(JLabel label, JComponent comp)
		{
			add(label);
			add(comp);
			updatePanel();
		}
		
		public void removeLabels()
		{
			removeAll();
			updatePanel();
		}
		
		public void updatePanel()
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