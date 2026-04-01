package a.entity.gus06.io.outputstream.textarea1.inv;

import a.framework.*;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import javax.swing.JTextArea;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140717";}
	
	
	public Object t(Object obj) throws Exception
	{return new OutputStreamJTextArea((JTextArea) obj);}
	
	
	
	private class OutputStreamJTextArea extends OutputStream
	{
		private JTextArea gui;
		private ByteArrayOutputStream baos;
		
		public OutputStreamJTextArea(JTextArea gui)
		{
			this.gui = gui;
			baos = new ByteArrayOutputStream();
		}
		
		public void write(int b)
		{
			baos.write(b);
			if((char) b=='\n')
			{
				 gui.setCaretPosition(gui.getDocument().getLength());
				 gui.setCaretPosition(0);
				 gui.replaceSelection(baos.toString());
				 baos.reset();
			}
		}
	}
}
