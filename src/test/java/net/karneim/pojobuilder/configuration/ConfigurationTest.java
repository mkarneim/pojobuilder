package net.karneim.pojobuilder.configuration;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.assertj.core.internal.cglib.core.CollectionUtils;
import org.junit.Before;
import org.junit.Test;

public class ConfigurationTest {

   @Before
   public void setUp() throws Exception {
   }

   @Test
   public final void Configuration() throws Exception {
      String string = Configuration.INSTANCE.toString();
      assertThat(string).isNotEmpty();
      assertThat(string).hasSize(33);
   }

   @Test
   public final void loadArguments() throws Exception {
      //setup
      
      Map<String, String> arguments = new HashMap<String, String>();
      arguments.put("k1", "v1");

      //execute
      Configuration.INSTANCE.loadArguments(arguments );
      String property = Configuration.INSTANCE.getProperty("k1");
      
      //verify
      assertThat(property).isEqualTo("v1");
   }
   
   
   @Test
   public final void loadArguments_emptyValue() throws Exception {
      //setup
      Map<String, String> arguments = new HashMap<String, String>();
      arguments.put("k2", "");

      //execute
      Configuration.INSTANCE.loadArguments(arguments);
      String property = Configuration.INSTANCE.getProperty("k2");
      
      //verify
      assertThat(property).isEqualTo("");
   }
   
   @Test
   public final void loadArguments_NullToEmpty() throws Exception {
      //setup
      Map<String, String> arguments = new HashMap<String, String>();
      arguments.put("k3", null);

      //execute
      Configuration.INSTANCE.loadArguments(arguments);
      String property = Configuration.INSTANCE.getProperty("k3");
      
      //verify
      assertThat(property).isEqualTo("");
   }


   @Test
   public final void getPropertyStringString() throws Exception {
      Map<String, String> arguments = new HashMap<String, String>();
      arguments.put("k4", "v4");
      
      Configuration.INSTANCE.loadArguments(arguments);
      
      String property = Configuration.INSTANCE.getProperty("k4","default");
      
      assertThat(property).isEqualTo("v4");
   }
   
   @Test
   public final void getPropertyStringString_default() throws Exception {
      String property = Configuration.INSTANCE.getProperty("prop.file.notExistent","default");
      assertThat(property).isEqualTo("default");
   }

}
