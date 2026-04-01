package a.entity.gus06.input.choose.dialog.multi;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250907";}

	private Service findStringArray;
	private Service findList;
	
	public EntityImpl() throws Exception
	{
		findStringArray = Outside.service(this,"gus06.find.stringarray");
		findList = Outside.service(this,"gus06.find.list");
	}
	
	public Object t(Object obj) throws Exception
	{
		if(!(obj instanceof Object[]))
			throw new Exception("Invalid data type: "+obj.getClass().getName());
		
		Object[] o = (Object[]) obj;
		if(o.length==2) 
		{
			String message = (String) o[0];
			String title = "CHOOSER";
			String[] values = (String[]) findStringArray.t(o[1]);
		
			return MultiSelectDialog.showMultiSelectDialog(null,message,title,values,null);
		}
		if(o.length==3) 
		{
			String message = (String) o[0];
			String title = (String) o[1];
			String[] values = (String[]) findStringArray.t(o[2]);
		
			return MultiSelectDialog.showMultiSelectDialog(null,message,title,values,null);
		}
		if(o.length==4) 
		{
			String message = (String) o[0];
			String title = (String) o[1];
			String[] values = (String[]) findStringArray.t(o[2]);
			List selected = (List) findList.t(o[3]);
			
			return MultiSelectDialog.showMultiSelectDialog(null,message,title,values,selected);
		}
		throw new Exception("Wrong data number: "+o.length);
	}
}
