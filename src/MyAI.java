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
        return Choice(grid);

    }

    public Location Choice(Grid grid){
        return attack(grid);
    }
    public Location attack(Grid grid)
    {
        if(IsASimpleStillLife(grid) != null)
        {
           Location loc = IsASimpleStillLife(grid);
           loc = new Location(loc.getRow()-1, loc.getCol());
           return loc;
        }
        else if(IsASimpleOscillator(grid) != null)
        {
            return IsASimpleOscillator(grid);
        }
        else
        {
            return null;
        }
    }
    
        
        
    
    public Location defend() 
    {
        return null;
    }
    
    public Location IsASimpleStillLife(Grid grid)
    {
        for(int i = 1; i < grid.getRows() - 1; i++)
        {
            for(int j = 1; j < grid.getCols() - 1; j++)
            {
                if(grid.getCell(i,j) != -1)
                {
                    if(grid.getCell(i, j) != getID()&& grid.getCell(i + 1, j) != getID()&& grid.getCell(i, j + 1) != getID()&& grid.getCell(i + 1, j + 1) != getID())
                    {
                        return new Location(i, j);
                    }
                }
            }
        }
        return null;
    }
    public Location IsASimpleOscillator(Grid grid)
    {
        for(int i = 1; i < grid.getRows()-1; i++)
        {
            for(int j = 1; j < grid.getCols()-1; j++)
            {
                if(grid.getCell(i, j) != -1)
                {  
                
                    if(grid.getCell(i+1, j) != getID() && grid.getCell(i+1, j-1) != getID() && grid.getCell(i+1, j+1) != getID())
                    {
                        return new Location(i, j);
                    }
                    else if(grid.getCell(i+1, j) != getID() && grid.getCell(i, j) != getID() && grid.getCell(i-1, j) != getID())
                    {
                        return new Location(i, j);
                    }
                }
            }
        }
        return null;
    }

    public boolean almostAStillLife()
    {
        return false;
    }
}
