/**
 * Glass Falling
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
    for(int x = 1; x <= floors; x++){
			value = Math.max(glassFallingRecur(x - 1, sheets - 1), 
					glassFallingRecur(floors - x, sheets));
			if(value < minTrails){
				minTrails = value;
  }
        return minTrails + 1;
    }
  // Optional:
  // Pick whatever parameters you want to, just make sure to return an int.
  public int glassFallingMemoized(int sheets, int floors) {
    int[][] memo = new int [sheets + 1] [floors + 1]; //This 2D array will store results to each trial that will take place. 

    int results;

    for (int i= 1; i<=sheets, i++){
      memo[0][i] = 1;
      memo[1][i] = 0;
     
    }

      for (int j= 1; j<=floors; j++){ 
        memo[j][1] = j;
      }
for(int currentFloor = 2; currentFloor <= floors; currentFloor++) {
			
			for(int currentNumSheet = 2; currentNumSheet <= sheets; currentNumSheet++) {
				
				//first we set the value of each position to the max
				memo[currentFloor][currentNumSheet] = Integer.MAX_VALUE;
				
				for( int x = 2; x <= currentFloor; x++ ) {
					
					result = 1+  max(memo[x - 1][ currentNumSheet - 1], memo[currentFloor - x][ currentNumSheet]);
				
					
					if(result < memo[currentFloor][currentNumSheet]) {
						memo[currentFloor][currentNumSheet] = result;
					}
				}
			}
			
		
		//This is the last slot of the table which is the minimum trials  
		return memo[floors][sheets];

    }
  }

  // Do not change the parameters!
  public int glassFallingBottomUp(int floors, int sheets) {
    // Fill in here and change the return
    return 0;
  }


  public static void main(String args[]){
      GlassFalling gf = new GlassFalling();

      // Do not touch the below lines of code, and make sure
      // in your final turned-in copy, these are the only things printed
      int minTrials1Recur = gf.glassFallingRecur(27, 2);
      int minTrials1Bottom = gf.glassFallingBottomUp(27, 2);
      int minTrials2Recur = gf.glassFallingRecur(100, 3);
      int minTrials2Bottom = gf.glassFallingBottomUp(100, 3);
      System.out.println(minTrials1Recur + " " + minTrials1Bottom);
      System.out.println(minTrials2Recur + " " + minTrials2Bottom);
  }
}
