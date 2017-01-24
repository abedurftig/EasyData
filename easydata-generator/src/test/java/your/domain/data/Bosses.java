
package your.domain.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import org.apache.commons.csv.CSVRecord;
import org.easydata.model.EasyCSVRepository;

public class Bosses
    extends EasyCSVRepository<Boss>
{

    private Object2ObjectMap<String, Boss> Bosses = new Object2ObjectOpenHashMap<String, Boss>();

    public Boss createFrom(CSVRecord record)
        throws ParseException
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        Boss boss = new Boss();
        boss.setPublicID((record.get(0)));
        boss.setFirstName((record.get(1)));
        boss.setLastName((record.get(2)));
        boss.setDateOfBirth(sdf.parse((record.get(3))));
        boss.setAddressID((record.get(4)));
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

    public Boss getById(java.lang.String id) {
        return this.Bosses.get(id);
    }

    public java.lang.String getInputFileName() {
        return "BOSS.CSV";
    }

    public int size() {
        return Bosses.size();
    }

}
