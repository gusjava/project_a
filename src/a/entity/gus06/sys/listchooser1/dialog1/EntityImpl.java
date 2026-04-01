package a.entity.gus06.sys.listchooser1.dialog1;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntityImpl implements Entity, ActionListener, T, V {

	public String creationDate() {return "20160504";}
	

	private Service okCancel;
	private Service selector;
	private Service buildLabelTitle;
	
	private JComponent selectorComp;
	
	private JPanel panel;
	private JLabel labelTitle;
	private Object width;
	private Object height;
	


	public EntityImpl() throws Exception
	{
		okCancel = Outside.service(this,"gus06.swing.dialog.blocked1.okcancel");
		selector = Outside.service(this,"*gus06.sys.listchooser1.gui.selector1");
		buildLabelTitle = Outside.service(this,"gus06.swing.label.build.titlelabel1");
		
		selectorComp = (JComponent) selector.i();
		labelTitle = (JLabel) buildLabelTitle.i();
		
		panel = new JPanel(new BorderLayout());
		panel.add(labelTitle,BorderLayout.NORTH);
		panel.add(selectorComp,BorderLayout.CENTER);
		
		selector.addActionListener(this);
	}
	
	
	
	public Object t(Object obj) throws Exception
	{
		selector.p(obj);
		
		okCancel.v("width",width);
		okCancel.v("height",height);
		boolean result = okCancel.f(panel);
		
		return result ? selector.g() : null;
	}
	
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("title"))
		{setTitle((String) obj);return;}
		
		if(key.equals("width"))
		{width = obj;return;}
		
		if(key.equals("height"))
		{height = obj;return;}
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	private void setTitle(String title)
	{labelTitle.setText(title);}



	public void actionPerformed(ActionEvent e)
	{
		String s = e.getActionCommand();
		if(s.equals("typed_enter()")) {typed_enter();return;}
		if(s.equals("cleared()")) {cleared();return;}
	}
	
	
	private void typed_enter()
	{
		try{okCancel.v("do","ok");}
		catch(Exception e)
		{Outside.err(this,"typed_enter()",e);}
	}

	private void cleared()
	{
		try{okCancel.v("do","cancel");}
		catch(Exception e)
		{Outside.err(this,"cleared()",e);}
	}
}