package alararestaurant.parsers;

import javax.xml.bind.JAXBException;
import java.io.BufferedWriter;

public interface JaxbParser {

    <T> T toString(String resourcePath, Class<T> klass) throws JAXBException;

    void fromString(Object object, BufferedWriter bfw);
}
