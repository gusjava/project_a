package a.entity.gus06.list.string.chooser.dialog;

import a.framework.*;
import java.util.List;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200327";}


	private Service dialog;
	private Service onKey;
	
	private JPanel panel;
	private String selected;
	
	public EntityImpl() throws Exception
	{
		dialog = Outside.service(this,"gus06.swing.dialog.blocked1.okcancel0");
		onKey = Outside.service(this,"gus06.swing.comp.cust3.on.keypressed.with.execute");
		
		panel = new JPanel(new GridLayout(0,1));
		onKey.p(new Object[]{panel,"escape",(E) this::cancel});
	}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof List) return handleList((List) obj);
		if(obj instanceof Object) return handleList((List) obj);
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private String handleList(List list) throws Exception
	{
		if(list==null || list.isEmpty()) return null;
		
		selected = null;
		
		dialog.v("width",1000);
		dialog.v("height",50*list.size());
		
		panel.removeAll();
		for(int i=0;i<list.size();i++)
		{
			String element = (String) list.get(i);
			panel.add(new JButton1(element));
		}
		boolean result = dialog.f(panel);
		return result ? selected : null;
	}
	
	
	
	private void cancel()
	{
		try{dialog.v("do","cancel");}
		catch(Exception e)
		{Outside.err(this,"cancel()",e);}
	}
	
	private void ok()
	{
		try{dialog.v("do","ok");}
		catch(Exception e)
		{Outside.err(this,"ok()",e);}
	}
	
	
	
	private class JButton1 extends JButton implements ActionListener
	{
		private String element;
		
		public JButton1(String element)
		{
			super(element);
			this.element = element;
			setFont(getFont().deriveFont((float) 16));
			addActionListener(this);
		}
		
		public void actionPerformed(ActionEvent e)
		{
			selected = element;
			ok();
		}
	}
}