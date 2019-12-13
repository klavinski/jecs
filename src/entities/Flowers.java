package entities;

import components.*;
import core.*;

public class Flowers extends Entity {

	public Flowers( double x, double y, double z, String color ) {
		
		Animated.of( this,               true );
		Burning .of( this,              false );
		Health  .of( this,                1.0 );
		Height  .of( this,                0.0 );
		Sprite  .of( this, color + "_flowers" );
		Width   .of( this,                1.0 );
		X       .of( this,                  x );
		Y       .of( this,                  y );
		Z       .of( this,                  z );
	}
}
