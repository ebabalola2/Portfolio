import java.util.*;

public class City{


    //Determine the City Grid based on the size of the Plotter
    public static final int WIDTH = 80;
    public static final int HEIGHT = 80;

    
    // The Grid World for your reference
    //
    //        
    //       (x)
    //        0 1 2 3 4 5 ... WIDTH
    //       .----------------...
    //  (y) 0|           ,--y
    //      1|      * (3,1) 
    //      2|         ^    
    //      3|         '-x
    //      .|
    //      .|
    //      .|       
    //HEIGHT :
    //



    //-------------------------------------
    //The simulation's Data Structures
    //
    public List<Creature> creatures; //list of all creatues
    public Queue<Creature> creaturesToAdd;
    private HashMap<GridPoint,List<Creature>> creatureGrid;
    //Random instance
    private Random rand;
    
    public City(Random rand, int numMice, int numCats, int numZombieCats, int numDogs) {
        this.rand = rand;

        this.creatures = new LinkedList<Creature>();
        this.creaturesToAdd = new LinkedList<Creature>();
        this.creatureGrid = new HashMap<GridPoint,List<Creature>>();
        /* Populate mice */
        for (int i=0; i<numMice; i++) addMouse();
        for (int i=0; i<numCats; i++) addCat();
        for (int i=0; i<numZombieCats; i++) addZombieCat();
        for (int i=0; i<numDogs; i++) addDogs();
        addNewCreatures();
      
        for(int i=0; i<WIDTH; i++)
      {
          for(int j =0; j< HEIGHT; j++)
          {
              creatureGrid.put(new GridPoint(i,j), new LinkedList<Creature>());
          }
      }
    }


    //Return the current number of creatures in the simulation
    public int numCreatures(){
        return creatures.size();
    }
    
    public void addMouse(){
        creaturesToAdd.add(new Mouse(rand.nextInt(HEIGHT),rand.nextInt(WIDTH),this,rand));
    }
    
    public void addCat(){
        
        creaturesToAdd.add(new Cat(rand.nextInt(HEIGHT),rand.nextInt(WIDTH),this,rand));
    }
    
    public void addZombieCat(){
        creaturesToAdd.add(new ZombieCat(rand.nextInt(HEIGHT),rand.nextInt(WIDTH),this,rand));
    }

    public void addDogs(){
        creaturesToAdd.add(new Dogs(rand.nextInt(HEIGHT),rand.nextInt(WIDTH),this,rand));
    }

    //use this method to queue up a create to be added
    public void addNewCreatures(){
        while(!creaturesToAdd.isEmpty()){
            creatures.add(creaturesToAdd.remove());
        }
    }

    public void updateGrid(Creature ctr, GridPoint prevP, GridPoint currP)
    {
        creatureGrid.get(prevP).remove(ctr);
        creatureGrid.get(currP).add(ctr);
    }

    public void predetor(Creature ctr, String[] preyL) {
        List<Creature> l = creatureGrid.get(ctr.getGridPoint());
        int i = 0;
        while (i < preyL.length) {
          for (Creature c : l) {
            if ((c.getId().equals(preyL[i])) && (c.eaten == false)) {
              c.eaten = true;
              ctr.ate = true;
            }
          }
          i++;
        }
      }

    public void speciesCheck(Creature ctr, String[] preyL)
    {
        List<Creature> m = creatureGrid.get(ctr.getGridPoint());
        for(int i = 0; i < preyL.length; i++)
        {
            for(Creature c : m)
            {
                if ((c.getId().equals(preyL[i])) && (c.eaten == false)) {
                    c.eaten = true;
                    ctr.ate = true;
                  }
            }
        }
    }

    public Creature findTarget(Creature c, String[] identity, int maxDist)
    {
        int leastDist = maxDist;
        Creature target = null;
        for(int i = 0; i< identity.length; i++)
        {
            for(Creature x : creatures)
            {
                if ((x.getId().equals(identity[i])) && (x.eaten == false)) {
                    int y = c.getGridPoint().dist(x.getGridPoint());
                    if ((y < leastDist) && (y != 0)) {
                      leastDist = y;
                      target = x;
                    }
                  }
            }
        }
        return target;
    }

    //You need to realize in your code such that simulate works for
    //**ALL** levels of simulkation, which means you'll need to take
    //advantage of inheritance and polymorphism.
    public void simulate() {
        //DO NOT EDIT!
        
        //You get this one for free, but you need to review this to
        //understand how to implement your various creatures

        //First, for all creatures ...
        for(Creature c : creatures){
            c.step(); 
        } //move everyone forward one step in simulation
        
        //Second, for all cratures ...
        for(Creature c : creatures){
            c.takeAction(); 
        }//take some action based on the new positions

        //Third, for all creatures ...
        LinkedList<Creature> deadCreatures = new LinkedList<Creature>();
        for(Creature c: creatures){
            if(c.isDead()) deadCreatures.add(c);
        }//find those that are dead after the action is taken

        //Four, for all creatures ...
        for(Creature c: deadCreatures){
            creatures.remove(c);
        }//remove any creatures that are dead
        
        //Five, add in any new creatures that have been added before ...
        addNewCreatures();

        //Five, for all creatures
        for(Creature c : creatures){
            System.out.println(c);
        }//print out all creatures

    }
}
