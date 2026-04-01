package a.entity.gus06.swing.tree.action.activation.select;

import a.framework.*;
import javax.swing.JComponent;
import java.awt.event.KeyListener;
import javax.swing.JTree;
import javax.swing.Action;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201216";}
	
	public static final String[] KEYS = new String[] {
		"selectLastExtendSelection",
		"selectNext",
		"selectChild",
		"selectNextChangeLead",
		"selectLastChangeLead",
		"selectPreviousExtendSelection",
		"selectChildChangeLead",
		"selectParentChangeLead",
		"selectNextExtendSelection",
		"selectLast",
		"selectPreviousChangeLead",
		"selectPrevious",
		"selectAll",
		"selectFirstExtendSelection",
		"selectFirst",
		"selectFirstChangeLead",
		"selectParent",
		
		"copy",
		"cut",
		"paste"
	};

	
	
	public Object t(Object obj) throws Exception
	{
		return new Holder((JTree) obj);
	}
	
	
	
	public static final Action EMPTYACTION = new AbstractAction() {
		public void actionPerformed(ActionEvent e) {}
	};
	
	
	private class Holder implements E, P, F
	{
		private JTree tree;
		private boolean removed = false;
		
		private Action[] actions;
		
		
		public Holder(JTree tree)
		{
			this.tree = tree;
			
			actions = new Action[KEYS.length];
			for(int i=0;i<KEYS.length;i++)
			actions[i] = tree.getActionMap().get(KEYS[i]);
		}
		
		private void removeAll()
		{
			if(removed) return;
			
			for(int i=0;i<KEYS.length;i++)
			tree.getActionMap().put(KEYS[i],EMPTYACTION);
			removed = true;
		}
		
		private void addAll()
		{
			if(!removed) return;
			
			for(int i=0;i<KEYS.length;i++)
			tree.getActionMap().put(KEYS[i],actions[i]);
			removed = false;
		}
		
		public void e() throws Exception
		{
			if(removed) addAll();
			else removeAll();
		}
		
		public void p(Object obj) throws Exception
		{
			String cmd = (String) obj;
			
			if(cmd.equals("addAll")) addAll();
			else if(cmd.equals("removeAll")) removeAll();
			else throw new Exception("Unknown cmd: "+cmd);
		}
		
		public boolean f(Object obj) throws Exception
		{return !removed;}
	}
}