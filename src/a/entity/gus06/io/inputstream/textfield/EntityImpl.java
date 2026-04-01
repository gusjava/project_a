package a.entity.gus06.io.inputstream.textfield;

import a.framework.*;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.PipedOutputStream;
import java.io.PipedInputStream;
import java.io.PrintStream;
import java.io.IOException;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180401";}


	public EntityImpl() throws Exception
	{
	}
	
	
	public Object t(Object obj) throws Exception
	{
		PipedOutputStream pos = new PipedOutputStream();
		return new InputStream1((JTextField) obj,pos);
	}
	
	
	private class InputStream1 extends PipedInputStream implements ActionListener
	{
		private JTextField comp;
		private PrintStream p;
		
		public InputStream1(JTextField comp, PipedOutputStream pos) throws IOException
		{
			super(pos);
			this.comp = comp;
			p = new PrintStream(pos);
			comp.addActionListener(this);
		}
		
		public void actionPerformed(ActionEvent e)
		{
			String s = comp.getText();
			comp.setText("");
			p.println(s);
		}
	}
}
