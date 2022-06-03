import java.util.Random;

public class Cat extends Creature {
    
    public final int [] x = {-2,0,2,0}, y = {0,2,0,-2};
    public final String [] preyL = {"Mouse"};
    private int roundCount = 0;
    private int roundsTillStarved = 50;

    public Cat(int x, int y, City cty, Random rand)
    {
        super(x,y,cty,rand);
        this.lab = LAB_YELLOW;
        this.eaten = false;
        this.id = "CAT";
        this.searchDistance = 20;
        this.target = null;
        this.randomTurn();
    }

   /* public void randomTurn()
    {
        if(rand.nextInt(5) == 0)
        {
            this.setDir(rand.nextInt(NUM_DIRS));
        }
    }
    */
    @Override
    public void takeAction()
    {
        
        if((eaten = true) || (roundsTillStarved == 0))
        {
            this.city.creaturesToAdd.add(new ZombieCat(this.getGridPoint().x, this.getGridPoint().y , this.city, rand));
            this.city.creaturesToAdd.remove(this);
        }
        this.city.speciesCheck(this, preyL);
        this.city.predetor(this, preyL);
        this.ate();
    }

    public boolean isDead()
    {
        if((roundCount == 50) || (roundsTillStarved == 0) || (eaten))
        {
            return true;
        }
        return false;
    }

    @Override
    public void step()
    {
        roundCount++;
        roundsTillStarved--;

        Creature prey = this.getTarget();

        if((prey != null) && (prey.isDead() != true) && (prey.eaten != true))
        {
            this.lab = LAB_CYAN;
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
                this.lab = LAB_CYAN;
                int d = this.guageDistance(this.getGridPoint(), ctr.getGridPoint(), x, y);
                this.setDir(d);
                this.bigStep(x, y);
            }
            else
            {
                this.lab = LAB_YELLOW;
                this.randomTurn();
                this.bigStep(x, y);

            }
        }
    }

    private void ate()
    {
        if(ate)
        {
            roundsTillStarved = 50;
            ate = false;
        }
    }
}