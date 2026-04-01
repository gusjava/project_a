package a.entity.gus06.sys.textcomparator1.textarea.bg.builder;

import a.framework.*;
import javax.swing.JTextArea;
import javax.swing.text.JTextComponent;
import java.util.List;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.text.BadLocationException;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190616";}
	
	
	public Object t(Object obj) throws Exception
	{
		JTextArea textArea = (JTextArea) obj;
		return new Holder(textArea);
	}
	
	
	
	public class Holder implements P, E, V, R
	{
		private JTextArea textArea;
		private List list1;
		private List list2;
		private int lineNb;
		
		public List getList1() {return list1;}
		public List getList2() {return list2;}
		
		public Holder(JTextArea textArea) throws Exception
		{
			list1 = new ArrayList();
			list2 = new ArrayList();
			lineNb = 0;
			
			this.textArea = textArea;
			((P) textArea).p(new Painter(this));
		}
		
		public void v(String key, Object obj) throws Exception
		{
			if(key.equals("hr")) {setBar((Color) obj);return;}
			
			throw new Exception("Unknown key: "+key);
		}
		
		public Object r(String key) throws Exception
		{
			if(key.equals("list1")) return list1;
			if(key.equals("list2")) return list2;
			if(key.equals("lineNb")) return lineNb;
			
			if(key.equals("keys")) return new String[]{"list1","list2","lineNb"};
			throw new Exception("Unknown key: "+key);
		}
		
		public void p(Object obj) throws Exception
		{
			if(obj instanceof String)
			{
				String line = checkLine((String) obj);
				textArea.append(line+"\n");
				lineNb++;
				return;
			}
			
			Object[] o = (Object[]) obj;
			if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
			
			String line = checkLine((String) o[0]);
			Color color = (Color) o[1];
			
			if(color!=null)
			{
				int pos = textArea.getText().length();
				textArea.append(line+"\n");
				list1.add(new Object[]{lineNb,pos,color});
				lineNb++;
			}
			else
			{
				textArea.append(line+"\n");
				lineNb++;
			}
		}
		
		
		public void e() throws Exception
		{
			list1.clear();
			list2.clear();
			lineNb = 0;
			
			textArea.setText("");
			textArea.repaint();
		}
		
		private void setBar(Color color)
		{
			int pos = textArea.getText().length();
			list2.add(new Object[]{lineNb,pos,color});
		}
		
		private String checkLine(String line) throws Exception
		{
			if(line.contains("\n")) throw new Exception("Invalid line: ["+line+"]");
			return line;
		}
	}
	
	
	
	
	private class Painter implements P
	{
		private List list1;
		private List list2;
		
		public Painter(Holder holder)
		{
			list1 = holder.getList1();
			list2 = holder.getList2();
		}
		
		public void p(Object obj) throws Exception
		{
			Object[] o = (Object[]) obj;
			if(o.length!=2) throw new Exception("Wrong data number: "+o.length);

			Graphics g = (Graphics) o[0];
			JTextComponent comp = (JTextComponent) o[1];
			
			int nb1 = list1.size();
			for(int i=0;i<nb1;i++)
			{
				Object[] info = (Object[]) list1.get(i);
				Integer pos = (Integer) info[1];
				Color color = (Color) info[2];
				
				try
				{
					Rectangle rect = comp.modelToView(pos);
					if(rect!=null)
					{
						g.setColor(color);
						g.fillRect(0,rect.y,comp.getWidth(),rect.height);
					}
				}
				catch (BadLocationException e) {}
			}
			
			int nb2 = list2.size();
			for(int i=0;i<nb2;i++)
			{
				Object[] info = (Object[]) list2.get(i);
				Integer pos = (Integer) info[1];
				Color color = (Color) info[2];
				
				try
				{
					Rectangle rect = comp.modelToView(pos);
					if(rect!=null)
					{
						g.setColor(color);
						g.fillRect(0,rect.y,comp.getWidth(),1);
					}
				}
				catch (BadLocationException e) {}
			}
		}
	}
}