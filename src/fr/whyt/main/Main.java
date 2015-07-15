/**
 * 
 */
package fr.whyt.main;

import java.nio.file.Paths;

import fr.whyt.utils.CSVUtils;
import fr.whyt.utils.csv.CSV;



/**
 * @author Jeremy
 *
 */
public class Main {
	
	public static void main(String[] args) throws ClassNotFoundException {
		
//		HashMap<Resource, Integer> m = new HashMap<Resource, Integer>();
//		m.put(new Resource("Thatch"), 15);
//		m.put(new Resource("Wood"), 40);
//		m.put(new Resource("Metal"), 45);
//		
//		String str = "Un:1,Deux:2,Trois:3";
//		HashMap<String, Integer> map = (HashMap<String, Integer>) Arrays.asList(str.split(",")).stream().map(s -> s.split(":")).collect(Collectors.toMap(e -> e[0], e -> Integer.parseInt(e[1])));
//		System.out.println(str);
//		System.out.println(map);
//		
//		Compound c = new Compound(
//				new Architecture("Wood", 15),
//				new Component("Wall", 15, m),
//				5);
//		
//		System.out.println(c);
		

//		HashMap<Header, CSVData> row = new HashMap<>();
//		Header h = new Header("Column0");
//		Header h2 = new Header("Zeta");
//		Header h3 = new Header("Alpha");
//		Header h4 = new Header("Beta");
//		
//		row.put(h, new CSVData("java.lang.Integer", h, 1, 0));
//		row.put(h2, new CSVData("java.lang.String", h2, "hello", 1));
//		row.put(h3, new CSVData("java.lang.Boolean", h3, true, 2));
//		row.put(h4, new CSVData("java.lang.Integer", h4, 6, 3));
//		
//		System.out.println(row);
//		
//		ArrayList<CSVData> data = (ArrayList<CSVData>) row.entrySet().stream()
//			.sorted( (e1, e2) -> e1.getKey().column < e2.getKey().column ? -1 : 1 )
//			.map( e -> e.getValue() )
//			.collect(Collectors.toList());
//		
//		System.out.println(data);
//		
//		System.out.println(Integer.MAX_VALUE);
		
		CSV csv = CSVUtils.deserialize(Paths.get("resources/data.csv"));
		System.out.println(csv);
		
	}

}
