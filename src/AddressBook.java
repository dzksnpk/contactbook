import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;


class Contact implements Serializable {
	
	private static final long serialVersionUID = 3254061273213948621L;
	private String name;
	private String surname;
	private String phoneNumber;
	private String email;
	

	public Contact(String name, String surname, String phoneNumber, String email) {
		this.name = name;
		this.surname = surname;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}
	public String getFullName() {
		return name + " " + surname;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Contact [name=" + name + ", surname=" + surname + ", phoneNumber=" + phoneNumber + ", email=" + email
				+ "]";
	}

}

public class AddressBook {
	public static AddressBook instance;
	private ArrayList<Contact> contacts;
	private Scanner scanner;
	
	public static void main(String[] args) throws ClassNotFoundException, FileNotFoundException {
		
		instance = new AddressBook();
		instance.init();
		instance.run();
		
	}

	@SuppressWarnings("unchecked")
	private void init() throws ClassNotFoundException, FileNotFoundException{
		
		contacts = new ArrayList<Contact>();
		scanner = new Scanner(System.in);
		
		//load file
		 try
	        {	
			 	      File infile = new File("src/adressbook.txt");
			 	      infile.createNewFile();
	            FileInputStream fis = new FileInputStream(infile);
	            ObjectInputStream ois = new ObjectInputStream(fis);
	            contacts = (ArrayList<Contact>) ois.readObject();
	            ois.close();
	        }
		 	catch (EOFException e) {
		 		System.out.println();
		 	}
	        catch (IOException e)
	        {
	            // no file
	            e.printStackTrace();
	        }
		
	}

	private void run() {
		
		System.out.println("What do you want to do?");
		System.out.println("1-add contact");
		System.out.println("2-search contacts");
		System.out.println("3-edit contacts");
		System.out.println("4-list contacts");
		System.out.println("5-delete contact");
		System.out.println("6-quit");

		int option = scanner.nextInt();
		
		switch (option) {
		case 1:
			addContact();
			break;
		case 2:
			searchContacts();
			break;
		case 3:
			editContacts();
			break;
		case 4:
			listContacts();
			break;
		case 5:
			deleteContact();
			break;
		case 6:
			 // save to file
	        try
	        {
              
	            FileOutputStream fos = new FileOutputStream("src/adressbook.txt");
	            ObjectOutputStream oos = new ObjectOutputStream(fos);
	            oos.writeObject(contacts);
	            oos.close();
	        }
	        catch (IOException e)
	        {
	            e.printStackTrace();
	        }
			
			System.exit(0);
		default:
			System.out.println("Error");
			break;
		}
		
	}

	private void addContact() {
		System.out.println("Name:");
		String name = scanner.next();
		System.out.println("Surname:");
		String surname = scanner.next();
		System.out.println("phonenumber:");
		String phoneNumber = scanner.next();
		System.out.println("email:");
		String email = scanner.next();
		contacts.add(new Contact(name,surname,phoneNumber,email));
		
		instance.run();
	}

	private void searchContacts() {
		System.out.println("Search by (1)name (2)surname (3)phone number :");
		int option2 = scanner.nextInt();
		
		switch (option2) {
		case 1:
			if (option2 == 1)
			for(int i=0; i < contacts.size(); i++) {
				if (contacts.get(i).getName().equals(scanner.next()))
						System.out.println(contacts.get(i));
						instance.run();
			}
		case 2:
			if (option2 == 2)
				for(int i=0; i < contacts.size(); i++) {
					if (contacts.get(i).getSurname().equals(scanner.next()))
							System.out.println(contacts.get(i));
							instance.run();
				}
		case 3:
			if (option2 == 3)
				for(int i=0; i < contacts.size(); i++) {
					if (contacts.get(i).getPhoneNumber().equals(scanner.next()))
							System.out.println(contacts.get(i));
							instance.run();
				}
		}
	}

	private void editContacts() {
		System.out.println("Type only name of contact:");
		for (int i=0; i != contacts.size(); i++) {
			if(contacts.get(i).getName().equals(scanner.next()))
				System.out.println(contacts.get(i));
			System.out.println();
			System.out.println("Name:");
			String name = scanner.next();
			contacts.get(i).setName(name);
			
			System.out.println("Surname:");
			String surname = scanner.next();
			contacts.get(i).setSurname(surname);
			
			System.out.println("phonenumber:");
			String phoneNumber = scanner.next();
			contacts.get(i).setPhoneNumber(phoneNumber);
			
			System.out.println("email:");
			String email = scanner.next();
			contacts.get(i).setEmail(email);
			
		}
		
		instance.run();
	}

	private void listContacts() {
		for (Contact current : contacts) {
			System.out.println(current);
		}
		System.out.println();
		instance.run();
	}

	private void deleteContact() {
		System.out.println("Remove by full name only seperated by space only ");
		System.out.println("example: Jan Kowalski");
		scanner.nextLine();
		for (int i=0; i<contacts.size(); i++)
			if(contacts.get(i).getFullName().equals(scanner.nextLine()))
				contacts.remove(i);
		System.out.println("Contact removed.");
		instance.run();
	}
}
