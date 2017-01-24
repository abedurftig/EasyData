
package your.domain.data;

import org.easydata.model.EasyPojo;

public class Address
    extends EasyPojo
{

    private String PublicID;
    private String City;
    private String HouseNumber;
    private String Street;

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
     * Returns the City.
     * 
     * @return
     *     City
     */
    public String getCity() {
        return City;
    }

    /**
     * Set the City.
     * 
     * @param City
     *     the new City
     */
    public void setCity(String City) {
        this.City = City;
    }

    /**
     * Returns the HouseNumber.
     * 
     * @return
     *     HouseNumber
     */
    public String getHouseNumber() {
        return HouseNumber;
    }

    /**
     * Set the HouseNumber.
     * 
     * @param HouseNumber
     *     the new HouseNumber
     */
    public void setHouseNumber(String HouseNumber) {
        this.HouseNumber = HouseNumber;
    }

    /**
     * Returns the Street.
     * 
     * @return
     *     Street
     */
    public String getStreet() {
        return Street;
    }

    /**
     * Set the Street.
     * 
     * @param Street
     *     the new Street
     */
    public void setStreet(String Street) {
        this.Street = Street;
    }

    public String getKeyValue() {
        return this.PublicID;
    }

}
