package PresentationLayer;

public class Main
{
    public static void main(String[] args)
    {
        try
        {
            GameInitializer gameInitializer = new GameInitializer(args);
            gameInitializer.choosePlayer();
            gameInitializer.runGame();
        }
        catch (Exception exception)
        {
            System.err.println("Error: " + exception.getMessage());
        }
    }
}
