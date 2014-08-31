package net.karneim.pojobuilder.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

@SuppressWarnings("serial")
public class ArgumentListM extends ArrayList<ArgumentM> {

  public String toArgumentString() {
    sortByPosition();
    StringBuilder result = new StringBuilder();
    for (ArgumentM arg : this) {
      if (result.length() > 0) {
        result.append(", ");
      }
      result.append(arg.getProperty().getValueFieldName());
    }
    return result.toString();
  }

  public ArgumentListM sortByPosition() {
    Collections.sort(this, new Comparator<ArgumentM>() {
      @Override
      public int compare(ArgumentM p1, ArgumentM p2) {
        return p1.getPos() - p2.getPos();
      }
    });
    return this;
  }

  public PropertyListM getPropertyList() {
    PropertyListM result = new PropertyListM();
    for (ArgumentM arg : this) {
      result.add(arg.getProperty());
    }
    return result;
  }

}
