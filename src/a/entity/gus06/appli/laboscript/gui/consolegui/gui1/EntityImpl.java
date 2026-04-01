package a.entity.gus06.appli.laboscript.gui.consolegui.gui1;

import a.framework.*;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.PrintStream;
import java.awt.Font;
import java.awt.Insets;

public class EntityImpl extends S1 implements Entity, I, R, ActionListener, Runnable {

	public String creationDate() {return "20160624";}
	
	
	public static final Font FONT = new Font("Courier",Font.PLAIN,16);
	public static final Insets MARGIN = new Insets(5,5,5,5);



	private Service scriptHolder;
	private Service textPanelHolder;
	private Service formatLine;
	private Service custFieldClear;
	private Service historyHolder;
	private Service autoCompleteOP;


	private JPanel panel;
	private JTextComponent area;
	private JTextField field;
	
	private PrintStream p_in;
	private PrintStream p_out;
	private PrintStream p_err;
	
	private Thread t;
	private P history;
	

	public EntityImpl() throws Exception
	{
		scriptHolder = Outside.service(this,"*gus06.sys.script1.main.main2");
		textPanelHolder = Outside.service(this,"*gus06.swing.textpane.holder.printstreamcomp");
		formatLine = Outside.service(this,"gus06.appli.laboscript.gui.consolegui.format");
		custFieldClear = Outside.service(this,"gus06.swing.textcomp.cust.action.escap.clear");
		historyHolder = Outside.service(this,"gus06.swing.textcomp.build.historyholder");
		autoCompleteOP = Outside.service(this,"gus06.swing.textcomp.cust.action.f2.gusscript.autocomplete.op");
		
		area = (JTextComponent) textPanelHolder.i();
		area.setEditable(false);
		area.setFont(FONT);
		area.setMargin(MARGIN);
		
		field = new JTextField();
		field.setFont(FONT);
		field.addActionListener(this);
		
		autoCompleteOP.p(field);
		custFieldClear.p(field);
		history = (P) historyHolder.t(field);
		
		p_in = (PrintStream) textPanelHolder.r("b black");
		p_out = (PrintStream) textPanelHolder.r("b 102,153,255");
		p_err = (PrintStream) textPanelHolder.r("b red");
		
		panel = new JPanel(new BorderLayout());
		panel.add(new JScrollPane(area),BorderLayout.CENTER);
		panel.add(field,BorderLayout.SOUTH);
		
		scriptHolder.v("output",p_out);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("input")) return scriptHolder.r("input");
		if(key.equals("keys")) return new String[]{"input"};
		
		throw new Exception("Unknown key: "+key);
	}


	public void actionPerformed(ActionEvent e)
	{start();}
	
	
	
	private void start()
	{
		if(t!=null && t.isAlive()) return;
		t = new Thread(this,"THREAD_"+getClass().getName());
		t.start();
	}
	
	
	public void run()
	{
		field.setEnabled(false);
		
		perform();
		
		field.setEnabled(true);
		field.setText("");
		field.requestFocusInWindow();
		
		performed();
	}
	
	
	private void perform()
	{
		try
		{
			String line = field.getText();
			history.p(line);
			
			p_in.println();
			p_in.println(line);
			
			if(line.equals("reset"))
			{reset();return;}
			
			Exception e1 = null;
			try{executeQuery(line);}
			catch(Exception e){e1 = e;}
			if(e1==null) return;
			
			if(!line.startsWith(">"))
			{
				Exception e2 = null;
				try{executeQuery(">"+line);}
				catch(Exception e){e2 = e;}
				if(e2==null) return;
			}
			
			if(!line.startsWith("$"))
			{
				Exception e2 = null;
				try{executeQuery("$"+line);}
				catch(Exception e){e2 = e;}
				if(e2==null) return;
			}
			
			throw e1;
		}
		catch(Exception e)
		{
			Outside.err(this,"perform()",e);
			e.printStackTrace(p_err);
		}
	}



	private void executeQuery(String query) throws Exception
	{
		scriptHolder.p(formatLine.t(query));
	}
	
	
	
	private void reset() throws Exception
	{
		scriptHolder.v("reset",null);
		area.setText("");
		resetDone();
	}
	
	
	private void performed()
	{send(this,"performed()");}
	
	private void resetDone()
	{send(this,"resetDone()");}
}