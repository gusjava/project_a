package a.entity.gus06.appli.gusexplorer.gui.editor.fillbar.git;

import a.framework.*;
import java.io.File;
import javax.swing.text.JTextComponent;
import javax.swing.JComponent;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201203";}
	
	public static final String KEY_GITHISTORY = "editor.file.githistory";
	public static final String DISPLAY = "GIT#Show git history";

	private Service propBoolDF;
	private Service builder;
	private Service showInFrame;
	private Service hasGitFolder;
	
	public EntityImpl() throws Exception
	{
		propBoolDF = Outside.service(this,"propbool_df");
		builder = Outside.service(this,"gus06.swing.action.builder0");
		showInFrame = Outside.service(this,"gus06.sys.git1.filehistory.show.inframe");
		hasGitFolder = Outside.service(this,"gus06.sys.git1.find.gitfolder");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		JComponent comp = (JComponent) o[0];
		File file = (File) o[1];
		
		if(!propBoolDF.f(KEY_GITHISTORY)) return null;
		if(file==null || !file.isFile()) return null;
		if(!hasGitFolder.f(file)) return null;
		
		E execute = new Execute(comp, file);
		return builder.t(new Object[]{DISPLAY,execute});
	}
	
	
	public class Execute implements E
	{
		private JComponent comp;
		private File file;
		
		public Execute(JComponent comp, File file)
		{
			this.comp = comp;
			this.file = file;
		}
		
		public void e() throws Exception
		{
			String selection = findSelection();
			if(selection!=null) showInFrame.p(new Object[]{file, selection});
			else showInFrame.p(file);
		}
		
		private String findSelection()
		{
			if(comp==null) return null;
			if(!(comp instanceof JTextComponent)) return null;
			
			JTextComponent textComp = (JTextComponent) comp;
			String selection = textComp.getSelectedText();
			if(selection==null || selection.equals("")) return null;
			return selection;
		}
	}
}
