package telran.bulls.net;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private final long id;
    private final String serverSequence;
    private boolean gameFinished;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private final List<MoveResult> moveResults;
    private int attempts; 

    public Game(long id, String serverSequence) {
        this.id = id;
        this.serverSequence = serverSequence;
        this.gameFinished = false;
        this.startTime = LocalDateTime.now();
        this.moveResults = new ArrayList<>();
        this.attempts = 0;}

    public long getId() {
        return id;
    }

    public String getServerSequence() {
        return serverSequence;
    }

    public boolean isGameFinished() {
        return gameFinished;
    }

    public void setGameFinished(boolean gameFinished) {
        this.gameFinished = gameFinished;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public List<MoveResult> getMoveResults() {
        return moveResults;
    }

    public int getAttempts() {
        return attempts;
    }

    public MoveResult processMove(Move move) {
        attempts++; 
        String serverSeq = this.serverSequence;
        String clientSeq = move.clientSequence();
        int bulls = 0;
        int cows = 0;

        for (int i = 0; i < 4; i++) {
            if (serverSeq.charAt(i) == clientSeq.charAt(i)) {
                bulls++;
            } else if (serverSeq.contains(String.valueOf(clientSeq.charAt(i)))) {
                cows++;
            }
        }

        Integer[] bullsCows = {bulls, cows};
        MoveResult result = new MoveResult(bullsCows, clientSeq);
        this.moveResults.add(result);
        return result;
    }
}