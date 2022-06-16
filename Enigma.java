//TODO
public class Enigma
{
  private Rotor[] rotors = new Rotor[3];
  private static final String[] vals = {"#GNUAHOVBIPWCJQXDKRYELSZFMT", "#EJOTYCHMRWAFKPUZDINSXBGLQV","#BDFHJLNPRTVXZACEGIKMOQSUWY","#NWDKHGXZVRIFJBLMAOPSCYUTQE", "#TGOWHLIFMCSZYRVXQABUPEJKND"};
  public Enigma( int x, int y, int z, String intialPosition)
  {
    int i;
    for(i=0; i <=5; i++)
    {
       if (x == i) {
        this.rotors[0] = new Rotor(vals[i-1],intialPosition.charAt(0),0);
      }

      if (y == i) {
        this.rotors[1] = new Rotor(vals[i-1],intialPosition.charAt(1),0);
      }

      if (z == i) {
        this.rotors[2] = new Rotor(vals[i-1],intialPosition.charAt(2),0);
      }
    }
  }
  
  public String encrypt(String words)
  {
    rotatePosition(rotors[0],rotors[0].returnIndex(rotors[0].gear, rotors[0].intialChar));
    rotatePosition(rotors[1],rotors[1].returnIndex(rotors[1].gear, rotors[1].intialChar));
    rotatePosition(rotors[2],rotors[2].returnIndex(rotors[2].gear, rotors[2].intialChar));

    String finalEncrypt = "";
    for (int i=0;i< words.length();i++) {
      char enc = words.charAt(i);
      int innerIndex = rotors[0].returnIndex(rotors[0].gear, enc);
      char outerIndex = rotors[2].gear.charAt(innerIndex);
      char r2 = rotors[2].gear.charAt(rotors[1].returnIndex(rotors[1].gear,outerIndex));
      finalEncrypt += r2;
      rotate(rotors[0],rotors[1]);
    }
    return finalEncrypt;
  }
  public String decrypt(String words)
  {
    rotatePosition(rotors[0],rotors[0].returnIndex(rotors[0].gear, rotors[0].intialChar));
    rotatePosition(rotors[1],rotors[1].returnIndex(rotors[1].gear, rotors[1].intialChar));
    rotatePosition(rotors[2],rotors[2].returnIndex(rotors[2].gear, rotors[2].intialChar));

    String finalDecrypt = "";
    for (int i=0;i< words.length();i++) {
      char r = rotors[1].gear.charAt(rotors[2].returnIndex(rotors[2].gear,words.charAt(i)));
      char r2 = rotors[0].gear.charAt(rotors[2].returnIndex(rotors[2].gear,r));
      finalDecrypt += r2;
      rotate(rotors[0],rotors[1]);
    }
    return finalDecrypt;
  }
  
  public void rotate (Rotor inside, Rotor middle)
  {
    rotatePosition(inside,26);
    inside.count++;
    if(inside.count % 27 == 0)
    {
      rotatePosition(middle,26);
    }
  }
  
  public void rotatePosition (Rotor r, int index)
  {
    r.gear = r.gear.substring(index) + r.gear.substring(0,index);
  }
}