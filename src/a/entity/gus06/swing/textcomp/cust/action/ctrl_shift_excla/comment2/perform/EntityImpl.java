package a.entity.gus06.swing.textcomp.cust.action.ctrl_shift_excla.comment2.perform;

import a.framework.*;
import javax.swing.text.Element;
import javax.swing.text.JTextComponent;
import javax.swing.text.PlainDocument;
import javax.swing.JTextArea;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20220530";}

	public static final String C_LINE = "!";


	public EntityImpl() throws Exception
	{
	}
	
	
	public void p(Object obj) throws Exception
	{
		if(obj instanceof JTextArea)
		new Holder((JTextComponent) obj);
	}
	
	
	
	private class Holder
	{
		private JTextComponent comp;
		private PlainDocument document;
		private String text;
		
		public Holder(JTextComponent comp) throws Exception
		{
			this.comp = comp;
			document = (PlainDocument) comp.getDocument();
			text = comp.getText();
			
			handle2();
		}
		
		
		private boolean isLineStart(int n)
		{return n==0 || text.charAt(n-1)=='\n';}
		
		private boolean isLineEnd(int n)
		{return n==text.length() || text.charAt(n)=='\n';}
		
		
		private void handle2() throws Exception
		{
			int length = document.getLength();
			Element root = document.getDefaultRootElement();
			
			Element element1 = document.getParagraphElement(comp.getSelectionStart());
			Element element2 = document.getParagraphElement(comp.getSelectionEnd());
			
			int blockStart = element1.getStartOffset();
			int blockEnd = element2.getEndOffset();
			
			boolean endReached = blockEnd>length;
			if(endReached) blockEnd = length;
			
			String text = comp.getText(blockStart,blockEnd-blockStart);
			String text1 = text.startsWith(C_LINE) ? decomment2(text) : comment2(text);
			
			int textLen = text.length();
			int text1Len = text1.length();
			int length1 = length - textLen + text1Len;
			
			int blockEnd1 = blockStart + text1Len - 1;
			if(endReached) blockEnd1++;
			
			document.remove(blockStart,blockEnd-blockStart);
			document.insertString(blockStart,text1,null);
			comp.select(blockStart,blockEnd1);
		}
		
		private String decomment2(String s)
		{
			StringBuffer b = new StringBuffer();
			boolean start = true;
			
			for(int i=0;i<s.length();i++)
			{
				char c = s.charAt(i);
				if(c=='!')
				{
					if(!start) b.append(c);
				}
				else if(c=='\n')
				{
					b.append(c);
					start = true;
				}
				else
				{
					b.append(c);
					start = false;
				}
			}
			return b.toString();
		}
		
		private String comment2(String s)
		{
			StringBuffer b = new StringBuffer();
			int n = 0;
			
			for(int i=0;i<s.length();i++)
			{
				char c = s.charAt(i);
				if(c=='!')
				{
					if(n==0)
					{
						n++;
						b.append(c);
					}
					else if(n==1)
					{
						
					}
					else b.append(c);
				}
				else if(c=='\n')
				{
					if(n==0) b.append(C_LINE);
					b.append(c);
					n = 0;
				}
				else
				{
					if(n==0) b.append(C_LINE);
					b.append(c);
					n = -1;
				}
			}
			return b.toString();
		}
	}
}