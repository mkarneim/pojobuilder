package net.karneim.pojobuilder.analysis.with.superclass;

import net.karneim.pojobuilder.GeneratePojoBuilder;
import net.karneim.pojobuilder.analysis.with.superclass.otherpackage.SuperclassPojo3;

@GeneratePojoBuilder
public class SubclassPojo3 extends SuperclassPojo3 {
  public int visibleMember;
}
