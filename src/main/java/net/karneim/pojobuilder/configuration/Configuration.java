package net.karneim.pojobuilder.configuration;

import static java.util.logging.Level.CONFIG;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.logging.Logger;

import net.karneim.pojobuilder.processor.AnnotationProcessor;

/**
 * The Global Configuration.
 */
public class Configuration {
   private static final Logger LOG = Logger.getLogger(AnnotationProcessor.class.getName());
   public final static Configuration INSTANCE = new Configuration();
   
   private final Properties properties;
   
   Configuration(){
      properties = new Properties();
      loadPropertiesFromFile();
   }
   
   /**
    * Load the Annotation Processor Supported Options to property files.
    * This is are parameter with option "-A"
    *
    * @param map the annotation processor argument map
    */
   public void loadArguments(Map<String, String> map) {
      for (Entry<String, String> entry : map.entrySet()) {
         String nullSafeValue = entry.getValue()==null?"":entry.getValue();
         properties.setProperty(entry.getKey(),nullSafeValue);
      }
   }

   /**
    * Searches for the property with the specified key in this property list.
    * If the key is not found in this property list, the default property list,
    * and its defaults, recursively, are then checked. The method returns
    * <code>null</code> if the property is not found.
    *
    * @param   key   the property key.
    * @return  the value in this property list with the specified key value.
    * @see     Configuration#getProperty(String, String)
    */
   public String getProperty(String key) {
      return properties.getProperty(key);
   }
   
   /**
    * Searches for the property with the specified key in this property list.
    * If the key is not found in this property list, the default property list,
    * and its defaults, recursively, are then checked. The method returns the
    * default value argument if the property is not found.
    *
    * @param   key            the hashtable key.
    * @param   defaultValue   a default value.
    *
    * @return  the value in this property list with the specified key value.
    * @see     Configuration#getProperty(String)
    */
   public String getProperty(String key, String defaultValue) {
      return properties.getProperty(key, defaultValue);
   }
   
   private void loadPropertiesFromFile() {
      InputStream propertiesStream = this.getClass().getResourceAsStream("/pojobuilder.properties");
      if (propertiesStream != null) {
         try {
            properties.load(propertiesStream);
         } catch (IOException e) {
            LOG.log(CONFIG, "could not load properties from pojobuilder.properties on classpath", e);
         }
      }
   }
   
   @Override
   public String toString() {
      return "PojoBuilder Configuration dump\n" + properties.toString();
   }
   
  
}
