package sr.serialization;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

enum PhoneTypeJ { MOBILE,  HOME,  WORK};

class PhoneNumberJ implements java.io.Serializable {
	private static final long serialVersionUID = 2463673954867473008L;
	public String number;
	public PhoneTypeJ type;

	public PhoneNumberJ(String number, PhoneTypeJ type) 
    { 
        this.number = number; 
        this.type = type; 
    } 
}


class PersonJ implements java.io.Serializable 
{ 
	private static final long serialVersionUID = 2363673954867473008L;
	public int a; 
	public String b; 

	public String name;
	public int id;
	public String email;
	public List<PhoneNumberJ> phones;

	public PersonJ(String name, int id, String email, List<PhoneNumberJ> phones) 
    { 
        this.name = name; 
        this.id = id; 
        this.email = email;
        this.phones = phones;
    } 

} 

public class JavaSerialization 
{ 
	public static void main(String[] args) 
	{    
		List<PhoneNumberJ> phones = new ArrayList<PhoneNumberJ>();
		phones.add(new PhoneNumberJ("+48-12-555-4321", PhoneTypeJ.HOME));
		phones.add(new PhoneNumberJ("+48-699-989-796", PhoneTypeJ.MOBILE));
		
		PersonJ object = new PersonJ("W�odzimierz Wr�blewski", 123456, "wrobel@poczta.com", phones); 
		
		String filename = "person1.ser"; 

		try
		{    
			FileOutputStream file = new FileOutputStream(filename); 
			ObjectOutputStream out = new ObjectOutputStream(file); 

			long n = 1000000;
	        System.out.println("Performing java serialization " + n + " times...");
			long start = System.nanoTime();
	        for(long i = 0; i< n; i++)
	        {
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
		        ObjectOutputStream out3 = new ObjectOutputStream(bos);
	        	out3.writeObject(object);
	        }
			long end = System.nanoTime();
			System.out.println("... finished.");
			System.out.println((end-start) / n); // in nanoseconds (1e-9)
			
			// Method for serialization of object 
			out.writeObject(object); 

			out.close(); 
			file.close(); 
		} 
		catch(IOException ex) 
		{ 
			System.out.println("IOException"); 
		} 
	} 
} 