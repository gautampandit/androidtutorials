package Json;

import java.util.List;

/**
 * Created by sanjib on 12/2/2017.
 */

public class Person {

    public String name;
    public String age;
    public String bloodgroup;
    public List<Contact> personContact;
    public List<Address> personAddress;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getBloodgroup() {
        return bloodgroup;
    }

    public void setBloodgroup(String bloodgroup) {
        this.bloodgroup = bloodgroup;
    }

    public List<Contact> getPersonContact() {
        return personContact;
    }

    public void setPersonContact(List<Contact> personContact) {
        this.personContact = personContact;
    }

    public List<Address> getPersonAddress() {
        return personAddress;
    }

    public void setPersonAddress(List<Address> personAddress) {
        this.personAddress = personAddress;
    }
}//person
