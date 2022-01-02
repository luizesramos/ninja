public class Ninja {
    public static void main(String[] args) {
        final Game gameEngine = new Game("Ninja");
        final TimerThread t = new TimerThread(gameEngine);
        t.start();
    }
}
