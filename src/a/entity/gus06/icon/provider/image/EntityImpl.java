package a.entity.gus06.icon.provider.image;

import a.framework.*;

public class EntityImpl implements Entity, T, R {

	public String creationDate() {return "20140803";}


	private Service getIcon;
	private Service iconToImage;
	
	public EntityImpl() throws Exception
	{
		getIcon = Outside.service(this,"gus06.icon.provider");
		iconToImage = Outside.service(this,"gus06.convert.icontoimage");
	}
	
	
	public Object t(Object obj) throws Exception
	{return r((String) obj);}
	
	
	
	public Object r(String key) throws Exception
	{return iconToImage.t(getIcon.t(key));}
}
