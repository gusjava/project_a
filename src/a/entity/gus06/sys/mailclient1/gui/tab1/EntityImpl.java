package a.entity.gus06.sys.mailclient1.gui.tab1;

import a.framework.*;
import javax.swing.JSplitPane;
import javax.mail.Transport;
import javax.mail.Store;
import javax.swing.JComponent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;

public class EntityImpl implements Entity, I, P, V {

	public String creationDate() {return "20201113";}


	private Service treeHolder;
	private Service guiHolder;
	private Service detailHolder;

	private JSplitPane split1;
	private JSplitPane split2;
	
	private Object holder;
	
	private Store store;
	private Transport transport;
	private File root;
	

	public EntityImpl() throws Exception
	{
		treeHolder = Outside.service(this,"*gus06.sys.mailclient1.gui.tab1.tree");
		guiHolder = Outside.service(this,"*gus06.sys.mailclient1.gui.tab1.messages");
		detailHolder = Outside.service(this,"*gus06.sys.mailclient1.gui.tab1.detail");
		
		split1 = new JSplitPane();
		split1.setLeftComponent((JComponent) treeHolder.i());
		split1.setRightComponent((JComponent) guiHolder.i());
		split1.setDividerSize(3);
		split1.setDividerLocation(150);
		
		split2 = new JSplitPane();
		split2.setOrientation(JSplitPane.VERTICAL_SPLIT);
		split2.setLeftComponent(split1);
		split2.setRightComponent((JComponent) detailHolder.i());
		split2.setDividerSize(3);
		split2.setDividerLocation(300);
		
		treeHolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{folderSelected();}
		});
		guiHolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{messageSelected();}
		});
	}
	
	
	public Object i() throws Exception
	{return split2;}
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("root")) {setRoot((File) obj);return;}
		throw new Exception("Unknown key: "+key);
	}
	
	
	public void p(Object obj) throws Exception
	{
		holder = obj;
		treeHolder.p(obj);
		
		store = holder!=null ? (Store) ((R)holder).r("storeImap") : null;
		transport = holder!=null ? (Transport) ((R)holder).r("transport") : null;
	}
	
	
	
	private void folderSelected()
	{
		try
		{
			Object selected = treeHolder.g();
			guiHolder.p(selected);
		}
		catch(Exception e)
		{Outside.err(this,"folderSelected()",e);}
	}
	
	
	private void messageSelected()
	{
		try
		{
			Object selected = guiHolder.g();
			detailHolder.p(selected);
		}
		catch(Exception e)
		{Outside.err(this,"messageSelected()",e);}
	}
	
	
	private void setRoot(File root) throws Exception
	{
		this.root = root;
		guiHolder.v("root", root);
	}
}