package net.karneim.pojobuilder.util;

import java.util.ArrayList;
import java.util.List;

import net.karneim.pojobuilder.model.PropertyM;
import net.karneim.pojobuilder.model.TypeM;

public class PropertyMBuilder implements Cloneable {
	private int buildCounter = 0;

	private String[] $name = new String[1];
	private String[] $fieldname = new String[1];
	private boolean[] $accessible = new boolean[0];
	private TypeM[] $type = new TypeM[1];
	private String[] $setter = new String[0];
	private String[] $getter = new String[0];
	private Integer[] $parameterPos = new Integer[0];

	public PropertyMBuilder withName(String value) {
		this.$name = new String[] { value };
		return this;
	}

	public PropertyMBuilder withNameFrom(String... values) {
		this.$name = values;
		return this;
	}

	public PropertyMBuilder withFieldname(String value) {
		this.$fieldname = new String[] { value };
		return this;
	}

	public PropertyMBuilder withFieldnameFrom(String... values) {
		this.$fieldname = values;
		return this;
	}

	public PropertyMBuilder withAccessible(boolean value) {
		this.$accessible = new boolean[] { value };
		return this;
	}

	public PropertyMBuilder withAccessibleFrom(boolean... values) {
		this.$accessible = values;
		return this;
	}

	public PropertyMBuilder withType(TypeM value) {
		this.$type = new TypeM[] { value };
		return this;
	}

	public PropertyMBuilder withTypeFrom(TypeM... values) {
		this.$type = values;
		return this;
	}

	public PropertyMBuilder withSetter(String value) {
		this.$setter = new String[] { value };
		return this;
	}

	public PropertyMBuilder withSetterFrom(String... values) {
		this.$setter = values;
		return this;
	}

	public PropertyMBuilder withGetter(String value) {
		this.$getter = new String[] { value };
		return this;
	}

	public PropertyMBuilder withGetterFrom(String... values) {
		this.$getter = values;
		return this;
	}

	public PropertyMBuilder withParameterPos(Integer value) {
		this.$parameterPos = new Integer[] { value };
		return this;
	}

	public PropertyMBuilder withParameterPosFrom(Integer... values) {
		this.$parameterPos = values;
		return this;
	}

	@Override
	public Object clone() {
		try {
			PropertyMBuilder result = (PropertyMBuilder) super.clone();
			result.buildCounter = 0;
			return result;
		} catch (CloneNotSupportedException e) {
			throw new InternalError(e.getMessage());
		}
	}

	public PropertyMBuilder override() {
		return (PropertyMBuilder) clone();
	}

	public PropertyM build() {
		PropertyM result = new PropertyM($name[buildCounter % $name.length],
				$fieldname[buildCounter % $fieldname.length],
				$type[buildCounter % $type.length]);
		if ($setter.length > 0) {
			result.setSetter($setter[buildCounter % $setter.length]);
		}
		if ($getter.length > 0) {
			result.setGetter($getter[buildCounter % $getter.length]);
		}
		if ($parameterPos.length > 0) {
			result.setParameterPos($parameterPos[buildCounter
					% $parameterPos.length]);
		}
		if ($accessible.length > 0) {
			result.setAccessible($accessible[buildCounter % $accessible.length]);
		}
		buildCounter++;
		return result;
	}

	public PropertyM[] buildArray(int size) {
		PropertyM[] result = new PropertyM[size];
		for (int i = 0; i < size; ++i) {
			result[i] = build();
		}
		return result;
	}

	public List<PropertyM> buildList(int size) {
		List<PropertyM> result = new ArrayList<PropertyM>(size);
		for (int i = 0; i < size; ++i) {
			result.add(build());
		}
		return result;
	}
}
