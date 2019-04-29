/**
 * Glass Falling
 * Sarmad Karatela
 */
public class GlassFalling {

  // Do not change the parameters!
  public int glassFallingRecur(int floors, int sheets) {

	    // 1. Base Case when floor is 1 or 0, we just return the number of floors
    if (floors == 1 || floors == 0 ){
      return floors;

    }
    // 2. Base Case when we only have one sheet to work with. we just return the current floor
     if (sheets == 1 ){
       return floors;

     }
    int minTrails = Integer.MAX_VALUE;
		
    int value; //setting the minTrails to the worst case. 
  

   //This loop will check every floor that whther the sheet breaks or not. Starting from floor 1
   // X is the current floor you're at. 
    for(int x = 1; x <= floors; x++)
    {
			value = Math.max(glassFallingRecur(x - 1, sheets - 1), 
					glassFallingRecur(floors - x, sheets));
            if(value < minTrails)
            {
				minTrails = value;
             }
       }
    return minTrails + 1;
  }
	    
	    
  // Optional:
  // Pick whatever parameters you want to, just make sure to return an int.
  public int glassFallingMemoized(int sheets, int floors, int Trials[][]) 
  {
	  
	  int [][] Trials1 = new int[sheets + 1][floors + 1];

      //  Base cases
     
	  // Whether the floor is 0 or 1
      for (int i = 1; i <=sheets ; i++) {
          Trials1[i][0] = 0;
          Trials1[i][1] = 1;
      }
     
      for (int s = 1; s <=floors ; s++) {
          Trials1[1][s] = s;
      }

      for (int s = 2; s <= sheets; s++)
      {
          for(int fl = 2; fl <= floors; fl++)
          {
              Trials1[s][fl] = Integer.MAX_VALUE;
              int tempResult;
              for(int k = 1; k <= fl; k++)
              {
                  tempResult = 1 + Math.max(Trials1[s-1][k-1], Trials1[s][fl-k]);
                  Trials1[s][fl] = Math.min(tempResult,Trials1[s][fl]);
              }
          }
      }
      return Trials1[sheets][floors];
  }


  public int glassFallingBottomUp(int floors, int sheets) {

	  int Trials[][] = new int[floors + 1][sheets + 1];

		for (int j = 0; j <= floors; j++) {

			for (int i = 0; i <= sheets; i++) {

				if (j == 0 || i == 0)
					Trials[j][i] = 0;
				else if (j == 1)
					Trials[j][i] = 1;
				else if (i == 1)
					Trials[j][i] = j;
				else
					Trials[j][i] = Integer.MAX_VALUE;
			}
		}

		for (int j = 2; j <= floors; j++) {
			for (int i = 2; i <= sheets; i++) {
				for (int k = 1; k <= j; k++) {
					int drops = Math.max(Trials[k - 1][i - 1], Trials[j - k][i]) + 1;
					Trials[j][i] = Math.min(drops, Trials[j][i]);

				}
			}

		}

		return Trials[floors][sheets];
	}

  public static void main(String args[]){
      GlassFalling gf = new GlassFalling();
      int Trials[][] = new int [4][101];

      // Do not touch the below lines of code, and make sure
      // in your final turned-in copy, these are the only things printed
      int minTrials1Recur = gf.glassFallingRecur(27, 2);
      int minTrials1Bottom = gf.glassFallingBottomUp(27, 2);
      int minTrials2Memo = gf.glassFallingMemoized(100, 3, Trials);
      int minTrials2Bottom = gf.glassFallingBottomUp(100, 3);
      
      System.out.println(minTrials1Recur + " " + minTrials1Bottom);
      System.out.println(minTrials2Memo + " " + minTrials2Bottom);
  }
 }
