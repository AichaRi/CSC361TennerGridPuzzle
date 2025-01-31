# Tenner Grid Puzzle Generator and Solver

## Summery
This project is designed to generate and solve the Tenner Grid puzzle using Constraint Satisfaction Problem (CSP) techniques. 
First the program generates a Tenner grid puzzle, then solves it by implemnting three algorithms
**Simple Backtracking**, **Forward Checking**, and **Forward Checking with Minimum Remaining Values (MRV) Heuristic**. 
## Problem Overview

Tenner Grid is a Mathematical puzzle that consists of a rectangular grid of width ten
cells. The task is to fill in the grid so that each row contains the numbers 0 to 9. Numbers
must not be repeated in rows. In columns the numbers may be repeated and the numbers
in a column must add up to a given target sum. Therefore, the bottom numbers give the
sum of the numbers in column. The digits in contiguous cells (even diagonally
contiguous cells) must be different.
The puzzle can vary in size, such as 10x3, 10x4, 10x5, or 10x6 grids, and the goal of this project is to solve 10x3 puzzles using CSP techniques.

## Rules of Tenner Grid
1. **Row Uniqueness**: Each row must contain the numbers 0-9 without repetition.
2. **Column Sum**: Numbers in columns may repeat, but their sum must equal a predefined target value for each column.
3. **Adjacency Constraint**: Contiguous cells (including diagonally adjacent ones) must contain different numbers.

## Solution Approach

The Tenner Grid puzzle is modeled as a **Constraint Satisfaction Problem (CSP)**, where each cell in the grid represents a variable, and the constraints are based on row uniqueness, column sums, and adjacency constraints. The puzzle is generated randomly and then solved using CSP algorithms.

### Algorithms Implemented

1. **Simple Backtracking**:
   - A standard backtracking approach is used to assign values to cells. If a value violates any constraints, the algorithm backtracks and tries a different value.

2. **Forward Checking**:
   - This approach improves on backtracking by checking the constraints as values are assigned. If any assignment causes a variable to have no possible values left, the algorithm backtracks early.

3. **Forward Checking with MRV (Minimum Remaining Values) Heuristic**:
   - This approach uses forward checking with the MRV heuristic, which selects the variable with the fewest remaining legal values first. 

### Puzzle Generation

- The program generates a random Tenner Grid puzzle by:
  - Creating a grid of size 10x3.
  - Filling in each row with numbers 0-9, ensuring no repetition.
  - Assigning random column sums to the grid.
  - Ensuring that adjacent cells (including diagonally adjacent ones) contain different numbers.

### Key Outputs
For each algorithm, the following results are displayed:
- **Initial State**: A randomly generated Tenner Grid puzzle.
- **Final State**: The solved puzzle.
- **Number of Variable Assignments**: The number of variable assignments made during the solving process.
- **Number of Consistency Checks**: The number of consistency checks performed.
- **Time Used**: The time taken to solve the puzzle.

### Sample Run
```
 Number of variable assignments: 1.16077348E8
 Number of consistency checks: 9.559385453E9

Initial state: 
4       -1      1       -1      -1      6       7       -1      0       9
7       -1      6       -1      -1      -1      5       2       -1      4
11      17      7       5       5       7       12      5       8       13

Solve using Backtrack: 
 Number of variable assignments: 1.16077373E8
 Number of consistency checks: 9.559386892E9
Time taken: 149892 ns
4       8       1       2       5       6       7       3       0       9
7       9       6       3       0       1       5       2       8       4
11      17      7       5       5       7       12      5       8       13
```
``` 
Initial state: 
4       -1      1       -1      -1      6       7       -1      0       9
7       -1      6       -1      -1      -1      5       2       -1      4
11      17      7       5       5       7       12      5       8       13

Solve using ForwardChecking: 
Time taken: 900637 ns
4       -1      1       -1      -1      6       7       -1      0       9
7       -1      6       -1      -1      -1      5       2       -1      4
11      17      7       5       5       7       12      5       8       13
 ```
```
Initial state: 
4       -1      1       -1      -1      6       7       -1      0       9
7       -1      6       -1      -1      -1      5       2       -1      4
11      17      7       5       5       7       12      5       8       13

 Solve using ForwardCheckingMRV: 
 Number of variable assignments: 10.0
 Number of consistency checks: 384.0
Time taken: 955579 ns
4       8       1       5       2       6       7       3       0       9
7       9       6       0       3       1       5       2       8       4
11      17      7       5       5       7       12      5       8       13

```
