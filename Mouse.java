import java.util.Random;

public class Mouse extends Creature {
    public final int [] x = {-1,0,1,0}, y = {0,1,0,-1};
    private int roundCount = 0;

    public Mouse(int x, int y ,City cty , Random rand)
    {
        super(x, y, cty , rand);
        this.lab = LAB_BLUE;
        this.eaten = false;
        this.id = "MOUSE";
    }

    public void randomTurn()
    {
        if(rand.nextInt(20) == 0)
        {
            this.setDir(rand.nextInt(NUM_DIRS));
        }
    }

    public void takeAction()
    {
        if(eaten)
        {
            this.city.creaturesToAdd.remove(this);
        }

        if(roundCount == 20)
        {
            this.city.creaturesToAdd.add(new Mouse(this.getGridPoint().x, this.getGridPoint().y, this.city, rand));
        }
    }

    public boolean isDead()
    {
        if((roundCount == 30) || (eaten))
        {
            return true;
        }
        return false;
    }

    public void step()
    {
        roundCount++;
        this.bigStep(x, y);
    }
}
