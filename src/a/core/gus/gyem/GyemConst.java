package a.core.gus.gyem;

public class GyemConst {

	public static final String MAIN_LAUNCH_DATE = "launch.date";
	public static final String MAIN_LAUNCH_ARGS = "launch.args";
	public static final String MAIN_LAUNCH_CLASS = "launch.class";

	public static final String MAIN_CORE_NAME = "core.name";
	public static final String MAIN_CORE_BUILD = "core.build";
	public static final String MAIN_CORE_ID = "core.id";
	
	public static final String CORE_NAME = "gus.gyem";
	public static final String CORE_BUILD = GyemBuild.CORE_BUILD;
	public static final String CORE_ID = CORE_NAME+"@"+CORE_BUILD;
	
	public static final String MODULECLASS_START = "a.core.gus.gyem.";
	public static final String MODULECLASS_END = ".Module";
	
	public static final String ENTITYCLASS_START = "a.entity.";
	public static final String ENTITYCLASS_END = ".EntityImpl";
	
	public static final String CONFIGPATH = "/a/config/";
	
	public static final String LOC_PROP = "prop";
	public static final String LOC_MAPPING = "mapping";
	public static final String LOC_ICON = "icon/";
	
	public static final String PARAM_CONFIG = "config";

	public static final String PROP_APP_TITLE		= "app.title";
	public static final String PROP_APP_SIZE		= "app.size";
	public static final String PROP_APP_ICON		= "app.icon";
	public static final String PROP_APP_MAINGUI		= "app.maingui";
	public static final String PROP_APP_MINIMIZED 	= "app.minimized";
	public static final String PROP_APP_MAINGUI_DISABLED = "app.maingui.disabled";
	public static final String PROP_BEFORE			= "before";
	public static final String PROP_AFTER			= "after";
	
	public static final String DEFAULTPROP_APP_TITLE	= "Application";
	public static final String DEFAULTPROP_APP_SIZE		= "600 400";
	
	public static final String RB_DEFAULT			= "entity";
	
