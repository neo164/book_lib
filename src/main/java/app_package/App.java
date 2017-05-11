package app_package;

import org.predybaylo.*;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import shlak.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.File;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * {@inheritDoc}
 * <p>
 * <p>
 * Суть проекта заключается в следующем: будем делать библтотеку
 * Реквайременты:
 * 1. Все работает через консоль.
 * 2. Для добавления книги На вход нужно подавать название книги, автора и
 * путь до файла с книгой на винте.
 * 3. Добавление книги происходит следующим образом.
 * У тебя будет фаил типа library.txt, который представляет из себя список с
 * авторами, названиями и путями на винте.
 * При добавлении книги, фаил книги добавляется в папку в которой лежит фаил
 * library.txt, а в library.txt делается запись об этой книге.
 * Естественно нужно реализовать проверки типа такая книга уже есть и тд.
 * 4. Реализовать методы поиска книги по автору, по названию, по автору и
 * названию и тд, и удаление книги из библиотеки
 * все должно быть сделано с использованием интерфейсов как в калькуляторе
 * <p/>
 */

public class App {
    private static boolean flag = true;
    private static boolean bulok = true;
    private static final String REGEXW = "\\w";
    private static final String REGEXD = "\\d";
    private static String INPUT;
    private static Pattern pattern;
    private static Matcher matcher;

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {

        BufferedReader d = new BufferedReader(new InputStreamReader(System.in));
        String sIn = " ";

        FileUtilReader taskread = new FileUtilReaderImpl();
        FileUtilWriter taskwrite = new FileUtilWriterImpl();
        FileUtilUpdate taskupdate = new FileUtilUpdateImpl();
        DOMUtil taskXml = new DOMUtilImpl();
        DateValidator taskDate = new DateValidatorImpl();

        String fileName = "file.txt";
        String data = "Test message";

        String filepath = System.getProperty("user.dir") + File.separator + fileName;

        while (flag) {

//*************************************super UI************************************************
            System.out.println("Fing " + taskXml.getIdMax(fileName) + " nodes.");
            System.out.println("--------------------------------------------");
            System.out.println("| Desired action:                          |");
            System.out.println("| 1 - Viewing the file cabinet;            |");
            System.out.println("| 2 - Adding a book to the card index;     |");
            System.out.println("| 3 - Search for a book in the card index; |");
            System.out.println("| 4 - Deleting a book in file.             |");
            System.out.println("| 5 - Exit the application.                |");
            System.out.println("--------------------------------------------");
            System.out.print(" =================================>");
//*************************************super UI************************************************
            try {
                sIn = d.readLine();
                switch (sIn) {

                    case "1"://seen
                        System.out.println("Item num is selected" + sIn);
                        pattern = Pattern.compile(REGEXW);
                        matcher = pattern.matcher(fileName);
                        while (!matcher.lookingAt()) {
                            pattern = Pattern.compile(REGEXW);
                            matcher = pattern.matcher(fileName);
                            System.out.println("Enter the path to the card file for later viewing:");
                            fileName = d.readLine();
                        }

                        if (!(fileName.equals("file.txt") || fileName.equals("library.txt"))) {
                            fileName = "file.txt";
                            System.out.println("The item is selected as the default file, because the path is not specified correctly:" + fileName);
                        }
                        // Создается построитель документа
                        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                        // Создается дерево DOM документа из файла
                        Document document = documentBuilder.parse(fileName);
                        // Выполнять нормализацию не обязательно, но рекомендуется
                        document.getDocumentElement().normalize();
                        Node root = document.getDocumentElement();
                        NodeList books = root.getChildNodes();
                        taskXml.showDom(fileName, books);
                        break;
                    //seen

                    case "2"://add
                        System.out.println("Item num is selected " + sIn);
                        //System.out.println("Enter the path in the format: " + filepath);
                        int id = taskXml.getIdMax(fileName) + 1;

                        String valTitle = " ";

                        pattern = Pattern.compile(REGEXW);
                        matcher = pattern.matcher(valTitle);

                        while (matcher.matches()) {
                            pattern = Pattern.compile(REGEXW);
                            matcher = pattern.matcher(valTitle);
                            System.out.println("Enter book title:");
                            valTitle = d.readLine();
                        }

                        String valName = null;
                        pattern = Pattern.compile(REGEXW);
                        matcher = pattern.matcher(valName);
                        while (matcher.lookingAt()) {
                            System.out.println("Enter author name:");
                            valName = d.readLine();
                        }

                        String valDateModify = "non date";
                        int counterStupped = 0;
                        while (bulok) {
                            if (counterStupped < 3) {
                                System.out.println("Enter the date of publication: (DD.MM.YYYY)");
                            }
                            valDateModify = d.readLine();
                            if (taskDate.validate(valDateModify)) {
                                bulok = false;
                            } else {
                                if (counterStupped >= 3) {
                                    System.out.print("Please enter the date of publication in the format (day.month.year):");
                                } else {
                                    System.out.print("Enter the date of publication in the format (DD.MM.YYYY)");
                                }
                                counterStupped++;
                            }
                            if (counterStupped > 5) {
                                valDateModify = new java.util.Date().toString();
                                System.out.println("Recording is generated automatically:" + valDateModify + " :)");
                                System.out.println();
                                bulok = false;
                            }
                        }

                        String valCost = null;
                        pattern = Pattern.compile(REGEXD);
                        matcher = pattern.matcher(valCost);
                        while (!matcher.lookingAt()) {
                            pattern = Pattern.compile(REGEXD);
                            matcher = pattern.matcher(valCost);
                            System.out.println("Enter the cost of the book:");
                            valCost = d.readLine();
                        }

                        String valPath = null;
                        pattern = Pattern.compile(REGEXW);
                        matcher = pattern.matcher(valPath);
                        while (!matcher.lookingAt()) {
                            pattern = Pattern.compile(REGEXW);
                            matcher = pattern.matcher(valPath);
                            System.out.println("Enter the save location on fs in the format: " + filepath);
                            valPath = d.readLine();
                        }

                        taskXml.addToLib(fileName, valTitle, valName, valDateModify, valCost, valPath, Integer.toString(id));
                        System.out.println("Record is saved: " + valPath + ".");
                        break;
                    //add

                    case "3"://find
                        System.out.println("Selected item №" + sIn);
                        System.out.println("Enter the title of the book or author or id book (1 - from book, 2 - from author, 3 from id)?");
                        fileName = "file.txt";
                        String searchParam = null;
                        String searchValBook = null;
                        String searchValAuthor = null;
                        String valId = null;
                        searchParam = d.readLine();

                        if (searchParam.equals("1")) {//by book
                            System.out.println("Enter the name of the book:");
                            searchValBook = d.readLine();
                        } else if (searchParam.equals("2")) {//by author
                            System.out.println("Lead the name of the author:");
                            searchValAuthor = d.readLine();
                        } else if (searchParam.equals("3")) {
                            System.out.println("Lead the name by id:");
                            valId = d.readLine();
                        }
                        taskXml.searchBook(fileName, searchValBook, searchValAuthor, valId);
                        break;
                    //find

                    case "4"://remove book
                        System.out.println("Selected item №" + sIn);
                        System.out.println("Enter remove param (1 - from book name, 2 from author, 3 from id):");
                        fileName = "file.txt";
                        String searcher = null;
                        String valNameAuth = null;
                        String valIdDel = null;
                        valTitle = d.readLine();
                        if (searcher.equals("1")) {//by book
                            System.out.println("Remove from book:");
                            valTitle = d.readLine();

                        } else if (searcher.equals("2")) {//by author
                            System.out.println("Remove from author:");
                            valNameAuth = d.readLine();

                        } else if (searcher.equals("3")) {//by id
                            System.out.println("Remove from id-s book:");
                            valIdDel = d.readLine();

                        }
                        taskXml.removeBook(fileName, valTitle, valNameAuth, valIdDel);
                        //removeBook( fileName,  valTitle,  valNameAuth,  valId)
                        break;
                    //remove book

                    case "5"://exit
                        System.out.println("Selected item №" + sIn);
                        System.out.println("Do you really want to exit the application (y / n)?");
                        String da = null;
                        da = d.readLine();
                        if (da.equals("y")) {
                            flag = false;
                        } else {
                            flag = true;
                        }//завершение цикла
                        break;
                    //exit

                    case "6"://hz
                        System.out.println("Selected item №" + sIn + "And this means that an incorrect item has been selected, it is necessary to choose from 5 items ...");
                        System.out.println("Try to choose the right item");
                        break;
                    case "7"://hz
                        System.out.println("Selected item №" + sIn + "And this means that an incorrect item has been selected, it is necessary to choose from 5 items ...");
                        System.out.println("Try to choose the right item");
                        break;
                    case "8"://hz
                        System.out.println("Selected item №" + sIn + "And this means that an incorrect item has been selected, it is necessary to choose from 5 items ...");
                        System.out.println("Try to choose the right item");
                        break;
                    case "9"://hz
                        System.out.println("Selected item №" + sIn + "And this means that an incorrect item has been selected, it is necessary to choose from 5 items ...");
                        System.out.println("Try to choose the right item");
                        break;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        filepath = System.getProperty("user.dir") + File.separator + fileName;
        System.out.print("The work was done on the file: ");
        System.out.println(filepath);
    }

}