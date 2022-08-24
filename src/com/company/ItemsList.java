package com.company;
import java.io.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

public class ItemsList {

    private static final String UP_AND_DOWN = "|---|------------------------|-------------------------|-------------------------|-------------|--------|";
    private static final String LABEL = "| № |  Название устройства   |    Фирма изготовитель   |   Главная тех. хар-ка   | Гарантия до | Цена,$ |";

    public static LinkedList<ItemOfList> createList() {
        return new LinkedList<>();
    }

    public static void addToListWithoutIndex(LinkedList<ItemOfList> list, ItemOfList item) {
        list.add(item);
    }

    public static void addToListWithIndex(LinkedList<ItemOfList> list, ItemOfList item, int index) {
        list.add(index, item);
    }

    public static void editInListWithIndex(LinkedList<ItemOfList> list, Scanner input) {
        showList(list);
        if (list.size() != 0) {
            System.out.println("Введите индекс элемента, которого хотите изменить: ");
            boolean isInCorrect = false;
            int index = 0;
            do {
                try {
                    index = Integer.parseInt(input.nextLine());
                } catch (NumberFormatException e) {
                    System.out.print("Введите корректно данные: ");
                }
                if (!isInCorrect && list.size() > index) {
                    isInCorrect = true;
                }
            } while (isInCorrect);
            ItemOfList item = list.get(index - 1);
            System.out.println(UP_AND_DOWN);
            System.out.println(LABEL);
            System.out.format("|%3d|", 1);
            ItemController.showItem(item);
            System.out.println("\n" + UP_AND_DOWN);
            String key;
            do {
                HelpController.showEditingMenu();
                key = HelpController.findMenuKey(input);
                switch (key) {
                    case ("1") -> {
                        System.out.print("Введите новое имя: ");
                        item.setDeviceName(ItemController.findLine(input));
                    }
                    case ("2") -> {
                        System.out.print("Введите нового производителя: ");
                        item.setManufacturer(ItemController.findLine(input));
                    }
                    case ("3") -> {
                        System.out.print("Введите новую техническую характеристику: ");
                        item.setMainTechSpec(ItemController.findLine(input));
                    }
                    case ("4") -> {
                        System.out.print("Введите новую дату гаранитии: ");
                        item.setDateWarranty(ItemController.findDate(input));
                    }
                    case ("5") -> {
                        System.out.print("Введите новую цену: ");
                        item.setPrice(ItemController.findPrice(input));
                    }
                    default -> System.out.println("Выберите корректный пункт меню.");
                }
            } while (!key.equals("6"));
            System.out.println("Успешно изменено.");
        }
    }


    public static void removeFromListByIndex(LinkedList<ItemOfList> list, int index) {
        list.remove(index);
    }

    public static void showList(LinkedList<ItemOfList> list) {
        ItemOfList item;
        int size;
        try {
            size = list.size();
        } catch (NullPointerException e){
            size = 0;
        }
        if (size == 0) {
            System.out.println("В списке нет ни одного комплектующего.");
        } else {
            System.out.println(UP_AND_DOWN);
            System.out.println(LABEL);
            System.out.println(UP_AND_DOWN);
            for (int index = 0; index < list.size(); index++) {
                System.out.format("|%3d|", index + 1);
                item = list.get(index);
                ItemController.showItem(item);
                System.out.println("\n" + UP_AND_DOWN);
            }
        }
    }

    public static void sortList(LinkedList<ItemOfList> list) {
        Collections.sort(list);
        showList(list);
    }

    private static String findFilePath(Scanner input) {
        String filePath;
        FileReader reader = null;
        boolean isIncorrect;
        do {
            System.out.print("Введите путь к файлу: ");
            filePath = input.nextLine();
            isIncorrect = false;
            try {
                reader = new FileReader(filePath);
            } catch (FileNotFoundException e) {
                System.out.println("Файл не найден.");
                isIncorrect = true;
            }
        } while (isIncorrect);
        try {
            reader.close();
        } catch (IOException e) {
            System.err.println("I/O error.");
        }
        return filePath;
    }

    public static void saveList(LinkedList<ItemOfList> list, Scanner input) {
        try {
            FileOutputStream outputStream = new FileOutputStream(findFilePath(input));
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(list);
            outputStream.close();
            System.out.println("Успешно сохранено.");
        } catch (FileNotFoundException e) {
            System.err.println("File not found.");
        } catch (IOException e) {
            System.err.println("I/O error.");
        }
    }

    public static LinkedList<ItemOfList> readList(Scanner input) {
        FileInputStream fReader;
        LinkedList<ItemOfList> list = null;
        boolean isCorrect = true;
        try {
            fReader = new FileInputStream(findFilePath(input));
            ObjectInputStream objectInputStream = new ObjectInputStream(fReader);
            list = (LinkedList<ItemOfList>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Файл поврежден");
            isCorrect = false;
        }
        if (isCorrect) {System.out.println("Успешно загружено.");}
        return list;
    }
}
