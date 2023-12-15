package org.example;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class BookParser {


    // 创建 BookShelf 对象
    public static void main(String[] args) throws ParserConfigurationException, IOException, TransformerException, SAXException {
        BookShelf XMLBookshelf = new BookShelf();
        BookShelf JSONBookshelf = new BookShelf();
        // Parse XML
        System.out.println("Parsing XML:");
        parseXML("src/main/resources/BookShelf.xml",XMLBookshelf);

        // Parse JSON
        System.out.println("\nParsing JSON:");
        parseJSON("src/main/resources/Bookshelf.json",JSONBookshelf);

        // Add a book programmatically
        System.out.println("\nAdding a book programmatically:");
        ArrayList newBookAuthors= new ArrayList<>();
        newBookAuthors.add("J.K.Rowling");
        Book newBook=new Book("Harry Potter","2000","498",newBookAuthors);

        //add new book to xml file and check the content of xmlbookshelf
        XMLBookshelf.addBook(newBook);
        XMLBookshelf.updateXMLFile(newBook,"src/main/resources/BookShelf.xml");
        System.out.println("Here is the info of XMLBookShelf: ");
        XMLBookshelf.printBookshelf();

        //add the new book to json file and check the content of jsonbookshelf
        JSONBookshelf.addBook(newBook);
        JSONBookshelf.updateJSONFile(newBook,"src/main/resources/Bookshelf.json");
        System.out.println("Here is the info of JSONBookShelf: ");
        JSONBookshelf.printBookshelf();
    }

    private static String getElementValue(Element element, String tagName) {
        NodeList nodeList = element.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent();
        } else {
            return "";
        }
    }
    private static void parseXML(String uri,BookShelf bookshelf) {
        try {
            // Create a DocumentBuilder
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Parse the XML file
            Document document = builder.parse(uri);
            // Get the root element
            Element rootElement = document.getDocumentElement();
            // Get the list of book elements
            NodeList bookList = rootElement.getElementsByTagName("Book");

            // Iterate through each book element
            for (int i = 0; i < bookList.getLength(); i++) {
                Node bookNode = bookList.item(i);

                if (bookNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element bookElement = (Element) bookNode;

                    // Create a Book object
                    Book book = new Book();

                    // Set values from XML
                    book.setTitle(getElementValue(bookElement, "title"));
                    book.setPublishedYear(getElementValue(bookElement, "publishedYear"));
                    book.setNumberOfPages(getElementValue(bookElement, "numberOfPages"));
                    bookshelf.addBook(book);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void parseJSON(String uri,BookShelf bookshelf) {
        try {
            // 使用 FileReader 和 BufferedReader 读取文件
            FileReader fileReader = new FileReader(uri);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // 读取文件内容并存储为 StringBuilder
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }

            // 关闭读取器
            bufferedReader.close();
            System.out.println(stringBuilder);
            // Remove all spaces from the StringBuilder using replace
            // 去掉json中的空格
            int index;
            while ((index = stringBuilder.indexOf(" ")) != -1) {
                stringBuilder.replace(index, index + 1, "");
            }

            // 先把stringBuilder转换成字符串，再使用 JSONObject 解析 JSON 字符串
            JSONObject bookshelfObject = new JSONObject(new String(stringBuilder));

            // 获取 BookShelf 对象
            JSONObject bookShelfInfo = bookshelfObject.getJSONObject("BookShelf");
            //获取 Book 列表
            JSONArray bookArray = bookShelfInfo.getJSONArray("Book");

            // 遍历 Book 列表
            for (int i = 0; i < bookArray.length(); i++) {
                JSONObject bookObject = bookArray.getJSONObject(i);
                String title=bookObject.getString("title");
                String numberOfPages=bookObject.getString("numberOfPages");
                String publishedYear=bookObject.getString("publishedYear");
                JSONArray authorsArray = bookObject.getJSONArray("authors");
                ArrayList<String> authors = new ArrayList<>();
                for (int j = 0; j < authorsArray.length(); j++) {
                    authors.add(authorsArray.getString(j));
                }
                Book book = new Book(title, publishedYear, numberOfPages, authors);
                bookshelf.addBook(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}