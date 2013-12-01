package testdata.factory;

import net.karneim.pojobuilder.FactoryProperties;
import net.karneim.pojobuilder.GeneratePojoBuilder;
import net.karneim.pojobuilder.PropertyNames;

@SuppressWarnings("deprecation")
public class PojoFactory {

    /**
     * Test factory method with deprecated property-naming annotation
     */
    @GeneratePojoBuilder
    @PropertyNames({ "firstname", "surname" })
    public static Contact createContact(String aFirstname, String aSurname) {
        Contact result = new Contact(aSurname, aFirstname);
        return result;
    }

    /**
     * Test factory method with property-naming annotation
     */
    @GeneratePojoBuilder
    @FactoryProperties({ "firstname", "surname" })
    public static Contact createContact2(String aFirstname, String aSurname) {
        Contact result = new Contact(aSurname, aFirstname);
        return result;
    }

    /**
     * Test factory method fails with both annotations
     */
    @GeneratePojoBuilder
    @FactoryProperties({ "firstname", "surname" })
    @PropertyNames({ "firstname", "surname" })
    public static Contact createContact3(String aFirstname, String aSurname) {
        Contact result = new Contact(aSurname, aFirstname);
        return result;
    }

    /**
     * Test factory method with implicit-property-naming
     */
    @GeneratePojoBuilder
    public static Contact createContactImplicit(String firstname, String surname) {
        Contact result = new Contact(surname, firstname);
        return result;
    }

    /**
     * Test factory method with no properties
     */
    @GeneratePojoBuilder
    public static Note createNote() {
        return new Note();
    }

    
    // TODO test factory methods with generics.
    
//    @GeneratePojoBuilder(withName="EmptyMapBuilder", intoPackage="sample")
//    public static Map createEmptyMap() {
//        return new HashMap();
//    }

//    @GeneratePojoBuilder(withName="GenericMapBuilder", intoPackage="sample")
//    public static <K, V> Map<K, V> createGenericMap() {
//        return new HashMap();
//    }

//    @GeneratePojoBuilder(withName="StringIntegerMapBuilder", intoPackage="sample")
//    public static Map<String, Integer> createStringIntegerMap() {
//        return new HashMap();
//    }
    
//    @GeneratePojoBuilder(withName="BoundedListBuilder", intoPackage="sample") 
//    public static <T extends Number & Comparable<T>> List<T> createBoundedList() {
//        return new ArrayList();
//    }

//    @GeneratePojoBuilder(withName="BoundedMapBuilder", intoPackage="sample")
//    public static <T extends Number, X extends Serializable & Comparable<X>> Map<T, X> createBoundedMap() {
//        return new HashMap();
//    }
    
//    @GeneratePojoBuilder(withName="IntegerMultiSetBuilder", intoPackage="sample")
//    public static <T extends Set<Integer>> Set<T> createIntegerMultiSet() {
//        return new HashSet();
//    }
    
//    @GeneratePojoBuilder(withName="MultiSetBuilder", intoPackage="sample") 
//    public static <T extends Set<V>, V> Set<T> createMultiSet() {
//        return new HashSet();
//    }
    
//    @GeneratePojoBuilder(withName="BoundedMapBuilder2", intoPackage="sample") 
//    public static <T extends Number, X extends T> Map<T, X> createBoundedMap2() {
//        return new HashMap();
//    }
}
