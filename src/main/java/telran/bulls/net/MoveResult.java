package telran.bulls.net;

public record MoveResult(Integer[] bullsCows, String guessStr) {
	@Override
    public String toString() {
        return String.format("Guess: %s, Bulls: %d, Cows: %d", guessStr, bullsCows[0], bullsCows[1]);
    }
}
