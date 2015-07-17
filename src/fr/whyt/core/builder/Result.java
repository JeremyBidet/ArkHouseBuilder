/**
 * 
 */
package fr.whyt.core.builder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Function;

import fr.whyt.core.Compound;


/**
 * @author Jeremy
 *
 */
// TODO class Result design
public class Result {
	
	/*
	 * 
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
windows_per_side={0=0, 1=1, 2=2, 3=3} 							# key=floor# ; value=window_quantity
windows_position={0=null, 1=centered, 2=bordered, 3=centered}	# key=floor# ; value=centered|bordered
indoor_roof_access=true											# true|false
indoor_roof_access_stair=ladder									# roof_acces=true only : ladder|ramp
outdoor_roof_acces=true											# true|false, ladder only	
	 * 
	 */
	
	
	private static final HashMap<ResultType, Function<ArrayList<Compound>, Result>> result_builder = new HashMap<>();
	static {
		Result.result_builder.put(ResultType.THATCH, compounds -> { return null; });
		Result.result_builder.put(ResultType.WOOD, compounds -> { return null; });
		Result.result_builder.put(ResultType.FIBER, compounds -> { return null; });
		Result.result_builder.put(ResultType.STONE, compounds -> { return null; });
		Result.result_builder.put(ResultType.METALINGOT, compounds -> { return null; });
		Result.result_builder.put(ResultType.CEMENTINGPASTE, compounds -> { return null; });
		Result.result_builder.put(ResultType.POLYMER, compounds -> { return null; });
		Result.result_builder.put(ResultType.METAL, compounds -> { return null; });
		Result.result_builder.put(ResultType.GROUNDFLOOR, compounds -> { return null; });
		Result.result_builder.put(ResultType.FLOORS, compounds -> { return null; });
		Result.result_builder.put(ResultType.FENCES, compounds -> { return null; });
		Result.result_builder.put(ResultType.SAS, compounds -> { return null; });
		Result.result_builder.put(ResultType.FUNDATION, compounds -> { return null; });
		Result.result_builder.put(ResultType.CEILING, compounds -> { return null; });
		Result.result_builder.put(ResultType.WALL, compounds -> { return null; });
		Result.result_builder.put(ResultType.FULLDOOR, compounds -> { return null; });
		
		Result.result_builder.put(ResultType.ALL, compounds -> { return null; });
	}
	
	
	public Result() {
		
	}
	
	
	public void append(Result result) {
		
	}
	
}
