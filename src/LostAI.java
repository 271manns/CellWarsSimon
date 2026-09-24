/**
 * STUDENT FILE
 *
 * Name: Simon Mann
 * AI Code Name: LostAI or name given by Mr.Tiveron
 *
 * Strategy Description:
 * My AI is a randomized AI that will either attack or defend based on a random number generated.
 * If the attack method is chosen, the code will specifically look for simple oscillators, a line of 3 cells that belongs to the opponent. If found, the code will return the location of the middle cell, seperating the oscillator from it's neighbors and killing it off.
 * If no oscillator is found, then it will look for a simple still life, a 2x2 grid of cells that belong to the opponent
 * If one of these is found, it will return a cell in a location to the left,right,above, or below that still life, depending on the Location of the still life, ultimately killing that still life. 
 * If none of these are found, the code specifically looks for a cell that belongs to the opponent and has 2 or 3 neighbors. This reflects many complex oscillators or still lifes that my methods don't check for, but could still be present and survive. 
 * If the random number generator chooses the defend method, the code will look for any line of two cells that belong to me and have one neighbor.
 * If found, then the code will return a location above that horizontal line, creating a simple still life. 
 * If none of these are found, the code will then default to calling the attack method so that I do not waste a turn.  
 */
public class LostAI extends CellAI {

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
                loc = new Location(loc.getRow(), loc.getCol()+2);
                return loc;
            }
            if(loc.getCol() == grid.getCols()-1)
            {
                loc = new Location(loc.getRow(), loc.getCol()-1);
                return loc;
            }
            if(loc.getRow() == 0)
            {
                loc = new Location(loc.getRow()+2, loc.getCol());
                return loc;
            }
            if(loc.getRow() == grid.getRows()-1)
            {
                loc = new Location(loc.getRow()-1, loc.getCol());
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
        for(int i = 0; i < grid.getRows()-1; i++)
        {
            for(int j = 0; j < grid.getCols()-1; j++)
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
                if(grid.getCell(i,j) == getID() && grid.getCell(i,j+1) == getID() && neighbors == 1 && neighbors2 ==1)
                {
                    return new Location(i-1, j);
                }
            }
        }
        return attack(grid);
    }
}
