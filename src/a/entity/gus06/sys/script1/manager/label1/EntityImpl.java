package a.entity.gus06.sys.script1.manager.label1;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class EntityImpl implements Entity, ActionListener, I {

	public String creationDate() {return "20180117";}
	
	public static final String ICONID = "FILE_gus";
	
	


	private Service frame;
	private Service labelToSup;
	private Service custLabel;
	private Service manager;


	private JLabel label;
	

	public EntityImpl() throws Exception
	{
		frame = Outside.service(this,"gus06.sys.script1.manager.gui1.frame");
		labelToSup = Outside.service(this,"gus06.swing.label.support.onclick");
		custLabel = Outside.service(this,"gus06.swing.label.cust2.icon");
		manager = Outside.service(this,"gus06.sys.script1.manager");
		
		label = new JLabel(" ");
		
		S s = (S) labelToSup.t(label);
		s.addActionListener(this);
		
		custLabel.v(ICONID,label);
		
		manager.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{updateLabel();}
		});
		updateLabel();
	}
	
	
	
	public Object i() throws Exception
	{return label;}


	public void actionPerformed(ActionEvent e)
	{perform();}
	
	
	
	private void perform()
	{
		try{frame.p("switch");}
		catch(Exception e)
		{Outside.err(this,"perform()",e);}
	}
	
	
	
	private void updateLabel()
	{
		try
		{
			label.setText(display());
		}
		catch(Exception e)
		{Outside.err(this,"updateLabel()",e);}
	}
	
	
	
	
	
	
	private String display() throws Exception
	{
		List l = (List) manager.r("list");
		
		int nb_running = l.size();
		
		StringBuffer b = new StringBuffer();
		if(nb_running>0) b.append(""+nb_running);
		
		return b.toString();
	}
}
