package net.karneim.pojobuilder.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import net.karneim.pojobuilder.model.BuilderM;
import net.karneim.pojobuilder.model.FactoryM;
import net.karneim.pojobuilder.model.PropertyM;
import net.karneim.pojobuilder.model.TypeM;

public class BuilderMBuilder implements Cloneable {
    private int buildCounter = 0;

    private TypeM[] $type = new TypeM[1];
    private TypeM[] $superType = new TypeM[1];
    private boolean[] $abstract = new boolean[1];
    private TypeM[] $productType = new TypeM[1];
    private TypeM[] $selfType = new TypeM[1];
    
    private Date[] $created = new Date[0];
    private FactoryM $factory;
    private boolean isSet$factory = false;

    private Collection<PropertyM> $properties = null;

    public BuilderMBuilder withFactory(FactoryM factoryM) {
        $factory = factoryM;
        isSet$factory = true;
        return this;
    }
    
    public BuilderMBuilder withType(TypeM value) {
        this.$type = new TypeM[] { value };
        return this;
    }
    
    public BuilderMBuilder withTypeFrom(TypeM... values) {
        this.$type = values;
        return this;
    }
    
    public BuilderMBuilder withSelfType(TypeM value) {
        this.$selfType = new TypeM[] { value };
        return this;
    }

    public BuilderMBuilder withSuperType(TypeM value) {
        this.$superType = new TypeM[] { value };
        return this;
    }

    public BuilderMBuilder withSuperTypeFrom(TypeM... values) {
        this.$superType = values;
        return this;
    }
    
    public BuilderMBuilder withAbstract(boolean value) {
        this.$abstract = new boolean[] { value };
        return this;
    }

    public BuilderMBuilder withProductType(TypeM value) {
        this.$productType = new TypeM[] { value };
        return this;
    }

    public BuilderMBuilder withProductTypeFrom(TypeM... values) {
        this.$productType = values;
        return this;
    }

    public BuilderMBuilder withCreated(Date value) {
        this.$created = new Date[] { value };
        return this;
    }

    public BuilderMBuilder withCreatedFrom(Date... values) {
        this.$created = values;
        return this;
    }

    public BuilderMBuilder withProperties(Collection<PropertyM> value) {
        this.$properties = value;
        return this;
    }

    public void withProperties(PropertyM... value) {
        $properties = Arrays.asList(value);
    }

    @Override
    public Object clone() {
        try {
            BuilderMBuilder result = (BuilderMBuilder)super.clone();
            result.buildCounter = 0;
            return result;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e.getMessage());
        }
    }

    public BuilderMBuilder override() {
        return (BuilderMBuilder)clone();
    }

    public BuilderM build() {
        BuilderM result = new BuilderM($type[buildCounter % $type.length], $superType[buildCounter % $superType.length], $abstract[buildCounter % $abstract.length],
        		$productType[buildCounter % $productType.length], $selfType[buildCounter % $selfType.length]);
        if ($created.length > 0) {
            result.setCreated($created[buildCounter % $created.length]);
        }
        if ($properties != null) {
            result.setProperties($properties);
        }
        if ( isSet$factory) {
            result.setFactory($factory);
        }
        buildCounter++;
        return result;
    }

    public BuilderM[] buildArray(int size) {
        BuilderM[] result = new BuilderM[size];
        for (int i = 0; i < size; ++i) {
            result[i] = build();
        }
        return result;
    }

    public List<BuilderM> buildList(int size) {
        List<BuilderM> result = new ArrayList<BuilderM>(size);
        for (int i = 0; i < size; ++i) {
            result.add(build());
        }
        return result;
    }

}
