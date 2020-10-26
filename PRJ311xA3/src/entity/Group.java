package entity;

import dao.ContactDAO;
import dao.GroupDAO;

import java.util.List;
import java.util.Vector;

public class Group {



    private String name;

    public  Group(String name) {
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;

    }

    @Override
    public String toString() {
        return String.format("%-20s\n",name);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || name == null) return false;
        Group g = (Group)obj;
        return name.equals(((Group) obj).getName());
    }

    //return list of contact by group name
    public List<Contact> contacts() throws  Exception {
        ContactDAO contactDAO = new ContactDAO();
        List<Contact> c = contactDAO.loadContact("data/contact.txt");
        List<Contact> result = new Vector<Contact>();
        for (Contact x: c) {
            if(x.getGroup().trim().equals(name)) result.add(x);
        }
        return result;
    }
}
