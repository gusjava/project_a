package a.entity.gus06.sys.expression1.apply.op._build;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160612";}


	private Service play1;
	private Service carto1;
	private Service points1;
	private Service drawing1;
	private Service mapEditor;
	private Service fileEditor;
	private Service listChooser;
	private Service gridChooser;
	private Service tsp_tools1;
	private Service scheduling1;
	private Service console1;
	private Service test1;
	private Service jsf1;
	private Service appMem0a;
	private Service appMem0b;
	private Service jvmMem0a;
	private Service jvmMem0b;
	private Service jvmMem1;
	private Service txtComparator1;
	private Service treeComparator1;
	private Service treeComparator2;
	private Service dateEditor1;
	private Service multiView1;
	private Service webApi;
	private Service treeBuilder1;
	private Service listTabViewer1;
	private Service buttonHoldExecute;
	private Service btnChooser;
	private Service timeTable;
	private Service countdownLabel;
	private Service countdownTriggerMin;
	private Service countdownTriggerS;
	private Service tesseract1;
	private Service jasper1;
	private Service dataFirstNameF;
	private Service dataFirstNameM;
	private Service dataLastName;
	private Service diffHandler;
	private Service screenViewer;
	private Service screenViewer2;
	private Service screenViewer2m;
	private Service authornameBest;
	private Service authornameMapping;
	private Service pdfGeneration2;
	private Service buildForm1;
	private Service mysqlTools;
	private Service physicsUnits;
	private Service physicsConsts;
	private Service physicsPrefix;
	private Service langFrenchNounDerive;
	private Service guiMapEurope;
	private Service guiMapDepartment;
	private Service guiMapGeneric1;
	private Service departementsFr;
	private Service mp3Button;
	private Service wavButton;
	private Service shiftPanel;
	private Service redmineWeb;
	private Service learning1;
	private Service chessGame;
	private Service gameOfLife;
	private Service jwpce1;
	private Service appConfig1;
	private Service helpViewer2;
	private Service helpViewer3;
	private Service iconProvider1;
	private Service kanjiVG1;
	private Service kanjiVG2;
	private Service kanjiVG3;
	private Service taskManager1;
	private Service googleTTS_mp3;
	private Service googleTTS_text;
	private Service vueJsParser1;
	private Service entityDep;
	private Service jarBuilder1;
	private Service frameworkSrc;
	private Service frameworkDocEn;
	private Service aiPromptGus06_v1;
	
	
	public EntityImpl() throws Exception
	{
		play1 = Outside.service(this,"factory#gus06.sys.play1.manager");
		carto1 = Outside.service(this,"factory#gus06.sys.carto1.panelholder");
		points1 = Outside.service(this,"factory#gus06.swing.panel.screen.points");
		drawing1 = Outside.service(this,"factory#gus06.sys.drawingpanel1.screen");
		mapEditor = Outside.service(this,"factory#gus06.sys.mapediting1.gui.main");
		fileEditor = Outside.service(this,"factory#gus06.file.editor.main");
		listChooser = Outside.service(this,"factory#gus06.sys.listchooser1.gui.main");
		gridChooser = Outside.service(this,"factory#gus06.sys.listchooser2.gui.main");
		tsp_tools1 = Outside.service(this,"factory#gus06.sys.ai1.genetics.tsp.tools");
		scheduling1 = Outside.service(this,"factory#gus06.sys.scheduling1.manager");
		console1 = Outside.service(this,"factory#gus06.swing.textpane.holder.printstreamcomp");
		test1 = Outside.service(this,"factory#gus06.test.test1");
		jsf1 = Outside.service(this,"factory#gus06.sys.analyzejsf1.analyzer");
		appMem0a = Outside.service(this,"factory#gus06.watching.app.memory.gui0a");
		appMem0b = Outside.service(this,"factory#gus06.watching.app.memory.gui0b");
		jvmMem0a = Outside.service(this,"factory#gus06.watching.jvm.memory.gui0a");
		jvmMem0b = Outside.service(this,"factory#gus06.watching.jvm.memory.gui0b");
		jvmMem1 = Outside.service(this,"factory#gus06.watching.jvm.memory.gui1");
		txtComparator1 = Outside.service(this,"factory#gus06.sys.textcomparator1.gui1");
		treeComparator1 = Outside.service(this,"factory#gus06.sys.treecomparator1.gui1");
		treeComparator2 = Outside.service(this,"factory#gus06.sys.treecomparator1.gui2");
		dateEditor1 = Outside.service(this,"factory#gus06.sys.datepicker1.fr.gui.editor1");
		multiView1 = Outside.service(this,"factory#gus06.swing.panel.multiview1");
		webApi = Outside.service(this,"gus06.web.api.provider");
		treeBuilder1 = Outside.service(this,"gus06.swing.tree.build.builder1");
		listTabViewer1 = Outside.service(this,"factory#gus06.sys.listtabviewer1.gui.main");
		buttonHoldExecute = Outside.service(this,"factory#gus06.swing.button.hold.execute");
		btnChooser = Outside.service(this,"gus06.list.string.chooser.dialog");
		timeTable = Outside.service(this,"factory#gus06.appli.entityhistory.gui.timetable");
		countdownLabel = Outside.service(this,"factory#gus06.sys.countdown.gui.label");
		countdownTriggerMin = Outside.service(this,"gus06.sys.countdown.popup.trigger.min");
		countdownTriggerS = Outside.service(this,"gus06.sys.countdown.popup.trigger.s");
		tesseract1 = Outside.service(this,"gus06.sys.tesseract1.engine");
		jasper1 = Outside.service(this,"gus06.file.pdf.jasper.generate1");
		dataFirstNameF = Outside.service(this,"factory#gus06.data.name.firstname.f");
		dataFirstNameM = Outside.service(this,"factory#gus06.data.name.firstname.m");
		dataLastName = Outside.service(this,"factory#gus06.data.name.lastname");
		diffHandler = Outside.service(this,"factory#gus06.data.diff.handler");
		screenViewer = Outside.service(this,"factory#gus06.sys.screen1.viewer.printscreen");
		screenViewer2 = Outside.service(this,"factory#gus06.sys.screen1.viewer.printscreen2");
		screenViewer2m = Outside.service(this,"factory#gus06.sys.screen1.viewer.printscreen2.withmouse");
		authornameBest = Outside.service(this,"gus06.data.authorname.list.findbest");
		authornameMapping = Outside.service(this,"gus06.data.authorname.list.mapping");
		pdfGeneration2 = Outside.service(this,"gus06.sys.pdfgeneration2.engine");
		buildForm1 = Outside.service(this,"factory#gus06.sys.form1.gui.maingui");
		mysqlTools = Outside.service(this,"factory#gus06.sys.mysqltools1.dump");
		physicsUnits = Outside.service(this,"gus06.data.physics.units");
		physicsConsts = Outside.service(this,"gus06.data.physics.constants");
		physicsPrefix = Outside.service(this,"gus06.data.physics.units.prefix");
		langFrenchNounDerive = Outside.service(this,"gus06.data.langage.french.noun.derive");
		guiMapEurope = Outside.service(this,"factory#gus06.sys.geomap1.europe.gui1");
		guiMapDepartment = Outside.service(this,"factory#gus06.sys.geomap1.fr.departments.gui1");
		guiMapGeneric1 = Outside.service(this,"factory#gus06.sys.geomap1.generic.gui1");
		departementsFr = Outside.service(this,"gus06.data.geo.france.department");
		mp3Button = Outside.service(this,"factory#gus06.io.inputstream.mp3.play.button");
		wavButton = Outside.service(this,"factory#gus06.io.inputstream.wav.play.button");
		shiftPanel = Outside.service(this,"factory#gus06.swing.panel.shiftpanel");
		redmineWeb = Outside.service(this,"gus06.web.redmine.urltotext");
		learning1 = Outside.service(this,"gus06.sys.learning1.engine");
		chessGame = Outside.service(this,"factory#gus06.sys.chessgame1.gui.game");
		gameOfLife = Outside.service(this,"factory#gus06.appli.gameoflife.gui.maingui");
		jwpce1 = Outside.service(this,"gus06.sys.jwpce1.engine");
		appConfig1 = Outside.service(this,"factory#gus06.sys.appconfig1.init");
		helpViewer2 = Outside.service(this,"factory#gus06.sys.helpviewer2.gui.maingui");
		helpViewer3 = Outside.service(this,"factory#gus06.sys.helpviewer3.gui.maingui");
		iconProvider1 = Outside.service(this,"gus06.y.iconprovider1.provider");
		kanjiVG1 = Outside.service(this,"factory#gus06.y.kanjivg1.anim.gui1");
		kanjiVG2 = Outside.service(this,"factory#gus06.y.kanjivg1.anim.gui2");
		kanjiVG3 = Outside.service(this,"factory#gus06.y.kanjivg1.anim.gui3");
		taskManager1 = Outside.service(this,"gus06.sys.taskmanager1.engine");
		googleTTS_mp3 = Outside.service(this,"gus06.web.google.tts.fetch.tomp3");
		googleTTS_text = Outside.service(this,"gus06.web.google.tts.fetch.translation");
		vueJsParser1 = Outside.service(this,"gus06.sys.vuejsparser1.engine");
		entityDep = Outside.service(this,"gus06.y.entitydev1.engine");
		jarBuilder1 = Outside.service(this,"factory#gus06.file.jar.builder1");
		frameworkSrc = Outside.service(this,"gus06.framework.sources");
		frameworkDocEn = Outside.service(this,"gus06.framework.doc.en");
		aiPromptGus06_v1 = Outside.service(this,"gus06.ai.prompt.gus06_v1");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof String) return build((String) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private Object build(String key) throws Exception
	{
		if(key.equals("mapeditor")) return mapEditor.g();
		if(key.equals("fileeditor")) return fileEditor.g();
		if(key.equals("dateeditor1")) return dateEditor1.g();
		if(key.equals("listchooser")) return listChooser.g();
		if(key.equals("gridchooser")) return gridChooser.g();
		if(key.equals("appMem0a")) return appMem0a.g();
		if(key.equals("appMem0b")) return appMem0b.g();
		if(key.equals("jvmMem0a")) return jvmMem0a.g();
		if(key.equals("jvmMem0b")) return jvmMem0b.g();
		if(key.equals("jvmMem1")) return jvmMem1.g();
		if(key.equals("txtComparator1")) return txtComparator1.g();
		if(key.equals("treeComparator1")) return treeComparator1.g();
		if(key.equals("treeComparator2")) return treeComparator2.g();
		if(key.equals("countdownLabel")) return countdownLabel.g();
		if(key.equals("countdownTriggerMin")) return countdownTriggerMin;
		if(key.equals("countdownTriggerS")) return countdownTriggerS;
		if(key.equals("dataFirstNameF")) return dataFirstNameF.g();
		if(key.equals("dataFirstNameM")) return dataFirstNameM.g();
		if(key.equals("dataLastName")) return dataLastName.g();
		if(key.equals("diffHandler")) return diffHandler.g();
		if(key.equals("multiView1")) return multiView1.g();
		if(key.equals("treeBuilder1")) return treeBuilder1;
		if(key.equals("listTabViewer1")) return listTabViewer1.g();
		if(key.equals("buttonHoldExecute")) return buttonHoldExecute.g();
		if(key.equals("btnChooser")) return btnChooser;
		if(key.equals("timeTable")) return timeTable.g();
		if(key.equals("play1")) return play1.g();
		if(key.equals("carto1")) return carto1.g();
		if(key.equals("points1")) return points1.g();
		if(key.equals("drawing1")) return drawing1.g();
		if(key.equals("tsp_tools1")) return tsp_tools1.g();
		if(key.equals("scheduling1")) return scheduling1.g();
		if(key.equals("console1")) return console1.g();
		if(key.equals("test1")) return test1.g();
		if(key.equals("jsf1")) return jsf1.g();
		if(key.equals("webApi")) return webApi;
		if(key.equals("tesseract1")) return tesseract1;
		if(key.equals("jasper1")) return jasper1;
		if(key.equals("screenViewer")) return screenViewer;
		if(key.equals("screenViewer2")) return screenViewer2;
		if(key.equals("screenViewer2m")) return screenViewer2m;
		if(key.equals("authornameBest")) return authornameBest;
		if(key.equals("authornameMapping")) return authornameMapping;
		if(key.equals("pdfGeneration2")) return pdfGeneration2;
		if(key.equals("form1")) return buildForm1.g();
		if(key.equals("mysqlTools")) return mysqlTools.g();
		if(key.equals("physicsUnits")) return physicsUnits;
		if(key.equals("physicsConsts")) return physicsConsts;
		if(key.equals("physicsPrefix")) return physicsPrefix;
		if(key.equals("langFrenchNounDerive")) return langFrenchNounDerive;
		if(key.equals("guiMapEurope")) return guiMapEurope.g();
		if(key.equals("guiMapDepartment")) return guiMapDepartment.g();
		if(key.equals("guiMapGeneric1")) return guiMapGeneric1.g();
		if(key.equals("departementsFr")) return departementsFr.g();
		if(key.equals("mp3Button")) return mp3Button.g();
		if(key.equals("wavButton")) return wavButton.g();
		if(key.equals("shiftPanel")) return shiftPanel.g();
		if(key.equals("redmineWeb")) return redmineWeb;
		if(key.equals("learning1")) return learning1;
		if(key.equals("chessGame")) return chessGame.g();
		if(key.equals("gameOfLife")) return gameOfLife.g();
		if(key.equals("jwpce1")) return jwpce1;
		if(key.equals("appConfig1")) return appConfig1.g();
		if(key.equals("helpViewer2")) return helpViewer2.g();
		if(key.equals("helpViewer3")) return helpViewer3.g();
		if(key.equals("iconProvider1")) return iconProvider1;
		if(key.equals("kanjiVG1")) return kanjiVG1.g();
		if(key.equals("kanjiVG2")) return kanjiVG2.g();
		if(key.equals("kanjiVG3")) return kanjiVG3.g();
		if(key.equals("taskManager1")) return taskManager1;
		if(key.equals("googleTTS_mp3")) return googleTTS_mp3;
		if(key.equals("googleTTS_text")) return googleTTS_text;
		if(key.equals("vueJsParser1")) return vueJsParser1;
		if(key.equals("entityDep")) return entityDep;
		if(key.equals("jarBuilder1")) return jarBuilder1.g();
		if(key.equals("frameworkSrc")) return frameworkSrc;
		if(key.equals("frameworkDocEn")) return frameworkDocEn;
		if(key.equals("aiPromptGus06_v1")) return aiPromptGus06_v1;
		
		if(key.equals("keys")) return new String[]{
			
			"mapeditor",
			"fileeditor",
			"dateeditor1",
			"listchooser",
			"gridchooser",
			"appMem0a",
			"appMem0b",
			"jvmMem0a",
			"jvmMem0b",
			"jvmMem1",
			"txtComparator1",
			"treeComparator1",
			"treeComparator2",
			"countdownLabel",
			"countdownTriggerMin",
			"countdownTriggerS",
			"dataFirstNameF",
			"dataFirstNameM",
			"dataLastName",
			"diffHandler",
			"multiView1",
			"treeBuilder1",
			"listTabViewer1",
			"buttonHoldExecute",
			"btnChooser",
			"play1",
			"carto1",
			"points1",
			"drawing1",
			"tsp_tools1",
			"scheduling1",
			"console1",
			"test1",
			"jsf1",
			"webApi",
			"timeTable",
			"tesseract1",
			"jasper1",
			"screenViewer",
			"screenViewer2",
			"screenViewer2m",
			"authornameBest",
			"authornameMapping",
			"pdfGeneration2",
			"form1",
			"mysqlTools",
			"physicsUnits",
			"physicsConsts",
			"physicsPrefix",
			"langFrenchNounDerive",
			"guiMapEurope",
			"guiMapDepartment",
			"guiMapGeneric1",
			"departementsFr",
			"mp3Button",
			"wavButton",
			"shiftPanel",
			"redmineWeb",
			"learning1",
			"chessGame",
			"gameOfLife",
			"jwpce1",
			"appConfig1",
			"helpViewer2",
			"helpViewer3",
			"iconProvider1",
			"kanjiVG1",
			"kanjiVG2",
			"kanjiVG3",
			"taskManager1",
			"googleTTS_mp3",
			"googleTTS_text",
			"vueJsParser1",
			"entityDep",
			"jarBuilder1",
			"frameworkSrc",
			"frameworkDocEn",
			"aiPromptGus06_v1"
		};
		
		throw new Exception("Unknown key: "+key);
	}
}