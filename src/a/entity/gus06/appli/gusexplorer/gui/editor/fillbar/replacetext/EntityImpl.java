package a.entity.gus06.appli.gusexplorer.gui.editor.fillbar.replacetext;

import a.framework.*;
import javax.swing.text.Element;
import javax.swing.text.JTextComponent;
import javax.swing.text.PlainDocument;
import javax.swing.JTextArea;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20190331";}


	private Service transformText;
	private Service findPainter;
	private Service highlight;
	private Service builder;
	private Service uniqueEntity;


	public EntityImpl() throws Exception
	{
		transformText = Outside.service(this,"gus06.swing.textcomp.perform3.transformtext");
		findPainter = Outside.service(this,"gus06.swing.textcomp.highlight.painter.findatposition");
		highlight = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_h.tool.perform.highlightpainter");
		builder = Outside.service(this,"*gus06.swing.textcomp.cust.action.ctrl_shift_h.tool.perform.chooser.t2.build");
		uniqueEntity = Outside.service(this,"entityunique");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		if(!(o[0] instanceof JTextComponent))
		{
			T t = (T) builder.t(o);
			if(t!=null) t.t(null);
			return;	
		}
		
		JTextComponent comp = (JTextComponent) o[0];
		String key = (String) o[1];
		
		T t = buildTrans(comp,key);
		
		if(hasSelection(comp))
		{
			transformText.p(new Object[]{comp,t});
			return;
		}
		
		Object painter = findPainter.t(comp);
		if(painter!=null)
		{
			highlight.p(new Object[]{comp,painter,t});
			return;
		}
		
		transformText.p(new Object[]{comp,t});
	}
	
	
	
	private T buildTrans(JTextComponent comp, String key) throws Exception
	{
		if(key.startsWith("@")) 
			return buildTransFromEntity(key.substring(1));
		return buildTransFromScript(comp, key);
	}
	
	private T buildTransFromScript(JTextComponent comp, String key) throws Exception
	{
		T t = (T) builder.t(new Object[]{comp,"t:"+key});
		if(t==null) throw new Exception("Invalid script key: "+key);
		return t;
	}
	
	private T buildTransFromEntity(String entityName) throws Exception
	{return (T) uniqueEntity.t(entityName);}

	
	
	private boolean hasSelection(JTextComponent comp)
	{
		String s = comp.getSelectedText();
		return s!=null && !s.equals("");
	}
}
