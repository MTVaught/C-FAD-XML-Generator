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
			
			//66-79
			
			//80-85
			
	 
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
	}

