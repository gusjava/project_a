package a.entity.gus06.sys.dirsearch1.gui.search.termsbar;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JToolBar;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import java.awt.Font;

public class EntityImpl extends S1 implements Entity, I, P, G {

	public String creationDate() {return "20200124";}


	private Service buildToolbar;
	private Service buildOvalBorder;
	
	
	private List listExtr;
	private List listLabel;
	private JToolBar toolBar;


	public EntityImpl() throws Exception
	{
		buildToolbar = Outside.service(this,"gus06.swing.toolbar.toolbar1");
		buildOvalBorder = Outside.service(this,"gus06.swing.border.build.ovalborder");
		
		toolBar = (JToolBar) buildToolbar.i();
		toolBar.add(new JLabel(" Terms: "));
		
		listLabel = new ArrayList();
	}
	
	
	public Object i() throws Exception
	{return toolBar;}
	
	
	
	public void p(Object obj) throws Exception
	{
		listExtr = (List) obj;
		
		if(!listLabel.isEmpty()) resetBar();
		
		if(listExtr!=null)
		for(int i=0;i<listExtr.size();i++)
		{
			Object extr = listExtr.get(i);
			JLabel label = new JLabelTerm(extr);
			listLabel.add(label);
			toolBar.add(label);
		}
	}
	
	
	private void resetBar()
	{
		for(int i=0;i<listLabel.size();i++)
		toolBar.remove((JLabel) listLabel.get(i));
		toolBar.repaint();
		listLabel.clear();
	}
	
	
	public Object g() throws Exception
	{return listExtr;}
	
	
	
	
	private class JLabelTerm extends JLabel implements MouseListener
	{
		private Object extr;
		
		public JLabelTerm(Object extr) throws Exception
		{
			super();
			this.extr = extr;
			
			Color color = (Color) ((R)extr).r("color");
			String term = (String) ((R)extr).r("search");
			
			Border border1 = (Border) buildOvalBorder.g();
			Border border2 = BorderFactory.createEmptyBorder(4,4,4,4);
			Border border3 = BorderFactory.createCompoundBorder(border2,border1);
			
			setFocusable(true);
			setBorder(border3);
			setText(" "+term+" ");
			setFont(getFont().deriveFont(Font.BOLD));
			setForeground(color);
			addMouseListener(this);
		}
		
		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
		public void mouseClicked(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
	}
}
