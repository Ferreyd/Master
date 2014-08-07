
package tp2domjeremy;
import java.io.*;
import java.util.*;
import java.text.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;
import org.xml.sax.*;

public abstract class DomUpdate {

	// arbre XML
	private Document doc;
	// fichier produit en sortie
	private String output;

	// on parse le document XML
	public DomUpdate(String input, String output) throws ParserConfigurationException, SAXException, IOException {
		// on parse le document XML
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setValidating( true );
		DocumentBuilder parser = factory.newDocumentBuilder();
		doc = parser.parse( input );
		this.output = output;
	}

	// on traverse tous les noeuds du document
	public void processNodeRecursively(Node node) throws Exception {
		performUpdates( node );
		for (Node child = node.getFirstChild(); child != null; child = child.getNextSibling()) {
			processNodeRecursively(child);
		}
	}

	// on réalise des mises à jour
	abstract public void performUpdates(Node node) throws Exception;

	// on sérialise le document
	public void serialize() throws TransformerConfigurationException, TransformerException {
		TransformerFactory factory = TransformerFactory.newInstance();
		Transformer transformer = factory.newTransformer();
		Source source = new DOMSource( doc );
		Result result = new StreamResult( output );
		transformer.transform( source, result );
	}

	static public void main(final String[] args) {
		try {
			DomUpdate zoo = new DomUpdate(args[0], args[1]) {
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
			System.out.println("Usage :\n DomUpdate in.xml out.xml date");
			e.printStackTrace();
		}
	}

}