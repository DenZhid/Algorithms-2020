package lesson1;

import kotlin.NotImplementedError;

import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class JavaTasks {
    /**
     * Сортировка времён
     *
     * Простая
     * (Модифицированная задача с сайта acmp.ru)
     *
     * Во входном файле с именем inputName содержатся моменты времени в формате ЧЧ:ММ:СС AM/PM,
     * каждый на отдельной строке. См. статью википедии "12-часовой формат времени".
     *
     * Пример:
     *
     * 01:15:19 PM
     * 07:26:57 AM
     * 10:00:03 AM
     * 07:56:14 PM
     * 01:15:19 PM
     * 12:40:31 AM
     *
     * Отсортировать моменты времени по возрастанию и вывести их в выходной файл с именем outputName,
     * сохраняя формат ЧЧ:ММ:СС AM/PM. Одинаковые моменты времени выводить друг за другом. Пример:
     *
     * 12:40:31 AM
     * 07:26:57 AM
     * 10:00:03 AM
     * 01:15:19 PM
     * 01:15:19 PM
     * 07:56:14 PM
     *
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    //Трудоёмкость - O(NlogN)
    //Ресурсоёмкость - R(N)
    static public void sortTimes(String inputName, String outputName) throws IOException {
        List<Integer> am = new ArrayList<>();
        List<Integer> pm = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(inputName));
        String line = reader.readLine();
        while (line != null) {
            if (!line.trim().matches("^(0[1-9]|1[0-2]):[0-5][0-9]:[0-5][0-9] [AP]M$")) {
                throw new IllegalArgumentException();
            }
            if (line.split(" ")[1].equals("AM")) {
                am.add(toNumber(line));
            }
            else {
                pm.add(toNumber(line));
            }
            line = reader.readLine();
        }
        reader.close();
        Collections.sort(am);
        Collections.sort(pm);
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputName));
        am.forEach (element -> {
            int hours = element / 10000;
            int minutes = (element / 100) % 100;
            int seconds = element % 100;
            if (hours == 0) {
                hours = 12;
            }
            try {
                writer.write(String.valueOf(new Formatter().format("%02d:%02d:%02d AM", hours, minutes, seconds)));
                writer.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        pm.forEach(element -> {
            int hours = element / 10000;
            int minutes = (element / 100) % 100;
            int seconds = element % 100;
            if (hours == 0) {
                hours = 12;
            }
            try {
                writer.write(String.valueOf(new Formatter().format("%02d:%02d:%02d PM", hours, minutes, seconds)));
                writer.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        writer.close();
    }

    private static int toNumber(String str) {
        String[] time = str.split(" ")[0].split(":");
        StringBuilder number = new StringBuilder();
        if (time[0].equals("12")) {
            number.append("0");
        } else {
            number.append(time[0]);
        }
        for (int i = 1; i <= 2; i++) {
            number.append(time[i]);
        }
        return Integer.parseInt(number.toString());
    }

    /**
     * Сортировка адресов
     *
     * Средняя
     *
     * Во входном файле с именем inputName содержатся фамилии и имена жителей города с указанием улицы и номера дома,
     * где они прописаны. Пример:
     *
     * Петров Иван - Железнодорожная 3
     * Сидоров Петр - Садовая 5
     * Иванов Алексей - Железнодорожная 7
     * Сидорова Мария - Садовая 5
     * Иванов Михаил - Железнодорожная 7
     *
     * Людей в городе может быть до миллиона.
     *
     * Вывести записи в выходной файл outputName,
     * упорядоченными по названию улицы (по алфавиту) и номеру дома (по возрастанию).
     * Людей, живущих в одном доме, выводить через запятую по алфавиту (вначале по фамилии, потом по имени). Пример:
     *
     * Железнодорожная 3 - Петров Иван
     * Железнодорожная 7 - Иванов Алексей, Иванов Михаил
     * Садовая 5 - Сидоров Петр, Сидорова Мария
     *
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public void sortAddresses(String inputName, String outputName) throws IOException {
        throw new NotImplementedError();
        /*Comparator<String> CForNames = (s, t1) -> {
            while (!s.equals("") && !t1.equals("")) {
                int codeOfSSymbol = s.charAt(0);
                int codeOfT1Symbol = t1.charAt(0);
                if (codeOfSSymbol > codeOfT1Symbol) {
                    return 1;
                }
                else if (codeOfSSymbol < codeOfT1Symbol) {
                    return -1;
                }
                else {
                    s = s.substring(1);
                    t1 = t1.substring(1);
                }
            }
            if (s.equals("") && !t1.equals("")) {
                return -1;
            }
            else if (!s.equals("")) {
                return 1;
            } else {
                return 0;
            }
        };
        Comparator<String> CForAddresses = (s, t1) -> {
            String nameOfS = s.split(" ")[0];
            int numbOfS = Integer.parseInt(s.split(" ")[1]);
            String nameOfT1 = t1.split(" ")[0];
            int numbOfT1 = Integer.parseInt(t1.split(" ")[1]);
            while (!nameOfS.equals("") && !nameOfT1.equals("")) {
                int codeOfSSymbol = nameOfS.charAt(0);
                int codeOfT1Symbol = nameOfT1.charAt(0);
                if (codeOfSSymbol > codeOfT1Symbol) {
                    return 1;
                }
                else if (codeOfSSymbol < codeOfT1Symbol) {
                    return -1;
                }
                else {
                    nameOfS = nameOfS.substring(1);
                    nameOfT1 = nameOfT1.substring(1);
                }
            }
            if (nameOfS.equals("") && !nameOfT1 .equals("")) {
                return -1;
            }
            else if (!nameOfS.equals("")) {
                return 1;
            }
            else {
                return Integer.compare(numbOfS, numbOfT1);
            }
        };
        SortedMap<String, List<String>> addressBook = new TreeMap<>(CForAddresses);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(inputName), StandardCharsets.UTF_8)
        );
        String line = reader.readLine();
        while (line != null) {
            if(!line.trim().matches("^([А-Я]([а-я])* ){2}- ([А-я-ё]* [0-9]+)$")) {
                throw new IllegalArgumentException();
            }
            String address = line.split(" - ")[1];
            String name = line.split(" - ")[0];
            if (!addressBook.containsKey(line.split(" - ")[1])) {
                addressBook.put(address, new ArrayList<>());
                addressBook.get(address).add(name);
            }
            else {
                addressBook.get(address).add(name);
                addressBook.get(address).sort(CForNames);
            }
            line = reader.readLine();
        }
        reader.close();
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(outputName), StandardCharsets.UTF_8)
        );
        for (String key : addressBook.keySet()) {
            writer.write(key + " - ");
            for (int i = 0; i < addressBook.get(key).size(); i++) {
                writer.write(addressBook.get(key).get(i));
                if (i != addressBook.get(key).size() - 1) {
                    writer.write(", ");
                }
            }
            writer.newLine();
        }
        writer.close();*/
    }

    /**
     * Сортировка температур
     *
     * Средняя
     * (Модифицированная задача с сайта acmp.ru)
     *
     * Во входном файле заданы температуры различных участков абстрактной планеты с точностью до десятых градуса.
     * Температуры могут изменяться в диапазоне от -273.0 до +500.0.
     * Например:
     *
     * 24.7
     * -12.6
     * 121.3
     * -98.4
     * 99.5
     * -12.6
     * 11.0
     *
     * Количество строк в файле может достигать ста миллионов.
     * Вывести строки в выходной файл, отсортировав их по возрастанию температуры.
     * Повторяющиеся строки сохранить. Например:
     *
     * -98.4
     * -12.6
     * -12.6
     * 11.0
     * 24.7
     * 99.5
     * 121.3
     */
    //Трудоёмкость - O(NlogN)
    //Ресурсоёмоксть - R(N)
    static public void sortTemperatures(String inputName, String outputName) throws IOException {
        SortedMap<Double, Integer> temperatures = new TreeMap<>();
        BufferedReader reader = new BufferedReader(new FileReader(inputName));
        String line = reader.readLine();
        while (line != null) {
           double number = Double.parseDouble(line);
           temperatures.merge(number, 1, Integer::sum);
           line = reader.readLine();
        }
        reader.close();
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputName));
        for (double key : temperatures.keySet()) {
            for (int i = 0; i < temperatures.get(key); i++) {
                writer.write(String.valueOf(key));
                writer.newLine();
            }
        }
        writer.close();
    }

    /**
     * Сортировка последовательности
     *
     * Средняя
     * (Задача взята с сайта acmp.ru)
     *
     * В файле задана последовательность из n целых положительных чисел, каждое в своей строке, например:
     *
     * 1
     * 2
     * 3
     * 2
     * 3
     * 1
     * 2
     *
     * Необходимо найти число, которое встречается в этой последовательности наибольшее количество раз,
     * а если таких чисел несколько, то найти минимальное из них,
     * и после этого переместить все такие числа в конец заданной последовательности.
     * Порядок расположения остальных чисел должен остаться без изменения.
     *
     * 1
     * 3
     * 3
     * 1
     * 2
     * 2
     * 2
     */
    //Трудоёмкость - O(N)
    //Ресурсоёмкость - R(N)
    static public void sortSequence(String inputName, String outputName) throws IOException {
        Map<Integer, Integer> mapOfQuantity = new HashMap<>();
        List<Integer> sequence = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(inputName));
        String line = reader.readLine();
        int max = 0;
        while (line != null) {
            int number = Integer.parseInt(line);
            mapOfQuantity.merge(number, 1, Integer::sum);
            sequence.add(number);
            if (mapOfQuantity.get(number) > max) {
                max = mapOfQuantity.get(number);
            }
            line = reader.readLine();
        }
        reader.close();
        Set<Map.Entry<Integer, Integer>> entrySet = mapOfQuantity.entrySet();
        List<Integer> listOfRecurringKeys = new ArrayList<>();
        int min = Integer.MAX_VALUE;
        for (Map.Entry<Integer, Integer> pair : entrySet) {
            if (max == pair.getValue()) {
                if (pair.getKey() < min) {
                    min = pair.getKey();
                }
            }
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputName));
        for (Integer number: sequence) {
            if (number != min) {
                writer.write(String.valueOf(number));
                writer.newLine();
            }
        }
        while (max > 0) {
            writer.write(String.valueOf(min));
            writer.newLine();
            max--;
        }
        writer.close();
    }

    /**
     * Соединить два отсортированных массива в один
     *
     * Простая
     *
     * Задан отсортированный массив first и второй массив second,
     * первые first.size ячеек которого содержат null, а остальные ячейки также отсортированы.
     * Соединить оба массива в массиве second так, чтобы он оказался отсортирован. Пример:
     *
     * first = [4 9 15 20 28]
     * second = [null null null null null 1 3 9 13 18 23]
     *
     * Результат: second = [1 3 4 9 9 13 15 20 23 28]
     */
    static <T extends Comparable<T>> void mergeArrays(T[] first, T[] second) {
        throw new NotImplementedError();
    }
}
