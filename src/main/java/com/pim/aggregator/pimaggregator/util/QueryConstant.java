package com.pim.aggregator.pimaggregator.util;

public class QueryConstant
{
  public final static String NAME = "name";
  public final static String DESCRIPTION = "description";
  public final static String PROVIDER = "provider";
  public final static String AVAILABLE = "available";
  public final static String MEASUREMENT_UNITS = "measurementUnits";
  public final static String CREATED_DATE = "createdDate";
  public final static String UPDATED_DATE = "updatedDate";
  public final static String QUERY_TO_GET_PRODUCT = "select * from product";
  public final static String CREATED_OR_UPDATED_PRODUCT_COUNT_QUERY = "Select count(*) from product where %s = CURRENT_DATE";
  public final static String SELECT_PRODUCT = "select * from product where name = ?";
  public final static String INSERT_PRODUCT = "insert into product (name,description,provider,available,measurementUnits, createdDate)  values(?,?,?,?,?,?)";
  public final static String UPDATE_PRODUCT = "update product  set description = ?,provider =?,available = ?, measurementUnits= ? , updatedDate = ?  where name = ?";
}
