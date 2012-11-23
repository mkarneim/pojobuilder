package samples.with.generics;

import java.util.HashMap;
import java.util.Map;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder
public class Settings {
	public Map<String,Object> entries = new HashMap<String,Object>();
	
}
