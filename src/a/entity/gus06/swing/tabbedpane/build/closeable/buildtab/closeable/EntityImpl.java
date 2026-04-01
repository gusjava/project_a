package a.entity.gus06.swing.tabbedpane.build.closeable.buildtab.closeable;

import a.framework.*;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class EntityImpl implements Entity, R, T {

	public String creationDate() {return "20141203";}


	private Service closeSensitiveIcon;
	private Service repaintLabel;
	
	private Icon closeIcon;
	
	
	
	public EntityImpl() throws Exception
	{
		closeSensitiveIcon = Outside.service(this,"gus06.swing.icon.build.sensitive.closeicon1");
		repaintLabel = Outside.service(this,"gus06.swing.label.cust2.display");
		
		closeIcon = (Icon) closeSensitiveIcon.g();
	}


	public Object t(Object obj) throws Exception
	{return new TabLabel(toComp(obj));}

	public Object r(String key) throws Exception
	{return new TabLabel(toComp(key));}
	
	
	
	
	private JComponent toComp(Object obj) throws Exception
	{
		if(obj instanceof JComponent) return (JComponent) obj;
		if(obj instanceof I) return (JComponent) ((I)obj).i();
		if(obj instanceof String) return displayToLabel((String)obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private JLabel displayToLabel(String display) throws Exception
	{
		JLabel label = new JLabel(" ");
		repaintLabel.v(display,label);
		return label;
	}
	
	
	private class TabLabel extends JPanel implements S
	{
		private S1 d;
		
		public TabLabel(JComponent comp) throws Exception
		{
			super(new BorderLayout());
			setOpaque(false);
			
			d = new S1();
			
			JLabel labelClose = new JLabel(closeIcon);
			labelClose.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
			
			add(comp,BorderLayout.CENTER);
			add(labelClose,BorderLayout.EAST);
			
			S sup = (S) ((T)closeIcon).t(labelClose);
			sup.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(e.getActionCommand().equals("clicked()")) closed();
				}
			});
		}
		
		private void closed()
		{d.send(this,"closed()");}
		
		public void addActionListener(ActionListener listener) {d.addActionListener(listener);}
		public void removeActionListener(ActionListener listener) {d.removeActionListener(listener);}
		public List listeners() {return d.listeners();}
	}
}

