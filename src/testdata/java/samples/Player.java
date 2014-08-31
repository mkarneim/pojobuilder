package samples;

import net.karneim.pojobuilder.GeneratePojoBuilder;

public class Player {
  private String name;
  private int points;

  @GeneratePojoBuilder(withGenerationGap = true)
  public Player(String name) {
    super();
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getPoints() {
    return points;
  }

  public void setPoints(int points) {
    this.points = points;
  }

}
