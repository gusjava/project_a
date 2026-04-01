package a.entity.gus06.sys.filemanagement1.tool.preview1.find.data;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201020";}


	private Service findImage;
	private Service imageToData;

	public EntityImpl() throws Exception
	{
		findImage = Outside.service(this,"gus06.sys.filemanagement1.tool.preview1.find.image");
		imageToData = Outside.service(this,"gus06.convert.bufferedimagetobytearray.jpg");
	}
	
	public Object t(Object obj) throws Exception
	{
		Object image = findImage.t(obj);
		if(image==null) return null;
		return imageToData.t(image);
	}
}
