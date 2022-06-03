import java.util.Random;

public class ZombieCat extends Cat{

    public final int [] x = {-3,0,3,0}, y = {0,3,0,-3};
    public final String [] preyL = {"CAT", "MOUSE"};
    private int roundCount = 0;
    private int roundsTillStarved = 100;

    public ZombieCat(int x, int y, City cty, Random rand)
    {
        super(x,y,cty,rand);
        this.lab = LAB_RED;
        this.id = "ZMCAT";
        this.searchDistance = 40;
        this.target = null;
    }

    public void randomTurn()
    {
        if(rand.nextInt(5) == 0)
        {
            this.setDir(rand.nextInt(NUM_DIRS));
        }
    }

    public void takeAction()
    {

        this.city.speciesCheck(this, preyL);
        this.ate();
        this.city.predetor(this, preyL);
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
            this.lab = LAB_BLACK;
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
                this.lab = LAB_BLACK;
                int d = this.guageDistance(this.getGridPoint(), ctr.getGridPoint(), x, y);
                this.setDir(d);
                this.bigStep(x, y);
            }
            else
            {
                this.lab = LAB_RED;
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
