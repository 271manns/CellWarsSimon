/**
 * STUDENT FILE
 *
 * Name: ______________________________
 * AI Code Name: ______________________
 *
 * Strategy Description:
 * Replace this comment with a short explanation of the strategy your AI uses.
 * Your final strategy must be fundamentally different from the sample AIs.
 */
public class MyAI extends CellAI {

    @Override
    public String getAIName() {
        return "MyAI - CHANGE ME";
    }

    @Override
    public Location select(Grid grid) {
        int ran = (int)(Math.random() *10)+ 1;
     for(int i = 0; i < grid.getRows(); i++) {
         for(int j = 0; j < grid.getCols(); j++) {
             if(super.getID() != grid.getCell(i,j) && grid.getCell(i,j) != -1 && GridFunctions.getNeighbors(i,j,grid) == 3) {
                return new Location(i, j);
             }
             if(GridFunctions.getNeighbors(i,j,grid) == 2 && ran <= 5) {
                 return new Location(i, j);
             }
             else if(GridFunctions.getNeighbors(i,j,grid) == 3 && ran >=5 ) {
                 return new Location(i, j);
            if
             
             }
         }
        return null;
     }
 }
