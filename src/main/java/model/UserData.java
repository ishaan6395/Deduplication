
package model;

/**
 *
 * @author Ishaan Thakker
 */
public class UserData {
    String row_id, first_name, last_name, company, email, address1, address2,
            zip, city, state_long, state_short, phone;

    public UserData() {
    }

    public UserData(String row_id, String first_name, String last_name, String company, String email, String address1, String address2, String zip, String city, String state_long, String state_short, String phone) {
        this.row_id = row_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.company = company;
        this.email = email;
        this.address1 = address1;
        this.address2 = address2;
        this.zip = zip;
        this.city = city;
        this.state_long = state_long;
        this.state_short = state_short;
        this.phone = phone;
    }

    public String getRow_id() {
        return row_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getCompany() {
        return company;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress1() {
        return address1;
    }

    public String getAddress2() {
        return address2;
    }

    public String getZip() {
        return zip;
    }

    public String getCity() {
        return city;
    }

    public String getState_long() {
        return state_long;
    }

    public String getState_short() {
        return state_short;
    }

    public String getPhone() {
        return phone;
    }
    
    
}
