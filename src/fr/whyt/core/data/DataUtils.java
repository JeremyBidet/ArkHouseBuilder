/**
 * 
 */
package fr.whyt.core.data;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.regex.Pattern;

import fr.whyt.core.Architecture;
import fr.whyt.core.Component;
import fr.whyt.utils.CSVUtils;
import fr.whyt.utils.csv.CSV;


/**
 * @author Jeremy
 *
 */
public class DataUtils {
	
	public static final Path path = Paths.get("resources/data.csv");
	
	private static final String architecture_regex = "\\w+";
	private static final Predicate<String> architecture_predicate = a -> Architecture.has(a);
	private static final Pattern architecture_pattern = Pattern.compile(architecture_regex);
	
	private static final String component_regex = "\\w+";
	private static final Predicate<String> component_predicate = c -> Component.has(c);
	private static final Pattern component_pattern = Pattern.compile(component_regex);
	
	public static ArrayList<Component> deserialize() {
		CSV csv = CSVUtils.deserialize(path);
		ArrayList<Component> components = new ArrayList<Component>(csv.rows());
		// TODO
		
		return components;
	}
	
	public static void serialize(ArrayList<Component> components) {
		// TODO
		
		CSV csv = new CSV(path, null, null);
		CSVUtils.serialize(csv, path);
	}
	
}
