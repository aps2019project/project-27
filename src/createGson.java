import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class createGson {
	private static Gson gson;
	public createGson(){
		GsonBuilder gsonBuilder = new GsonBuilder ();
		gson = gsonBuilder.create ();
	}
	public static Gson getGson(){
		if ( gson == null ) {
			new createGson ();
		}
		return gson;
	}
}
