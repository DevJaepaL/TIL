package Maze;

class Maze {
    /* 0 = 빈 칸
     * 1 = 장애물
     * 2 = 도착 지점 */
    private final int O = 0;
    private final int X = 1;
    private final int C = 2;
    private int[][] matrix = {{O,O,X},
                              {X,O,O},
                              {O,O,C}};

    void Matrix() {     
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++) {
                System.out.println("[" + i + "]" + "[" + j + "]");
            }
        }
    }

    public static void main(String[] args) {
        Maze m = new Maze();
        m.Matrix();
    }
}
