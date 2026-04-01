package a.entity.gus06.swing.label.build.from.map;

import a.framework.*;
import javax.swing.JLabel;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191114";}


	private Service custLabel;
	
	public EntityImpl() throws Exception
	{
		custLabel = Outside.service(this,"gus06.swing.label.cust3.filedisplay");
	}
	
	public Object t(Object obj) throws Exception
	{
		Map map = (Map) obj;
		JLabel label = new JLabel();
		custLabel.p(new Object[]{label,map});
		return label;
	}
}
