package a.entity.gus06.swing.textcomp.build.historyholder;

import a.framework.*;
import javax.swing.text.JTextComponent;
import java.util.List;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.util.Collections;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160624";}

	public static final KeyStroke UP = KeyStroke.getKeyStroke(KeyEvent.VK_UP,0);
	public static final KeyStroke DOWN = KeyStroke.getKeyStroke(KeyEvent.VK_DOWN,0);
	public static final int MAX = 10;
	
	
	
	public Object t(Object obj) throws Exception
	{
		return new Holder((JTextComponent) obj);
	}
	
	
	private class Holder implements P, E
	{
		private JTextComponent comp;
		private List history;
		private int index = 0;
		
		public Holder(JTextComponent comp)
		{
			this.comp = comp;
			history = new ArrayList();
			
			AbstractAction actionUp = new AbstractAction()
			{public void actionPerformed(ActionEvent e){up();}};
			
			AbstractAction actionDown = new AbstractAction()
			{public void actionPerformed(ActionEvent e){down();}};
			
			comp.getInputMap().put(UP,actionUp);
			comp.getInputMap().put(DOWN,actionDown);
		}
		
		
		public void p(Object obj) throws Exception
		{
			updateHistory((String) obj);
		}
		
		public void e() throws Exception
		{
			history.clear();
			index = 0;
		}
		
		
		private void updateHistory(String line)
		{
			while(history.contains(line)) history.remove(line);
			history.add(line);
			while(history.size()>MAX) history.remove(0);
			index = history.size()-1;
		}
		
		
		private String valueAt(int index)
		{
			if(history.isEmpty()) return null;
			return (String) history.get(index);
		}
		
		private void up()
		{
			if(history.isEmpty()) return;
			
			index++;
			if(index>=history.size()) index = 0;
			comp.setText(valueAt(index));
		}
		
		private void down()
		{
			if(history.isEmpty()) return;
			
			index--;
			if(index<0) index = history.size()-1;
			comp.setText(valueAt(index));
		}
	}
}
