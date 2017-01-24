
package your.domain.data;

import java.text.ParseException;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import org.apache.commons.csv.CSVRecord;
import org.easydata.model.EasyCSVRepository;

public class Addresses
    extends EasyCSVRepository<Address>
{

    private Object2ObjectMap<String, Address> Addresses = new Object2ObjectOpenHashMap<String, Address>();

    public Address createFrom(CSVRecord record)
        throws ParseException
    {
        Address address = new Address();
        address.setPublicID((record.get(0)));
        address.setStreet((record.get(1)));
        address.setHouseNumber((record.get(2)));
        address.setCity((record.get(3)));
        return address;
    }

    public boolean add(Address item)
        throws IllegalArgumentException
    {
        if (item == null) {
            throw new IllegalArgumentException("item cannot be null!");
        }
        if ((item.getKeyValue() == null)||item.getKeyValue().isEmpty()) {
            throw new IllegalArgumentException("item key value cannot be null or empty!");
        }
        if (!Addresses.containsKey(item.getKeyValue())) {
            Addresses.put(item.getKeyValue(), item);
            return true;
        }
        return false;
    }

    public Address getById(java.lang.String id) {
        return this.Addresses.get(id);
    }

    public java.lang.String getInputFileName() {
        return "ADDRESS.CSV";
    }

    public int size() {
        return Addresses.size();
    }

}