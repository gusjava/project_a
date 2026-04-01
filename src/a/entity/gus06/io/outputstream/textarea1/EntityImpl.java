package a.entity.gus06.io.outputstream.textarea1;

import a.framework.*;
import java.io.OutputStream;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140717";}
	
	
	public Object t(Object obj) throws Exception
	{return new OutputStreamJTextArea((JTextArea) obj);}
	
	
	private class OutputStreamJTextArea extends OutputStream
	{
		private JTextArea gui;
		private StringBuilder sb;
		
		public OutputStreamJTextArea(JTextArea gui)
		{
			this.gui = gui;
			sb = new StringBuilder();
		}
		
		public void write(int b)
		{
			if(b=='\r') return;
			if(b=='\n')
			{
				final String text = sb.toString() + "\n";
				SwingUtilities.invokeLater(new Runnable() {
					public void run()
					{
						gui.append(text);
						gui.setCaretPosition(gui.getDocument().getLength());
					}
				});
				sb.setLength(0);
				return;
			}
			sb.append((char) b);
		}
	}
}
