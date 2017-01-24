
package your.domain.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import org.apache.commons.csv.CSVRecord;
import org.easydata.model.EasyCSVRepository;

public class Employees
    extends EasyCSVRepository<Employee>
{

    private Object2ObjectMap<String, Employee> Employees = new Object2ObjectOpenHashMap<String, Employee>();

    public Employee createFrom(CSVRecord record)
        throws ParseException
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        Employee employee = new Employee();
        employee.setPublicID((record.get(0)));
        employee.setFirstName((record.get(1)));
        employee.setLastName((record.get(2)));
        employee.setDateOfBirth(sdf.parse((record.get(3))));
        employee.setAddressID((record.get(4)));
        employee.setNoOfChildren(Integer.valueOf((record.get(5))));
        employee.setBossID((record.get(6)));
        return employee;
    }

    public boolean add(Employee item)
        throws IllegalArgumentException
    {
        if (item == null) {
            throw new IllegalArgumentException("item cannot be null!");
        }
        if ((item.getKeyValue() == null)||item.getKeyValue().isEmpty()) {
            throw new IllegalArgumentException("item key value cannot be null or empty!");
        }
        if (!Employees.containsKey(item.getKeyValue())) {
            Employees.put(item.getKeyValue(), item);
            return true;
        }
        return false;
    }

    public Employee getById(java.lang.String id) {
        return this.Employees.get(id);
    }

    public Address getEmployeeAddress(java.lang.String id) {
    	return Repositories.get(Addresses.class).getById(this.getById(id).getAddressID());
    }
    
    public Boss getEmployeeBoss(java.lang.String id) {
    	return Repositories.get(Bosses.class).getById(this.getById(id).getBossID());
    }
    
    public java.lang.String getInputFileName() {
        return "EMPLOYEE.CSV";
    }

    public int size() {
        return Employees.size();
    }

}
