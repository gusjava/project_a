package a.entity.gus06.appli.vindinium.init.appli;

import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity {

	public String creationDate() {return "20170917";}

	
	public static final String NAME = "vindinium";
	public static final String PSEUDO = "vin";
	public static final String ICONID = "APP_vindinium";
	public static final String TITLE = "Vindinium Gus Client";
	public static final String MAINGUI = "gus.game.vindinium.gui.maingui";
	public static final String VERSION = "1.0";


	public EntityImpl() throws Exception
	{
		Map prop = (Map) Outside.resource(this,"app.propmap");
		Map mapping = (Map) Outside.resource(this,"internal.mapping");
		
		
		prop.put("path.app.mapsdir","<path.app.datadir>\\maps");
		
		mapping.put("gus.game.vindinium.data.retrievedata.recorder@defaultdir","dir#path.app.mapsdir");
		mapping.put("gus.game.vindinium.gui.configview.localgame.maps.list@defaultdir","dir#path.app.mapsdir");
		mapping.put("gus.game.vindinium.engine.getinitial.findfile@defaultdir","dir#path.app.mapsdir");
		mapping.put("gus.game.vindinium.map.combobox@defaultdir","dir#path.app.mapsdir");
		
		mapping.put("@gus.icondisplay.file4","gus.icondisplay.file1");
		mapping.put("@app.entity","app.entityprovider3");
		mapping.put("@app.entity.new","app.entitygenerator");
		mapping.put("@app.entity.unique","app.entityprovider1");
		mapping.put("@app.out","app.logout");
		mapping.put("@app.prop.map","app.propmap");
		mapping.put("@app.pers","gus.persistence");
		mapping.put("@app.gui.frame","pool#app.frame");
		mapping.put("@app.gui.icons","app.iconprovider");
		mapping.put("@iconMap","app.iconmap");
		mapping.put("@lingString","app.lingstring");
		mapping.put("@lingSupport","app.supportling");
		mapping.put("@menubar","app.menubar");
		mapping.put("@app.managerid","internal.managerid");
		mapping.put("@appIcon_big","null");
		mapping.put("@defaultdir","dir#pool");
		mapping.put("@defaultdatalist","data.list#pool");
		mapping.put("@defaultdatamap","data.map#pool");
		mapping.put("@defaultdataset","data.set#pool");
		mapping.put("gus.persistence@map","data.map#path.persistentfile");
		mapping.put("@gus.app.action.about.darkpanel1","gus.app.action.about.darkpanel2");
		
		mapping.put("@app.icon","icon#"+ICONID);		
		mapping.put("@app.title","string#"+TITLE);
		
		prop.put("app.author.email","adelale@hotmail.com");
		prop.put("app.author.name","Augustin Delale");
		prop.put("app.titlever","true");
		
		prop.put("app.icon",ICONID);
		prop.put("app.maingui",MAINGUI);
		prop.put("app.pseudo",PSEUDO);
		prop.put("app.name",NAME);
		prop.put("app.title",TITLE);
		prop.put("app.version",VERSION);
		
		prop.put("app.pathcreationlevel","neverask");
		prop.put("path.gus05dir","<path.sys.user.home>\\gus05");
		prop.put("path.app.rootdir","<path.gus05dir>\\"+NAME);
		prop.put("path.persistentfile","<path.app.rootdir>\\persistence.properties");
		
		prop.put("path.logdir","<path.app.rootdir>\\log");
		prop.put("path.pooldir","<path.app.rootdir>\\pool");
		prop.put("path.app.datadir","<path.app.rootdir>\\data");
		
		
		Outside.service(this,"gus06.app.init.mainframe.persister");
		Outside.service(this,"gus06.lookandfeel.uimanager.init1");
		Outside.service(this,"gus06.exception.uncaughtexceptionhandler.init");
	}
}
