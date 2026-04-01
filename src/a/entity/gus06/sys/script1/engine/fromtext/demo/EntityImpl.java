package a.entity.gus06.sys.script1.engine.fromtext.demo;

import a.framework.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.util.List;
import java.util.Map;
import java.io.OutputStream;
import java.awt.GridLayout;
import java.awt.Font;

public class EntityImpl implements Entity, ActionListener, I, G, E, Runnable {

	public String creationDate() {return "20150829";}
	
	public static final Font FONT = new Font("Courier New", Font.PLAIN, 13);


	private Service engine;
	private Service persister;
	private Service buildOutputStream;
	private Service viewer;
	private Service contextBuilder;
	private Service putAction;

	private JButton button;
	private JTextArea area1;
	private JTextArea area2;
	
	private JPanel panel;
	
	private OutputStream os;
	private Thread t;
	
	

	public EntityImpl() throws Exception
	{
		engine = Outside.service(this,"gus06.sys.script1.engine.fromtext");
		persister = Outside.service(this,"gus06.swing.textcomp.persister.text");
		buildOutputStream = Outside.service(this,"gus06.io.outputstream.textarea1");
		viewer = Outside.service(this,"*gus06.data.viewer.g.output");
		contextBuilder = Outside.service(this,"gus06.sys.script1.context.builder1");
		putAction = Outside.service(this,"gus06.swing.textcomp.cust.putaction.ctrl_q");
		
		Outside.service(this,"gus06.beep");
		Outside.service(this,"gus06.beep.sleep1000");
		Outside.service(this,"gus06.string.transform.str.upper");
		Outside.service(this,"gus06.string.transform.str.lower");
		
		
		button = new JButton("Execute");
		button.addActionListener(this);
		
		area1 = new JTextArea();
		area1.setFont(FONT);
		persist("area",area1);
		
		area2 = new JTextArea();
		area2.setFont(FONT);
		area2.setEditable(false);
		
		putAction.p(new Object[]{area1,this});
		
		os = (OutputStream) buildOutputStream.t(area2);
		
		JPanel panel1 = new JPanel(new GridLayout(2,1));
		panel1.add(new JScrollPane(area1));
		panel1.add(new JScrollPane(area2));
		
		JSplitPane split = new JSplitPane();
		split.setDividerLocation(600);
		split.setLeftComponent(panel1);
		split.setRightComponent((JComponent) viewer.i());
		
		panel = new JPanel(new BorderLayout());
		panel.add(split,BorderLayout.CENTER);
		panel.add(button,BorderLayout.SOUTH);
	}
	
	
	private void persist(String key, Object obj) throws Exception
	{persister.v(getClass().getName()+"_"+key,obj);}
	
	
	
	public Object i() throws Exception
	{return panel;}

	
	public void e() throws Exception
	{perform();}
	

	public void actionPerformed(ActionEvent e)
	{perform();}
	
	
	
	public Object g() throws Exception
	{
		area2.setText("");
		
		Map context = (Map) contextBuilder.t(os);
		String script = area1.getText();
		
		return engine.t(new Object[]{script,context});
	}
	
	
	
	
	private void perform()
	{
		if(t!=null && t.isAlive()) return;
		t = new Thread(this,"THREAD_"+getClass().getName());
		t.start();
	}
	
	
	public void run()
	{
		try{viewer.p(this);}
		catch(Exception e)
		{Outside.err(this,"run()",e);}
	}
}
