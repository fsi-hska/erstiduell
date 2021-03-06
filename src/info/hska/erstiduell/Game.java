package info.hska.erstiduell;

import info.hska.erstiduell.buzzer.BuzzerHandler;
import info.hska.erstiduell.questions.Question;
import info.hska.erstiduell.questions.QuestionLibrary;
import info.hska.erstiduell.view.ControllerWindow;
import info.hska.erstiduell.view.GameWindow;
import java.awt.GraphicsDevice;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 *
 * @author Tim Roes
 */
public class Game extends Observable {

    private List<Team> teams;
    private ControllerWindow cw;
    private GameWindow gw;
    private Question currentQuestion;
    private boolean finished;
    private int currentTeam = -1;
    private BuzzerHandler bh;
    private boolean buzzersBlocked = true;
    private GraphicsDevice device;
    private int nextQuestion;

    public Game(Config config) {

        teams = new ArrayList<Team>();
        for (int i = 0; i < config.players; i++) {
            teams.add(new Team("T" + (i)));
        }
        device = config.display;
    }

    public int getNumberOfPlayers() {
        return teams.size();
    }

    public int getCurrentTeam() {
        return currentTeam;
    }

    public boolean areBuzzersBlocked() {
        return buzzersBlocked;
    }

    public void setBuzzersBlocked(boolean buzzersBlocked) {
        this.buzzersBlocked = buzzersBlocked;
    }

    public int getPoint(int player) {
        if (player >= teams.size()) {
            return 0;
        }
        return teams.get(player).getPoints();
    }
    
    public GraphicsDevice getDevice() {
        return device;
    }

    public List<Team> getTeams() {
        return this.teams;
    }

    public void setCurrentTeam(int team) {
        this.currentTeam = team;

        setChanged();
        notifyObservers(this);
    }

    public void setCurrentQuestion(Question question) {
        this.currentQuestion = question;
        
        setChanged();
        notifyObservers(this);
    }
    
    public Question getCurrentQuestion() {
        return currentQuestion;
    }

    public int getNextQuestion() {
        return nextQuestion;
    }
    
    public void setNextQuestion(int nextQuestion) {
        this.nextQuestion = nextQuestion;
    }

    public void resetPenalties() {
        for (Team t : this.getTeams()) {
            t.resetPenalty();
        }
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;

        setChanged();
        notifyObservers(this);
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
}