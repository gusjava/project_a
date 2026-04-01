package a.entity.gus06.sys.filemanagement1.build.engine;

import a.framework.*;
import java.io.File;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191107";}
	
	public static final String FAILED_MD5 = "###";
	
	public static final String KEY_PATH = "PATH";


	private Service loadAll;
	private Service dirAccessBuilder;
	private Service findPreview;
	private Service findPreviewG;
	private Service findPreviewData;
	private Service findPreviewFile;
	private Service findProp;
	private Service findPropFile;
	private Service readProp;
	private Service writeProp;
	private Service walkThrough;

	public EntityImpl() throws Exception
	{
		loadAll = Outside.service(this,"gus06.sys.filemanagement1.tool.rootmap.loadall");
		dirAccessBuilder = Outside.service(this,"gus06.dir.accessbuilder.properties");
		findPreview = Outside.service(this,"gus06.sys.filemanagement1.tool.preview.find.image");
		findPreviewG = Outside.service(this,"gus06.sys.filemanagement1.tool.preview.find.image.g");
		findPreviewData = Outside.service(this,"gus06.sys.filemanagement1.tool.preview.find.data");
		findPreviewFile = Outside.service(this,"gus06.sys.filemanagement1.tool.preview.find.file");
		findProp = Outside.service(this,"gus06.sys.filemanagement1.tool.prop.find.map");
		findPropFile = Outside.service(this,"gus06.sys.filemanagement1.tool.prop.find.file");
		readProp = Outside.service(this,"gus06.file.read.properties");
		writeProp = Outside.service(this,"gus06.file.write.properties");
		walkThrough = Outside.service(this,"gus06.dir.walkthrough.files");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		return new Engine((File) obj);
	}
	
	
	private class Engine extends S1 implements R, V
	{
		private File root;
		private Map config;
		
		private File dirRoots;
		private File dirScans;
		private File dirPreviews;
		private File dirProps;
		private File dirInfos;
		private File dirLocal;
		private File dirAllocine;
		
		private File dirGenerated;
		private File dirGeneratedAllocine;
		private File dirGeneratedEbook;
		private File dirGeneratedPdf;
		
		public Engine(File root)
		{
			this.root = root;
			config = new HashMap();
			
			dirRoots = new File(root,"roots");
			dirScans = new File(root,"scans");
			dirPreviews = new File(root,"previews");
			dirProps = new File(root,"props");
			dirInfos = new File(root,"infos");
			dirLocal = new File(root,"local");
			dirAllocine = new File(root,"allocine");
			
			dirGenerated = new File(root,"generated");
			dirGeneratedAllocine = new File(dirGenerated,"allocine");
			dirGeneratedEbook = new File(dirGenerated,"ebook");
			dirGeneratedPdf = new File(dirGenerated,"pdf");
		
			dirRoots.mkdirs();
			dirScans.mkdirs();
			dirPreviews.mkdirs();
			dirProps.mkdirs();
			dirInfos.mkdirs();
			dirLocal.mkdirs();
			dirAllocine.mkdirs();
			
			dirGenerated.mkdirs();
			dirGeneratedAllocine.mkdirs();
			dirGeneratedEbook.mkdirs();
			dirGeneratedPdf.mkdirs();
		}
		
		public void v(String key, Object obj) throws Exception
		{
			if(key.equals("writeProp")) {writeProp((Map) obj);return;}
			if(key.equals("walkProps")) {walkProps((P) obj);return;}
			if(key.equals("walkPreviews")) {walkPreviews((P) obj);return;}
			
			if(key.equals("scanCompleted")) {scanCompleted();return;}
			if(key.equals("rootChanged")) {rootChanged();return;}
			if(key.equals("allocineGenerated")) {allocineGenerated();return;}
			if(key.equals("ebookGenerated")) {ebookGenerated();return;}
			if(key.equals("pdfGenerated")) {pdfGenerated();return;}
			
			if(key.startsWith("config:")) {setConfig(key.substring(7),obj);return;}
			
			throw new Exception("Unknown key: "+key);
		}
		
		
		public Object r(String key) throws Exception
		{
			if(key.startsWith("preview:")) return previewFor(key.substring(8));
			if(key.startsWith("previewData:")) return previewDataFor(key.substring(12));
			if(key.startsWith("previewFile:")) return previewFileFor(key.substring(12));
			
			if(key.startsWith("preview_g:")) return previewGFor(key.substring(10));
			
			if(key.startsWith("prop:")) return propFor(key.substring(5));
			if(key.startsWith("propFile:")) return propFileFor(key.substring(9));
			
			if(key.startsWith("info:")) return infoFor(key.substring(5));
			if(key.startsWith("infoFile:")) return infoFileFor(key.substring(9));
			
			if(key.startsWith("prop1:")) return prop1For(key.substring(6));
			if(key.startsWith("config:")) return configFor(key.substring(7));
			if(key.startsWith("mapRoot:")) return mapRootFor(key.substring(8));
			if(key.startsWith("pathRoot:")) return pathRootFor(key.substring(9));
			
			if(key.equals("root")) return root;
			if(key.equals("config")) return config;
			
			if(key.equals("dirRoots")) return dirRoots;
			if(key.equals("dirScans")) return dirScans;
			if(key.equals("dirPreviews")) return dirPreviews;
			if(key.equals("dirProps")) return dirProps;
			if(key.equals("dirInfos")) return dirInfos;
			if(key.equals("dirLocal")) return dirLocal;
			if(key.equals("dirAllocine")) return dirAllocine;
			
			if(key.equals("dirGenerated")) return dirGenerated;
			if(key.equals("dirGenerated_allocine")) return dirGeneratedAllocine;
			if(key.equals("dirGenerated_ebook")) return dirGeneratedEbook;
			if(key.equals("dirGenerated_pdf")) return dirGeneratedPdf;
			
			if(key.equals("mapRoots")) return loadAll.t(dirRoots);
			if(key.equals("accessRoots")) return dirAccessBuilder.t(dirRoots);
			
			if(key.equals("keys")) return new String[]{
				"root",
				"config",
				
				"dirRoots",
				"dirScans",
				"dirPreviews",
				"dirProps",
				"dirInfos",
				"dirLocal",
				"dirAllocine",
				
				"dirGenerated",
				"dirGenerated_allocine",
				"dirGenerated_ebook",
				"dirGenerated_pdf",
				
				"mapRoots",
				"accessRoots"};
			
			throw new Exception("Unknown key: "+key);
		}
		
		
		
		private Object previewFor(String md5) throws Exception
		{return findPreview.t(new Object[]{dirPreviews,md5});}
		
		private Object previewDataFor(String md5) throws Exception
		{return findPreviewData.t(new Object[]{dirPreviews,md5});}
		
		private Object previewFileFor(String md5) throws Exception
		{return findPreviewFile.t(new Object[]{dirPreviews,md5});}
		
		
		private Object previewGFor(String md5) throws Exception
		{return findPreviewG.t(new Object[]{dirPreviews,md5});}
		
		
		private Map propFor(String md5) throws Exception
		{return (Map) findProp.t(new Object[]{dirProps,md5});}
		
		private File propFileFor(String md5) throws Exception
		{return (File) findPropFile.t(new Object[]{dirProps,md5});}
		
		
		
		private Map infoFor(String md5) throws Exception
		{return (Map) findProp.t(new Object[]{dirInfos,md5});}
		
		private File infoFileFor(String md5) throws Exception
		{return (File) findPropFile.t(new Object[]{dirInfos,md5});}
		
		
		
		
		private Object configFor(String key) throws Exception
		{return config.containsKey(key) ? config.get(key) : null;}
		
		
		private Map mapRootFor(String rootName) throws Exception
		{
			if(rootName==null) return null;
			File f = new File(dirRoots, rootName+".properties");
			return (Map) readProp.t(f);
		}
		
		private String pathRootFor(String rootName) throws Exception
		{
			if(rootName==null) return null;
			Map m = mapRootFor(rootName);
			if(m==null) return null;
			return (String) m.get(KEY_PATH);
		}
		
		
		
		private Map prop1For(String md5) throws Exception
		{
			Map prop = propFor(md5);
			if(prop==null) return null;
			Map info = infoFor(md5);
			if(info==null) return prop;
			prop.putAll(info);
			return prop;
		}
		
		
		
		private void writeProp(Map prop) throws Exception
		{
			if(!prop.containsKey("md5")) throw new Exception("MD5 not found inside prop");
			
			String md5 = (String) prop.get("md5");
			if(md5.equals(FAILED_MD5)) throw new Exception("Failed MD5 found inside prop");
			
			File f = propFileFor(md5);
			if(f==null) throw new Exception("No prop file found for MD5: "+md5);
			
			writeProp.p(new Object[]{f,prop});
		}
		
		
		private void walkProps(P p) throws Exception
		{
			walkThrough.p(new Object[]{dirProps,p});
		}
		
		private void walkPreviews(P p) throws Exception
		{
			walkThrough.p(new Object[]{dirPreviews,p});
		}
		
		
		private void setConfig(String key, Object value)
		{
			if(value==null) config.remove(key);
			else config.put(key,value);
			configChanged();
		}
		
		private void configChanged()
		{send(this,"configChanged()");}
		
		private void scanCompleted()
		{send(this,"scanCompleted()");}
		
		private void rootChanged()
		{send(this,"rootChanged()");}
		
		private void allocineGenerated()
		{send(this,"allocineGenerated()");}
		
		private void ebookGenerated()
		{send(this,"ebookGenerated()");}
		
		private void pdfGenerated()
		{send(this,"pdfGenerated()");}
	}
}