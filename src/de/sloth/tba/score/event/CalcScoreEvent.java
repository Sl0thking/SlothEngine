package de.sloth.tba.score.event;

import de.sloth.core.main.event.GameEvent;
import de.sloth.tba.score.other.ScoreType;

/**
 * Event for the score system.
 * 
 * @author Kevin Jolitz
 * @version 1.0.1
 * @date 21.05.2017
 */
public class CalcScoreEvent extends GameEvent {
	
	private ScoreType sType;
	
	public CalcScoreEvent(ScoreType sType) {
		super("CalcScore");
		this.sType = sType;
	}

	public ScoreType getsType() {
		return sType;
	}

	public void setsType(ScoreType sType) {
		this.sType = sType;
	}
}