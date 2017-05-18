package de.sloth.system.game.collision.commonBehavior;

import de.sloth.component.Position3DComp;
import de.sloth.system.game.collision.CollisionEvent;
import de.sloth.system.game.core.GameEvent;
import de.sloth.system.game.core.GameSystem;
import de.sloth.system.game.core.IBehavior;

public class Deglitch implements IBehavior {

	private final int CANVAS_WIDTH = 640;
	private int deglitch_factor;
	
	public Deglitch() {
		this.deglitch_factor = 8;
	}
	
	public Deglitch(int deglitch_factor) {
		this.deglitch_factor = deglitch_factor;
	}
	
	@Override
	public void execute(GameSystem system) {}

	@Override
	public void execute(GameSystem system, GameEvent expectedEvent) {
		CollisionEvent cevent = (CollisionEvent) expectedEvent;
		Position3DComp tPosComp = (Position3DComp) cevent.getCollisionTarget().getComponent(Position3DComp.class);
		Position3DComp srcPosComp = (Position3DComp) cevent.getCollisionSrc().getComponent(Position3DComp.class);
		
		
		//left sided
		if(srcPosComp.getX() < tPosComp.getX()) {
			if(srcPosComp.getX() - deglitch_factor >= 0) {
				srcPosComp.setX(srcPosComp.getX() - deglitch_factor);
			} else {
				srcPosComp.setX(0);
			}
			if(tPosComp.getX() + deglitch_factor <= CANVAS_WIDTH-64) {
				tPosComp.setX(tPosComp.getX() + deglitch_factor);
			} else {
				tPosComp.setX(CANVAS_WIDTH-64);
			}
		} else {
			if(tPosComp.getX() - deglitch_factor > 0) {
				tPosComp.setX(tPosComp.getX() - deglitch_factor);
			} else {
				tPosComp.setX(0);
			}
			if(srcPosComp.getX() + deglitch_factor < CANVAS_WIDTH-64) {
				srcPosComp.setX(srcPosComp.getX()+ deglitch_factor);
			} else {
				srcPosComp.setX(CANVAS_WIDTH-64);
			}
		}
	}
}
