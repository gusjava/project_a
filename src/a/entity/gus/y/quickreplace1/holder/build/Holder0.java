package a.entity.gus.y.quickreplace1.holder.build;			

import javax.swing.event.DocumentListener;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;
import javax.swing.event.DocumentEvent;
import javax.swing.text.JTextComponent;

public abstract class Holder0 implements DocumentListener, CaretListener
{
	protected JTextComponent comp;
	protected int memDot;
	protected int memMark;
	
	private boolean justEdited = false;
	
	
	public Holder0(JTextComponent comp)
	{
		this.comp = comp;
		comp.addCaretListener(this);
		comp.getDocument().addDocumentListener(this);
	}

	
	public void caretUpdate(CaretEvent e)
	{
		memDot = e.getDot();
		memMark = e.getMark();
		
		if(!justEdited) caretMoved();
		justEdited=false;
	}
	
	public void changedUpdate(DocumentEvent e) {}
	
	public void insertUpdate(DocumentEvent e)
	{
		justEdited = true;
		int start = e.getOffset();
		int length = e.getLength();
		if(start==0 && length==e.getDocument().getLength()) return;
		
		try
		{
			String text = e.getDocument().getText(start,length);
			insert(text);
		}
		catch(Exception ex){}
	}
	
	public void removeUpdate(DocumentEvent e)
	{
		justEdited = true;
		int start = e.getOffset();
		int length = e.getLength();
		if(start==0 && length==e.getDocument().getLength()) return;
		
		if(memDot!=memMark){deleteSelection();}
		else if(start==memDot){deleteOneAfter();}
		else if(start==memDot-1){deleteOneBefore();}
	}
	
	
	protected abstract void insert(String text);
	protected abstract void caretMoved();
	protected abstract void deleteSelection();
	protected abstract void deleteOneAfter();
	protected abstract void deleteOneBefore();
}
