package org.predybaylo;

import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Element;

import java.io.FileOutputStream;

import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMUtilImpl implements DOMUtil {

    public void showDom(String fileName, NodeList books) {
        try {
            // Создается построитель документа
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            // Создается дерево DOM документа из файла
            Document document = documentBuilder.parse(System.getProperty("user.dir") + File.separator + fileName);

            // Получаем корневой элемент
            Node root = document.getDocumentElement();

            System.out.println("List of books:");
            System.out.println("-------------------------");
            // Просматриваем все подэлементы корневого - т.е. книги
            books = root.getChildNodes();

            for (int i = 0; i < books.getLength(); i++) {
                Node book = books.item(i);
                // Если нода не текст, то это книга - заходим внутрь
                if (book.getNodeType() != Node.TEXT_NODE) {
                    NodeList bookProps = book.getChildNodes();
                    for (int j = 0; j < bookProps.getLength(); j++) {
                        Node bookProp = bookProps.item(j);
                        // Если нода не текст, то это один из параметров книги - печатаем
                        if (bookProp.getNodeType() != Node.TEXT_NODE) {
                            System.out.println(bookProp.getNodeName() + ":" + bookProp.getChildNodes().item(0).getTextContent());
                        }
                    }
                    System.out.println("===========>>>>");
                }
            }
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace(System.out);
        } catch (SAXException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public void addToLib(String fileName, String valTitle, String valNameAuth, String valDateCrate, String valCost, String valPath, String id) {
//       <Book id="1">
//         <Title>War and peace</Title>
//         <Author>Lev Tolstoi</Author>
//         <Date>2030</Date>
//         <Cost>110.50</Cost>
//         <PathFS>D:\YaDisk\YandexDisk\java\book_lib\War_and_peace.djvu</PathFS>
//       </Book>
        try {
            // Создается построитель документа
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            // Создается дерево DOM документа из файла
            Document document;
            document = documentBuilder.parse(fileName);

            // Получаем корневой элемент
            Node root = document.getDocumentElement();

            // Создаем новую книгу по элементам
            // Сама книга <Book>
            Element book = document.createElement("Book");


            // Определяем идентификатор книги
            Attr ids = document.createAttribute("id");
            ids.setTextContent(id);
            book.setAttributeNode(ids);
            book.getAttributeNode(id + 1);

            // <Title>
            Element title = document.createElement("Title");
            // Устанавливаем значение текста внутри тега
            title.setTextContent(valTitle);
            // <Author>
            Element author = document.createElement("Author");
            author.setTextContent(valNameAuth);
            // <Date>
            Element date = document.createElement("Date");
            date.setTextContent(valDateCrate);
            // <Cost>
            Element cost = document.createElement("Cost");
            cost.setTextContent(valCost);
            // Устанавливаем атрибут
            cost.setAttribute("currency", "RUB");
            // <Path>
            Element path = document.createElement("Path");
            path.setTextContent(valPath);

            // Добавляем внутренние элементы книги в элемент <Book>
            book.appendChild(title);
            book.appendChild(author);
            book.appendChild(date);
            book.appendChild(cost);
            book.appendChild(path);
            // Добавляем книгу в корневой элемент
            root.appendChild(book);
            document.normalize();
            // Записываем XML в файл общий
            writeDocument(document, fileName);

       /*     // Записываем XML в файл в назначенную директорию это для будущей сепарации сохранения файла - пока сохраняем файл всей структурой DOM-а а надо делать + новый файл с 1 записью!

            document = null;
            document = documentBuilder.parse(valPath);
            root = document.getDocumentElement();

            // Создаем новую книгу по элементам
            // Сама книга <Book>
            book = document.createElement("Book");
            // Определяем идентификатор книги
            ids = document.createAttribute("id");
            ids.setTextContent(id);
            book.setAttributeNode(ids);
            // <Title>
            title = document.createElement("Title");
            // Устанавливаем значение текста внутри тега
            title.setTextContent(valTitle);
            // <Author>
            author = document.createElement("Author");
            author.setTextContent(valNameAuth);
            // <Date>
            date = document.createElement("Date");
            date.setTextContent(valDateCrate);
            // <Cost>
            cost = document.createElement("Cost");
            cost.setTextContent(valCost);
            // Устанавливаем атрибут
            cost.setAttribute("currency", "RUB");
            // <Path>
            path = document.createElement("Path");
            path.setTextContent(valPath);
            // Добавляем внутренние элементы книги в элемент <Book>
            book.appendChild(title);
            book.appendChild(author);
            book.appendChild(date);
            book.appendChild(cost);
            book.appendChild(path);
            // Добавляем книгу в корневой элемент
            root.appendChild(book);
            */
            document.normalize();
            writeDocument(document, valPath);

        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(DOMUtilImpl.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    // Функция для сохранения DOM в файл
    private static void writeDocument(Document document, String valPath) {
        try {
            Transformer tr = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(document);
            FileOutputStream fos = new FileOutputStream(valPath);
            StreamResult result = new StreamResult(fos);
            tr.transform(source, result);
            System.out.println("Saving complete. ");
        } catch (TransformerException | IOException e) {
            e.printStackTrace(System.out);
        }
    }

    public int getIdMax(String fileName) {
        int valIdNum = 0;
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse(fileName);
            XPathFactory pathFactory = XPathFactory.newInstance();
            XPath xpath = pathFactory.newXPath();
            pathFactory = XPathFactory.newInstance();
            xpath = pathFactory.newXPath();

            XPathExpression expr = xpath.compile("BookCatalogue/Book");

            NodeList nodes = (NodeList) expr.evaluate(document, XPathConstants.NODESET);
            valIdNum = nodes.getLength();

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
        }
        return valIdNum;
    }


    public void searchBook(String fileName, String valTitle, String valNameAuth, String valId) {
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse(fileName);
            XPathFactory pathFactory = XPathFactory.newInstance();
            XPath xpath = pathFactory.newXPath();

            pathFactory = XPathFactory.newInstance();
            xpath = pathFactory.newXPath();

            if (valTitle != null) {//по имени книги
                XPathExpression expr = xpath.compile("BookCatalogue/Book/Title");
                NodeList nodes = (NodeList) expr.evaluate(document, XPathConstants.NODESET);
                if (nodes.getLength() > 0) {
                    for (int i = 0; i < nodes.getLength(); i++) {
                        Node n = nodes.item(i);
                        if (n.getTextContent().equals(valTitle)) {
                            System.out.println("Value find! Id value is: " + i);

                            XPathExpression expr1 = xpath.compile("BookCatalogue/Book[@id=" + i + "]");
                            NodeList nodes1 = (NodeList) expr1.evaluate(document, XPathConstants.NODESET);
                            System.out.println("Value find! ");
                            System.out.println(n.getAttributes());
                        }
                    }
                    System.out.println();
                } else {
                    System.out.println("Not book with " + valTitle);
                }
            }
            if (valNameAuth != null) { //по имени автора
                XPathExpression expr = xpath.compile("BookCatalogue/Book/Author");
                NodeList nodes = (NodeList) expr.evaluate(document, XPathConstants.NODESET);
                if (nodes.getLength() > 0) {
                    for (int i = 0; i < nodes.getLength(); i++) {
                        Node n = nodes.item(i);
                        if (n.getTextContent().equals(valNameAuth)) {
                            System.out.println("Value find");
                            System.out.println(n.getTextContent());
                        }
                    }
                    System.out.println();
                } else {
                    System.out.println("Not book with " + valNameAuth);
                }
            }
            if (valId != null) { //по ид
                XPathExpression expr = xpath.compile("BookCatalogue/Book[@id=" + valId + "]");
                NodeList nodes = (NodeList) expr.evaluate(document, XPathConstants.NODESET);
                if (nodes.getLength() > 0) {
                    for (int i = 0; i < nodes.getLength(); i++) {
                        Node n = nodes.item(i);
                        System.out.println("Value:" + n.getTextContent());
                    }
                    System.out.println();
                } else {
                    System.out.println("Not book with id: " + valId);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

    public void removeBook(String fileName, String valTitle, String valNameAuth, String valId) {
        try {
            // Строим объектную модель исходного XML файла
            final String filepath = System.getProperty("user.dir")
                    + File.separator + fileName;
            final File xmlFile = new File(filepath);
            DocumentBuilder db = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder();
            Document doc = db.parse(xmlFile);
            doc.normalize();

            // Получаем корневой элемент
            Node BookCatalogue = doc.getFirstChild();
            // Получаем элемент Book. Можно сделать это с помощью
            // метода getFirstChild(), так как мы точно знаем структуру
            // файла. Однако, лучше использовать более надежный метод
            // getElementsByTagName().
            Node book = doc.getElementsByTagName("Book").item(0);

            NodeList nodeList = BookCatalogue.getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node nextNode = nodeList.item(i);

                //удаление по названию книги
                if (nextNode.getNodeName().equals(valTitle)) {
                    BookCatalogue.removeChild(nextNode);
                }
                //удаление по имени автора
                if (nextNode.getNodeName().equals(valNameAuth)) {
                    BookCatalogue.removeChild(nextNode);
                }
                //удаление по номеру ID
                if (nextNode.getNodeName().equals(valId)) {
                    BookCatalogue.removeChild(nextNode);
                }
            }
            // Записываем изменения в XML файл
            Transformer transformer = TransformerFactory.newInstance()
                    .newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filepath));
            transformer.transform(source, result);

            System.out.println("Update complete.");

        } catch (SAXException | IOException | ParserConfigurationException
                | TransformerConfigurationException ex) {
            Logger.getLogger(DOMUtilImpl.class.getName())
                    .log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(DOMUtilImpl.class.getName())
                    .log(Level.SEVERE, null, ex);
        }

    }

}

