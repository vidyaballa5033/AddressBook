
import java.util.*;
import java.util.stream.Collectors;

public class AddressBookMain {

    public static Map<String,AddressBook> addressBookMap= new HashMap<>();


    public static void addAddressBook() {
        Scanner in = new Scanner(System.in);
        AddressBook addressBook = new AddressBook();

        System.out.println("Enter the name of the new Address Book: ");
        addressBook.setAddressBookName(in.next());

        if (addressBookMap.containsKey(addressBook.getAddressBookName())) {
            System.out.println("Address Book already exists!!!!");
            return;
        }

        addressBookMap.put(addressBook.getAddressBookName(),addressBook);
        System.out.println("Address Book Added!!!");
        System.out.println();

        boolean status= true;
        while(status){
            System.out.println("=> To ADD a Contact to this Address Book: PRESS 1");
            System.out.println("=> To Close this Address Book: PRESS 2");
            int choice = in.nextInt();
            switch (choice){
                case 1:
                    addressBook.addContact();
                    System.out.println(addressBook);
                    System.out.println("Contact Added!!!");
                    System.out.println();
                    break;
                case 2:
                    status=false;
                    break;
                default:
                    System.out.println("Enter a valid choice!!!");
            }
        }
    }

    public static void addContacts() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the name of the address book you want to add contact:");
        String name = in.next();

        if(addressBookMap.containsKey(name)) {
            AddressBook Temp= addressBookMap.get(name);
            Temp.addContact();
            System.out.println(Temp);
            System.out.println("Contact Added!!!");
            System.out.println();
        }
        else
            System.out.println("Given Address Book not Found!!!\n");
    }

    public static void editContact(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the name of the address book, the contact you want to edit exists:");
        String name= in.next();

        if(addressBookMap.containsKey(name)) {
            AddressBook Temp= addressBookMap.get(name);
            Temp.editDetails();
        }
        else
            System.out.println("Given Address Book not Found!!!\n");
    }

    public static void deleteContact(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the name of the address book, the contact you want to Delete exists:");
        String name= in.next();

        if(addressBookMap.containsKey(name)) {
            AddressBook Temp= addressBookMap.get(name);
            Temp.deleteDetails();
        }
        else
            System.out.println("Given Address Book not Found!!!\n");
    }

    public static void searchContact(){
        Scanner in = new Scanner(System.in);
        System.out.println("=> To search all Contacts from a specific City: PRESS 1");
        System.out.println("=> To search all Contacts from a specific State: PRESS 2");
        int choice = in.nextInt();

        switch (choice){
            case 1:
                System.out.print("Enter the name of the City: ");
                String cityName = in.next();
                List<Contact> cityList = new ArrayList<>();
                addressBookMap.values().stream().forEach(addressBook -> cityList.addAll(addressBook.getContacts().stream().filter(
                        contact -> contact.getCity().equalsIgnoreCase(cityName)).toList()));
                int count1 = cityList.size();
                System.out.println(count1+" Contacts Found, which belongs to " + cityName +" city");
                System.out.println(cityList);
                System.out.println();
                break;
            case 2:
                System.out.print("Enter the name of the State: ");
                String stateName = in.next();
                List<Contact> stateList = new ArrayList<>();
                addressBookMap.values().stream().forEach(addressBook -> stateList.addAll(addressBook.getContacts().stream().filter(
                        contact -> contact.getState().equalsIgnoreCase(stateName)).toList()));
                int count2 = stateList.size();
                System.out.println(count2+" Contacts Found, which belongs to " + stateName +" city");
                System.out.println(stateList);
                System.out.println();
                break;
            default:
                System.out.println("Please Choose valid option!!!");
                searchContact();
                break;
        }
    }

    public static void displayAddressBook(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the name of the address book you want to Display:");
        String name = in.next();
        if(addressBookMap.containsKey(name)) {
            AddressBook Temp = addressBookMap.get(name);
            System.out.println(Temp);
        }
        else
            System.out.println("Given Address Book not Found!!!\n");
    }

    public static void displaySortedAddressBook(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the name of the address book you want to Display:");
        String name = in.next();
        if(addressBookMap.containsKey(name)) {
            AddressBook Temp = addressBookMap.get(name);
            System.out.println("Choose the option to sort the contacts in the Address Book based on:");
            System.out.println("1.First Name\t 2.City \t 3.State\t 4.ZIP Code");
            int choice = in.nextInt();

            List<Contact> sortedList = new ArrayList<>();
            switch (choice){
                case 1:
                    sortedList = Temp.getContacts().stream().sorted(Comparator.comparing(Contact::getFirstName)).collect(Collectors.toList());
                    break;
                case 2:
                    sortedList = Temp.getContacts().stream().sorted(Comparator.comparing(Contact::getCity)).collect(Collectors.toList());
                    break;
                case 3:
                    sortedList = Temp.getContacts().stream().sorted(Comparator.comparing(Contact::getState)).collect(Collectors.toList());
                    break;
                case 4:
                    sortedList = Temp.getContacts().stream().sorted(Comparator.comparing(Contact::getZip)).collect(Collectors.toList());
                    break;
                default:
                    System.out.println("Choose Valid Option!!!");
                    break;
            }
            System.out.println("The Sorted Contacts: ");
            System.out.println(sortedList);
            System.out.println();
        }
        else
            System.out.println("Given Address Book not Found!!!\n");
    }
    public static void main(String[] args) {
        System.out.println("Welcome to Address Book Program in Address Book Main class on Main Branch");

        Scanner in = new Scanner(System.in);
        boolean status= true;
        while(status) {
            System.out.println("******************MENU:******************");
            System.out.println("=> To Add Address Book: PRESS 1");
            System.out.println("=> To Add Contact: PRESS 2");
            System.out.println("=> To Edit an Existing Contact: PRESS 3");
            System.out.println("=> To Delete a Contact: PRESS 4");
            System.out.println("=> To Search all the Contacts from a specific City or specific State: PRESS 5");
            System.out.println("=> To Display Dictionary of Address Books: PRESS 6");
            System.out.println("=> To Display Address Books Of Contacts: PRESS 7");
            System.out.println("=> To Display Contacts in an Address Book in Sorted Order based on a specific detail: PRESS 8");
            System.out.println("=> To EXIT: PRESS 9");
            int choice = in.nextInt();

            switch (choice) {
                case 1:
                    addAddressBook();
                    System.out.println();
                    break;
                case 2:
                    addContacts();
                    break;
                case 3:
                    editContact();
                    break;
                case 4:
                    deleteContact();
                    break;
                case 5:
                    searchContact();
                    break;
                case 6:
                    System.out.println(addressBookMap);
                    break;
                case 7:
                    displayAddressBook();
                    break;
                case 8:
                    displaySortedAddressBook();
                    break;
                default:
                    status=false;
                    break;
            }
        }
    }
}