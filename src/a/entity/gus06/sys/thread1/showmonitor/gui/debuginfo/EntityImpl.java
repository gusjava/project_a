package a.entity.gus06.sys.thread1.showmonitor.gui.debuginfo;

import a.framework.*;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20180124";}


	private Service stacktraceToString;
	private Service getLockInfo;
	private Service getLockName;
	private Service getLockOwnerName;
	
	private JTextArea area;
	private JPanel panel;
	
	private JButton buttonLockInfo;
	private JButton buttonLockName;
	private JButton buttonLockOwnerName;
	private JButton buttonStacktrace;

	private Thread t;
	
	

	public EntityImpl() throws Exception
	{
		stacktraceToString = Outside.service(this,"gus06.tostring.stacktrace");
		getLockInfo = Outside.service(this,"gus06.thread.info.lockinfo");
		getLockName = Outside.service(this,"gus06.thread.info.lockname");
		getLockOwnerName = Outside.service(this,"gus06.thread.info.lockownername");
		
		area = new JTextArea();
		area.setMargin(new Insets(3,3,3,3));
		area.setEditable(false);
		
		buttonLockInfo = new JButton("Lock info");
		buttonLockInfo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{showLockInfo();}
		});
		buttonLockName = new JButton("Lock name");
		buttonLockName.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{showLockName();}
		});
		buttonLockOwnerName = new JButton("Lock owner name");
		buttonLockOwnerName.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{showLockOwnerName();}
		});
		buttonStacktrace = new JButton("Stacktrace");
		buttonStacktrace.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{showStacktrace();}
		});
		
		JPanel buttonPanel = new JPanel(new GridLayout(2,2,5,5));
		
		buttonPanel.add(buttonLockInfo);
		buttonPanel.add(buttonLockName);
		buttonPanel.add(buttonLockOwnerName);
		buttonPanel.add(buttonStacktrace);
		
		
		panel = new JPanel(new BorderLayout());
		panel.add(new JScrollPane(area), BorderLayout.CENTER);
		panel.add(buttonPanel, BorderLayout.SOUTH);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	public void p(Object obj) throws Exception
	{
		t = (Thread) obj;
	}
	
	
	
	
	private void showLockInfo()
	{
		try
		{
			String s = (String) getLockInfo.t(t);
			area.setText(s);
		}
		catch(Exception e)
		{Outside.err(this,"showLockInfo()",e);}
	}
	
	private void showLockName()
	{
		try
		{
			String s = (String) getLockName.t(t);
			area.setText(s);
		}
		catch(Exception e)
		{Outside.err(this,"showLockName()",e);}
	}
	
	private void showLockOwnerName()
	{
		try
		{
			String s = (String) getLockOwnerName.t(t);
			area.setText(s);
		}
		catch(Exception e)
		{Outside.err(this,"showLockOwnerName()",e);}
	}
	
	private void showStacktrace()
	{
		try
		{
			String s = (String) stacktraceToString.t(t);
			area.setText(s);
		}
		catch(Exception e)
		{Outside.err(this,"showStacktrace()",e);}
	}
	
}
