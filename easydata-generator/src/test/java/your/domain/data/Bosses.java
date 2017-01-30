
package your.domain.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import org.apache.commons.csv.CSVRecord;
import org.easydata.access.EasyCSVRepository;


/**
 * This class has been generated by the EasyData library.
 * DO NOT MODIFY this class manually.
 * 
 */
public class Bosses
    extends EasyCSVRepository<Boss>
{

    private Object2ObjectMap<String, Boss> Bosses = new Object2ObjectOpenHashMap<String, Boss>();
    private Object2ObjectMap<String, HashSet<String>> BossesByAddress = new Object2ObjectOpenHashMap<String, HashSet<String>>();

    public Address getAddress(Boss boss) {
        return Repositories.get(Addresses.class).getById(boss.getAddressId());
    }

    public Boss getById(String id) {
        return this.Bosses.get(id);
    }

    public Set<Boss> getBossesByAddressId(String AddressId) {
        Set<String> keys = BossesByAddress.get(AddressId);
        Set<Boss> bosses = new HashSet<Boss>();
        if ((!(keys == null))&&(keys.size()> 0)) {
            Iterator<String> keyIter = keys.iterator();
            while (keyIter.hasNext()) {
                bosses.add(this.getById(keyIter.next()));
            }
        }
        return bosses;
    }

    public Boss createFrom(CSVRecord record)
        throws ParseException
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        Boss boss = new Boss();
        boss.setPublicId((record.get(0)));
        boss.setFirstName((record.get(1)));
        boss.setLastName((record.get(2)));
        boss.setDateOfBirth(sdf.parse((record.get(3))));
        boss.setAddressId((record.get(4)));
        return boss;
    }

    public boolean add(Boss item)
        throws IllegalArgumentException
    {
        if (item == null) {
            throw new IllegalArgumentException("item cannot be null!");
        }
        if ((item.getKeyValue() == null)||item.getKeyValue().isEmpty()) {
            throw new IllegalArgumentException("item key value cannot be null or empty!");
        }
        if (!Bosses.containsKey(item.getKeyValue())) {
            Bosses.put(item.getKeyValue(), item);
            return true;
        }
        return false;
    }

    public String getInputFileName() {
        return "BOSS.CSV";
    }

    public int size() {
        return Bosses.size();
    }

    protected void populateReferenceIndices() {
        Iterator<Boss> valuesIter = Bosses.values().iterator();
        while (valuesIter.hasNext()) {
            Boss boss = valuesIter.next();
            // populate BossesByAddress
            HashSet<String> bossesByAddress = BossesByAddress.get(boss.getAddressId());
            if (bossesByAddress == null) {
                bossesByAddress = new HashSet<String>();
                BossesByAddress.put(boss.getAddressId(), bossesByAddress);
            }
            bossesByAddress.add(boss.getKeyValue());
        }
    }

}
