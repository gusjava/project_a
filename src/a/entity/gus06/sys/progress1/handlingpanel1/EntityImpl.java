package a.entity.gus06.sys.progress1.handlingpanel1;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextArea;
import java.awt.Insets;
import javax.swing.JScrollPane;


public class EntityImpl extends S1 implements Entity, E, I, P, Runnable {

	public String creationDate() {return "20150227";}


	private Service progressbar;

	private JTextArea area;
	private JPanel panel;
	
	private Object engine;
	private Thread t;
	

	public EntityImpl() throws Exception
	{
		progressbar = Outside.service(this,"*gus06.swing.progressbar.progress1a");
		
		area = new JTextArea();
		area.setEditable(false);
		area.setMargin(new Insets(3,3,3,3));
		
		panel = new JPanel(new BorderLayout());
		panel.add(new JScrollPane(area),BorderLayout.CENTER);
		panel.add((JComponent) progressbar.i(),BorderLayout.SOUTH);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void e() throws Exception
	{reset();}
	
	
	
	public void p(Object obj) throws Exception
	{
		if(t!=null && t.isAlive()) return;
		
		reset();
		engine = obj;
		
		t = new Thread(this,"THREAD_"+getClass().getName());
		t.start();
	}
	
	
	
	
	
	private void reset() throws Exception
	{
		((V)progressbar).v("reset",null);
		area.setText("");
	}
	
	
	
	private void println(String line)
	{
		area.append(line+"\n");
		area.setCaretPosition(area.getText().length());
	}
	
	private void printLine() throws Exception
	{
		String line = (String) ((R)engine).r("line");
		if(line!=null && !line.equals("")) println(line);
	}
	
	
	
	
	
	public void run()
	{
		try
		{
			((P)engine).p("init");
			printLine();
			
			String size_ = (String) ((R)engine).r("size");
			int size = Integer.parseInt(size_);
			
			((V)progressbar).v("size",size_);
			
			for(int i=0;i<size;i++)
			{
				((E)engine).e();
				((E)progressbar).e();
				printLine();
			}
		}
		catch(Exception e)
		{Outside.err(this,"run()",e);}
		over();
	}
	
	
	
	private void over()
	{send(this,"over()");}
}
