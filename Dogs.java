import java.util.Random;

public class Dogs extends Creature{

    public final int [] x = {-4,0,4,0}, y = {0,4,0,-4};
    public final String [] preyL = {"CAT", "ZMCat"};
    private int roundCount = 0;
    private int roundsTillStarved = 100;

    public Dogs(int x, int y, City cty, Random rand)
    {
        super(x,y,cty,rand);
        this.lab = LAB_GREEN;
        this.id = "DOG";
        this.searchDistance = 50;
        this.target = null;
    }

    public void randomTurn()
    {
        if(rand.nextInt(10) == 0)
        {
            this.setDir(rand.nextInt(NUM_DIRS));
        }
    }

    public void takeAction()
    {

        this.city.speciesCheck(this, preyL);
        //this.city.predetor(this, preyL);
        this.ate();
    }

    public boolean isDead()
    {
        if((roundCount == 100) || (roundsTillStarved == 0))
        {
            return true;
        }
        return false;
    }

    public void step()
    {
        roundCount++;
        roundsTillStarved--;

        Creature prey = this.getTarget();

        if((prey != null) && (prey.isDead() != true) && (prey.eaten != true))
        {
            this.lab = LAB_PINK;
            int d = this.guageDistance(this.getGridPoint(), prey.getGridPoint(), x, y);
            this.setDir(d);
            this.bigStep(x, y);
        }
        else if((prey == null) || (prey.isDead() == true) || (prey.eaten == true))
        {
            Creature ctr = this.city.findTarget(this, preyL, this.searchDistance);
            if (ctr != null)
            {
                this.setTarget(ctr);
                this.lab = LAB_PINK;
                int d = this.guageDistance(this.getGridPoint(), ctr.getGridPoint(), x, y);
                this.setDir(d);
                this.bigStep(x, y);
            }
            else
            {
                this.lab = LAB_GREEN;
                this.randomTurn();
                this.bigStep(x, y);

            }
        }
    }

    private void ate()
    {
        if(ate)
        {
            roundsTillStarved = 100;
            ate = false;
        }
        
    }
    
}
