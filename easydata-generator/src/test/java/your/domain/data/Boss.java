
package your.domain.data;

import java.util.Date;
import org.easydata.model.EasyPojo;

public class Boss
    extends EasyPojo
{

    private String PublicID;
    private String FirstName;
    private String AddressID;
    private String LastName;
    private Date DateOfBirth;

    /**
     * Returns the PublicID.
     * 
     * @return
     *     PublicID
     */
    public String getPublicID() {
        return PublicID;
    }

    /**
     * Set the PublicID.
     * 
     * @param PublicID
     *     the new PublicID
     */
    public void setPublicID(String PublicID) {
        this.PublicID = PublicID;
    }

    /**
     * Returns the FirstName.
     * 
     * @return
     *     FirstName
     */
    public String getFirstName() {
        return FirstName;
    }

    /**
     * Set the FirstName.
     * 
     * @param FirstName
     *     the new FirstName
     */
    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    /**
     * Returns the AddressID.
     * 
     * @return
     *     AddressID
     */
    public String getAddressID() {
        return AddressID;
    }

    /**
     * Set the AddressID.
     * 
     * @param AddressID
     *     the new AddressID
     */
    public void setAddressID(String AddressID) {
        this.AddressID = AddressID;
    }

    /**
     * Returns the LastName.
     * 
     * @return
     *     LastName
     */
    public String getLastName() {
        return LastName;
    }

    /**
     * Set the LastName.
     * 
     * @param LastName
     *     the new LastName
     */
    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    /**
     * Returns the DateOfBirth.
     * 
     * @return
     *     DateOfBirth
     */
    public Date getDateOfBirth() {
        return DateOfBirth;
    }

    /**
     * Set the DateOfBirth.
     * 
     * @param DateOfBirth
     *     the new DateOfBirth
     */
    public void setDateOfBirth(Date DateOfBirth) {
        this.DateOfBirth = DateOfBirth;
    }

    public String getKeyValue() {
        return this.PublicID;
    }

}
