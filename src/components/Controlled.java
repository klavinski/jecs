package components;

import java.util.WeakHashMap;

import core.*;

public class Controlled extends Component {
	
	private static WeakHashMap<Entity, Boolean> values = new WeakHashMap<Entity, Boolean>();
	
	public static boolean in( Entity entity ) {
		
		return values.containsKey( entity );
	}
	
	public static boolean of( Entity entity ) {

		return values.get( entity );
	}
	
	public static void of( Entity entity, boolean value ) {
		
		values.put( entity, value );
	}
}