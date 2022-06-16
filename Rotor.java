//TODO
public class Rotor
{
  String gear;
  char intialChar;
  int count = 0;
  
  public Rotor (String gear, char intialChar,int count) {
    this.gear = gear;
    this.intialChar = intialChar;
    this.count = count;
  }
  
 public int returnIndex (String gear, char r)
 {
   for(int i =0; i < gear.length(); i++)
   {
     if(Character.compare(gear.charAt(i), r) == 0)
     {
       return i;
     }
   }
    return -1;
 }
  public char returnChar (String gear,int index) {
    return gear.charAt(index);
  }
}