package a.entity.gus06.sys.fileeditorpersister1.textcomp.load;

import a.framework.*;
import javax.swing.text.JTextComponent;
import java.util.Map;
import java.awt.Rectangle;
import javax.swing.text.BadLocationException;
import javax.swing.SwingUtilities;

public class EntityImpl implements Entity, V {

	public String creationDate() {return "20200428";}

	public static final String KEY_CARET = "caret";


	private Service engine;
	private Service buildThread;

	public EntityImpl() throws Exception
	{
		engine = Outside.service(this,"gus06.sys.fileeditorpersister1.textcomp");
		buildThread = Outside.service(this,"gus.x.thread.wrap1");
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		int pos = findPosition(key,comp);
		comp.setCaretPosition(pos);
		
		if(pos>0) scrollAsSoonAsPossible(comp,pos);
	}
	
	
	private int findPosition(String key, JTextComponent comp) throws Exception
	{
		Map map = (Map) engine.r(key);
		if(map==null) return 0;
		
		String caretInfo = get(map,KEY_CARET);
		if(caretInfo==null) return 0;
		
		int caret = Integer.parseInt(caretInfo);
		int len = comp.getText().length();
		return Math.min(len,caret);
	}
	
	
	private String get(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return (String) map.get(key);
	}
	
	
	
	
	private void scrollAsSoonAsPossible(final JTextComponent comp, final int pos) throws Exception
	{
		Runnable r = new Runnable() {
			public void run()
			{
				try
				{
					Rectangle viewRect = null;
					while(viewRect==null)
					{
						viewRect = comp.modelToView(pos);
						
						try{Thread.sleep(10);}
						catch(InterruptedException e) {}
					}
					
					scrollToRect(comp,viewRect);
				}
				catch(BadLocationException e){}
			}
		};
		
		buildThread.p(r);
	}
	
	private void scrollToRect(final JTextComponent comp, final Rectangle viewRect)
	{
		SwingUtilities.invokeLater(new Runnable() {
			public void run()
			{
				try
				{
					int len = comp.getText().length();
					Rectangle lastRect = comp.modelToView(len);
					
					comp.scrollRectToVisible(lastRect);
					comp.scrollRectToVisible(viewRect);
				}
				catch(BadLocationException e){}
			}
		});
	}
}