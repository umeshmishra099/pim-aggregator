package com.pim.aggregator.pimaggregator.entity;

public class Product
{
  private String name;
  private String description;
  private String provider;
  private boolean available;
  private String measurementUnits;

  public Product()
  {
  }

  public Product(String name, String description, String provider, boolean available, String measurementUnits)
  {
    this.name = name;
    this.description = description;
    this.provider = provider;
    this.available = available;
    this.measurementUnits = measurementUnits;
  }

  public String getName()
  {
    return name;
  }

  public String getDescription()
  {
    return description;
  }

  public String getProvider()
  {
    return provider;
  }

  public boolean isAvailable()
  {
    return available;
  }

  public String getMeasurementUnits()
  {
    return measurementUnits;
  }

  @Override
  public String toString()
  {
    return "Product{" +
            "name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", provider='" + provider + '\'' +
            ", available=" + available +
            ", measurementUnits='" + measurementUnits + '\'' +
            '}';
  }
}
