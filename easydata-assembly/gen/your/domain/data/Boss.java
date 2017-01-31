
package your.domain.data;

import java.util.Date;
import org.easydata.model.EasyPojo;


/**
 * This class has been generated by the EasyData library.
 * DO NOT MODIFY this class manually.
 * 
 */
public class Boss
    extends EasyPojo
{

    private String LastName;
    private String FirstName;
    private String PublicId;
    private String AddressID;
    private Date DateOfBirth;

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
     * Returns the PublicId.
     * 
     * @return
     *     PublicId
     */
    public String getPublicId() {
        return PublicId;
    }

    /**
     * Set the PublicId.
     * 
     * @param PublicId
     *     the new PublicId
     */
    public void setPublicId(String PublicId) {
        this.PublicId = PublicId;
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
        return null;
    }

}
