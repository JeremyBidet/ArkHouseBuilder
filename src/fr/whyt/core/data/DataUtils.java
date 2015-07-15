/**
 * 
 */
package fr.whyt.core.data;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import fr.whyt.core.Component;
import fr.whyt.utils.CSVUtils;
import fr.whyt.utils.csv.CSV;


/**
 * @author Jeremy
 *
 */
public class DataUtils {
	
	public static final Path path = Paths.get("resources/data.csv");
	
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
