package a.entity.gus06.appli.gusiconviewer.gui.panel;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.Scrollable;
import javax.swing.JComponent;
import java.io.File;

import a.framework.*;
import java.awt.Rectangle;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Set;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class EntityImpl implements Entity, ActionListener, I, P {

	public String creationDate() {return "20160421";}


	private Service listing0;
	private Service labelBuilder;
	private Service fieldHolder;
	private Service fieldPersister;
	private Service toFileMap;
	private Service filterSet;

	private JComponent field;
	private JPanel1 panel1;
	private JPanel panel;
	
	private File dir;
	
	
	
	public EntityImpl() throws Exception
	{
		listing0 = Outside.service(this,"gus06.dir.listing0.ext.gif");
		labelBuilder = Outside.service(this,"gus06.appli.gusiconviewer.gui.panel.labelbuilder");
		fieldHolder = Outside.service(this,"*gus06.data.editor.string.textfield.editor1");
		fieldPersister = Outside.service(this,"gus06.swing.textcomp.persister.text");
		toFileMap = Outside.service(this,"gus06.dir.listing0.names0.asfilemap");
		filterSet = Outside.service(this,"gus06.set.filter.rule.one");
		
		field = (JComponent) fieldHolder.i();
		fieldPersister.v(getClass().getName()+"_field",field);
		
		panel1 = new JPanel1();
		
		panel = new JPanel(new BorderLayout());
		panel.add(field,BorderLayout.NORTH);
		panel.add(panel1,BorderLayout.CENTER);
		
		fieldHolder.addActionListener(this);
	}


	public Object i()
	{return panel;}
	
	
	
	public void p(Object obj) throws Exception
	{
		dir = (File) obj;
		refresh();
	}



	public void actionPerformed(ActionEvent e)
	{refresh();}
	
	
	
	private void refresh()
	{
		try
		{
			if(dir==null) return;
			
			File[] ff = (File[]) listing0.t(dir);
			String input = (String) fieldHolder.g();
			
			Map map = (Map) toFileMap.t(ff);
			Set set = (Set) filterSet.t(new Object[]{map.keySet(),input});
			List keys = new ArrayList(set);
			Collections.sort(keys);
			
			int number = keys.size();
			
			panel1.removeAll();
			panel1.setLayout(buildLayout(number));
			
			for(int i=0;i<number;i++)
			{
				String key = (String) keys.get(i);
				File file = (File) map.get(key);
				JLabel label = (JLabel) labelBuilder.t(file);
				
				panel1.add(label);
			}
			
			panel1.refresh();
		}
		catch(Exception e)
		{Outside.err(this,"refresh()",e);}
	}
	
	
	
	private GridLayout buildLayout(int n)
	{
		int dim = (int)Math.sqrt(n);
		if(dim*dim<n) dim++;
		
		if(n<=dim*dim-dim)
			return new GridLayout(dim,dim-1);
		return new GridLayout(dim,dim);
	}
	
	
	
	private class JPanel1 extends JPanel implements Scrollable
	{
		public void refresh()
		{
			validate();
			repaint();
		}
		
		private JList list = new JList();
	
		public Dimension getPreferredScrollableViewportSize() {
			return list.getPreferredScrollableViewportSize();
		}
		public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
			return list.getScrollableUnitIncrement(visibleRect,orientation,direction);
		}
		public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
			return list.getScrollableBlockIncrement(visibleRect,orientation,direction);
		}
		public boolean getScrollableTracksViewportWidth() {
			return list.getScrollableTracksViewportWidth();
		}
		public boolean getScrollableTracksViewportHeight() {
			return list.getScrollableTracksViewportHeight();
		}
	}

}
