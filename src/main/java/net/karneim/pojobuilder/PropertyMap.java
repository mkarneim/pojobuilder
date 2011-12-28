package net.karneim.pojobuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.karneim.pojobuilder.model.PropertyM;
import net.karneim.pojobuilder.util.PropertyMBuilder;

public class PropertyMap {
    private final Map<String, PropertyMBuilder> content;

    public PropertyMap() {
        super();
        this.content = new HashMap<String, PropertyMBuilder>();
    }

    public PropertyMBuilder getEntry(String propName, String type) {
        String fieldname = toFieldname(propName, type);
        PropertyMBuilder result = content.get(fieldname);
        if (result == null) {
            result = new PropertyMBuilder().withName(propName).withFieldname(fieldname);
            content.put(fieldname, result);
        }
        return result;
    }

    private static String toFieldname(String propName, String type) {
        String typeString = type.replaceAll("\\.", "\\$");
        typeString = typeString.replaceAll("\\[\\]", "\\$");
        return propName + "$" + typeString;
    }

    public Collection<PropertyM> build() {
        List<PropertyM> result = new ArrayList<PropertyM>();
        for (PropertyMBuilder builder : content.values()) {
            result.add(builder.build());
        }
        return result;
    }
}
