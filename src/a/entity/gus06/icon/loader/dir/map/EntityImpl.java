package a.entity.gus06.icon.loader.dir.map;

import a.framework.*;
import java.io.File;
import java.util.Map;
import java.util.HashMap;
import javax.swing.Icon;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220609";}


	private Service loadIcon;
	private Service listingGif;
	private Service listingPng;
	private Service getName0;

	public EntityImpl() throws Exception
	{
		loadIcon = Outside.service(this,"gus.x.file.icon.read.imageio");
		listingGif = Outside.service(this,"gus06.dir.listing0.ext.gif");
		listingPng = Outside.service(this,"gus06.dir.listing0.ext.png");
		getName0 = Outside.service(this,"gus06.file.getname0");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		File dir = (File) obj;
		
		File[] png = (File[]) listingPng.t(dir);
		File[] gif = (File[]) listingGif.t(dir);
		
		Map map = new HashMap();
		for(File file : gif)
		{
			String key = (String) getName0.t(file);
			Icon icon = (Icon) loadIcon.t(file);
			map.put(key,icon);
		}
		for(File file : png)
		{
			String key = (String) getName0.t(file);
			if(!map.containsKey(key))
			{
				Icon icon = (Icon) loadIcon.t(file);
				map.put(key,icon);
			}
		}
		return map;
	}
}