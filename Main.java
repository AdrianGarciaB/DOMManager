import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws ParserConfigurationException, TransformerException, IOException, SAXException, DOMManager.ModeException {
	      // Modo Añadir
        DOMManager domManager = new DOMManager(DOMManager.MODE.ADD, new File("data/output.xml"), "alumnes");
        for (int i = 4; i <= 6; i++) {
            domManager.write("alumne", true);

            domManager.write("ID", String.valueOf(i));
            domManager.write("Nom", "Alumno-"+ i);
            domManager.write("Curso", "DAM");
            domManager.setParent(1);
        }
        domManager.close();



        }
    }

