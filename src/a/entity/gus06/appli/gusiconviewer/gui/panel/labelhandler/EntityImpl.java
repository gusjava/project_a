package a.entity.gus06.appli.gusiconviewer.gui.panel.labelhandler;

import java.awt.Event;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Action;
import javax.swing.JLabel;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;
import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160501";}

	
	public static final KeyStroke KS_COPYFILE = KeyStroke.getKeyStroke(KeyEvent.VK_C,Event.CTRL_MASK);
	public static final KeyStroke KS_COPYID = KeyStroke.getKeyStroke(KeyEvent.VK_SPACE,0);
	public static final KeyStroke KS_DELETE = KeyStroke.getKeyStroke(KeyEvent.VK_DELETE,0);
	
	public static final KeyStroke KS_LAUNCH = KeyStroke.getKeyStroke(KeyEvent.VK_F1,0);
	public static final KeyStroke KS_RENAME = KeyStroke.getKeyStroke(KeyEvent.VK_F2,0);
	public static final KeyStroke KS_DUPLICATE = KeyStroke.getKeyStroke(KeyEvent.VK_F3,0);
	public static final KeyStroke KS_REFRESH = KeyStroke.getKeyStroke(KeyEvent.VK_F5,0);
	
	
	
	private Service menuBuilder;
	private Service executeOnClick;
	private Service onRollover;
	private Service fileName0;
	

	public EntityImpl() throws Exception
	{
		menuBuilder = Outside.service(this,"gus06.swing.popupmenu.builder1");
		executeOnClick = Outside.service(this,"gus06.swing.label.cust3.ondoubleclick.execute");
		onRollover = Outside.service(this,"gus06.swing.comp.cust.onrollover.bgwhite");
		fileName0 = Outside.service(this,"gus06.file.getname0");
	}



	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		JLabel label = (JLabel) o[0];
		File file = (File) o[1];
		
		
		onRollover.p(label);
		executeOnClick.p(new Object[]{label,new E(){
			public void e() throws Exception
			{}
		}});
		
		String name0 = (String) fileName0.t(file);
		JLabel labelTitle = new JLabel("   "+name0);
		labelTitle.setFont(labelTitle.getFont().deriveFont(Font.BOLD));
		
		
		JPopupMenu menu = (JPopupMenu) menuBuilder.t(label);
		
		menu.add(labelTitle);
		/*
		menu.addSeparator();
		menu.add(refreshAction);
		menu.add(launchAction);
		menu.add(renameAction);
		menu.add(duplicateAction);
		menu.add(deleteAction);
		menu.addSeparator();
		menu.add(idToClipboard);
		menu.add(fileToClipboard);
		*/
	}
}
