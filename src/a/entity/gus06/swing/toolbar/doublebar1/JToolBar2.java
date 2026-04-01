
package a.entity.gus06.swing.toolbar.doublebar1;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.border.Border;


public class JToolBar2 extends JToolBar implements PropertyChangeListener {

	private static Border empty = BorderFactory.createEmptyBorder();
	private static Border etched = BorderFactory.createEtchedBorder();
	
	private Dimension buttonSize;
	private Border border; 
	
	public static int H = 20;
	
	private ButtonGroup group;
	


	public JToolBar2()
	{this(H);}

	public JToolBar2(int x)
	{
		super();
		setFloatable(false);
		group = new ButtonGroup();
		buttonSize = new Dimension(x,x);
		border = empty;
		
		setFocusable(true);
		setBorder(empty);
		
		addMouseListener(new MouseAdapter(){
		public void mousePressed(MouseEvent e)
		{requestFocusInWindow();}});
	}
	
	
	
	public JButton add(Action a)
	{
		JButton b = super.add(a);
		b.setMinimumSize(buttonSize);
		b.setMaximumSize(buttonSize);
		b.setBorder(border);
		setMinimumSize(new Dimension(0,0));
		return b;
	}
	
	
	
	public JButton addToggle(Action a)
	{
		JToggleButton b = new JToggleButton(a);
		super.add(b);
		
		b.setText("");
		b.addPropertyChangeListener("text",this);
		b.setMinimumSize(buttonSize);
		b.setMaximumSize(buttonSize);
		b.setBorder(border);
		setMinimumSize(new Dimension(0,0));
		
		return new JButton();
	}
	
	
	
	
	
	public JButton addGroup(Action a)
	{
		JToggleButton b = new JToggleButton(a);
		group.add(b);
		super.add(b);
		
		b.setText("");
		b.addPropertyChangeListener("text",this);
		b.setMinimumSize(buttonSize);
		b.setMaximumSize(buttonSize);
		b.setBorder(border);
		setMinimumSize(new Dimension(0,0));
		
		return new JButton();
	}
	
	
	
	public void propertyChange(PropertyChangeEvent e)
	{
		JToggleButton b = (JToggleButton)e.getSource();
		b.setText("");
	}

}
