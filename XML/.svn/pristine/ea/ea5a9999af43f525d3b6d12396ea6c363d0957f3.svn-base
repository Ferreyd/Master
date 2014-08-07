package tp2nicolas;

import java.io.*;
import java.util.*;
import java.text.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;
import org.xml.sax.*;

/**
 * The type TP 2.
 * @author Nicolas          Date : 10/10/13 15:24
 */
public abstract class TP2
{
    private Document doc;
    private String output;
    private String input;

    /**
     * Instantiates a new TP 2.
     *
     * @param output the output
     * @param input the input
     */
    public TP2(String output, String input)
    {
        this.output = output;
        this.input = input;
    }

    /**
     * Parser void.
     *
     * @throws ParserConfigurationException the parser configuration exception
     * @throws IOException the iO exception
     * @throws SAXException the sAX exception
     */
    public void parser() throws ParserConfigurationException, IOException, SAXException
    {

        DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
        domFactory.setValidating(true);
        DocumentBuilder builder = domFactory.newDocumentBuilder();
        doc = builder.parse(input);

    }

    /**
     * Process node recursively.
     *
     * @param node the node
     * @throws Exception the exception
     */// on traverse tous les noeuds du document
    public void processNodeRecursively(Node node) throws Exception {
        performUpdates( node );
        for (Node child = node.getFirstChild(); child != null; child = child.getNextSibling()) {
            processNodeRecursively(child);
        }
    }

    /**
     * Perform updates.
     *
     * @param node the node
     * @throws Exception the exception
     */// on réalise des mises à jour
    abstract public void performUpdates(Node node) throws Exception;

    /**
     * Serialize void.
     *
     * @throws TransformerConfigurationException the transformer configuration exception
     * @throws TransformerException the transformer exception
     */// on sérialise le document
    public void serialize() throws TransformerConfigurationException, TransformerException {
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer();
        Source source = new DOMSource( doc );
        Result result = new StreamResult( output );
        transformer.transform( source, result );
    }

    /**
     * Main void.
     *
     * @param args the args
     */
    static public void main(final String[] args) {
        try {
            TP2 zoo = new TP2(args[0], args[1]) {
                // concrète implémentation de DomUpdate
                // qui cherche les animaux de moins d'un an
                // et leur ajoute la date de visite médicale

                private String DATE_NAISSANCE_ATTR = "date-naissance";
                private DateFormat DATE_FORMAT = new SimpleDateFormat("y-M-d");
                private Calendar TODAY = Calendar.getInstance();
                private String DATE_VISITE_MEDICALE = args[2];

                // le traitement à faire sur les éléments
                public void performUpdates(Node node) throws ParseException {
                    if ( node.getNodeType() == Node.ELEMENT_NODE ) {
                        Element elem = (Element) node;
                        // sont concernés les éléments qui ont un attribut "date-naissance"
                        if ( elem.hasAttribute( DATE_NAISSANCE_ATTR ) ) {
                            // on récupère l'attribut
                            String birthDate = elem.getAttribute( DATE_NAISSANCE_ATTR );
                            Calendar cal = new GregorianCalendar();
                            cal.setTime( DATE_FORMAT.parse( birthDate ) );
                            // on ajoute 1 an à la date de naissance pour comparer avec la date du jour
                            cal.add(Calendar.YEAR, 1);
                            if ( cal.after( TODAY ) ) {
                                // l'animal est jeune : on le marque pour la visite médicale
                                elem.setAttribute("visite-médicale", DATE_VISITE_MEDICALE);
                            }
                        }
                    }
                }
            };

            // on traverse le document
            zoo.processNodeRecursively( zoo.doc.getDocumentElement() );

            // on sérialise le document
            zoo.serialize();
        } catch (Exception e) {
            System.out.println("Usage :\n    DomUpdate in.xml out.xml date");
            e.printStackTrace();
        }
    }


}
