package a.entity.gus06.file.read.image.from.ods;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20210609";}


	private Service fileToImage;

	public EntityImpl() throws Exception
	{fileToImage = Outside.service(this,"gus06.file.opendocument.ods.filetoimage");}
	
	public Object t(Object obj) throws Exception
	{return fileToImage.t(obj);}
}