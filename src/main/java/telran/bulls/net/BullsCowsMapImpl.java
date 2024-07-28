package telran.bulls.net;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

public class BullsCowsMapImpl implements BullsCowsService {
	private static final long SECRET_LENGTH = 4;
    private Map<Long, Game> games = new HashMap<>();
    private long nextId = 1;


    @Override
    public Long createNewGame() {
        String serverSequence = generateServerSequence();
        Game game = new Game(nextId, serverSequence);
        games.put(nextId, game);
        return nextId++;
    }

    public List<MoveResult> getResults(long gameId, Move move) {
        Game game = games.get(gameId);
        if (game != null && !game.isGameFinished()) {
            MoveResult result = game.processMove(move);
            if (result.bullsCows()[0] == 4) {
                game.setGameFinished(true);
                game.setEndTime(LocalDateTime.now());
            }
            return game.getMoveResults();
        }
        return null;
    }

    @Override
    public Boolean isGameOver(long gameId) {
        Game game = games.get(gameId);
        return game != null && game.isGameFinished();
    }

    private String generateServerSequence() {
    	 return new Random().ints('0', '9' + 1).distinct().limit(SECRET_LENGTH) //IntStream
				 .mapToObj(n -> (char)n) 
				 .map(c -> "" + c) 
				 .collect(Collectors.joining());
    }
    }