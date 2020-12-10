package lesson7;

import kotlin.NotImplementedError;

import java.util.*;

@SuppressWarnings("unused")
public class JavaDynamicTasks {
    /**
     * Наибольшая общая подпоследовательность.
     * Средняя
     *
     * Дано две строки, например "nematode knowledge" и "empty bottle".
     * Найти их самую длинную общую подпоследовательность -- в примере это "emt ole".
     * Подпоследовательность отличается от подстроки тем, что её символы не обязаны идти подряд
     * (но по-прежнему должны быть расположены в исходной строке в том же порядке).
     * Если общей подпоследовательности нет, вернуть пустую строку.
     * Если есть несколько самых длинных общих подпоследовательностей, вернуть любую из них.
     * При сравнении подстрок, регистр символов *имеет* значение.
     */
    //Трудоёмкость (N*M)
    //Ресурсоёмкость (N*M), где N и М - длины соответствующиъ строк
    public static String longestCommonSubSequence(String first, String second) {
        int[][] matrix = fillMatrix(first, second);
        StringBuilder result = new StringBuilder();
        int x = first.length();
        int y = second.length();
        while (x >= 1 && y >= 1) {
            if (first.charAt(x - 1) == second.charAt(y - 1)) {
                result.append(first.charAt(x - 1));
                x--;
                y--;
            }
            else if (matrix[x - 1][y] > matrix[x][y - 1]) x--;
            else y--;
        }
        return result.reverse().toString();
    }

    private static int[][] fillMatrix(String first, String second) {
        int[][] result = new int[first.length() + 1][second.length() + 1];
        for (int x = 1; x <= first.length(); x++) {
            for (int y = 1; y <= second.length(); y++) {
                if (first.charAt(x - 1) == second.charAt(y - 1)) result[x][y] = result[x - 1][y - 1] + 1;
                else result[x][y] = Math.max(result[x][y - 1], result[x - 1][y]);
            }
        }
        return result;
    }

    /**
     * Наибольшая возрастающая подпоследовательность
     * Сложная
     *
     * Дан список целых чисел, например, [2 8 5 9 12 6].
     * Найти в нём самую длинную возрастающую подпоследовательность.
     * Элементы подпоследовательности не обязаны идти подряд,
     * но должны быть расположены в исходном списке в том же порядке.
     * Если самых длинных возрастающих подпоследовательностей несколько (как в примере),
     * то вернуть ту, в которой числа расположены раньше (приоритет имеют первые числа).
     * В примере ответами являются 2, 8, 9, 12 или 2, 5, 9, 12 -- выбираем первую из них.
     */
    //Трудоёмкость O(N^2)
    //Ресурсоёмкость O(N^2)
    public static List<Integer> longestIncreasingSubSequence(List<Integer> list) {
        throw new NotImplementedError();
        /*if (list.size() == 0) return list;
        int[] numbers = new int[list.size()];
        int[] indices = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            numbers[i] = 1;
            indices[i] = -1;
            for (int j = 0; j < i; j++) {
                if (list.get(j) < list.get(i) && (1 + numbers[j] > numbers[i])) {
                    numbers[i] = 1 + numbers[j];
                    indices[i] = j;
                }
            }
        }
        int num = numbers[0];
        int pos = 0;
        for (int i = 0; i < list.size(); i++) {
            if (numbers[i] > num) pos = i;
        }
        List<Integer> result = new ArrayList<>();
        while (pos != -1) {
            result.add(0, list.get(pos));
            pos = indices[pos];
        }
        return result;*/
    }

    /**
     * Самый короткий маршрут на прямоугольном поле.
     * Средняя
     *
     * В файле с именем inputName задано прямоугольное поле:
     *
     * 0 2 3 2 4 1
     * 1 5 3 4 6 2
     * 2 6 2 5 1 3
     * 1 4 3 2 6 2
     * 4 2 3 1 5 0
     *
     * Можно совершать шаги длиной в одну клетку вправо, вниз или по диагонали вправо-вниз.
     * В каждой клетке записано некоторое натуральное число или нуль.
     * Необходимо попасть из верхней левой клетки в правую нижнюю.
     * Вес маршрута вычисляется как сумма чисел со всех посещенных клеток.
     * Необходимо найти маршрут с минимальным весом и вернуть этот минимальный вес.
     *
     * Здесь ответ 2 + 3 + 4 + 1 + 2 = 12
     */
    public static int shortestPathOnField(String inputName) {
        throw new NotImplementedError();
    }

    // Задачу "Максимальное независимое множество вершин в графе без циклов"
    // смотрите в уроке 5
}
