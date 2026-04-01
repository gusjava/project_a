package a.entity.gus06.jdbc.gui.selector.path.dialog1;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntityImpl implements Entity, T, V {

	public String creationDate() {return "20161114";}
	
	public static final String WIDTH = "600";
	public static final String HEIGHT = "600";
	

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
		selector = Outside.service(this,"*gus06.jdbc.gui.selector.path");
		buildLabelTitle = Outside.service(this,"gus06.swing.label.build.titlelabel1");
		
		selectorComp = (JComponent) selector.i();
		labelTitle = (JLabel) buildLabelTitle.i();
		
		panel = new JPanel(new BorderLayout());
		panel.add(labelTitle,BorderLayout.NORTH);
		panel.add(selectorComp,BorderLayout.CENTER);
	}
	
	
	
	public Object t(Object obj) throws Exception
	{
		selector.p(obj);
		
		okCancel.v("width",width!=null?width:WIDTH);
		okCancel.v("height",height!=null?height:HEIGHT);
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
}
