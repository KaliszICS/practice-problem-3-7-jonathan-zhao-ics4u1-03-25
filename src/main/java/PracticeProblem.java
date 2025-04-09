public class PracticeProblem {

    public static void main(String[] args) {
    
    }

    public static int searchMazeMoves(String[][] arr) {
        int startRow = arr.length - 1;
        int startCol = 0;
        int result = dfsHelper(arr, startRow, startCol, 0)[0]; //[0] is the rows
		if (result == Integer.MAX_VALUE) { //“Was the result invalid (from any reason — like out of bounds, hit a wall, or just didn’t find the finish)?”

			return -1; // means no path to "F" was found
		} else {
			return result; // return the actual number of moves
		}
		
        }
    


    public static int[] dfsHelper(String[][] arr, int row, int col, int moves) {
        if (row < 0 || col < 0 || row >= arr.length || col >= arr[0].length) {
            return new int[]{Integer.MAX_VALUE, 0}; //0 → this path did not lead to the finish, so it adds zero successful paths
        } else if (arr[row][col].equals("F")) {
            return new int[]{moves, 1};
        } else if (arr[row][col].equals("*")) {
            return new int[]{Integer.MAX_VALUE, 0};
        }

        String temp = arr[row][col]; //temp stores the original value of the current cell 
        arr[row][col] = "*"; //Then we temporarily mark that cell as "*" — which acts like a wall.

        int[] moveUp = dfsHelper(arr, row - 1, col, moves + 1);
        int[] moveRight = dfsHelper(arr, row, col + 1, moves + 1);
        int[] moveLeft = dfsHelper(arr, row, col - 1, moves + 1);
        int[] moveDown = dfsHelper(arr, row + 1, col, moves + 1);

        arr[row][col] = temp; //removes the walls so that it can caculate amount of moves and find another possible path

        int minMoves = Math.min(Math.min(moveUp[0], moveDown[0]), Math.min(moveRight[0], moveLeft[0]));

        return new int[]{minMoves};
    }
}

//Use Integer.MAX_VALUE instead of -1 So it never gets picked by Math.min() unless it's the only option & will not mistakenly pick -1 as the "best" path

