package entities;

import core.*;
import components.*;

public class Ground extends Entity {

	public Ground( double x, double y, double width ) {
				
		Height.of( this,   2.0 );
		Width .of( this, width );
		X     .of( this,     x );
		Y     .of( this,     y );
		Z     .of( this,  -2.0 );
	}
}
