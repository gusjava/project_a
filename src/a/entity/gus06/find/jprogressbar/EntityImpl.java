package a.entity.gus06.find.jprogressbar;

import a.framework.*;
import java.util.Map;
import javax.swing.JProgressBar;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250713";}

	
	
	public EntityImpl() throws Exception
	{
	}

	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof Integer) return build((Integer) obj);
		if(obj instanceof Map) return build((Map) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private JProgressBar build(Integer max) throws Exception
	{
		JProgressBar bar = new JProgressBar();
		bar.setMaximum(max);
		return bar;
	}
	
	private JProgressBar build(Map map) throws Exception
	{
		JProgressBar bar = new JProgressBar();
//		custLabel.p(new Object[]{bar,map});
		return bar;
	}
}
