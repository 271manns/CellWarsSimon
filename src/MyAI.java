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
        return "LostAI";
    }

    @Override
    public Location select(Grid grid) {
        return Choice(grid);

    }

    public Location Choice(Grid grid){
        int ran = (int)(Math.random() * 2);
        if(ran == 0)
        {
            return defend(grid);
        }
        else
        {
            return attack(grid);
        }
    }
    public Location attack(Grid grid)
    {
        if(IsASimpleOscillator(grid) != null)
        {
           Location loc = IsASimpleOscillator(grid);
           loc = new Location(loc.getRow(), loc.getCol());
           return loc;
        }
        else if(IsASimpleStillLife(grid) != null)
        {
            Location loc = IsASimpleStillLife(grid);
            if(loc.getCol() == 0)
            {
                loc = new Location(loc.getRow(), loc.getCol()+1);
                return loc;
            }
            loc = new Location(loc.getRow(), loc.getCol()-1);
            return loc;
        }
        else
        {
            for(int i = 1; i < grid.getRows() - 1; i++)
            {
                for(int j = 1; j < grid.getCols() - 1; j++)
                {
                    int neighbors = GridFunctions.getNeighbors(i,j,grid);
                    if(grid.getCell(i,j) != getID() && grid.getCell(i,j) != -1 &&(neighbors== 2 || neighbors == 3))
                    {
                        return new Location(i, j);
                    }
                }
            }
               
        }
        return null;    
    }
    
        
        
    
    public Location defend(Grid grid) 
    {
        return almostAStillLife(grid);
    }
    
    public Location IsASimpleStillLife(Grid grid)
    {
        for(int i = 1; i < grid.getRows() - 1; i++)
        {
            for(int j = 1; j < grid.getCols() - 1; j++)
            {
                int neighbors1 = GridFunctions.getNeighbors(i, j, grid);
                int neighbors2 = GridFunctions.getNeighbors(i + 1, j, grid);
                int neighbors3 = GridFunctions.getNeighbors(i, j + 1, grid);
                int neighbors4 = GridFunctions.getNeighbors(i + 1, j + 1, grid);
                    if(grid.getCell(i, j) != getID() && grid.getCell(i,j) != -1&& grid.getCell(i + 1, j) != getID()&& grid.getCell(i+1,j) != -1 && grid.getCell(i, j + 1) != getID()&& grid.getCell(i,j+1) != -1 && grid.getCell(i + 1, j + 1) != getID() && grid.getCell(i+1,j+1) != -1
                    && neighbors1 == 3 && neighbors2 == 3 && neighbors3 == 3 && neighbors4 == 3)
                    {
                        return new Location(i, j);
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
                {  
                    int neighbors1 = GridFunctions.getNeighbors(i, j-1, grid);
                    int neighbors2 = GridFunctions.getNeighbors(i, j+1, grid);
                    int neighbors3 = GridFunctions.getNeighbors(i, j, grid);
                    if(grid.getCell(i, j-1) != getID() && grid.getCell(i,j-1)!= -1 && grid.getCell(i, j+1) != getID() && grid.getCell(i,j+1)!= -1 && grid.getCell(i, j) != getID() && grid.getCell(i,j)!= -1
                    && neighbors1 == 2 && neighbors2 == 2 && neighbors3 == 2)
                    {
                        return new Location(i, j);
                    }
                    neighbors1 = GridFunctions.getNeighbors(i-1, j, grid);
                    neighbors2 = GridFunctions.getNeighbors(i+1, j, grid);
                    if(grid.getCell(i+1, j) != getID() && grid.getCell(i+1, j)!= -1 && grid.getCell(i, j)!= getID() && grid.getCell(i,j)!= -1 && grid.getCell(i-1, j) != getID() && grid.getCell(i-1, j)!= -1
                    && neighbors1 == 2 && neighbors2 == 2 && neighbors3 == 2)
                    {
                        return new Location(i, j);
                    }
                }
            }
        }
        return null;
    }

    public Location almostAStillLife(Grid grid)
    {
        
        for(int i = 1; i < grid.getRows() - 1; i++)
        {
            for(int j = 1; j < grid.getCols() - 1; j++)
            {
                int neighbors = GridFunctions.getNeighbors(i, j, grid);
                int neighbors2 = GridFunctions.getNeighbors(i, j+1, grid);
                if(grid.getCell(i,j) == getID() && grid.getCell(i,j+1) == getID() && neighbors == 2 && neighbors2 ==2)
                {
                    return new Location(i-1, j);
                }
            }
        }
        return attack(grid);
    }
}
