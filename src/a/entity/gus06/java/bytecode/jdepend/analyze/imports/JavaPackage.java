package a.entity.gus06.java.bytecode.jdepend.analyze.imports;

import java.util.*;

public class JavaPackage {

	private String name;
	private int volatility;
	private Set classes;

	public JavaPackage(String name) {
		this(name, 1);
	}

	public JavaPackage(String name, int volatility) {
		this.name = name;
		setVolatility(volatility);
		classes = new HashSet();
	}

	public String getName() {
		return name;
	}

	public int getVolatility() {
		return volatility;
	}
	
	public void setVolatility(int v) {
		volatility = v;
	}

	public void addClass(JavaClass clazz) {
		classes.add(clazz);
	}

	public Collection getClasses() {
		return classes;
	}

	public int getClassCount() {
		return classes.size();
	}

	public boolean equals(Object other) {
		if (other instanceof JavaPackage) {
			JavaPackage otherPackage = (JavaPackage) other;
			return otherPackage.getName().equals(getName());
		}
		return false;
	}

	public int hashCode() {
		return getName().hashCode();
	}
}
