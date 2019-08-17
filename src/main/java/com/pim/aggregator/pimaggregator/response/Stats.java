package com.pim.aggregator.pimaggregator.response;

public class Stats
{
  private long numberOfProductsCreated;
  private long numberOfProductsUpdated;

  public Stats(long numberOfProductsCreated, long numberOfProductsUpdated)
  {
    this.numberOfProductsCreated = numberOfProductsCreated;
    this.numberOfProductsUpdated = numberOfProductsUpdated;
  }

  public long getNumberOfProductsCreated()
  {
    return numberOfProductsCreated;
  }

  public long getNumberOfProductsUpdated()
  {
    return numberOfProductsUpdated;
  }
}
