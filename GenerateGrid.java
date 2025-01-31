public class GenerateGrid {

    static int assignmentCounter=0;
    static int consistencyCounter=0;
    static int backtrackingCounter=0;

    public static void generateGridB()
    {
        for(int i = 0;i < 3;i++)
            for(int j = 0;j < 10;j++)
            TennerGrid.grid[i][j] = new Cell(-1,false);
do
        { 
            for(int i = 0;i < 10;i++)
            {
                int val = (int)((Math.random() * (18 - 0)) + 0);
                TennerGrid.grid[2][i].setValue(val);
                TennerGrid.grid[2][i].setGivenInInitialState(true);
            }
        }while(!Backtrack.backtrack());
    }

    public static boolean backtrack() {
        // Check if the assignment is complete
        if (isAssignmentComplete()) {
            printGrid();
            System.out.println(" Number of variable assignments: "+assignmentCounter);
            System.out.println(" Number of consistency checks: "+consistencyCounter);
            System.out.println(" Number of Backtracking: \n"+backtrackingCounter);
            // assignmentCounter=0;consistencyCounter=0;backtrackingCounter=0;


            return true; // Assignment is complete
        }


        int[] rowAndColumn = selectUnassignedVariable();
        int rowIndex = rowAndColumn[0];
        int columnIndex = rowAndColumn[1];
        // assignmentCounter++;

        for (int value = 0; value < 10; value++) { 
            if (valueIsConsistent(value, rowIndex, columnIndex)) {
                TennerGrid.grid[rowIndex][columnIndex].setValue(value);
                TennerGrid.grid[rowIndex][columnIndex].setGivenInInitialState(false); // we might not need it
                assignmentCounter++;

                if (backtrack()) {
                    return true; 
                }else
                {

                backtrackingCounter++;
                TennerGrid.grid[rowIndex][columnIndex].setValue(-1); // Reset cell value
                }
            }
        }

        return false; 
    }

    private static boolean isAssignmentComplete() {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j <10 ; j++) {
                if (TennerGrid.grid[i][j].getValue() == -1) {
                    return false; // Found unassigned cell
                }
            }
        }
        return true; // All cells are assigned
    }

    private static int[] selectUnassignedVariable() {
        int[] rowAndColumn=new int[2];

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 10; j++) {
                if ( TennerGrid.grid[i][j].getValue()==-1) {
                    rowAndColumn[0]=i;
                    rowAndColumn[1]=j;
                    return rowAndColumn;
                }
            }
        }
        return null; // If all cells are assigned, return null

    }

    private static boolean valueIsConsistent(int value, int rowIndex, int columnIndex ) {
        if (isRowConsistent(value, rowIndex,columnIndex )
        &&  isDiagonalConsistent(value, rowIndex, columnIndex)
        &&  isSumConsistent(value, rowIndex, columnIndex)) {
            return true;
        }
        return false;
    }


    private static boolean isRowConsistent(int value, int rowIndex, int columnIndex ) {
        consistencyCounter++;
        if(rowIndex==2)
        return true;

        // Check row constraint
        for (int column = 0; column < 10; column++) {
            if (column != columnIndex && TennerGrid.grid[rowIndex][column].getValue() == value) {
                return false; // Value violates row constraint
            }
        }
        return true;
    }

    private static boolean isDiagonalConsistent(int value, int rowIndex, int columnIndex ) {
        consistencyCounter++;

        int temp1 = columnIndex-1;
        int temp2 = columnIndex+1;
        for(int k = rowIndex-1;k >= 0;k--)//diagonal check
        {
            if(temp1 <= 9 && temp1 >= 0)
                if(value == TennerGrid.grid[k][temp1].getValue())
                    return false;    
            if(temp2 <= 9 && temp2 >= 0)
                if(value == TennerGrid.grid[k][temp2].getValue())
                    return false; 
            temp1--;
            temp2++;
        }
        temp1 = columnIndex-1;
        temp2 = columnIndex+1;
        for(int k = rowIndex+1;k < 2;k++)//diagonal check
        {
            if(temp1 <= 9 && temp1 >= 0)
                if(value == TennerGrid.grid[k][temp1].getValue())
                    return false;    
            if(temp2 <= 9 && temp2 >= 0)
                if(value == TennerGrid.grid[k][temp2].getValue())
                    return false;
            temp1--;
            temp2++;
        }
        return true; // Placeholder for diagonal consistency check
    }
 
    private static boolean isSumConsistent(int value, int rowIndex, int columnIndex) {
        consistencyCounter++;

        // Check if the value is not larger than the sum value
        if (value > TennerGrid.grid[2][columnIndex].getValue()) {
            return false;
        }
    
        // Check the sum consistency based on the row index
        else if (rowIndex == 0) {
            // Check if the cell below is initialized
            if (TennerGrid.grid[1][columnIndex].isInitialState() || TennerGrid.grid[1][columnIndex].getValue()!=-1) {
                int sum = TennerGrid.grid[1][columnIndex].getValue() + value;
                return sum == TennerGrid.grid[2][columnIndex].getValue();
            }
        } else if (rowIndex == 1) {
            // Check if the cell above is initialized
            if (TennerGrid.grid[0][columnIndex].isInitialState()|| TennerGrid.grid[0][columnIndex].getValue()!=-1) {
                int sum = TennerGrid.grid[0][columnIndex].getValue() + value;
                return sum == TennerGrid.grid[2][columnIndex].getValue();
            }
        }
    
        // If the conditions are not met, return true
        return true;
    }
    

    public static void printGrid() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 10; j++) {
                    System.out.print(TennerGrid.grid[i][j].getValue() + "\t");
                }
             
            System.out.println();
        }
    }

    public static int getAssignmentCounter() {
        return assignmentCounter;
    }

    public static int getConsistencyCounter() {
        return consistencyCounter;
    }

    
    
}
