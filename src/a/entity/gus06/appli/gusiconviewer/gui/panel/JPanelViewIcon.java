
package a.entity.gus06.appli.gusiconviewer.gui.panel;

import a.framework.*;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.Scrollable;
import javax.swing.SwingConstants;


public class JPanelViewIcon extends JPanel implements Scrollable {

	
	private JLabel[] labels;
	private Service labelHandler;
	private HashMap cache;
	
	
	public JPanelViewIcon(Service labelHandler)
	{
		this.labelHandler = labelHandler;
		cache = new HashMap();
	}
	
	
	private JLabel buildLabel(String key, Icon icon) throws Exception
	{
		JLabel label = new JLabel(icon,SwingConstants.CENTER);
		label.setToolTipText(key);
		label.setOpaque(true); 
		labelHandler.v(key,label);
		return label;
	}
	
	
	
	
	public void init(Map map) throws Exception
	{
		removeAll();
		Vector keys = new Vector(map.keySet());
		Collections.sort(keys);
		
		int number = map.size();
		
		setLayout(buildLayout(number));
		labels = new JLabel[number];
		
		for(int i=0;i<number;i++)
		{
			String key = (String) keys.get(i);
			Icon icon = (Icon) map.get(key);
			
			if(!cache.containsKey(key))
				cache.put(key,buildLabel(key,icon));
			
			labels[i] = (JLabel) cache.get(key);
			labels[i].setIcon(icon);
			add(labels[i]);
		}
		validate();
		repaint();
	}
	
	
	
	
	private GridLayout buildLayout(int n)
	{
		int dim = (int)Math.sqrt(n);
		if(dim*dim<n) dim++;
		
		if(n<= dim*dim-dim)
			return new GridLayout(dim,dim-1);
		return new GridLayout(dim,dim);
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
