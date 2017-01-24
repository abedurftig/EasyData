package your.domain.data;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.easydata.model.EasyRepository;

public class Repositories {

	private static Map<Class<?>, EasyRepository<?>> REPOS = new HashMap<Class<?>, EasyRepository<?>>();
	
	static {
		REPOS.put(Address.class, new Addresses());
		REPOS.put(Employee.class, new Employees());
		REPOS.put(Boss.class, new Bosses());
	}
	
	public static void init(String path) throws IOException {
		for (EasyRepository<?> repo : REPOS.values()) {
			repo.init(path);
		}
	}
	
	public static <T extends EasyRepository<?>> T get(Class<T> type) {
		return type.cast(REPOS.get(type));
	}
	
}
