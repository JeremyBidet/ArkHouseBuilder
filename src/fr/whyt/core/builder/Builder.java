/**
 * 
 */
package fr.whyt.core.builder;

import java.nio.file.Paths;
import java.util.HashMap;

import fr.whyt.core.config.Config;
import fr.whyt.core.config.Config.ArchitectureModel;
import fr.whyt.core.config.ConfigParser;
import fr.whyt.core.data.Compound;
import fr.whyt.core.data.Data;
import fr.whyt.core.data.DataParser;


/**
 * @author Jeremy
 *
 */
public class Builder {
	
	private final Data data;
	private Config config;
	
	private Builder(Data data, Config config) {
		this.data = data;
		this.config = config;
	}
	
	public Result build(ResultType... result_types) {
		// TODO build the config according to rules and get results.
		// - Rules are defining how many compound (Architecture+Component) it needs to match the given config.
		// e.g. assume width=5, depth=5, floors=1, floors_height=2 (with simple example)
		// 		it needs at least 5x5=25 foundations, 5x5x2=50 walls, 5x5=25 ceiling/roof
		//		in this case, there are no doors, no windows, no sas, no fences, etc...
		// - Results are about
		// 			- quantities needed (total and by component, architecture, floors, fences, etc...),
		// 			- harvesting time (average, according to effectiveness and resources type),
		//			- and so much others helpfully...
		//
		/*
			width=2
			depth=2
			architecture_model=full 										# full|hybrid
			main_architectures={0=metal, 1=stone, 2=wood, 3=thatch} 		# key=floor# ; value=thatch|wood|stone|metal|etc...
			floors_height={0=double, 1=double, 2=simple, 3=simple}			# key=floor# ; value=simple|double
			floors=3														# only floors ground floor not included
			main_door=normal							 					# normal|dinausor|behemoth
			indoor_stairs=ramp								 				# ladder|ramp
			fences=3									 					# outdoor fences quantity to expand or secure house
			fences_architectures={1=metal, 2=stone, 3=wood}			 		# key=fence# ; value=thatch|wood|stone|metal|etc...
			fences_doors={1=normal, 2=dinausor, 3=dinausor}					# key=fence# ; value=normal|dinausor|behemoth
			fences_height={1=4, 2=4, 3=4}									# key=fence# ; value=fence height
			security_sas=true												# true|false
			security_sas_door=behemoth; 									# security_sas=true only : normal|dinausor|behemoth
			windows_per_side={0=0, 1=2, 2=2, 3=3} 							# key=floor# ; value=window_quantity
			windows_position={0=null, 1=centered, 2=bordered, 3=centered}	# key=floor# ; value=centered|bordered
			indoor_roof_access=true											# true|false
			indoor_roof_access_stair=ladder									# roof_acces=true only : ladder|ramp
			outdoor_roof_acces=true											# true|false, ladder only
		*/
		HashMap<Compound, Integer> house = new HashMap<>();
		
		int width = config.getWidth();
		int depth = config.getDepth();
		ArchitectureModel am = config.getArchitectureModel();
		int floors = config.getFloors();
		config.getMainArchitectures();
		
		
		
		Result result = new Result();
		
		for(ResultType rt : result_types) {
			//Result.result_builder.get(rt).apply(null);
		}
		
		return result;
	}
	
	public void saveData() {
		this.data.save();
	}
	
	public void saveConfig() {
		this.config.save();
	}
	
	public void save() {
		this.saveData();
		this.saveConfig();
	}
	
	/* STATIC */
	public static Builder init() {
		return new Builder(DataParser.deserialize(), ConfigParser.deserialize(Paths.get("resources/config")));
	}
	
}
