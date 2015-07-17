/**
 * 
 */
package fr.whyt.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;





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
		
//		CSV csv = CSVUtils.deserialize(Paths.get("resources/data.csv"));
//		System.out.println(csv);
		
		//ArrayList<Compound> compounds = DataParser.deserialize();
		//System.out.println(compounds.stream().map(c -> c.toString()).reduce((s1, s2) -> s1 + '\n' + s2).get());
		
		System.out.println(Test.decompose(323));
		System.out.println(Test.compose(Test.THATCH, Test.WOOD, Test.FENCES, Test.FUNDATION));
	}
	
	public static enum Test {
		THATCH 		(1 << 0),  // 1
		WOOD 		(1 << 1),  // 2
		STONE 		(1 << 2),  // 4
		METAL 		(1 << 3),  // 8
		GROUNDFLOOR (1 << 4),  // 16
		FLOORS		(1 << 5),  // 32
		FENCES		(1 << 6),  // 64
		SAS			(1 << 7),  // 128
		FUNDATION	(1 << 8),  // 256
		CEILING		(1 << 9),  // 512
		WALL		(1 << 10), // 1024
		FULLDOOR	(1 << 11), // 2048
		
		ALL 		((FULLDOOR.result() << 1) - 1); // 4095 = FULLDOOR + WALL + ... + THATCH
		
		private final int result;
		
		Test(final int result) {
			this.result = result;
		}
		
		public int result() {
			return this.result;
		}
		
		public static ArrayList<Test> decompose(int x) {
			ArrayList<Test> x2 = new ArrayList<>();
			List<Test> values = Arrays.asList(Test.values());
			int shift;
			for(int i=0; (shift = 1<<i) <= x; i++) {
				int x_and_shift = x & shift;
				if( (x_and_shift) != 0 ) {
					x2.add(  values.stream().filter(rt -> rt.result == x_and_shift).findFirst().get() );
				}
			}
			return x2;
		}
		
		public static int compose(Test... tests) {
			int result_type = 0;
			for(Test t : tests) {
				result_type |= t.result;
			}
			return result_type;
		}
	};
	
}
