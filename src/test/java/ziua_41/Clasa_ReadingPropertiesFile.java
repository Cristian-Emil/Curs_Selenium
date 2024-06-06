package ziua_41;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Properties;
import java.util.Set;

public class Clasa_ReadingPropertiesFile {

	public static void main(String[] args) throws IOException, InterruptedException {
		
//	Location of properties file
		FileInputStream file=new FileInputStream("C:\\Users\\CRISTIAN ZIDARESCU\\IdeaProjects\\Project_with_Selenium\\src\\test\\java\\ziua_41\\Fisiere_41\\config.properties");
		
		
//	Loading properties file
		Properties propertiesobj=new Properties();
		propertiesobj.load(file);
		Thread.sleep(2000);

//Reading data from properties file
		
		String url=propertiesobj.getProperty("appurl");
		String email=propertiesobj.getProperty("email");
		String pwd=propertiesobj.getProperty("password");
		String orid=propertiesobj.getProperty("orderid");
		String custid=propertiesobj.getProperty("customerid");

		Thread.sleep(2000);

		System.out.println("Valorile initiale sunt : " + url+" "+email+"  "+pwd+"  "+orid+" "+custid + "\n");

//Reading all the keys from properties file
		System.out.println("Primul set de valori : " );
		Set<String> keys=propertiesobj.stringPropertyNames();
		System.out.println(keys + "\n"); //[password, orderid, customerid, appurl, email]

		System.out.println("Al doilea set de valori : " );
		Set<Object> keys1 =propertiesobj.keySet();
		System.out.println(keys1 + "\n"); //[password, orderid, customerid, appurl, email]


//Reading all teh values from properties file
		Collection<Object> values=propertiesobj.values();
		System.out.println("Restul valorilor : ");
		System.out.println(values); //[abcxyz, 123, 234, https://demo.opencart.com/, abc@gmail.com]

	Thread.sleep(3500);
		file.close();
		
	}

}
