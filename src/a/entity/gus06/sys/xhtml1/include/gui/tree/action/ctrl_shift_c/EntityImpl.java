package a.entity.gus06.sys.xhtml1.include.gui.tree.action.ctrl_shift_c;

import a.framework.*;
import javax.swing.JTree;
import java.util.Map;
import java.io.File;
import javax.swing.KeyStroke;
import javax.swing.Action;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20221011";}

	public static final String DISPLAY = "CLIPBOARD_file#Copy file (ctrl shift c)";
	public static final String KEY = "ctrl shift c";
	public static final String ID = "copyFile";
	
	public static final String KEY_FILE = "file";
	
	
	private Service buildAction;
	private Service stringToKeyStroke;
	private Service toClipboard;
	

	public EntityImpl() throws Exception
	{
		buildAction = Outside.service(this,"gus06.swing.action.builder1");
		stringToKeyStroke = Outside.service(this,"gus06.convert.stringtokeystroke");
		toClipboard = Outside.service(this,"gus06.clipboard.access");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		JTree tree = (JTree) obj;
		Holder holder = new Holder(tree);
		
		Action action = (Action) buildAction.t(new Object[]{DISPLAY,holder});
		KeyStroke keyStroke = (KeyStroke) stringToKeyStroke.t(KEY);
		
		tree.getActionMap().put(ID,action);
		tree.getInputMap().put(keyStroke,ID);
		
		return action;
	}
	
	
	private class Holder implements E
	{
		private JTree tree;
		public Holder(JTree tree)
		{this.tree = tree;}
		
		public void e() throws Exception
		{perform(tree);}
	}
	
	private void perform(JTree tree) throws Exception
	{
		Map m = (Map) tree.getLastSelectedPathComponent();
		if(m==null) return;
		
		File file = (File) m.get(KEY_FILE);
		toClipboard.p(file);
	}
}