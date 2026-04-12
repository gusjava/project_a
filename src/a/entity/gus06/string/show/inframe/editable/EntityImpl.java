package a.entity.gus06.string.show.inframe.editable;

import a.framework.*;
import javax.swing.JFrame;
import java.io.File;

public class EntityImpl implements Entity, V {

	public String creationDate() {return "20231109";}


	private Service show;
	private Service onKey;
	private Service newEditor;
	private Service dragframe;
	private Service shiftUndecorated;
	private Service custDisplay;


	public EntityImpl() throws Exception
	{
		show = Outside.service(this,"gus06.swing.frame.show");
		onKey = Outside.service(this,"gus06.swing.comp.cust3.on.keypressed.with.execute");
		newEditor = Outside.service(this,"factory#gus06.data.editor.string.textarea.editor1");
		dragframe = Outside.service(this,"gus06.swing.comp.cust.dragframe");
		shiftUndecorated = Outside.service(this,"gus06.swing.frame.undecorated.shift");
		custDisplay = Outside.service(this,"gus06.swing.frame.cust2.display");
	}
	
	
	public void v(String key, Object obj) throws Exception
	{new Holder(key,obj);}
	
	
	
	private Object comp(Object text) throws Exception
	{
		Object viewer = newEditor.g();
		((P)viewer).p(text);
		return ((I)viewer).i();
	}
	
	private JFrame performUndecoratedShift(JFrame frame)
	{
		try{return (JFrame) shiftUndecorated.t(frame);}
		catch(Exception e){Outside.err(this,"performUndecoratedShift(JFrame)",e);}
		return frame;
	}
	
	
	
	
	private class Holder
	{
		private Object comp;
		private JFrame frame;
		
		public Holder(String title, Object image) throws Exception
		{
			comp = comp(image);
			dragframe.p(comp);
			
			frame = (JFrame) show.t(comp);
			custDisplay.v(title,frame);
			frame.setAlwaysOnTop(true);
			
			
			onKey.p(new Object[]{comp,"escape",new E(){
				public void e() throws Exception {close();}
			}});
		}
		
		private void close()
		{frame.dispose();}
	}
}