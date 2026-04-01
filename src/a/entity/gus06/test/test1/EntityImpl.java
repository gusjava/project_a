package a.entity.gus06.test.test1;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, R, G {

	public String creationDate() {return "20180308";}


	private Map map;

	public EntityImpl() throws Exception
	{
		map = new HashMap();
		
		put("writeImageAll",	Outside.service(this,"gus06.file.write.image.all"));
		put("writeGif",		Outside.service(this,"gus06.file.write.image.gif"));
		put("writeJpgIOq",	Outside.service(this,"gus06.file.write.image.imageio.jpg.quality"));
		put("writeJpgIOq1",	Outside.service(this,"gus06.file.write.image.imageio.jpg.quality01"));
		put("writeJpgIOq2",	Outside.service(this,"gus06.file.write.image.imageio.jpg.quality02"));
		put("writeJpgIOq3",	Outside.service(this,"gus06.file.write.image.imageio.jpg.quality03"));
		put("writeJpgIOq4",	Outside.service(this,"gus06.file.write.image.imageio.jpg.quality04"));
		put("writeJpgIOq5",	Outside.service(this,"gus06.file.write.image.imageio.jpg.quality05"));
		put("writeJpgIOq6",	Outside.service(this,"gus06.file.write.image.imageio.jpg.quality06"));
		put("writeJpgIOq7",	Outside.service(this,"gus06.file.write.image.imageio.jpg.quality07"));
		put("writeJpgIOq8",	Outside.service(this,"gus06.file.write.image.imageio.jpg.quality08"));
		put("writeJpgIOq9",	Outside.service(this,"gus06.file.write.image.imageio.jpg.quality09"));
		put("writeJpgIOq10",	Outside.service(this,"gus06.file.write.image.imageio.jpg.quality10"));
		put("writeBmpJAI",	Outside.service(this,"gus06.file.write.image.jai.bmp"));
		put("writeJpgJAI",	Outside.service(this,"gus06.file.write.image.jai.jpg"));
		put("writeJpg",		Outside.service(this,"gus06.file.write.image.jpg"));
		put("writePng",		Outside.service(this,"gus06.file.write.image.png"));
		put("writeSmall1Jpg",	Outside.service(this,"gus06.file.write.image.small1.jpg"));
		put("writeTiff",	Outside.service(this,"gus06.file.write.image.tiff"));
		put("charanalyzer1",	Outside.service(this,"gus06.sys.charanalyzer1.gui.gui1"));
		put("hsbinfos1",	Outside.service(this,"gus06.awt.bufferedimage.color.hsbinfos1"));
		put("hsbinfos2",	Outside.service(this,"gus06.awt.bufferedimage.color.hsbinfos2"));
		put("hsbinfos3",	Outside.service(this,"gus06.awt.bufferedimage.color.hsbinfos3"));
		put("histo10_hue",	Outside.service(this,"gus06.awt.bufferedimage.color.histogram10.hue"));
		put("histo100_hs",	Outside.service(this,"gus06.awt.bufferedimage.color.histogram100.hs"));
		put("histo100_hue",	Outside.service(this,"gus06.awt.bufferedimage.color.histogram100.hue"));
		put("histo1000_hsb",	Outside.service(this,"gus06.awt.bufferedimage.color.histogram1000.hsb"));
		put("histo30_h_s_b",	Outside.service(this,"gus06.awt.bufferedimage.color.histogram30.h_s_b"));
		put("entitysrc_info",	Outside.service(this,"gus06.java.srccode.extract.entity.infomap1"));
		put("entityfile_info",	Outside.service(this,"gus06.java.srcfile.extract.entity.infomap1"));
		put("printstreamcomp",	Outside.service(this,"gus06.swing.textpane.holder.printstreamcomp"));
		put("dirviewerarea",	Outside.service(this,"gus06.dir.viewer.area.listing.rel1"));
		put("clustering_hac",	Outside.service(this,"gus06.sys.clustering1.engine.hac"));
		put("clustering_kmeans",Outside.service(this,"gus06.sys.clustering1.engine.kmeans"));
		put("clustering_kmedoids",Outside.service(this,"gus06.sys.clustering1.engine.kmedoids"));
		put("allocine_search",	Outside.service(this,"gus06.web.allocine.api.search"));
		put("allocine_movie",	Outside.service(this,"gus06.web.allocine.api.movie"));
		put("youtube_getinfo",	Outside.service(this,"gus06.web.youtube.api.getinfo"));
		put("youtube_thumbnail",Outside.service(this,"gus06.web.youtube.api.map1.thumbnail"));
	}
	
	
	private void put(String name, Service s) throws Exception
	{
		if(map.containsKey(name)) throw new Exception("Operator name already registered: "+name);
		map.put(name,s);
	}
	
	public Object g() throws Exception
	{return new HashMap(map);}
	
	public Object r(String key) throws Exception
	{
		if(map.containsKey(key)) return map.get(key);
		throw new Exception("Unknown key: "+key);
	}
}
