package a.entity.gus06.swing.textcomp.cust.autoedit.tab1;

import a.framework.*;
import javax.swing.text.JTextComponent;
import javax.swing.SwingUtilities;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160410";}


	private Service trimStart;
	private Service enlargeToLine;

	public EntityImpl() throws Exception
	{
		trimStart = Outside.service(this,"gus.x.transform.string.trim.start");
		enlargeToLine = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_space.enlargeselection.perform.q2");
	}




	public void p(Object obj) throws Exception
	{new Holder1((JTextComponent) obj);}




	private class Holder1 implements KeyListener
	{
		private JTextComponent comp;
		public Holder1(JTextComponent comp)
		{
			this.comp = comp;
			comp.addKeyListener(this);
		}
		public void keyTyped(KeyEvent e) {}
		public void keyReleased(KeyEvent e) {}
		public void keyPressed(KeyEvent e)
		{
			if(e.getKeyCode()==KeyEvent.VK_TAB)
			{
				if(is_shift(e)) handleTabInv();
				else handleTab();
			}
		}
		
		private char delim()
		{
			if(comp.getText().contains("\t")) return '\t';
			return ' ';
		}
		
		private void handleTab()
		{
			String selection = comp.getSelectedText();
			if(selection==null) return;
			if(!selection.contains("\n")) return;
			
			String s0 = moveRight(selection, delim());
			SwingUtilities.invokeLater(new Holder2(comp,s0));
		}
		
		private void handleTabInv()
		{
			char delim = delim();
			String selection = comp.getSelectedText();
			if(selection==null)
			{
				enlargeToLine(comp);
				selection = comp.getSelectedText();
				if(selection==null) return;
			}
			else
			{
				selection = widdenSelection(selection,comp, delim);
			}
			String s0 = moveLeft(selection, delim);
			SwingUtilities.invokeLater(new Holder2(comp,s0));
		}
	}
	
	
	
	
	private String moveRight(String s, char delim)
	{
		StringBuffer b = new StringBuffer();
		String[] line = s.split("\n");
		for(int i=0;i<line.length;i++)
		{
			b.append(delim+line[i]+"\n");
		}
		
		if(b.length()>0) b.deleteCharAt(b.length()-1);
		return b.toString();
	}


	private String moveLeft(String s, char delim)
	{
		try
		{
			String[] line = s.split("\n");
		
			boolean moved = false;
			for(int i=0;i<line.length;i++)
			{
				if(line[i].startsWith(""+delim)) 
				{
					line[i] = line[i].substring(1);
					moved = true;
				}
			}
			
			if(!moved)
			{
				for(int i=0;i<line.length;i++)
				line[i] = (String) trimStart.t(line[i]);
			}
			
			StringBuffer b = new StringBuffer();
			for(int i=0;i<line.length;i++)
			b.append(line[i]+"\n");
			
			if(b.length()>0) b.deleteCharAt(b.length()-1);
			return b.toString();
		}
		catch(Exception e)
		{Outside.err(this,"moveLeft(String)",e);}
		return s;
	}
	
	
	private void enlargeToLine(JTextComponent comp)
	{
		try{enlargeToLine.p(comp);}
		catch(Exception e)
		{Outside.err(this,"enlargeToLine(JTextComponent)",e);}
	}
	
	
	
	private String widdenSelection(String selection, JTextComponent comp, char delim)
	{
		if(selection.startsWith(""+delim)) return selection;
		
		int start = comp.getSelectionStart();
		if(start==0) return selection;
		
		char c = comp.getText().charAt(start-1);
		if(c==delim) 
		{
			int end = comp.getSelectionEnd();
			comp.select(start-1,end);
			return delim+selection;
		}
		
		int k=0;
		StringBuffer buff = new StringBuffer();
		while((c==' ' || c=='\t') && start-1-k>0)
		{
			k++;
			buff.append(' ');
			c = comp.getText().charAt(start-1-k);
		}
		if(k>0)
		{
			int end = comp.getSelectionEnd();
			comp.select(start-k,end);
			
			buff.append(selection);
			return buff.toString();
		}
		
		return selection;
	}
	

	
	private boolean is_shift(KeyEvent e)
	{return e.getModifiers() == KeyEvent.SHIFT_MASK;}
	
	
	
	private class Holder2 implements Runnable
	{
		private JTextComponent comp;
		private String s0;
		
		public Holder2(JTextComponent comp, String s0)
		{
			this.comp = comp;
			this.s0 = s0;
		}
		public void run()
		{replaceText(comp,s0);}
	}
	
	
	
	
	private void replaceText(JTextComponent comp, String s0)
	{
		try
		{
			if(!hasSelection(comp))
			{
				int pos = comp.getCaretPosition();
				if(pos==0) throw new Exception("UNEXPECTED: Invalid caret position after tab press: 0");
				comp.select(pos-1,pos);
			}
			
			int start = comp.getSelectionStart();
			comp.replaceSelection(s0);
			comp.select(start,start+s0.length());
		}
		catch(Exception e)
		{Outside.err(this,"replaceText(JTextComponent,String)",e);}
	}
	
	
	private boolean hasSelection(JTextComponent comp)
	{return comp.getSelectedText()!=null && comp.getSelectedText().length()>0;}
}