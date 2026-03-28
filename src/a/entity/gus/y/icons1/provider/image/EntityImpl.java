package a.entity.gus.y.icons1.provider.image;

import a.framework.*;

public class EntityImpl implements Entity, T, R {
	public String creationDate() {return "20240712";}

	private Service getIcon;
	private Service iconToImage;
	
	public EntityImpl() throws Exception
	{
		getIcon = Outside.service(this,"gus.y.icons1.provider");
		iconToImage = Outside.service(this,"gus.y.convert1.icontoimage");
	}
	
	public Object t(Object obj) throws Exception
	{return r((String) obj);}
	
	public Object r(String key) throws Exception
	{return iconToImage.t(getIcon.t(key));}
}
