package a.entity.gus06.swing.table.buildlabel.cellposition;

import a.framework.*;

import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JTable;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150511";}

	
	public Object t(Object obj) throws Exception
	{return new JLabelPosition((JTable)obj);}
	
	
	private class JLabelPosition extends JLabel implements KeyListener, MouseListener
	{
		private JTable table;
		
		public JLabelPosition(JTable table)
		{
			this.table = table;
			setFont(getFont().deriveFont(Font.PLAIN));
			
			table.addKeyListener(this);
			table.addMouseListener(this);
		}
		
		private void updateDisplay()
		{
			int x = table.getSelectedRow();
			int y = table.getSelectedColumn();
			setText(" ("+x+","+y+") ");
		}

		public void keyPressed(KeyEvent e) {updateDisplay();}
		public void keyReleased(KeyEvent e) {updateDisplay();}
		public void keyTyped(KeyEvent e) {updateDisplay();}
		public void mouseClicked(MouseEvent e) {updateDisplay();}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void mousePressed(MouseEvent e) {updateDisplay();}
		public void mouseReleased(MouseEvent e) {updateDisplay();}
	}
}
