package ru.job4j.io.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public class MainJAXB {
    public static void main(String[] args) throws JAXBException, IOException {
        final Command command = new Command(true, 10, "Lokomotiv", new Coach("Semin"),
                new String[]{"Bilyaletdinov", "Sychev"});
        JAXBContext context = JAXBContext.newInstance(Command.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(command, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Command loko = (Command) unmarshaller.unmarshal(reader);
            System.out.println(loko);
        }
    }
}
