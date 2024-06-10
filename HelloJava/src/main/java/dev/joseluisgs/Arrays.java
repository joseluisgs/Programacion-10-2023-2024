package dev.joseluisgs;

public class Arrays {
    public void arrays() {
        int[] numbers = new int[5];
        numbers[0] = 1;
        numbers[1] = 2;
        numbers[2] = 3;
        numbers[3] = 4;
        numbers[4] = 5;

        for (int i = 0; i < numbers.length; i++) {
            System.out.println(numbers[i]);
        }

        int[] numbers2 = {1, 2, 3, 4, 5};
        for (int i = 0; i < numbers2.length; i++) {
            System.out.println(numbers2[i]);
        }

        // Matriz
        int[][] matrix = new int[2][2];
        matrix[0][0] = 1;
        matrix[0][1] = 2;
        matrix[1][0] = 3;
        matrix[1][1] = 4;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.println(matrix[i][j]);
            }
        }

        int[][] matrix2 = {{1, 2}, {3, 4}};

        // For each
        for (int[] row : matrix2) {
            for (int value : row) {
                System.out.println(value);
            }
        }
    }
}
