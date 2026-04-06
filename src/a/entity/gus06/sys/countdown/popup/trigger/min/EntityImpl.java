package a.entity.gus06.sys.countdown.popup.trigger.min;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20201216";}


	private Service buildLabel;
	private Service support;
	private Service showOnTop;
	private Service input;


	public EntityImpl() throws Exception
	{
		buildLabel = Outside.service(this,"factory#gus06.sys.countdown.gui.label");
		support = Outside.service(this,"gus06.support.watch.e");
		showOnTop = Outside.service(this,"gus06.swing.dialog.build.dialogontop.tabbed");
		input = Outside.service(this,"gus06.input.text.dialog");
	}
	
	
	public void p(Object obj) throws Exception
	{
		if(obj instanceof E) trigger((E) obj);
		else if(obj instanceof Map) trigger((Map) obj);
		else throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private void trigger(E atEnd) throws Exception
	{
		String duration = (String) input.t("Please, enter duration (min)");
		if(duration==null) return;
		
		Object holder = buildLabel.g();
		support.p(new Object[]{holder,atEnd});
		
		((V)holder).v("timeUnit","min");
		((V)holder).v("duration",duration);
		
		showOnTop.p(((I)holder).i());
		((E)holder).e();
	}
	
	private void trigger(Map map) throws Exception
	{
		
	}
}
