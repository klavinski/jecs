package systems;

import components.*;
import core.*;

public class ChangeControlled extends System_ {
	
	boolean changing = false;

	@Override
	public void apply( Entity[] entities ) {

		if ( changing == false && Frame.getKey( "shift" ) ) {

			changing = true;
			
			int controlledId = 0;
			
			for ( int i = 0; i < entities.length; i++ )
			if ( Controlled.in( entities[ i ] ) )
			if ( Controlled.of( entities[ i ] ) )
				controlledId = i;
			
			Controlled.of( entities[ controlledId ], false );
			
			for ( int i = 1; i <= entities.length; i++ ) {
				
				Entity entity = entities[ ( controlledId + i ) % entities.length ];
				if ( Controlled.in( entity ) ) {
					
					Controlled.of( entity, true );
					
					if            ( Cry.in( entity ) )
						Sound.play( Cry.of( entity ) );
					
					if ( Sprite.in( entity )               )
					if ( Sprite.of( entity ) == "pokeball" ) {
						
					    Sprite.of( entity, "pidgey" );
						Sound.play( "open" );
					}
					return;
				}
			}
		}	
		changing = Frame.getKey( "shift" );
	}
}
