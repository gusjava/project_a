package a.entity.gus06.io.outputstream.textpane1.shell;

import a.framework.*;
import java.io.OutputStream;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180402";}


	private Service append;
	
	public EntityImpl() throws Exception
	{
		append = Outside.service(this,"gus06.io.outputstream.textpane1.shell.append");
	}

	
	public Object t(Object obj) throws Exception
	{return new OutputStreamShell((JTextPane) obj);}
	
	
	private class OutputStreamShell extends OutputStream
	{
		private JTextPane gui;
		private StringBuilder sb;
		
		public OutputStreamShell(JTextPane gui)
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
					{appendText(gui,text);}
				});
				sb.setLength(0);
				return;
			}
			sb.append((char) b);
		}
	}
	
	
	private void appendText(JTextPane gui, String text)
	{
		try{append.p(new Object[]{gui,text});}
		catch(Exception e)
		{Outside.err(this,"appendText(JTextPane,String)",e);}
	}
}
