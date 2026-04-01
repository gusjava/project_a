package a.entity.gus06.file.editor.main.filetoname;

import java.io.File;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140723";}


	
	public static final String BAT = "gus.file.editor.ext.bat";
	
	public static final String CBR = "gus.file.editor.ext.cbr";
	public static final String CBZ = "gus.file.editor.ext.cbz";
	public static final String CLASS = "gus.file.editor.ext.class1";
	public static final String CSS = "gus.file.editor.ext.css";
	public static final String CSV = "gus.file.editor.ext.csv";
	
	public static final String DB = "gus.file.editor.ext.db";
	public static final String DLL = "gus.file.editor.ext.dll";
	
	public static final String EML = "gus.file.editor.ext.eml";
	public static final String EPUB = "gus.file.editor.ext.epub";
	public static final String EXE = "gus.file.editor.ext.exe";
	
	public static final String GROOVY = "gus.file.editor.ext.groovy";
	public static final String GUS = "gus.file.editor.ext.gus";
	
	public static final String JAR = "gus.file.editor.ext.jar";
	public static final String JAVA = "gus.file.editor.ext.java";
	public static final String JRXML = "gus.file.editor.ext.jrxml";
	public static final String JS = "gus.file.editor.ext.js";
	public static final String JSON = "gus.file.editor.ext.json";
	
	public static final String LINK_GUS = "gus.file.editor.ext.link_gus";
	public static final String LNK = "gus.file.editor.ext.lnk";
	
	public static final String MOBI = "gus.file.editor.ext.mobi";
	public static final String MP3 = "gus.file.editor.ext.mp3";
	
	public static final String ODS = "gus.file.editor.ext.ods";
	public static final String OTF = "gus.file.editor.ext.otf";
	
	public static final String PDF = "gus.file.editor.ext.pdf";
	public static final String PHP = "gus.file.editor.ext.php";
	public static final String PROPERTIES = "gus.file.editor.ext.properties";
	public static final String PROPERTIES_VAULT = "gus.file.editor.ext.properties_vault";
	public static final String PYTHON = "gus.file.editor.ext.python";
	
	public static final String RAR = "gus.file.editor.ext.rar";
	
	public static final String SQLITE = "gus.file.editor.ext.sqlite";
	public static final String SRT = "gus.file.editor.ext.srt";
	public static final String SVG = "gus.file.editor.ext.svg";
	
	public static final String TOOL = "gus.file.editor.ext.tool";
	public static final String TREE = "gus.file.editor.ext.tree";
	public static final String TTF = "gus.file.editor.ext.ttf";
	public static final String TXT = "gus.file.editor.ext.txt";
	
	public static final String VUE = "gus.file.editor.ext.vue";
	
	public static final String WAV = "gus.file.editor.ext.wav";
	
	public static final String XHTML = "gus.file.editor.ext.xhtml";
	public static final String XML = "gus.file.editor.ext.xml";
	
	public static final String ZIP = "gus.file.editor.ext.zip";
	public static final String ZIP_GUS = "gus.file.editor.ext.zip_gus";
	
	
	
	public static final String DIR = "gus.dir.explorer.simple";
	public static final String IMAGE = "gus.file.editor.ext.image";
	public static final String VIDEO = "gus.file.editor.ext.video";
	public static final String DEFAULT0 = "gus.file.editor.default0";
	public static final String NOTFOUND = "gus.file.editor.notfound";



	private Service isOfTypeText;

	public EntityImpl() throws Exception
	{
		isOfTypeText = Outside.service(this,"gus06.file.filter.mime.isoftype.text.plain");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		if(file==null) return null;
		
		if(!file.exists()) return NOTFOUND;
		if(file.isDirectory()) return DIR;
		
		String name = file.getName().toLowerCase();
		
		if(name.endsWith(".asf")) return VIDEO;
		if(name.endsWith(".avi")) return VIDEO;
		
		if(name.endsWith(".bat")) return BAT;
		if(name.endsWith(".bmp")) return IMAGE;
		
		if(name.endsWith(".cbr")) return CBR;
		if(name.endsWith(".cbz")) return CBZ;
		if(name.endsWith(".class")) return CLASS;
		if(name.endsWith(".css")) return CSS;
		if(name.endsWith(".csv")) return CSV;
		
		if(name.endsWith(".db")) return DB;
		if(name.endsWith(".dll")) return DLL;
		
		if(name.endsWith(".eml")) return EML;
		if(name.endsWith(".epub")) return EPUB;
		if(name.endsWith(".exe")) return EXE;
		
		if(name.endsWith(".f4v")) return VIDEO;
		if(name.endsWith(".flv")) return VIDEO;
		
		if(name.endsWith(".gif")) return IMAGE;
		if(name.endsWith(".gradle")) return TXT;
		if(name.endsWith(".groovy")) return GROOVY;
		if(name.endsWith(".gsp")) return TXT;
		if(name.endsWith(".gus")) return GUS;
		
		if(name.endsWith(".hevc")) return VIDEO;
		if(name.endsWith(".htm")) return XHTML;
		if(name.endsWith(".html")) return XHTML;
		
		if(name.endsWith(".ico")) return IMAGE;
		if(name.endsWith(".ini")) return TXT;
		
		if(name.endsWith(".jar")) return JAR;
		if(name.endsWith(".java")) return JAVA;
		if(name.endsWith(".jpeg")) return IMAGE;
		if(name.endsWith(".jpg")) return IMAGE;
		if(name.endsWith(".jrxml")) return JRXML;
		if(name.endsWith(".js")) return JS;
		if(name.endsWith(".json")) return JSON;
		if(name.endsWith(".jsx")) return TXT;
		
		if(name.endsWith(".less")) return CSS;
		if(name.endsWith(".lnk")) return LNK;
		if(name.endsWith(".link_gus")) return LINK_GUS;
		if(name.endsWith(".log")) return TXT;
		
		if(name.endsWith(".m2ts")) return VIDEO;
		if(name.endsWith(".m2v")) return VIDEO;
		if(name.endsWith(".m4v")) return VIDEO;
		if(name.endsWith(".md")) return TXT;
		if(name.endsWith(".mjpeg")) return VIDEO;
		if(name.endsWith(".mkv")) return VIDEO;
		if(name.endsWith(".mobi")) return MOBI;
		if(name.endsWith(".mov")) return VIDEO;
		if(name.endsWith(".m3u")) return TXT;
		if(name.endsWith(".mp3")) return MP3;
		if(name.endsWith(".mp4")) return VIDEO;
		if(name.endsWith(".mepg")) return VIDEO;
		if(name.endsWith(".mpg")) return VIDEO;
		if(name.endsWith(".mts")) return VIDEO;
		if(name.endsWith(".mxf")) return VIDEO;
		
		if(name.endsWith(".ods")) return ODS;
		if(name.endsWith(".ogv")) return VIDEO;
		if(name.endsWith(".odt")) return ZIP;
		if(name.endsWith(".otf")) return OTF;
		
		if(name.endsWith(".pdf")) return PDF;
		if(name.endsWith(".pem")) return TXT;
		if(name.endsWith(".php")) return PHP;
		if(name.endsWith(".phtml")) return TXT;
		if(name.endsWith(".png")) return IMAGE;
		if(name.endsWith(".ppk")) return TXT;
		if(name.endsWith(".properties")) return PROPERTIES;
		if(name.endsWith(".properties_vault")) return PROPERTIES_VAULT;
		if(name.endsWith(".py")) return PYTHON;
		
		if(name.endsWith(".rar")) return RAR;
		if(name.endsWith(".rm")) return VIDEO;
		
		if(name.endsWith(".scss")) return CSS;
		if(name.endsWith(".sh")) return TXT;
		if(name.endsWith(".sql")) return TXT;
		if(name.endsWith(".sqlite")) return SQLITE;
		if(name.endsWith(".srt")) return SRT;
		if(name.endsWith(".svg")) return SVG;
		if(name.endsWith(".swf")) return VIDEO;
		
		if(name.endsWith(".tiff")) return IMAGE;
		if(name.endsWith(".tool")) return TOOL;
		if(name.endsWith(".tree")) return TREE;
		if(name.endsWith(".ts")) return TXT; //typescript
//		if(name.endsWith(".ts")) return VIDEO;//transform stream
		if(name.endsWith(".ttf")) return TTF;
		if(name.endsWith(".twig")) return TXT;
		if(name.endsWith(".txt")) return TXT;
		
		if(name.endsWith(".vob")) return VIDEO;
		if(name.endsWith(".vue")) return VUE;
		
		if(name.endsWith(".wav")) return WAV;
		if(name.endsWith(".webm")) return VIDEO;
		if(name.endsWith(".webp")) return IMAGE;
		if(name.endsWith(".wmv")) return VIDEO;
		if(name.endsWith(".wtv")) return VIDEO;
		
		if(name.endsWith(".xhtml")) return XHTML;
		if(name.endsWith(".xml")) return XML;
		
		if(name.endsWith(".yml")) return TXT;
		
		if(name.endsWith(".zip")) return ZIP;
		if(name.endsWith(".zip_gus")) return ZIP_GUS;
		
		//if(name.endsWith(".chm")) return ZIP;
		
		
		if(isOfTypeText.f(file)) return TXT;
		
		return DEFAULT0;
	}
}