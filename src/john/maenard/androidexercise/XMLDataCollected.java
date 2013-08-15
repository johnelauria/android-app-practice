package john.maenard.androidexercise;

public class XMLDataCollected {
	private double temp = 0;
	private String city = null;
	private String weather = null;
	
	public void setCity(String c) {
		city = c;
	}
	
	public void setTemp(double t) {
		temp = t;
	}
	
	public void setWeatherType(String w) {
		weather = w;
	}
	
	public String dataToString() {
		return "The current temperature in " + city + " is " + (temp - 272.15) + " degrees celcius.\nThe current weather is " + weather;
	}
}
