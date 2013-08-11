package john.maenard.androidexercise;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class HandlingXmlStuff extends DefaultHandler {
	
	private XMLDataCollected info = new XMLDataCollected();
	public String getInformation() {
		return info.dataToString();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if (localName.equals("city")) {
			String city = attributes.getValue("name");
			info.setCity(city);
		} else if (localName.equals("temperature")) {
			String t = attributes.getValue("value");
			double temp = Double.parseDouble(t);
			info.setTemp(temp);
		} else if (localName.equals("weather")) {
			String weather = attributes.getValue("value");
			info.setWeatherType(weather);
		}
	}

}
