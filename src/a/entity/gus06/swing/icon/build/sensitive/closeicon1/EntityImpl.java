package a.entity.gus06.swing.icon.build.sensitive.closeicon1;

import a.framework.*;
import javax.swing.Icon;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20141203";}


	private Service buildSensitive;
	private Icon icon1;


	public EntityImpl() throws Exception
	{
		buildSensitive = Outside.service(this,"gus06.swing.icon.build.sensitive");
		
		Icon icon = (Icon) Outside.resource(this,"icon#UTIL_close");
		Icon icon_r = (Icon) Outside.resource(this,"icon#UTIL_close_r");
		Icon icon_p = (Icon) Outside.resource(this,"icon#UTIL_close_p");
		 
		icon1 = (Icon) buildSensitive.t(new Object[]{icon,icon_r,icon_p});
	}
	
	
	public Object g() throws Exception
	{return icon1;}
}
