/**
 * 
 */
package fr.whyt.core.config;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import fr.whyt.core.Architecture;
import fr.whyt.core.config.Config.ArchitectureModel;
import fr.whyt.core.config.Config.Doors;
import fr.whyt.core.config.Config.FloorHeight;
import fr.whyt.core.config.Config.Position;
import fr.whyt.core.config.Config.Stairs;


/**
 * @author Jeremy
 *
 */
public class ConfigParser {
	
	private static String config_regex = "((?<key>\\w+)=(?<value>\\w+))?\\p{Blank}*(?<comment>#.*)?";
	
	public static Config deserialize(Path file) {		
		try(BufferedReader br = Files.newBufferedReader(file)) {
			Pattern p = Pattern.compile(config_regex);
			Config config = new Config(file);
			for(String line; (line = br.readLine()) != null;) {
				Matcher m = p.matcher(line);
				if( m.matches() ) {
					String key = m.group("key");
					String value = m.group("value");
					switch(key) {
						case "width": config.setWidth(Integer.parseInt(value)); break;
						case "depth": config.setDepth(Integer.parseInt(value)); break;
						case "architecture_model": config.setArchitectureModel(ArchitectureModel.valueOf(value)); break;
						case "main_architectures": config.setMainArchitectures((HashMap<Integer, Architecture>) Arrays.asList(value.split(",")).stream().map(s -> s.split(":")).collect(Collectors.toMap(e -> Integer.parseInt(e[0]), e -> Architecture.get(value)))); break;
						case "floors_height": config.setFloorsHeight((HashMap<Integer, FloorHeight>) Arrays.asList(value.split(",")).stream().map(s -> s.split(":")).collect(Collectors.toMap(e -> Integer.parseInt(e[0]), e -> FloorHeight.valueOf(value)))); break;
						case "floors": config.setFloors(Integer.parseInt(value)); break;
						case "main_door": config.setMainDoor(Doors.valueOf(value)); break;
						case "indoor_stairs": config.setIndoorStairs(Stairs.valueOf(value)); break;
						case "fences": config.setFences(Integer.parseInt(value)); break;
						case "fences_architectures": config.setFencesArchitectures((HashMap<Integer, Architecture>) Arrays.asList(value.split(",")).stream().map(s -> s.split(":")).collect(Collectors.toMap(e -> Integer.parseInt(e[0]), e -> Architecture.get(value)))); break;
						case "fences_doors": config.setFencesDoors((HashMap<Integer, Doors>) Arrays.asList(value.split(",")).stream().map(s -> s.split(":")).collect(Collectors.toMap(e -> Integer.parseInt(e[0]), e -> Doors.valueOf(value)))); break;
						case "fences_height": config.setFencesHeight((HashMap<Integer, Integer>) Arrays.asList(value.split(",")).stream().map(s -> s.split(":")).collect(Collectors.toMap(e -> Integer.parseInt(e[0]), e -> Integer.parseInt(value)))); break;
						case "security_sas": config.setSecuritySas(Boolean.getBoolean(value)); break;
						case "security_sas_door": config.setSecuritySasDoor(Doors.valueOf(value)); break;
						case "windows_per_side": config.setWindowsPerSide((HashMap<Integer, Integer>) Arrays.asList(value.split(",")).stream().map(s -> s.split(":")).collect(Collectors.toMap(e -> Integer.parseInt(e[0]), e -> Integer.parseInt(value)))); break;
						case "windows_position": config.setWindowsPosition((HashMap<Integer, Position>) Arrays.asList(value.split(",")).stream().map(s -> s.split(":")).collect(Collectors.toMap(e -> Integer.parseInt(e[0]), e -> Position.valueOf(value)))); break;
						case "indoor_roof_access": config.setIndoorRoofAccess(Boolean.getBoolean(value)); break;
						case "indoor_roof_access_stair": config.setIndoorRoofAccessStair(Stairs.valueOf(value)); break;
						case "outdoor_roof_acces": config.setOutdoorRoofAcces(Boolean.getBoolean(value)); break;
						default: break;
					}
				}
			}
			
			return config;
		} catch ( IOException e1 ) {
			System.err.println("Unable to open this file !");
			return null;
		}
	}
	
	public static void serialize(Config config) {
		Path path = checkName(config.getFile());
		try {
			File file = path.toFile();
			file.createNewFile();
			
			StringBuilder sb = new StringBuilder();
			sb	.append("depth")					.append("=").append(config.getDepth())							.append("\n")
				.append("width")					.append("=").append(config.getWidth())							.append("\n")
				.append("architecture_model")		.append("=").append(config.getArchitectureModel())				.append("\n")
				.append("main_architectures")		.append("=").append(config.getMainArchitectures())				.append("\n")
				.append("floors_height")			.append("=").append(config.getFloorsHeight())					.append("\n")
				.append("floors")					.append("=").append(config.getFloors())							.append("\n")
				.append("main_door")				.append("=").append(config.getMainDoor().name())				.append("\n")
				.append("indoor_stairs")			.append("=").append(config.getIndoorStairs().name())			.append("\n")
				.append("fences")					.append("=").append(config.getFences())							.append("\n")
				.append("fences_architectures")		.append("=").append(config.getFencesArchitectures())			.append("\n")
				.append("fences_doors")				.append("=").append(config.getFencesDoors())					.append("\n")
				.append("fences_height")			.append("=").append(config.getFencesHeight())					.append("\n")
				.append("security_sas")				.append("=").append(config.isSecuritySas())						.append("\n")
				.append("security_sas_door")		.append("=").append(config.getSecuritySasDoor().name())			.append("\n")
				.append("windows_per_side")			.append("=").append(config.getWindowsPerSide())					.append("\n")
				.append("windows_position")			.append("=").append(config.getWindowsPosition())				.append("\n")
				.append("indoor_roof_access")		.append("=").append(config.isIndoorRoofAccess())				.append("\n")
				.append("indoor_roof_access_stair")	.append("=").append(config.getIndoorRoofAccessStair().name())	.append("\n")
				.append("outdoor_roof_access")		.append("=").append(config.isOutdoorRoofAcces())				.append("\n");
			
			BufferedWriter bw = Files.newBufferedWriter(path);
			bw.write(sb.toString());
			bw.close();
		} catch ( IOException e ) {
			System.err.println("File " + path + " has not been created !");
		}
	}
	
	private static Path checkName(Path file) {
		Path path = Paths.get("resources/default_config");
		
		if( Files.exists(file, LinkOption.NOFOLLOW_LINKS) ) {
			System.err.println("This file already exists ! Do you want to erase ? y/n");
			Scanner sc = new Scanner(System.in);
			char response = sc.nextLine().charAt(0);
			switch( response ) {
				case 'y': path = file; break;
				case 'n':
					System.out.println("Save anyway ? y/n");
					response = sc.nextLine().charAt(0);
					switch( response ) {
						case 'y': 
							System.out.println("Enter new name :");
							path = checkName(Paths.get(sc.nextLine())); 
							break;
						case 'n': break;
						default: System.err.println("I did not understand your answer... :("); break;
					}
					break;
				default: System.err.println("I did not understand your answer... :("); break;
			}
			sc.close();
		} else {
			path = file;
		}
		
		return path;
	}
	
}
