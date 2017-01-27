package de.sloth.system.game;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import de.sloth.component.EnemyComp;
import de.sloth.component.FocusComp;
import de.sloth.component.LivingComp;
import de.sloth.component.Position3DComp;
import de.sloth.component.SpriteComp;
import de.sloth.entity.Entity;
import de.sloth.event.GameEvent;
import de.sloth.event.HMIEvent;
import de.sloth.event.HMIKeyword;
import de.sloth.event.RestartEvent;
import de.sloth.event.StartEvent;
import de.sloth.generators.VikingGenerator;
import de.sloth.system.GameSystem;
import de.sloth.system.SystemActivationEvent;

public class StartGameSystem extends GameSystem {
	
	public StartGameSystem() {
		super();
	}

	public StartGameSystem(ConcurrentLinkedQueue<Entity> entities, ConcurrentLinkedQueue<GameEvent> eventQueue) {
		super(entities, eventQueue);
	}

	@Override
	public void executeSystem() {
		List<GameEvent> delEvents = new LinkedList<GameEvent>();
		for(GameEvent event:this.getEventQueue()) {
			if(event.getClass().equals(RestartEvent.class) || event.getClass().equals(StartEvent.class)) {
				//int screenWidth = (int) Screen.getPrimary().getBounds().getWidth();
				//int screenHeight = (int) Screen.getPrimary().getBounds().getHeight(); 
				int spriteHeight = 32;
				int spriteWidth = 32;
				int xFields = 128;
				int yFields = 128;
				this.getEntities().clear();
				int id = 1;
				
				Entity viking = VikingGenerator.getInstance().generateViking(spriteWidth, spriteHeight);
				viking.setId(id);
				viking.addComponent(new FocusComp(true));
				((Position3DComp) viking.getComponent(Position3DComp.class)).setX(1);
				((Position3DComp) viking.getComponent(Position3DComp.class)).setY(1);
				this.getEntities().add(viking);
				for(int y = 0; y < yFields; y++) {
					for(int x = 0; x < xFields; x++) {
						
						Position3DComp posComp = new Position3DComp();
						id+=1;
						if(x > 0 && x < xFields-1) {
							Entity floor = new Entity();
							floor.setId(id);
							floor.setName("Floor");
							posComp.setX(x);
							posComp.setY(y);
							posComp.setZ(0);
							floor.addComponent(posComp);
							floor.addComponent(new SpriteComp("field.png"));
							this.getEntities().add(floor);
						}
						if(y == 0 || y == yFields-1 || x == 0 || x == xFields-1) {
							Entity wall = new Entity();
							wall.setId(id);
							wall.setName("Wall");
							posComp = new Position3DComp();
							posComp.setX(x);
							posComp.setY(y);
							wall.addComponent(posComp);
							if(posComp.getX() == 0) {
								wall.addComponent(new SpriteComp("wall_side_left.png"));
							} else if(posComp.getX() == xFields-1) {
								wall.addComponent(new SpriteComp("wall_side_right.png"));
							} else {
								wall.addComponent(new SpriteComp("wall.png"));
							}
							this.getEntities().add(wall);
						} else if((y != 1 || x != 1) && Math.random() < 0.02) {
							Entity enemy = new Entity();
							enemy.setId(id		);
							enemy.setName("Enemy");
							posComp = new Position3DComp();
							posComp.setX(x);
							posComp.setY(y);
							enemy.addComponent(posComp);
							enemy.addComponent(new SpriteComp("orc.png"));
							enemy.addComponent(new LivingComp(true));
							enemy.addComponent(new EnemyComp());
							this.getEntities().add(enemy);
						}
					}
				}
				delEvents.add(event);
				this.getEventQueue().add(new SystemActivationEvent(EndConditionSystem.class));
				HMIEvent hmiEvent;
				if(event.getClass().equals(RestartEvent.class)) {
					hmiEvent = new HMIEvent(HMIKeyword.toggleGameOver);
				} else {
					hmiEvent = new HMIEvent(HMIKeyword.startGame);
				}
				this.getEventQueue().add(hmiEvent);
			}
			this.getEventQueue().removeAll(delEvents);
			System.out.println("Generated Map");
		}
	}
}
