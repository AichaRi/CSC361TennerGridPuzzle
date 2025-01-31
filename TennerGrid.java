import java.util.Scanner;

import java.util.Random;
import java.util.Scanner;
public class TennerGrid {

    static Cell[][] grid = new Cell[3][10];
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in); 
        for(int i = 0;i < 3;i++)
            for(int j = 0;j < 10;j++)
                grid[i][j] = new Cell(-1,false);
        do
        { 
            for(int i = 0;i < 10;i++)
            {
                int val = (int)((Math.random() * (18 - 0)) + 0);
                grid[2][i].setValue(val);
                grid[2][i].setGivenInInitialState(true);
            }
        }while(!Backtrack.backtrack());
        Random random = new Random();
        for(int i = 0;i < 2;i++)
            for(int j = 0;j < 10;j++)
            {
                if(random.nextBoolean())
                    grid[i][j].setValue(-1);
                else
                    grid[i][j].setGivenInInitialState(true);
            }  
        System.out.println("Initial state: ");
        printGrid(grid);
        int choice;
        do
        {
            System.out.println("which algorithm you want to use? 1.Simple backtracking 2.Forward checking 3.Forward checking with MRV heuristic 4.Exit: ");
            choice = input.nextInt();
            switch(choice)
            {
                case 1: 
                {
                    Backtrack.backtrack();
                    printGrid(grid);
                    break;
                }
                case 2:
                {
                    ForwardChecking.backtrackingWithForwardChecking();
                    printGrid(grid);
                    break;
                }
                case 3:
                {
                    ForwardCheckingMRV.forwardCheckingMRV();
                    printGrid(grid);
                    break;
                }   
            }
        }while(choice != 4);
        System.out.println("Solve using forward checking with MRV: ");
    }

    public static void printGrid(Cell[][] grid) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(grid[i][j].getValue()+"\t");
            }
            System.out.println();
        }
    }
    
}