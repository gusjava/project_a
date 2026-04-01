package a.entity.gus06.data.perform.imageop;

import a.framework.*;
import java.util.Map;
import javax.swing.text.JTextComponent;
import java.awt.Image;
import java.awt.image.RenderedImage;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180401";}
	
	public static final String KEY_NAME = "name";


	private Service convolve;
	private Service errorDiffusion;
	private Service gradientMagnitude;
	private Service invertCenterBand;
	private Service invertFirstBand;
	private Service invertLastBand;

	public EntityImpl() throws Exception
	{
		convolve = Outside.service(this,"gus06.awt.bufferedimage.transform.kernel.convolve");
		errorDiffusion = Outside.service(this,"gus06.awt.bufferedimage.transform.kernel.errordiffusion");
		gradientMagnitude = Outside.service(this,"gus06.awt.bufferedimage.transform.kernel.gradientmagnitude");
		invertCenterBand = Outside.service(this,"gus06.awt.bufferedimage.transform.color.invert.centerband");
		invertFirstBand = Outside.service(this,"gus06.awt.bufferedimage.transform.color.invert.firstband");
		invertLastBand = Outside.service(this,"gus06.awt.bufferedimage.transform.color.invert.lastband");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object data = o[0];
		Map map = (Map) o[1];
		
		String name = get(map,KEY_NAME);
		
		if(name.equals("convolve")) 
		{
			return convolve.t(data);
		}
		if(name.equals("errorDiffusion")) 
		{
			return errorDiffusion.t(data);
		}
		if(name.equals("gradientMagnitude")) 
		{
			return gradientMagnitude.t(data);
		}
		if(name.equals("invertCenterBand")) 
		{
			return invertCenterBand.t(data);
		}
		if(name.equals("invertFirstBand")) 
		{
			return invertFirstBand.t(data);
		}
		if(name.equals("invertLastBand")) 
		{
			return invertLastBand.t(data);
		}
		
		throw new Exception("Unsupported operation name: "+name);
	}
	
	
	public String get(Map map, String key) throws Exception
	{
		if(!map.containsKey(key)) throw new Exception("Unknown key: "+key);
		return (String) map.get(key);
	}
}
