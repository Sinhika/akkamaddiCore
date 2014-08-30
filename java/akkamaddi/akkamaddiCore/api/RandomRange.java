package akkamaddi.akkamaddiCore.api;

import java.util.Random;

@SuppressWarnings("serial")
public class RandomRange extends Random
{
    public int nextInt(int min, int max)
    {
        return nextInt(max - min + 1) + min;
    }
}