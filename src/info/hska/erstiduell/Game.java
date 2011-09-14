package info.hska.erstiduell;

import info.hska.erstiduell.buzzer.BuzzerHandler;
import info.hska.erstiduell.questions.Answer;
import info.hska.erstiduell.questions.Question;
import info.hska.erstiduell.questions.QuestionLibrary;
import info.hska.erstiduell.view.ConfigWindow;
import info.hska.erstiduell.view.ControllerWindow;
import info.hska.erstiduell.view.GameWindow;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Tim Roes
 */
public class Game {

	private int[] points;
	private List<Team> teams;
	private ControllerWindow cw;
	private GameWindow gw;
	private QuestionLibrary questions;
	private Question currentQuestion;
	private boolean finished;
	private int currentTeam = -1;

	public Game() {
	}

	public void start() {
		ConfigWindow cw = new ConfigWindow(this);

		cw.setMonitors(GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices());

		cw.setVisible(true);
	}

	public void update() {
		cw.refresh();
		gw.redraw();
	}

	public void configured(Config config) {

		questions = QuestionLibrary.getInstance();

		teams = new ArrayList<Team>();
		for (int i = 0; i < config.players; i++) {
			teams.add(new Team("T" + (i + 1)));
		}

		BuzzerHandler.setKeys(config.hotkeys);
		Toolkit.getDefaultToolkit().getSystemEventQueue().push(BuzzerHandler.getInstance());

		cw = new ControllerWindow(this);
		cw.setVisible(true);

		gw = new GameWindow(config.display, this);
		gw.setBGColor(config.background);
		gw.setFGColor(config.foreground);
		gw.setVisible(true);

		BuzzerHandler.getInstance().setControllerWindow(cw);
		BuzzerHandler.getInstance().setGameWindow(gw);

	}

	public int getPlayers() {
		return teams.size();
	}

	public void setPoints(int player, int points) {
		teams.get(player - 1).setPoints(points);

		update();
	}

	public int getPoint(int player) {
		if (player > teams.size()) {
			return 0;
		}

		return teams.get(player - 1).getPoints();
	}

	public List<Team> getTeams() {
		return this.teams;
	}

	public void setCurrentTeam(int team) {
		this.currentTeam = team;
	}

	public void resetPenalties() {
		for (Team t : this.getTeams()) {
			t.resetPenalty();
		}
	}

	public void nextQuestion(Question q) {
		currentQuestion = q;
		currentQuestion.done();
		resetPenalties();
		currentTeam = -1;
		update();
		releaseBuzzer();
	}

	public Question nextQuestion() {
		List<Question> qs = questions.getAllQuestions();

		// Get random question
		int i = (int) (Math.random() * qs.size());
		int count = 0;

		while (count++ < qs.size() && qs.get(i).getDone()) {
			i = (i + 1) % qs.size();
		}

		if (count >= qs.size()) {
			return null;
		}

		currentQuestion = qs.get(i);
		currentQuestion.done();
		resetPenalties();
		currentTeam = -1;
		update();
		releaseBuzzer();

		return currentQuestion;
	}

	public Question getCurrentQuestion() {
		return currentQuestion;
	}

	public void showAnswer(Answer a) {
		a.done();
		update();
	}

	public void guessedAnswer(Answer a, int player) {
		if (!a.getDone()) {
			teams.get(player - 1).addPoints(a.getAmount());
			a.done();
		}
		currentTeam = -1;
		update();
	}

	public void endGame() {
		finished = true;
		update();
	}

	public boolean isFinished() {
		return finished;
	}

	public List<Team> getWinner() {
		List<Team> winners = new ArrayList<Team>();
		int max = -1;

		for (Team t : teams) {
			if (t.getPoints() > max) {
				max = t.getPoints();
			}
		}

		for (Team t : teams) {
			if (t.getPoints() == max) {
				winners.add(t);
			}
		}

		return winners;
	}

	private class ReleaseTimer extends Thread {

		@Override
		public void run() {
			try {
				Game.this.gw.showTimer("Get ready!<br>3");
				Thread.sleep(1000);
				Game.this.gw.showTimer("Get ready!<br>2");
				Thread.sleep(1000);
				Game.this.gw.showTimer("Get ready!<br>1");
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
			}

			Game.this.releaseTimer = null;
			BuzzerHandler.getInstance().release();
			Game.this.update();
		}
	};
	ReleaseTimer releaseTimer;

	public void releaseBuzzer() {
		if (currentTeam >= 0) {
			this.getTeams().get(currentTeam).addPenalty();
			currentTeam = -1;
			this.gw.redraw();
			boolean endQuestion = true;
			for (Team t : this.getTeams()) {
				if (t.getPenalty() < this.getCurrentQuestion().getAnswers().size()) {
					endQuestion = false;
					break;
				}
			}
			if (endQuestion) {
				return;
			}
		}
		if (releaseTimer == null) {
			releaseTimer = new ReleaseTimer();
			releaseTimer.start();
		}
	}
}