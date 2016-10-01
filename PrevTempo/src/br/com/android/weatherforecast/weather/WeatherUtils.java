package br.com.android.weatherforecast.weather;

import java.util.Calendar;


/**
 * Utilitarios
 * @author Felipe Cobello
 *
 */
public class WeatherUtils {

	public static int fahrenheitToCelsius(int tFahrenheit) 
	{
		return (int) ((5.0f / 9.0f) * (tFahrenheit - 32));
	}

	public static int celsiusToFahrenheit(int tCelsius) 
	{
		return (int) ((9.0f / 5.0f) * tCelsius + 32);
	}
	
//	public static boolean checkInternet(Context ctx) 
//	{
//	    NetworkInfo info = (NetworkInfo) ((ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
//	    
//	    if (info == null || !info.isConnected()) 
//	        return false;
//	    if (info.isRoaming())
//	        return false;
//	    return true;
//	}
	
	public static String getImage(String fileName) 
	{
		boolean day = Calendar.getInstance().get(Calendar.HOUR_OF_DAY) > 6 && Calendar.getInstance().get(Calendar.HOUR_OF_DAY) < 18;
    	String retorno = "undefined.png";
    	
		if(fileName.startsWith("partly_cloudy") || fileName.startsWith("cloudy"))
			retorno = day ? "cloud.png":"cloud_night.png";
		else if(fileName.startsWith("mostly_cloudy"))
			retorno = day ? "mostly_cloudy.png":"cloud_night";
		else if(fileName.contains("rain_snow"))
			retorno = day ? "rain_snow.png":"snow_night.png";
		else if(fileName.contains("snow"))
			retorno = day ? "snow.png":"snow_night.png";
		else if(fileName.startsWith("storm"))
			retorno = day ? "thunderstorm.png":"thunderstorm_night.png";
		else if(fileName.contains("sunny"))
			retorno = day ? "sunny.png":"sunny_night.png";
		else if(fileName.contains("rain"))
			retorno = day ? "rain.png":"rain_night.png";
		else if(fileName.contains("haze") || fileName.contains("fog"))
			retorno = day ? "fog.png":"fog_night.png";
		else if(fileName.contains("mist"))
			retorno = day ? "rain.png":"rain_night.png";
		return retorno;
	}
	
	public static String captalizeWords(String words)
	{
		char[] caracteres = words.toCharArray();
		
		for (int i = 0; i < caracteres.length; i++)
		{
			if(i == 0 || caracteres[i-1] == ' ')
			{
				caracteres[i] = Character.toUpperCase(caracteres[i]);
			}
		}
		return new String(caracteres);
	}
}
