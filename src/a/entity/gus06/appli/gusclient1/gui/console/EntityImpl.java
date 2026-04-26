package a.entity.gus06.appli.gusclient1.gui.console;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.io.OutputStream;
import java.io.PrintStream;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import a.framework.*;

public class EntityImpl implements Entity, I, ActionListener {

	public String creationDate() {return "20140717";}
	
	
	private Service buildOutput;
	private Service custArea;
	private Service custFieldClear;
	private Service perform;
	private Service historyHolder;
	
	private JPanel panel;
	private JTextArea area;
	private JTextField field;
	
	private PrintStream areaOut;
	private P history;
	
	
	
	public EntityImpl() throws Exception
	{
		buildOutput = Outside.service(this,"gus06.io.outputstream.textarea1");
		custArea = Outside.service(this,"gus06.swing.textcomp.cust.console1.blue");
		custFieldClear = Outside.service(this,"gus.x.swing.textcomp.cust.action.escap.clear");
		perform = Outside.service(this,"gus06.appli.gusclient1.gui.console.perform");
		historyHolder = Outside.service(this,"gus06.swing.textcomp.build.historyholder");
		
		area = new JTextArea();
		area.setEditable(false);
		
		field = new JTextField();
		field.addActionListener(this);
		
		custArea.p(area);
		custArea.p(field);
		
		custFieldClear.p(field);
		history = (P) historyHolder.t(field);
		
		panel = new JPanel(new BorderLayout());
		panel.add(new JScrollPane(area),BorderLayout.CENTER);
		panel.add(field,BorderLayout.SOUTH);

		areaOut = new PrintStream((OutputStream) buildOutput.t(area));
		
		perform.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				boolean isRunning = e.getActionCommand().equals("start()");
				updateGui(isRunning);
			}
		});
	}
	
	
	
	public Object i() throws Exception
	{return panel;}

	
	public void actionPerformed(ActionEvent e)
	{perform();}
	
	
	
	private void perform()
	{
		try
		{
			String line = field.getText();
			history.p(line);
			field.setText("");
			
			if(line.equals("clear"))
			{area.setText("");return;}
			
			perform.p(new Object[]{line,areaOut});
		}
		catch(Exception e)
		{Outside.err(this,"perform()",e);}
	}
	
	
	
	private void updateGui(boolean isRunning)
	{
		Color fg = isRunning?Color.YELLOW.darker():Color.WHITE;
		area.setForeground(fg);
		field.setForeground(fg);
		field.setCaretColor(fg);
	}
}