	public static final Class M001_E_LAUNCH = 					a.core.gus.gyem.m001.e.launch.Module.class;
	public static final Class M002_E_CUSTOMIZE =				a.core.gus.gyem.m002.e.customize.Module.class;
	public static final Class M003_G_PROP =						a.core.gus.gyem.m003.g.prop.Module.class;
	public static final Class M004_G_CONFIG_PROP =				a.core.gus.gyem.m004.g.config.prop.Module.class;
	public static final Class M005_G_CONFIG_PROP_PATH =			a.core.gus.gyem.m005.g.config.prop.path.Module.class;
	public static final Class M006_G_CONFIG_ROOT =				a.core.gus.gyem.m006.g.config.root.Module.class;
	public static final Class M007_G_CONFIG_ID =				a.core.gus.gyem.m007.g.config.id.Module.class;
	public static final Class M008_G_PARAM =					a.core.gus.gyem.m008.g.param.Module.class;
	public static final Class M009_G_PARAM_INTERNAL =			a.core.gus.gyem.m009.g.param.internal.Module.class;
	public static final Class M010_G_PARAM_ARGS =				a.core.gus.gyem.m010.g.param.args.Module.class;
	public static final Class M011_T_READ_PROP =				a.core.gus.gyem.m011.t.read.prop.Module.class;
	public static final Class M012_T_READ_INPUTSTREAM =			a.core.gus.gyem.m012.t.read.inputstream.Module.class;
	public static final Class M013_T_MODULE_BUILD =				a.core.gus.gyem.m013.t.module.build.Module.class;
	public static final Class M014_G_SERVICE_BUILD_EMPTY =		a.core.gus.gyem.m014.g.service.build.empty.Module.class;
	public static final Class M015_T_ENTITY_PROVIDE =			a.core.gus.gyem.m015.t.entity.provide.Module.class;
	public static final Class M016_T_ENTITY_UNIQUE =			a.core.gus.gyem.m016.t.entity.unique.Module.class;
	public static final Class M017_T_ENTITY_GENERATE =			a.core.gus.gyem.m017.t.entity.generate.Module.class;
	public static final Class M018_T_ENTITY_FINDCLASS =			a.core.gus.gyem.m018.t.entity.findclass.Module.class;
	public static final Class M019_T_ENTITY_LOADCLASS =			a.core.gus.gyem.m019.t.entity.loadclass.Module.class;
	public static final Class M020_T_ENTITY_NAMETOPATH =		a.core.gus.gyem.m020.t.entity.nametopath.Module.class;
	public static final Class M021_E_MAINFRAME = 				a.core.gus.gyem.m021.e.mainframe.Module.class;
	public static final Class M022_G_MAINFRAME_BUILD = 			a.core.gus.gyem.m022.g.mainframe.build.Module.class;
	public static final Class M023_E_MAINFRAME_EXIT = 			a.core.gus.gyem.m023.e.mainframe.exit.Module.class;
	public static final Class M024_P_MAINFRAME_HANDLE = 		a.core.gus.gyem.m024.p.mainframe.handle.Module.class;
	public static final Class M025_I_MAINFRAME_CONTENT = 		a.core.gus.gyem.m025.i.mainframe.content.Module.class;
	public static final Class M026_I_BUILDGUI_ENTITY = 			a.core.gus.gyem.m026.i.buildgui.entity.Module.class;
	public static final Class M027_I_BUILDGUI_DEFAULTPANEL = 	a.core.gus.gyem.m027.i.buildgui.defaultpanel.Module.class;
	public static final Class M028_T_BUILDGUI_ERRORPANEL = 		a.core.gus.gyem.m028.t.buildgui.errorpanel.Module.class;
	public static final Class M029_F_PROP_BOOL_DF = 			a.core.gus.gyem.m029.f.prop.bool.df.Module.class;
	public static final Class M030_F_PROP_BOOL_DT = 			a.core.gus.gyem.m030.f.prop.bool.dt.Module.class;
	public static final Class M031_P_EXECUTE_SEQUENCE =			a.core.gus.gyem.m031.p.execute.sequence.Module.class;
	public static final Class M032_F_EXECUTE_RULE =				a.core.gus.gyem.m032.f.execute.rule.Module.class;
	public static final Class M033_T_READ_ICON =				a.core.gus.gyem.m033.t.read.icon.Module.class;
	public static final Class M034_T_CONFIG_ICON =				a.core.gus.gyem.m034.t.config.icon.Module.class;
	public static final Class M035_T_CONFIG_ICON_PATH =			a.core.gus.gyem.m035.t.config.icon.path.Module.class;
	public static final Class M036_T_MANAGER_SERVICE =			a.core.gus.gyem.m036.t.manager.service.Module.class;
	public static final Class M037_T_MANAGER_RESOURCE =			a.core.gus.gyem.m037.t.manager.resource.Module.class;
	public static final Class M038_P_MANAGER_ERR =				a.core.gus.gyem.m038.p.manager.err.Module.class;
	public static final Class M039_G_ERR_LIST =					a.core.gus.gyem.m039.g.err.list.Module.class;
	public static final Class M040_T_SERVICE_WRAP = 			a.core.gus.gyem.m040.t.service.wrap.Module.class;
	public static final Class M041_T_SERVICE_CALL_DESCRIPTION =	a.core.gus.gyem.m041.t.service.call.description.Module.class;
	public static final Class M042_T_ENTITY_FINDNAME = 			a.core.gus.gyem.m042.t.entity.findname.Module.class;
	public static final Class M043_T_RESOURCE_FINDRULE = 		a.core.gus.gyem.m043.t.resource.findrule.Module.class;
	public static final Class M044_T_RESOURCE_FINDRULE1 = 		a.core.gus.gyem.m044.t.resource.findrule1.Module.class;
	public static final Class M045_T_RESOURCE_PROVIDE =			a.core.gus.gyem.m045.t.resource.provide.Module.class;
	public static final Class M046_T_RESOURCE_BUILD =			a.core.gus.gyem.m046.t.resource.build.Module.class;
	public static final Class M047_T_RESOURCE_FIND_RB =			a.core.gus.gyem.m047.t.resource.find.rb.Module.class;
	public static final Class M048_G_MAPPING =					a.core.gus.gyem.m048.g.mapping.Module.class;
	public static final Class M049_G_CONFIG_MAPPING =			a.core.gus.gyem.m049.g.config.mapping.Module.class;
	public static final Class M050_G_CONFIG_MAPPING_PATH =		a.core.gus.gyem.m050.g.config.mapping.path.Module.class;
	public static final Class M051_T_RB_ENTITY =				a.core.gus.gyem.m051.t.rb.entity.Module.class;
	public static final Class M052_T_RB_G =						a.core.gus.gyem.m052.t.rb.g.Module.class;
	public static final Class M053_P_LOGGER =					a.core.gus.gyem.m053.p.logger.Module.class;
	public static final Class M054_E_STARTED =					a.core.gus.gyem.m054.e.started.Module.class;
	public static final Class M055_R_SLIST =					a.core.gus.gyem.m055.r.build.slist.Module.class;
	public static final Class M056_T_RB_SYS =					a.core.gus.gyem.m056.t.rb.sys.Module.class;
	public static final Class M057_T_RB_STRING =				a.core.gus.gyem.m057.t.rb.string.Module.class;
	public static final Class M058_T_RB_CLASS1 =				a.core.gus.gyem.m058.t.rb.class1.Module.class;
	public static final Class M059_T_RB_NEW1 =					a.core.gus.gyem.m059.t.rb.new1.Module.class;
	public static final Class M060_T_RB_PROP =					a.core.gus.gyem.m060.t.rb.prop.Module.class;
	public static final Class M061_T_RB_PROPBOOL =				a.core.gus.gyem.m061.t.rb.propbool.Module.class;
	public static final Class M062_T_RB_PROPINT =				a.core.gus.gyem.m062.t.rb.propint.Module.class;
	public static final Class M063_T_RB_R =						a.core.gus.gyem.m063.t.rb.r.Module.class;
	public static final Class M064_G_PARAM_ROOTDIR =			a.core.gus.gyem.m064.g.param.rootdir.Module.class;
	public static final Class M065_G_PROP_OUTSIDE_FILE =		a.core.gus.gyem.m065.g.prop.outside.file.Module.class;
	public static final Class M066_G_PROP_OUTSIDE_PARAMS =		a.core.gus.gyem.m066.g.prop.outside.params.Module.class;
	public static final Class M067_G_MAPPING_OUTSIDE_FILE =		a.core.gus.gyem.m067.g.mapping.outside.file.Module.class;
	public static final Class M068_G_MAPPING_OUTSIDE_PARAMS =	a.core.gus.gyem.m068.g.mapping.outside.params.Module.class;
}
