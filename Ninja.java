public class Ninja {
    public static void main(String[] args) {
        final Game gameEngine = new Game("Maze Game");
        final TimerThread t = new TimerThread(gameEngine);
        t.start();
    }
}
