package components;

import java.util.WeakHashMap;

import core.*;

public class Z extends Component {
	
	private static WeakHashMap<Entity, Double> values = new WeakHashMap<Entity, Double>();
	
	public static boolean in( Entity entity ) {
		
		return values.containsKey( entity );
	}
	
	public static double of( Entity entity ) {

		return values.get( entity );
	}
	
	public static void of( Entity entity, double value ) {
		
		values.put( entity, value );
	}
}