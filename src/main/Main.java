package main;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Builds a xml that is in this format:
 * <!--?xml version="1.0" encoding="utf-8"?-->
<maps xmlns:android="http://schemas.android.com/apk/res/android">
    <map name="gridmap">
        <area id="@+id/area1001" shape="rect" coords="118,124,219,226" />
        <area id="@+id/area1002" shape="rect" coords="474,374,574,476" />
        <area id="@+id/area1004" shape="rect" coords="710,878,808,980" />
        <area id="@+id/area1005" shape="circle" coords="574,214,74" />
        <area id="@+id/area1006" shape="poly" coords="250,780,250,951,405,951" />
        <area id="@+id/area1007" shape="poly" coords="592,502,592,730,808,730,808,502,709,502,709,603,690,603,690,502" />
    </map>
    <map name="usamap">
        <area id="@+id/area1" shape="poly" coords="230,35,294,38,299,57,299,79,228,79" />
...
     </map>
</maps>
 * @author daniel
 *
 */
public class Main 
{
	public static void main(String argv[])
	{ 
		  try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			// root elements
			Document doc = docBuilder.newDocument();
			Element root = doc.createElement( "map" );
			root.setAttribute( "name" , "multimap" );
			doc.appendChild( root );
			
			//1-4
			generateRun(new Pair(395,764), new Pair(423,799), 1, 4, 30, 0,
					doc, root);
			
			//5
			generateSingleBox(new Pair(515,764), new Pair(573, 799), 5, doc, root);
			
			//6-20
			generateRun(new Pair(575,764), new Pair(603,799), 6, 20, 30, 0,
					doc, root);
			
			//21
			generateSingleBox(new Pair(1025,687), new Pair(1050, 760), 21, doc, root);
			
			//22-34
			generateRun(new Pair(1025,650), new Pair(1050,685), 22, 34, 0, -37,
					doc, root);
			//35
			generateSingleBox(new Pair(1025,130), new Pair(1050, 197), 35, doc, root);
			
			//Back row configuration 36-65
			Pair upperLeft = new Pair( 935, 126 );
			Pair lowerRight = new Pair( 963, 161 );
			int boothStart = 36;
			int boothEnd = 65;
			int space = 30; //space b/w booths
			for ( int i = boothStart; i <= boothEnd; i++ )
			{
				String id = "@+id/booth" + i;
				String shape = "rect";
				String coords = "" + upperLeft.getX() + "," + upperLeft.getY() + "," +
								"" + lowerRight.getX() + "," + lowerRight.getY();
				
				Element booth = doc.createElement( "area" );
				booth.setAttribute("id", id );
				booth.setAttribute("shape", shape);
				booth.setAttribute("coords", coords);
				root.appendChild( booth );
				
				upperLeft.setX( upperLeft.getX() - (space) );
				lowerRight.setX( lowerRight.getX() - (space) );
				
			}
			
			//Left-Most Column
			
			// Unique Case 66
			upperLeft = new Pair(35, 163);
			lowerRight = new Pair(62, 237);
			boothStart = 66;
			Element uniqueBooth = doc.createElement("area");
			uniqueBooth.setAttribute("id", "@+id/booth" + boothStart);
			uniqueBooth.setAttribute("shape", "rect");
			uniqueBooth.setAttribute("coords", "" + upperLeft.getX() + ","
					+ upperLeft.getY() + "," + "" + lowerRight.getX() + ","
					+ lowerRight.getY());
			root.appendChild(uniqueBooth);

			// Main chunk 67-78
			upperLeft = new Pair(36, 239);
			lowerRight = new Pair(60, 273);
			boothStart = 67;
			boothEnd = 78;
			space = 37;
			for (int i = boothStart; i <= boothEnd; i++) {
				String id = "@+id/booth" + i;
				String shape = "rect";
				String coords = "" + upperLeft.getX() + "," + upperLeft.getY()
						+ "," + "" + lowerRight.getX() + ","
						+ lowerRight.getY();

				Element booth = doc.createElement("area");
				booth.setAttribute("id", id);
				booth.setAttribute("shape", shape);
				booth.setAttribute("coords", coords);
				root.appendChild(booth);

				upperLeft.setY(upperLeft.getY() + (space));
				lowerRight.setY(lowerRight.getY() + (space));

			}

			// Unique Case 79
			upperLeft = new Pair(35, 687);
			lowerRight = new Pair(62, 762);
			boothStart = 79;
			uniqueBooth = doc.createElement("area");
			uniqueBooth.setAttribute("id", "@+id/booth" + boothStart);
			uniqueBooth.setAttribute("shape", "rect");
			uniqueBooth.setAttribute("coords", "" + upperLeft.getX() + ","
					+ upperLeft.getY() + "," + "" + lowerRight.getX() + ","
					+ lowerRight.getY());
			root.appendChild(uniqueBooth);
			
			//80-84
			
			upperLeft = new Pair(64, 764);
			lowerRight = new Pair(92, 799);
			boothStart = 80;
			boothEnd = 84;
			space = 30;
			generateRun(upperLeft, lowerRight, boothStart, boothEnd, space, 0,
					doc, root);
			
			//Unique Case 85
			upperLeft = new Pair(214, 764);
			lowerRight = new Pair(272, 799);
			boothStart = 85;
			generateRun(upperLeft, lowerRight, 85, 85, 0, 0, doc, root);
			
			//212
			generateSingleBox(new Pair(907,631), new Pair(937, 666), 212, doc, root);
			//213
			generateSingleBox(new Pair(896,556), new Pair(921, 629), 213, doc, root);
			//214-220
			generateRun(new Pair(896,519), new Pair(920,554), 214, 220, 0, -38,
					doc, root);
			//221
			generateSingleBox(new Pair(907,256), new Pair(937, 291), 221, doc, root);
			//222
			generateSingleBox(new Pair(923,293), new Pair(947, 366), 222, doc, root);
			//223-229
			generateRun(new Pair(923,369), new Pair(947,404), 223, 229, 0, 36,
					doc, root);
			
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("file.xml"));
	 
			// Output to console for testing
			//StreamResult result = new StreamResult(System.out);
	 
			transformer.transform(source, result);
	 
			System.out.println("File saved!");
	 
		  } catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		  } catch (TransformerException tfe) {
			tfe.printStackTrace();
		  }
		}
		
	private static void generateSingleBox(Pair upperLeft, Pair lowerRight,
			int boothID, Document doc, Element root) {
		generateRun(upperLeft, lowerRight, boothID, boothID, boothID, boothID,
				doc, root);
	}
	
	private static void generateRun(Pair upperLeft, Pair lowerRight,
			int boothStart, int boothEnd, int spaceX, int spaceY, Document doc,
			Element root) {
		for (int i = boothStart; i <= boothEnd; i++) {
			String id = "@+id/booth" + i;
			String shape = "rect";
			String coords = "" + upperLeft.getX() + "," + upperLeft.getY()
					+ "," + "" + lowerRight.getX() + "," + lowerRight.getY();

			Element booth = doc.createElement("area");
			booth.setAttribute("id", id);
			booth.setAttribute("shape", shape);
			booth.setAttribute("coords", coords);
			root.appendChild(booth);

			upperLeft.setX(upperLeft.getX() + (spaceX));
			lowerRight.setX(lowerRight.getX() + (spaceX));
			
			upperLeft.setY(upperLeft.getY() + (spaceY));
			lowerRight.setY(lowerRight.getY() + (spaceY));

		}
	}
}
