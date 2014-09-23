package net.karneim.pojobuilder.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

public class TypeMTest {

   @Before
   public void setUp() throws Exception {
   }

   @Test
   public void isInPackage() throws Exception {
      TypeM typeM = new TypeM("a","b");
      assertThat(typeM.isInPackage("c")).isFalse();
   }

   @Test
   public void isInPackage_NoInput() throws Exception {
      TypeM typeM = new TypeM("a","b");
      assertThat(typeM.isInPackage(null)).isFalse();
   }

   @Test
   public void isInPackage_AllNull() throws Exception {
      TypeM typeM = new TypeM(null,null);
      assertThat(typeM.isInPackage(null)).isTrue();
   }
   
   @Test
   public void isInPackage_OnlyInputNotNull() throws Exception {
      TypeM typeM = new TypeM(null,null);
      assertThat(typeM.isInPackage("c")).isFalse();
   }
}
