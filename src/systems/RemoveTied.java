package systems;

import components.*;
import core.*;

public class RemoveTied extends System_ {

	@Override
	public void apply( Entity[] entities ) {
		
		entityLoop: for ( Entity entity: entities )
		if ( TiedTo.in( entity ) ) {

			
	    	Entity tiedTo = TiedTo.of( entity );
	    	//If the tied-to entity does no longer exist, we remove the entity too.
	    	for ( Entity otherEntity: entities )
	    	if ( otherEntity == tiedTo )
	    		continue entityLoop;
	    	entity.remove();
	    }
	}
}