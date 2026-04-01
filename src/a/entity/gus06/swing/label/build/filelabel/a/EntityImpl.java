package a.entity.gus06.swing.label.build.filelabel.a;

import a.framework.*;
import java.io.File;
import javax.swing.JLabel;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191028";}

	private Service labelCust;
	private Service toFile;
	private Service initCopy;
	
	public EntityImpl() throws Exception
	{
		labelCust = Outside.service(this,"gus06.swing.label.cust3.filedisplay");
		toFile = Outside.service(this,"gus06.find.file");
		initCopy = Outside.service(this,"gus06.swing.comp.cust3.filecopy");
	}
	
	public Object t(Object obj) throws Exception
	{
		File file = (File) toFile.t(obj);
		JLabel label = new JLabel();
		labelCust.p(new Object[]{label,file});
		initCopy.p(new Object[]{label,file});
		
		return label;
	}
}