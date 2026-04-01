package a.entity.gus06.sys.git1.filehistory.gui.commitdetail.src;

import a.framework.*;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.util.List;
import java.util.Map;
import javax.swing.JTextArea;
import javax.swing.JComponent;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, I, P, V {

	public String creationDate() {return "20201130";}


	private Service areaHolder;
	private Service findCaretLineIndex;
	private Service setCaretAtLine;
	private Service onKey;
	private Service highlight;

	private JPanel panel;
	private JTextComponent areaComp;
	
	private List commits;
	private Map commit;
	private String src;
	
	private P selectionHandler;
	
	

	public EntityImpl() throws Exception
	{
		areaHolder = Outside.service(this,"*gus06.data.viewer.string.textarea.editor1");
		findCaretLineIndex = Outside.service(this,"gus06.swing.textcomp.caret.find.lineindex");
		setCaretAtLine = Outside.service(this,"gus06.swing.textcomp.caret.linestart");
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
		commit = (Map) o[1];
		
		String srcKey = commit.containsKey("srcN") ? "srcN" : "src";
		src = (String) commit.get(srcKey);
		
		Integer index = (Integer) findCaretLineIndex.t(areaComp);
		areaHolder.p(src);
		if(index!=null) setCaretAtLine.p(new Object[]{areaComp,index+1});
		
		highlight.p(new Object[]{areaComp, commit});
	}
	
	
	private void reset() throws Exception
	{
		commits = null;
		commit = null;
		src = null;
		areaHolder.p(null);
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