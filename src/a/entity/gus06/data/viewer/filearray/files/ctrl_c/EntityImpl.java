package a.entity.gus06.data.viewer.filearray.files.ctrl_c;

import a.framework.*;
import javax.swing.JList;
import java.util.List;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20221118";}

	private Service onKey;
	private Service clipboardFiles;
	private Service perform;

	public EntityImpl() throws Exception
	{
		onKey = Outside.service(this,"gus06.swing.comp.cust3.on.keypressed.with.execute");
		clipboardFiles = Outside.service(this,"gus.y.clipboard1.files");
		perform = Outside.service(this,"gus06.swing.frame.show.data");
	}
	
	public void p(Object obj) throws Exception
	{
		JList list = (JList) obj;
		Holder holder = new Holder(list);
		onKey.p(new Object[]{list,"ctrl c", holder});
	}
	
	private class Holder implements E
	{
		private JList list;
		public Holder(JList list)
		{this.list = list;}
		
		public void e() throws Exception
		{
			Object[] selection = list.getSelectedValues();
			if(selection!=null && selection.length>0) clipboardFiles.p(selection);
		}
	}
}