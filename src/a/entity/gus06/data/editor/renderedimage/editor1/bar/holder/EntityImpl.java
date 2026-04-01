package a.entity.gus06.data.editor.renderedimage.editor1.bar.holder;

import a.framework.*;
import javax.swing.Action;
import javax.swing.JToolBar;
import java.awt.Color;
import javax.swing.BorderFactory;

public class EntityImpl extends S1 implements Entity, I, P, G, V {

	public String creationDate() {return "20191120";}


	private JToolBar toolbar;
	private ImageAccess access;
	private Object image;
	
	
	public EntityImpl() throws Exception
	{
		access = new ImageAccess();
		
		toolbar = new JToolBar();
		toolbar.setFloatable(false);
		toolbar.setBackground(Color.WHITE);
		toolbar.setBorder(BorderFactory.createEmptyBorder(8,8,8,8));
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("action")) {add((T) obj);return;}
		if(key.equals("sep")) {addSep();return;}
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	private void addSep()
	{toolbar.addSeparator();}
	
	private void add(T t) throws Exception
	{toolbar.add((Action) t.t(access));}
	
	
	public Object g() throws Exception
	{return image;}
	
	
	public void p(Object obj) throws Exception
	{image = obj;}
	
	
	public Object i() throws Exception
	{return toolbar;}
	
	
	
	private class ImageAccess implements P, G
	{
		public void p(Object obj) throws Exception
		{
			image = obj;
			imageEdited();
		}
		
		public Object g() throws Exception
		{return image;}
	}
	
	
	private void imageEdited()
	{send(this,"imageEdited()");}
}
