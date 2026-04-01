package a.entity.gus06.sys.filemanagement1.gui.detailpanel.dir.summary.toolbar;

import a.framework.*;
import java.util.Map;
import javax.swing.JToolBar;
import javax.swing.Action;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20200402";}

	public static final String DISPLAY_EXPORT_PDF = "ACTION_exportAsPdf#Export as PDF";
	public static final String DISPLAY_EXPORT_TREE = "ACTION_exportAsTree#Export as Tree";
	

	private Service buildToolbar;
	private Service buildAction;
	private Service exportAsPdf;
	private Service exportAsTree;


	private Object engine;
	private Map selected;
	
	private JToolBar bar;
	private Action actionExportPdf;
	private Action actionExportTree;
	
	
	public EntityImpl() throws Exception
	{
		buildToolbar = Outside.service(this,"gus06.swing.toolbar.toolbar1");
		buildAction = Outside.service(this,"gus06.swing.action.builder1");
		exportAsPdf = Outside.service(this,"gus06.sys.filemanagement1.tool.export.dirpreview.aspdf");
		exportAsTree = Outside.service(this,"gus06.sys.filemanagement1.tool.export.dirpreview.astree");
		
		actionExportPdf = (Action) buildAction.t(new Object[]{DISPLAY_EXPORT_PDF,(E) this::exportAsPdf});
		actionExportTree = (Action) buildAction.t(new Object[]{DISPLAY_EXPORT_TREE,(E) this::exportAsTree});
		
		bar = (JToolBar) buildToolbar.i();
		bar.setOrientation(JToolBar.VERTICAL);
		
		bar.add(actionExportPdf);
		bar.add(actionExportTree);
	}
	
	
	public Object i() throws Exception
	{return bar;}
	
	
	public void p(Object obj) throws Exception
	{
		if(obj==null) {reset();return;}
		
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		engine = o[0];
		selected = (Map) o[1];
		
		refresh();
	}
	
	
	
	private void reset()
	{
		try
		{
			engine = null;
			selected = null;
		}
		catch(Exception e)
		{Outside.err(this,"reset()",e);}
	}
	
	
	private void refresh()
	{
		try
		{
			
		}
		catch(Exception e)
		{Outside.err(this,"refresh()",e);}
	}
	
	
	
	private void exportAsPdf()
	{
		try
		{
			exportAsPdf.p(new Object[]{engine,selected});
		}
		catch(Exception e)
		{Outside.err(this,"exportAsPdf()",e);}
	}
	
	
	private void exportAsTree()
	{
		try
		{
			exportAsTree.p(new Object[]{engine,selected});
		}
		catch(Exception e)
		{Outside.err(this,"exportAsTree()",e);}
	}
}