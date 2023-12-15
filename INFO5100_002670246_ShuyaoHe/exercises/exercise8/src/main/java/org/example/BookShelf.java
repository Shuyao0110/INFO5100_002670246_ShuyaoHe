package org.example;

import com.google.gson.*;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@XStreamAlias("BookShelf")
public class BookShelf {
    public static String beanToXml(Object obj) {
        XStream xstream = new XStream(new DomDriver("UTF-8"));
        //不输出class信息,不然标签中会包含class属性
        xstream.aliasSystemAttribute(null, "class");
        //支持注解，不然使用的 @XStreamAlias() 注解不会生效,不生效并不会报错，可以测试看下
        xstream.autodetectAnnotations(true);
        return xstream.toXML(obj);
    }

    private List<Book> books = new ArrayList<>();
    public BookShelf() {

    }

    public ArrayList getBooks(){
        ArrayList<String> booksTitle = new ArrayList<>();
        for (Book book: books){
            booksTitle.add(book.getTitle());
        }
        return booksTitle;
    }
    public void addBook(Book book) throws ParserConfigurationException, IOException, TransformerException, SAXException {
        this.books.add(book);
        //addBookToXMLFile(book, uri);
        System.out.println("Successfully add the book: "+book.getTitle()+" to the bookshelf!");
    }

    // Print all books in the bookshelf
    public void printBookshelf() {
        for (Book book : books) {
            System.out.println("Book Name: "+book.getTitle());
            System.out.println("Book Pages: "+book.getNumberOfPages());
            System.out.println("Published Year: "+ book.getPublishedYear());
            System.out.println("Authors: "+book.getAuthors());
            System.out.println("////////////////////////////////////");
        }}
    public void updateXMLFile(Book book, String uri) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        try {
            // 读取现有的 XML 文件
            File xmlFile = new File(uri);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            // 创建新的 Book 元素
            Element newBook = doc.createElement("Book");

            // 添加 title 元素
            Element titleElement = doc.createElement("title");
            titleElement.appendChild(doc.createTextNode(book.getTitle()));
            newBook.appendChild(titleElement);

            // 添加 publishedYear 元素
            Element publishedYearElement = doc.createElement("publishedYear");
            publishedYearElement.appendChild(doc.createTextNode(book.getPublishedYear()));
            newBook.appendChild(publishedYearElement);

            // 添加 numberOfPages 元素
            Element numberOfPagesElement = doc.createElement("numberOfPages");
            numberOfPagesElement.appendChild(doc.createTextNode(book.getNumberOfPages()));
            newBook.appendChild(numberOfPagesElement);

            // 添加 authors 元素
            Element authorsElement = doc.createElement("authors");
            String authors=new String();
            for (String author: book.getAuthors()){
                authors+=author;
            }
            authorsElement.appendChild(doc.createTextNode(authors));
            newBook.appendChild(authorsElement);

            // 获取 BookShelf 元素
            Element bookShelf = (Element) doc.getElementsByTagName("BookShelf").item(0);

            // 在 BookShelf 元素下追加新的 Book 元素
            bookShelf.appendChild(newBook);

            // 将修改后的 XML 写回文件
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(xmlFile);
            transformer.transform(source, result);
            System.out.println("New Book added successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void updateJSONFile(Book book, String uri){
        try {
            // 读取原始 JSON 文件
            String originalJson = readJsonFromFile(uri);

            // 创建 GsonBuilder 实例
            GsonBuilder gsonBuilder = new GsonBuilder();

            // 创建 Gson 实例
            Gson gson = gsonBuilder.create();

            // 解析原始 JSON 文件为 JsonObject
            JsonObject jsonObject = gson.fromJson(originalJson, JsonObject.class);

            // 获取原始的 Book 数组
            JsonArray originalBooks = jsonObject.getAsJsonObject("BookShelf").getAsJsonArray("Book");

            // 创建新的 Book 对象
            JsonObject newBook = new JsonObject();
            newBook.addProperty("title", book.getTitle());
            newBook.addProperty("publishedYear", book.getPublishedYear());
            newBook.addProperty("numberOfPages", book.getNumberOfPages());
            newBook.add("authors", gson.toJsonTree(book.getAuthors()));


            // 在原始 Book 数组中添加新的 Book
            originalBooks.add(newBook);

            // 将修改后的内容写回文件
            writeJsonToFile(jsonObject, uri);

            System.out.println("New Book has been appended to the JSON file.");
            System.out.println("File content has been rewritten.");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static String readJsonFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void writeJsonToFile(JsonObject jsonObject, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(jsonObject);
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 自定义 ExclusionStrategy
    class ClassPathExclusionStrategy implements ExclusionStrategy {
        @Override
        public boolean shouldSkipField(FieldAttributes f) {
            return false;
        }

        @Override
        public boolean shouldSkipClass(Class<?> clazz) {
            // 返回 true 表示跳过类的完整路径
            return false;
        }
    }

}


