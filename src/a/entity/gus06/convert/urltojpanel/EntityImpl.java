package a.entity.gus06.convert.urltojpanel;

import a.framework.*;
import javax.swing.JPanel;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190625";}


	private Service screenBuilder;
	private Service urlToImage;
	
	public EntityImpl() throws Exception
	{
		screenBuilder = Outside.service(this,"factory#gus06.swing.panel.screen.image");
		urlToImage = Outside.service(this,"gus06.convert.urltoimage");
	}


	
	public Object t(Object obj) throws Exception
	{
		Object image = urlToImage.t(obj);
		
		Object screen = screenBuilder.g();
		((P) screen).p(image);
		return (JPanel) ((I) screen).i();
	}
}
