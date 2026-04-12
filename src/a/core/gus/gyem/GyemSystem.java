package a.core.gus.gyem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import a.core.gus.gyem.utils.UtilArgs;
import a.core.gus.gyem.utils.UtilDate;
import a.framework.*;

public class GyemSystem extends GyemConst {

	// PROTECTED
	
	protected static Map main;

	protected static void init(String[] args) throws Exception {
		main = new HashMap();

		put(MAIN_LAUNCH_DATE, new Date());
		put(MAIN_LAUNCH_ARGS, args);
		put(MAIN_LAUNCH_CLASS, GyemMain.class);
		put(MAIN_CORE_NAME, CORE_NAME);
		put(MAIN_CORE_BUILD, CORE_BUILD);
		put(MAIN_CORE_ID, CORE_ID);
		
		Field[] ff = GyemConst.class.getFields();
		for(int i=0;i<ff.length;i++) {
			if(ff[i].getType().equals(Class.class)) {
				initModule((Class) ff[i].get(null));
			}
		}
		Outside.setManager(new GyemManager());
	}
	
	protected static void put(String key, Object value) {
		main.put(key, value);
	}
	
	protected static boolean has(String key) {
		return main.containsKey(key);
	}
	
	protected static Object get(String key) {
		return has(key) ? main.get(key) : null;
	}
	
	protected static Object module(Class c) {
		return get(classToName(c));
	}
	
	protected static E moduleE(Class c) {
		return (E) module(c);
	}
	
	protected static G moduleG(Class c) {
		return (G) module(c);
	}
	
	protected static P moduleP(Class c) {
		return (P) module(c);
	}
	
	protected static F moduleF(Class c) {
		return (F) module(c);
	}
	
	protected static T moduleT(Class c) {
		return (T) module(c);
	}
	
	protected static R moduleR(Class c) {
		return (R) module(c);
	}
	
	protected static I moduleI(Class c) {
		return (I) module(c);
	}
	
	protected static void fatalMain(Exception e) {
		fatal(e, 1, GyemMain.class);
	}
	
	protected static void fatalManagerErr(Exception e) {
		fatal(e, 2, GyemManager.class);
	}
	
	protected static void fatalEDT(Exception e) {
		fatal(e, 3, GyemMain.class);
	}
	
	protected static void log(Object src, String message) throws Exception {
		moduleP(M053_P_LOGGER).p(new Object[] {src, message});
	}
	
	protected static boolean isMainguiDisabled() throws Exception {
		return moduleF(M029_F_PROP_BOOL_DF).f(PROP_APP_MAINGUI_DISABLED);
	}
	
	
	// PRIVATE
	
	private static String classToName(Class c) {
		String n = c.getName();
		return n.substring(MODULECLASS_START.length(), n.length() - MODULECLASS_END.length());
	}
	
	private static void initModule(Class c) throws Exception {
		put(classToName(c), c.getDeclaredConstructor().newInstance());
	}
	
	private static void fatal(Exception ex, int code, Class c) {
		String message = "A fatal error has occured in " + c.getSimpleName();
		
		System.err.println(message);
		ex.printStackTrace();
		
		String abortTime = abortTime();
		String startTime = startTime();
		String argsLine = argsLine();
		
		File dir = new File("fatalerr");
		dir.mkdirs();
		
		String fileName = "fatalerr_" + abortTime + "_" + CORE_NAME.replace(".", "_") + "_" + CORE_BUILD + ".txt";
		File file = new File(dir, fileName);
		
		try(PrintStream p = new PrintStream(file)) {

			p.println("FATAL ERROR REPORT");
			p.println("------------------");
			p.println(message);
			p.println("The application has aborted");
			p.println("------------------");
			p.println("- Abort time: " + abortTime);
			p.println("- Launch time: " + startTime);
			p.println("- Launch args: " + argsLine);
			p.println("- Launch dir: " + System.getProperty("user.dir"));
			p.println("- Java version: " + System.getProperty("java.version"));
			p.println("- Java home: " + System.getProperty("java.home"));
			p.println("- Core name: " + CORE_NAME);
			p.println("- Core build: " + CORE_BUILD);
			p.println("- Exit code: " + code);
			p.println("- Exception type: " + ex.getClass().getName());
			p.println("- Exception message: " + ex.getMessage());
			p.println("------------------");
			ex.printStackTrace(p);
			
			p.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		System.exit(code);
	}
	
	
	private static String abortTime() {
		return UtilDate.yyyymmdd_hhmmss(new Date());
	}
	
	private static String startTime() {
		if(!has(MAIN_LAUNCH_DATE)) return "not found";
		Object obj = get(MAIN_LAUNCH_DATE);
		if(!(obj instanceof Date)) return "Invalid type: "+obj.getClass().getName();
		return UtilDate.yyyymmdd_hhmmss((Date) obj);
	}
	
	private static String argsLine() {
		if(!has(MAIN_LAUNCH_ARGS)) return "not found";
		Object obj = get(MAIN_LAUNCH_ARGS);
		if(!(obj instanceof String[])) return "Invalid type: "+obj.getClass().getName();
		return UtilArgs.toString((String[]) obj);
	}
}
