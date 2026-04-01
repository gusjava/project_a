package a.entity.gus06.sys.git1.filehistory.gui.commitdetail.diff;

import a.framework.*;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.util.List;
import java.util.Map;
import javax.swing.JTextArea;
import javax.swing.JComponent;
import javax.swing.text.JTextComponent;
import java.util.Objects;

public class EntityImpl implements Entity, I, P, V {

	public String creationDate() {return "20201130";}


	private Service areaHolder;
	private Service onKey;
	private Service highlight;

	private JPanel panel;
	private JTextComponent areaComp;
	
	private List commits;
	
	private Map commit1;
	private Map commit0;
	
	private String src1;
	private String src0;
	
	private P selectionHandler;
	

	public EntityImpl() throws Exception
	{
		areaHolder = Outside.service(this,"*gus06.sys.textcomparator1.gui1");
		onKey = Outside.service(this,"gus06.swing.comp.cust3.on.keypressed.with.execute");
		highlight = Outside.service(this,"gus06.sys.git1.filehistory.handle.highlight.src");
		
		areaComp = (JTextComponent) areaHolder.r("comp");
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) areaHolder.i(),BorderLayout.CENTER);
		
		E exeSelect = (E) this::select;
		onKey.p(new Object[]{areaComp, "ctrl R", exeSelect});
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void p(Object obj) throws Exception
	{
		if(obj==null) {reset();return;}
		
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		commits = (List) o[0];
		commit1 = (Map) o[1];
		commit0 = previousFor(commit1);
		
		if(hasSrcN(commit0) && hasSrcN(commit1))
		{
			src0 = srcNFor(commit0);
			src1 = srcNFor(commit1);
		}
		else
		{
			src0 = srcFor(commit0);
			src1 = srcFor(commit1);
		}
		
		areaHolder.p(new Object[]{src0,src1});
		highlight.p(new Object[]{areaComp, commit1});
	}
	
	
	
	
	private void reset() throws Exception
	{
		commits = null;
		
		commit1 = null;
		commit0 = null;
		
		src1 = null;
		src0 = null;
		
		areaHolder.p(null);
	}
	
	
	
	private Map commitAt(int index)
	{
		if(index>=commits.size()) return null;
		return (Map) commits.get(index);
	}
	
	private Map previousFor(Map m)
	{
		if(m==null) return null;
		return m.containsKey("previous") ? (Map) m.get("previous") : null;
	}
	
	private String srcFor(Map m) throws Exception
	{
		if(m==null) return null;
		return (String) m.get("src");
	}
	
	private String srcNFor(Map m) throws Exception
	{
		if(m==null) return null;
		return (String) m.get("srcN");
	}
	
	private boolean hasSrcN(Map m) throws Exception
	{
		if(m==null) return false;
		return m.containsKey("srcN");
	}
	
	
	private void select() throws Exception
	{
		if(selectionHandler==null) return;
		
		String selection = areaComp.getSelectedText();
		if(selection!=null && !selection.equals("")) selectionHandler.p(selection);
	}

	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("selectionHandler"))
		{selectionHandler = (P) obj;return;}
		
		throw new Exception("Unknown key: "+key);
	}
}