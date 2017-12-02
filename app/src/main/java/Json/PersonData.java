package Json;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sanjib on 12/2/2017.
 */

public class PersonData {

    private Person person = null;

    public PersonData(){
        person = new Person();
    }//constructor

    public void createPersonData(){
        person.setName("Gautam Pandit");
        person.setAge("28");
        person.setBloodgroup("O+ive");

        Contact c1 = new Contact();
        c1.setHomephnumber("+123456");
        Contact c2 = new Contact();
        c2.setOfficephnumber("+7890");
        List<Contact> contactlist = new ArrayList<Contact>();
        contactlist.add(c1);
        contactlist.add(c2);
        person.setPersonContact(contactlist);

        Address a1 = new Address();
        a1.setHomeAddress("This is home addresss");
        Address a2 = new Address();
        a2.setOfficeAddress("This is office address");
        List<Address> addressList = new ArrayList<Address>();
        addressList.add(a1);
        addressList.add(a2);
        person.setPersonAddress(addressList);

    }//createPerosnData

    public Person getPersonObj(){
        if(person != null){
            return person;
        }
        return null;
    }//getPersonObj

}//persondata
