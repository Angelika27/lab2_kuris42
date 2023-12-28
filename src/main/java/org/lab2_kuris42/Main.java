package org.lab2_kuris42;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {

    private static void clearConsole()
    {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void main(String[] args) {

        var library = new Library();
        var scanner = new Scanner(System.in);
        var buffReader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("WELCOME TO ANGELICA KURIS LIBRARY!");
            System.out.println("");
            System.out.println("1. Додати предмет");
            System.out.println("2. Видалити предмет");
            System.out.println("3. Додати читача");
            System.out.println("4. Видати предмет");
            System.out.println("5. Повернути предмет");
            System.out.println("6. Список доступних предметів");
            System.out.println("7. Список позичених предметів");
            System.out.println("");
            System.out.println("0. Вийти з додатка");

            var i = scanner.nextInt();
            try {
                switch (i) {
                    case 1:
                        clearConsole();
                        System.out.println("ДОДАННЯ ПРЕДМЕТА");
                        System.out.println("");
                        System.out.println("Виберіть тип предмету:");
                        System.out.println("1. Книга");
                        System.out.println("2. DVD");
                        var t = scanner.nextInt();
                        if (t == 1) {
                            System.out.println("ДОДАТИ КНИГУ");
                            System.out.println("Введіть назву книги:");
                            String bookTitle = buffReader.readLine();
                            System.out.println("Введіть автора книги:");
                            String bookAuthor = buffReader.readLine();
                            System.out.println("Введіть ISBN книги:");
                            String bookIsbn = buffReader.readLine();
                            System.out.println("Введіть рік книги:");
                            String bookYear = String.valueOf(Integer.parseInt(buffReader.readLine()));


                            Book book = new Book(bookTitle, bookAuthor,bookIsbn,bookYear);
                            library.add(book);
                            System.out.println("Книга додана:");
                        } else if (t == 2){
                            System.out.println("ДОДАТИ DVD");
                            System.out.println("Введіть назву DVD:");
                            String dvdTitle = buffReader.readLine();
                            System.out.println("Введіть тривалість DVD:");
                            String dvdDuration = buffReader.readLine();
                            DVD dvd = new DVD(dvdTitle, dvdDuration);
                            library.add(dvd);
                            System.out.println("DVD додана:");
                        }
                        var s = buffReader.readLine();
                        break;

                    case 2:
                        clearConsole();
                        System.out.println("ВИДАЛИТИ ПРЕДМЕТ ПО НАЗВІ");
                        System.out.println("Введіть назву предмета, який ви хочете видалити:");
                        String itemTitleToDelete = buffReader.readLine();

                        boolean removed = false;
                        for (i = 0; i <library.items.size(); i++) {
                            Item item = library.items.get(i);
                            if (item.getTitle().equalsIgnoreCase(itemTitleToDelete)) {
                                library.items.remove(i);
                                removed = true;
                                System.out.println("Предмет успішно видалено з бібліотеки.");
                                break;
                            }
                        }

                        if (!removed) {
                            System.out.println("Предмет з такою назвою не знайдено.");
                        }
                        break;

                    case 3:

                        clearConsole();
                        System.out.println("ДОДАТИ ЧИТАЧА");
                        System.out.println("Введіть ім'я читача:");
                        String patronName = buffReader.readLine();

                        Patron patron = new Patron(patronName);
                        library.addPatron(patron); // Предполагается, что у вас есть метод addPatron в классе Library

                        System.out.println("Читач додано:");
                        break;
                    case 4:
                        clearConsole();
                        System.out.println("ВИДАТИ ПРЕДМЕТ");
                        System.out.println("Введіть ім'я читача:");
                        String patronNameToIssue = buffReader.readLine();

                        Patron issuingPatron = null;
                        for (Patron patronBorrowed : library.patrons) {
                            if (patronBorrowed.getName().equalsIgnoreCase(patronNameToIssue)) {
                                issuingPatron = patronBorrowed;
                                break;
                            }
                        }

                        if (issuingPatron != null) {
                            System.out.println("Введіть назву предмета, який ви хочете видати:");
                            String itemTitleToIssue = buffReader.readLine();

                            boolean issued = false;
                            for (int j = 0; j < library.items.size(); j++) {
                                Item item = library.items.get(j);
                                if (item.getTitle().equalsIgnoreCase(itemTitleToIssue)) {
                                    issued = issuingPatron.borrowItem(item);
                                    break;
                                }
                            }

                            if (!issued) {
                                System.out.println("Предмет з такою назвою не знайдено або вже видано.");
                            }
                        } else {
                            System.out.println("Читача з таким ім'ям не знайдено.");
                        }
                        break;
                    case 5:
                        clearConsole();
                        System.out.println("ПОВЕРНУТИ ПРЕДМЕТ");
                        System.out.println("Введіть ім'я читача:");
                        String patronNameToReturn = buffReader.readLine();

                        Patron returningPatron = null;
                        for (Patron patronReturn : library.patrons) {
                            if (patronReturn.getName().equalsIgnoreCase(patronNameToReturn)) {
                                returningPatron = patronReturn;
                                break;
                            }
                        }

                        if (returningPatron != null) {
                            System.out.println("Введіть назву предмета, який ви хочете повернути:");
                            String itemTitleToReturn = buffReader.readLine();

                            boolean returned = false;
                            List<Item> borrowedItems = returningPatron.getBorrowedItems();
                            for (Item item : borrowedItems) {
                                if (item.getTitle().equalsIgnoreCase(itemTitleToReturn)) {
                                    returned = true;
                                    returningPatron.returnItem(item); // Викликаємо метод returnItem() для патрона
                                    System.out.println("Предмет '" + item.getTitle() + "' успішно повернуто.");
                                    break;
                                }
                            }

                            if (!returned) {
                                System.out.println("Предмет з такою назвою не знайдено або він не був запозичений цим читачем.");
                            }
                        } else {
                            System.out.println("Читача з таким ім'ям не знайдено.");
                        }
                        break;
                    case 6:
                        clearConsole();
                        System.out.println("СПИСОК ДОСТУПНИХ ПРЕДМЕТІВ ПО ТИПАМ");
                        Map<String, List<Item>> availableItemsByType = library.getAvailableItems();

                        for (Map.Entry<String, List<Item>> entry : availableItemsByType.entrySet()) {



                            for (Item item : entry.getValue()) {
                                System.out.println("Назва: " + item.getTitle());

                                if (item instanceof Book) {
                                    Book book = (Book) item;
                                    System.out.println("Тип: Книга");
                                    System.out.println("Автор: " + book.getAuthor());
                                    System.out.println("ISBN: " + book.getISBN());
                                    System.out.println("Рік: " + book.getYear());
                                } else if (item instanceof DVD) {
                                    DVD dvd = (DVD) item;
                                    System.out.println("Тип: DVD");
                                    System.out.println("Тривалість: " + dvd.getDuration());
                                }
                                System.out.println("------------------------");
                            }
                        }
                        break;
                    case 7:
                        clearConsole();
                        System.out.println("СПИСОК ПОЗИЧЕНИХ ПРЕДМЕТІВ");

                        for (Patron patronBorrowed : library.patrons) {
                            List<Item> borrowedItems = patronBorrowed.getBorrowedItems();
                            if (!borrowedItems.isEmpty()) {
                                System.out.println("Позичені предмети для читача " + patronBorrowed.getName() + ":");
                                for (Item item : borrowedItems) {
                                    System.out.println("Назва: " + item.getTitle() );
                                    // Тут можна вивести додаткову інформацію про кожен предмет (автора, ISBN, рік тощо)
                                }
                            }
                        }

                        break;
                }
            }
            catch (Exception ex)
            {
                System.out.println(String.format("ERROR: %s", ex.getMessage()));
            }
            finally {
                System.out.println("Для продовження натисніть ENTER");
                try {
                    buffReader.readLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }
}