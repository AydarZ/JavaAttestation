import java.util.Scanner;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class taskAttestation {
    public static void main(String[] args) {
        PhoneBook myPhoneBook = new PhoneBook();
        Scanner scan = new Scanner(System.in);
        int num;
        while (true) {
            System.out.print(
                    "Выберите действие:\n 1.Добавить новую запись\n 2.Поиск записи по имени\n 3.Поиск записи по номеру\n 4.Удалить запись\n 5.Показать всю телефонную книгу\n");
            num = scan.nextInt();
            switch (num) {
                case 1:
                    System.out.println("Введите имя записи для добавления: ");
                    String nameAdd = scan.next();
                    System.out.println("Введите номер телефона для данной записи: ");
                    Integer tel = scan.nextInt();
                    myPhoneBook.add(nameAdd, tel);
                    System.out.println("Номер телефона " + tel + " для имени " + nameAdd + " добавлен");
                    break;
                case 2:
                    System.out.println("Введите имя записи: ");
                    String nameSearch = scan.next();
                    System.out.println(myPhoneBook.findByName(nameSearch));
                    break;
                case 3:
                    System.out.println("Введите номер записи: ");
                    Integer numSearch = scan.nextInt();
                    myPhoneBook.findByNum(numSearch);
                    break;
                case 4:
                    System.out.println("Введите имя записи для удаления: ");
                    String nameRemove = scan.next();
                    myPhoneBook.RemoveByName(nameRemove);
                    break;
                case 5:
                    myPhoneBook.getPhoneBook();
                    break;
                default:
                    System.out.println("Повторите ввод: ");
                    break;
            }
        }
    }
}

class PhoneBook {
    private static HashMap<String, ArrayList<Integer>> phoneBook = new HashMap<>();

    public void add(String name, Integer phoneNum) {
        if (phoneBook.containsKey(name)) {
            phoneBook.get(name).add(phoneNum);
        } else {
            ArrayList<Integer> values = new ArrayList<>();
            values.add(phoneNum);
            phoneBook.put(name, values);
        }
    }

    public String findByName(String name) {
        if (phoneBook.containsKey(name)) {
            return phoneBook.get(name).toString();
        }
        return "запись с таким именем не найдена";
    }

    public void findByNum(Integer num) {
        boolean flag = false;
        for (Map.Entry<String, ArrayList<Integer>> i : phoneBook.entrySet()) {
            for (Integer j : i.getValue()) {
                if (j.equals(num)) {
                    flag = true;
                    System.out.println(i.getKey().toString());
                }
            }
        }
        if (!flag)
            System.out.println("не найдено!");

    }

    public void RemoveByName(String name) {
        if (phoneBook.containsKey(name)) {
            phoneBook.remove(name);
            System.out.println("запись удалена");
        } else
            System.out.println("запись с таким именем не найдена");
    }

    public void getPhoneBook() {
        HashMap<String, Integer> countOfNumbers = new HashMap<>();
        for (Map.Entry<String, ArrayList<Integer>> i : phoneBook.entrySet()) {
            countOfNumbers.put(i.getKey(), i.getValue().size());
        }
        ArrayList<Integer> sorted = new ArrayList<>();
        for (Map.Entry<String, Integer> k : countOfNumbers.entrySet()) {
            sorted.add(k.getValue());
        }
        Collections.sort(sorted, Collections.reverseOrder());
        for (int j = Collections.max(sorted); j > 0; j--) {
            for (Map.Entry<String, Integer> k : countOfNumbers.entrySet()) {
                if (k.getValue().equals(j)){
                    System.out.print(k.getKey() + ": ");
                    System.out.println(phoneBook.get(k.getKey()));
                }
            }
        }
    }

}
