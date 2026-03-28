package a.entity.gus.y.quickreplace1.holder.build;

import a.framework.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.text.JTextComponent;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.Icon;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20240714";}

	private Service perform;
	
	private Icon iconPosition1;
	private Icon iconPosition2;
	private Icon iconSelection1;
	private Icon iconSelection2;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus.y.quickreplace1.perform");
		
		iconPosition1 = (Icon) Outside.resource(this,"icon#ACTION_quickRepl_position_1");
		iconPosition2 = (Icon) Outside.resource(this,"icon#ACTION_quickRepl_position_2");
		iconSelection1 = (Icon) Outside.resource(this,"icon#ACTION_quickRepl_selection_1");
		iconSelection2 = (Icon) Outside.resource(this,"icon#ACTION_quickRepl_selection_2");
	}
	
	public Object t(Object obj) throws Exception
	{
		return new Holder((JTextComponent) obj);
	}
	
	private class Holder extends Holder0 implements P, I
	{
		private JLabel label;
		private ReplaceInfo info;
		private volatile boolean performing;
		
		private String memSelected;
		private String memText;
		private int memSelectionStart;
		private int memSelectionEnd;
		private int memCaretPos;
		
		public Holder(JTextComponent comp)
		{
			super(comp);
			label = new JLabel();
			performing = false;
		}
		
		protected void insert(String text)
		{
			if(performing) return;
			
			initReplaceInfo();
			info.add(text);
			updateLabel();
		}
		
		protected void deleteSelection()
		{
			if(performing) return;
			initReplaceInfo();
			updateLabel();
		}
		
		protected void deleteOneBefore()
		{
			if(performing) return;
			
			initReplaceInfo();
			info.deleteBefore();
			updateLabel();
		}
		
		protected void deleteOneAfter()
		{
			if(performing) return;
			
			initReplaceInfo();
			info.deleteAfter();
			updateLabel();
		}
		
		protected void caretMoved()
		{
			if(performing) return;
			if(info!=null) info.complete();
			
			memSelected = comp.getSelectedText();
			memText = comp.getText();
			memSelectionStart = comp.getSelectionStart();
			memSelectionEnd = comp.getSelectionEnd();
			memCaretPos = comp.getCaretPosition();
				
			updateLabel();
		}
		
		private void initReplaceInfo()
		{
			if(info==null || info.isComplete()) info = new ReplaceInfo();
		}
		
		private void updateLabel()
		{
			if(info==null)
			{
				label.setToolTipText(null);
				label.setIcon(null);
			}
			else
			{
				label.setToolTipText(info.getDescription());
				label.setIcon(info.getIcon());
			}
		}
		
		public void p(Object obj) throws Exception
		{
			T t = (T) obj;
			
			if(info==null) return;
			info.complete();
			
			performing = true;
			T trans = (T) t.t(info);
			
			boolean fullTransform = perform.f(new Object[]{comp, info, trans});
			if(fullTransform) info = null;
			else info.partial();
			
			updateLabel();
			performing = false;
		}
		
		public Object i() throws Exception
		{return label;}
		
		private class ReplaceInfo implements R, F
		{
			private String text;
			private String selected;
			private int selectionStart;
			private int selectionEnd;
			private int caretPos;
			
			private StringBuffer replace;
			private int delAfter;
			private int delBefore;
			private boolean complete;
			private boolean partial;
			
			public ReplaceInfo()
			{
				text = memText;
				selected = memSelected;
				selectionStart = memSelectionStart;
				selectionEnd = memSelectionEnd;
				caretPos = memCaretPos;
				
				replace = new StringBuffer();
				delAfter = 0;
				delBefore = 0;
				
				complete = false;
				partial = false;
			}
			
			public boolean isComplete()
			{return complete;}
			
			public boolean isPartial()
			{return partial;}
			
			public String getText()
			{return text;}
			
			public String getSelected()
			{return selected;}
			
			public String getReplace()
			{return replace.toString();}
			
			public int getCaretPos()
			{return caretPos;}
			
			public int getDelBefore()
			{return delBefore;}
			
			public int getDelAfter()
			{return delAfter;}
			
			public void complete()
			{complete = true;}
			
			public void partial()
			{partial = true;}
			
			public void add(String s)
			{replace.append(s);}
			
			public void deleteBefore()
			{
				if(replace.length()>0) replace.deleteCharAt(replace.length()-1);
				else delBefore++;
			}
			
			public void deleteAfter()
			{
				delAfter++;	
			}
			
			public boolean isTypeSelection()
			{return selected!=null && selected.length()>0;}
			
			public Icon getIcon()
			{
				if(isTypeSelection()) return complete ? iconSelection2: iconSelection1;
				return complete ? iconPosition2: iconPosition1;
			}
			
			public String getDescription()
			{
				StringBuffer b = new StringBuffer();
				if(isTypeSelection()) b.append(selected.length()+"\u2192");
				b.append(delBefore+"."+getReplace().length()+"."+delAfter);
				return b.toString();
			}
			
			public Object r(String key) throws Exception
			{
				if(key.equals("text")) return text;
				if(key.equals("selected")) return selected;
				if(key.equals("replace")) return getReplace();
				if(key.equals("caretPos")) return caretPos;
				if(key.equals("delAfter")) return delAfter;
				if(key.equals("delBefore")) return delBefore;
				
				if(key.equals("keys")) return new String[]{
					"text","selected","replace",
					"caretPos","delAfter","delBefore"};
				throw new Exception("Unknown key: "+key);
			}
			
			public boolean f(Object obj) throws Exception
			{
				String key = (String) obj;
				if(key.equals("complete")) return complete;
				if(key.equals("partial")) return partial;
				
				throw new Exception("Unknown key: "+key);
			}
		}
	}
}