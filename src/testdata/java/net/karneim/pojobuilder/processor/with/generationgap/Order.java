package net.karneim.pojobuilder.processor.with.generationgap;

import java.util.List;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder(withGenerationGap=true)
public class Order {
  private long customerNo;
  private long orderNo;
  private List<Item> items;
  
  public static class Item {
    private long articleNo;
    private int amount;
    public long getArticleNo() {
      return articleNo;
    }
    public void setArticleNo(long articleNo) {
      this.articleNo = articleNo;
    }
    public int getAmount() {
      return amount;
    }
    public void setAmount(int amount) {
      this.amount = amount;
    }
  }

  public long getCustomerNo() {
    return customerNo;
  }

  public void setCustomerNo(long customerNo) {
    this.customerNo = customerNo;
  }

  public long getOrderNo() {
    return orderNo;
  }

  public void setOrderNo(long orderNo) {
    this.orderNo = orderNo;
  }

  public List<Item> getItems() {
    return items;
  }

  public void setItems(List<Item> items) {
    this.items = items;
  }
  
}
