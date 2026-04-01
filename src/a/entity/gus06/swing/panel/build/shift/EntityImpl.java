package a.entity.gus06.swing.panel.build.shift;

import a.framework.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140913";}


	
	public Object t(Object obj) throws Exception
	{return new JPanel1(obj);}



	private JComponent getComp(G g)
	{
		try{return (JComponent) g.g();}
		catch(Exception e)
		{Outside.err(this,"getComp(G)",e);}
		return null;
	}



	private class JPanel1 extends JPanel implements ActionListener
	{
		private JScrollPane scroll = new JScrollPane();
		private G g;
		
		public JPanel1(Object obj) throws Exception
		{
			super(new BorderLayout());
			g = (G) obj;
			((S) obj).addActionListener(this);
			updatePanel();
		}
		
		public void actionPerformed(ActionEvent e)
		{updatePanel();}

		public void updatePanel()
		{
			removeAll();

			JComponent comp = getComp(g);
			if(comp!=null)
			{
				if(comp instanceof Scrollable)
				{
					scroll.setViewportView(comp);
					add(scroll,BorderLayout.CENTER);
				}
				else add(comp,BorderLayout.CENTER);
			}
			
			synchronized(getTreeLock())
			{validateTree();}

			if(isDisplayable())
			{validate();repaint();}
		}
	}
}
