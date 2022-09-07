import com.google.gson.Gson;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stax.StAXSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

public class UnmarshalJAXB {
    public static void main(String[] args) throws JAXBException, TransformerConfigurationException, IOException, SAXException, XMLStreamException {
        File file = new File("src/main/resources/example2.xml");

        // Валидация
        boolean ok = schemaValidator();

        if (ok) {
            JAXBContext jaxbContext = JAXBContext.newInstance(SystemXML.class);

            // Анмаршалинг
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            SystemXML systemXML = (SystemXML) jaxbUnmarshaller.unmarshal(file);

            // Меняем объект
            systemXML.personList.personList.get(0).firstName = "Anton";
            systemXML.personList.personList.get(0).lastName = "Antonov";
            systemXML.personList.personList.get(0).pnoneNumber = "12345";
            systemXML.chatHistory.chatList.get(1).messageList.messageList.get(0).content.body = "NEW MESSAGE";

            // Создаём HTML
            htmlGen(systemXML);

            // Создаём XML с измененными данными
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            jaxbMarshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "src/main/resources/XMLScheme.xsd");

            OutputStream os = new FileOutputStream(".\\myxml.xml");
            jaxbMarshaller.marshal(systemXML, os);

            try (FileWriter writer = new FileWriter("myjson.json", false)) {
                writer.write(new Gson().toJson(systemXML));
                writer.flush();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public static void htmlGen(SystemXML system) throws IOException, TransformerConfigurationException, SAXException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss.SS", Locale.ENGLISH);
        String encoding = "UTF-8";
//        File file = new File(Objects.requireNonNull(XMLstaxParser.class.getClassLoader().getResource("example2.xml")).getFile());
        FileOutputStream fos = new FileOutputStream(".\\myfile.html");
        OutputStreamWriter writer = new OutputStreamWriter(fos, encoding);
        StreamResult streamResult = new StreamResult(writer);

        SAXTransformerFactory saxFactory =
                (SAXTransformerFactory) TransformerFactory.newInstance();
        TransformerHandler tHandler = saxFactory.newTransformerHandler();
        tHandler.setResult(streamResult);

        Transformer transformer = tHandler.getTransformer();
        transformer.setOutputProperty(OutputKeys.METHOD, "html");
        transformer.setOutputProperty(OutputKeys.ENCODING, encoding);
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");


        writer.write("<!DOCTYPE html>\n");
        writer.flush();
        tHandler.startDocument();
        tHandler.startElement("", "", "html", new AttributesImpl());
        tHandler.startElement("", "", "head", new AttributesImpl());
        tHandler.startElement("", "", "link rel=\"stylesheet\" href=\"mysite.css\"", new AttributesImpl());
        tHandler.startElement("", "", "title", new AttributesImpl());
        tHandler.characters("Состояния чатов".toCharArray(), 0, "Состояния чатов".length());
        tHandler.endElement("", "", "title");
        tHandler.endElement("", "", "head");
        tHandler.startElement("", "", "body", new AttributesImpl());

        //Заголовок
        tHandler.startElement("", "", "h1", new AttributesImpl());
        tHandler.characters("Пользователи".toCharArray(), 0, "Пользователи".length());
        tHandler.endElement("", "", "h1");

        //Таблица
        tHandler.startElement("", "", "table", new AttributesImpl());
        //ID
        tHandler.startElement("", "", "td", new AttributesImpl());
        tHandler.characters("ID".toCharArray(), 0, "ID".length());
        tHandler.endElement("", "", "td");
        //Имя
        tHandler.startElement("", "", "td", new AttributesImpl());
        tHandler.characters("Имя".toCharArray(), 0, "Имя".length());
        tHandler.endElement("", "", "td");
        //Фамилия
        tHandler.startElement("", "", "td", new AttributesImpl());
        tHandler.characters("Фамилия".toCharArray(), 0, "Фамилия".length());
        tHandler.endElement("", "", "td");
        //Номер телефона
        tHandler.startElement("", "", "td", new AttributesImpl());
        tHandler.characters("Номер телефона".toCharArray(), 0, "Номер телефона".length());
        tHandler.endElement("", "", "td");


        for (Person i : system.personList.personList) {
            tHandler.startElement("", "", "tr", new AttributesImpl());
            //ID
            tHandler.startElement("", "", "td", new AttributesImpl());
            tHandler.characters(Integer.toString(i.id).toCharArray(), 0, Integer.toString(i.id).length());
            tHandler.endElement("", "", "td");
            //Имя
            tHandler.startElement("", "", "td", new AttributesImpl());
            tHandler.characters(i.firstName.toCharArray(), 0, i.firstName.length());
            tHandler.endElement("", "", "td");
            //Фамилия
            tHandler.startElement("", "", "td", new AttributesImpl());
            tHandler.characters(i.lastName.toCharArray(), 0, i.lastName.length());
            tHandler.endElement("", "", "td");
            //Номер телефона
            tHandler.startElement("", "", "td", new AttributesImpl());
            tHandler.characters(i.pnoneNumber.toCharArray(), 0, i.pnoneNumber.length());
            tHandler.endElement("", "", "td");


            tHandler.endElement("", "", "tr");
        }
        tHandler.startElement("", "", "tr", new AttributesImpl());
        tHandler.startElement("", "", "th", new AttributesImpl());
        tHandler.endElement("", "", "th");
        tHandler.startElement("", "", "td", new AttributesImpl());
        tHandler.characters("Всего пользователей".toCharArray(), 0, "Всего пользователей".length());
        tHandler.endElement("", "", "td");
        tHandler.startElement("", "", "td", new AttributesImpl());
        tHandler.characters(Integer.toString(system.personList.personList.size()).toCharArray(), 0, Integer.toString(system.personList.personList.size()).length());
        tHandler.endElement("", "", "td");
        tHandler.endElement("", "", "tr");

        tHandler.endElement("", "", "table");

        for (int i = 0; i < system.chatHistory.chatList.size(); i++) {
            //Заголовок
            tHandler.startElement("", "", "h1", new AttributesImpl());
            if (system.chatHistory.chatList.get(i).type.group != null) {
//            if (system.chatHistory.chatList.get(i) == "group") {
                String chatName = ("Чат " + system.chatHistory.chatList.get(i).id + " | Тип чата: group ") +
                        " | Чат создан: " + formatter.format(system.chatHistory.chatList.get(i).type.group.dateOfCreation) + " | Название чата: " + system.chatHistory.chatList.get(i).type.group.title;
                tHandler.characters(chatName.toCharArray(),
                        0,
                        chatName.length());
            } else {
                String chatName = ("Чат " + system.chatHistory.chatList.get(i).id + " | Тип чата: private") +
                        " | Чат создан: " + formatter.format(system.chatHistory.chatList.get(i).type.aPrivate.dateOfCreation);
                tHandler.characters(chatName.toCharArray(),
                        0,
                        chatName.length());
            }
            tHandler.endElement("", "", "h1");

            //Таблица 2
            tHandler.startElement("", "", "table", new AttributesImpl());
            tHandler.startElement("", "", "tr", new AttributesImpl());
            //ID отправителя
            tHandler.startElement("", "", "td", new AttributesImpl());
            tHandler.characters("ID отправителя".toCharArray(), 0, "ID отправителя".length());
            tHandler.endElement("", "", "td");
            //ID получателя
            tHandler.startElement("", "", "td", new AttributesImpl());
            tHandler.characters("ID получателя".toCharArray(), 0, "ID получателя".length());
            tHandler.endElement("", "", "td");
            //Время сообщения
            tHandler.startElement("", "", "td", new AttributesImpl());
            tHandler.characters("Время сообщения".toCharArray(), 0, "Время сообщения".length());
            tHandler.endElement("", "", "td");
            //Тип сообщения
            tHandler.startElement("", "", "td", new AttributesImpl());
            tHandler.characters("Тип сообщения".toCharArray(), 0, "Тип сообщения".length());
            tHandler.endElement("", "", "td");
            //Кодировка
            tHandler.startElement("", "", "td", new AttributesImpl());
            tHandler.characters("Кодировка".toCharArray(), 0, "Кодировка".length());
            tHandler.endElement("", "", "td");
            //Контент сообщения
            tHandler.startElement("", "", "td", new AttributesImpl());
            tHandler.characters("Контент сообщения".toCharArray(), 0, "Контент сообщения".length());
            tHandler.endElement("", "", "td");
            tHandler.endElement("", "", "tr");

            for (Message m : system.chatHistory.chatList.get(i).messageList.messageList) {
                tHandler.startElement("", "", "tr", new AttributesImpl());

                //ID отправителя
                tHandler.startElement("", "", "td", new AttributesImpl());
                tHandler.characters(Integer.toString(m.sender.idref).toCharArray(), 0, Integer.toString(m.sender.idref).length());
                tHandler.endElement("", "", "td");
                //ID получателя
                tHandler.startElement("", "", "td", new AttributesImpl());
                tHandler.characters(Integer.toString(m.receivers.idref).toCharArray(), 0, Integer.toString(m.receivers.idref).length());
                tHandler.endElement("", "", "td");
                //Время сообщения
                tHandler.startElement("", "", "td", new AttributesImpl());
                tHandler.characters(formatter.format(m.time).toCharArray(), 0, formatter.format(m.time).length());
                tHandler.endElement("", "", "td");
                //Тип сообщения
                //TODO
                tHandler.startElement("", "", "td", new AttributesImpl());
                try {
                    tHandler.characters(m.content.type.toCharArray(), 0, m.content.type.length());
                } catch (Exception e) {
                    tHandler.characters("".toCharArray(), 0, "".length());
                }
                tHandler.endElement("", "", "td");
                //Кодировка
                tHandler.startElement("", "", "td", new AttributesImpl());
                try {
                    tHandler.characters(Boolean.toString(m.content.messageEncoded).toCharArray(), 0, Boolean.toString(m.content.messageEncoded).length());
                } catch (Exception e) {
                    tHandler.characters("false".toCharArray(), 0, "false".length());
                }
                tHandler.endElement("", "", "td");
                //Контент сообщения
                tHandler.startElement("", "", "td", new AttributesImpl());
                tHandler.characters(m.content.body.toCharArray(), 0, m.content.body.length());
                tHandler.endElement("", "", "td");

                tHandler.endElement("", "", "tr");
            }
            tHandler.endElement("", "", "table");
        }

        tHandler.endElement("", "", "body");
        tHandler.endElement("", "", "html");
        tHandler.endDocument();
        writer.close();

        fos.close();
    }

    public static boolean schemaValidator() throws SAXException, IOException, XMLStreamException {
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(".\\src\\main\\resources\\XMLScheme.xsd"));
            Validator validator = schema.newValidator();
            XMLStreamReader reader = XMLInputFactory.newInstance().createXMLStreamReader(Files.newInputStream(Paths.get(".\\src\\main\\resources\\example2.xml")));
            validator.validate(new StAXSource(reader));

            System.out.println("XML is valid");
            return true;
        } catch (Exception e) {
            System.out.println("XML is not valid");
            return false;
        }
    }
}
