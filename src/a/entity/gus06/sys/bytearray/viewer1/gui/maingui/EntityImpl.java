package a.entity.gus06.sys.bytearray.viewer1.gui.maingui;

import a.framework.*;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.JComponent;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20191007";}

	private Service viewer;
	
	private Service utf8;
	private Service string;
	private Service base64;
	private Service hexa;
	private Service binary;
	private Service long1;
	private Service int1;
	private Service image;

	private JPanel panel;
	private JPanel panel_buttons;
	
	
	private byte[] data;
	
 
	public EntityImpl() throws Exception
	{
		viewer = Outside.service(this,"*gus06.data.viewer.object");
		
		utf8 = Outside.service(this,"gus06.convert.bytearraytoutf8");
		string = Outside.service(this,"gus06.convert.bytearraytostring");
		base64 = Outside.service(this,"gus06.tostring.bytetobase64");
		hexa = Outside.service(this,"gus.x.bytearraytohexa1");
		binary = Outside.service(this,"gus06.tostring.bytetobinary");
		long1 = Outside.service(this,"gus06.convert.bytearraytolong");
		int1 = Outside.service(this,"gus06.convert.bytearraytoint");
		image = Outside.service(this,"gus06.convert.bytearraytobufferedimage");
		
	
		panel_buttons = new JPanel(new GridLayout(2,4));
		
		addButton("base64",base64);
		addButton("hexa",hexa);
		addButton("binary",binary);
		addButton("image",image);
		
		addButton("long",long1);
		addButton("int",int1);
		addButton("utf8",utf8);
		addButton("string",string);
		
		panel = new JPanel(new BorderLayout());
		panel.add(panel_buttons,BorderLayout.NORTH);
		panel.add((JComponent) viewer.i(),BorderLayout.CENTER);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void p(Object obj) throws Exception
	{
		data = (byte[]) obj;
		viewer.p(null);
	}
	
	
	
	
	private void addButton(String title, final T t)
	{
		JButton b = new JButton(title);
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {perform(t);}
		});
		panel_buttons.add(b);
	}
	
	
	private void perform(T t)
	{
		try
		{
			if(data==null) {view(null);return;}
			
			Object obj = t.t(data);
			if(obj==null) throw new Exception("Result null after conversion with t="+t);
			
			view(obj);
		}
		catch(Exception e)
		{
			Outside.err(this,"perform(T)",e);
			view(e);
		}
	}
	
	
	
	private void view(Object obj)
	{
		try{viewer.p(obj);}
		catch(Exception e)
		{Outside.err(this,"view(Object)",e);}
	}
}
