package a.entity.gus06.sys.filemanagement1.tool.preview.find.image.g;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250613";}
	
	private Service find;

	public EntityImpl() throws Exception
	{
		find = Outside.service(this,"gus06.sys.filemanagement1.tool.preview.find.image");
	}
	
	public Object t(Object obj) throws Exception
	{return new G1(obj);}
	
	
	private class G1 implements G
	{
		private Object data;
		private Object image;
		
		public G1(Object data) {this.data = data;}
		
		public Object g() throws Exception
		{
			if(image==null) image = find.t(data);
			return image;
		}
	}
}