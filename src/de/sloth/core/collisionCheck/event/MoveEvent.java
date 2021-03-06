package de.sloth.core.collisionCheck.event;

import de.sloth.core.main.entity.Entity;
import de.sloth.core.main.event.GameEvent;
import de.sloth.core.movement.event.Direction;

public class MoveEvent extends GameEvent {
	private Direction direction;
	private Entity entity;
	
	public MoveEvent(Entity entity, Direction direction) {
		this.direction = direction;
		this.setEntity(entity);
	}
	
	public MoveEvent(Direction direction) {
		this.direction = direction;
		this.setEntity(null);
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}
}