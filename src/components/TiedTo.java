package components;

import java.util.WeakHashMap;

import core.*;

public class TiedTo extends Component {
	
	private static WeakHashMap<Entity, Entity> values = new WeakHashMap<Entity, Entity>();
	
	public static boolean in( Entity entity ) {
		
		return values.containsKey( entity );
	}
	
	public static Entity of( Entity entity ) {

		return values.get( entity );
	}
	
	public static void of( Entity entity, Entity value ) {
		
		values.put( entity, value );
	}
}