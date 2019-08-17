package com.pim.aggregator.pimaggregator.persistence;

import com.pim.aggregator.pimaggregator.entity.Product;
import com.pim.aggregator.pimaggregator.response.Stats;
import com.pim.aggregator.pimaggregator.util.QueryConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Repository
public class ProductPersistenceManagerImpl implements ProductPersistenceManager
{

  private static final Logger logger = LoggerFactory.getLogger(ProductPersistenceManagerImpl.class);

  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public ProductPersistenceManagerImpl(JdbcTemplate jdbcTemplate)
  {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public List<Product> getProducts()
  {
    logger.debug("Query to getProducts: " + QueryConstant.QUERY_TO_GET_PRODUCT);
    return jdbcTemplate.query(QueryConstant.QUERY_TO_GET_PRODUCT, new ProductRowMapper());
  }

  @Override
  public void save(Product product)
  {
    synchronized (this)
    {
      if (findProductByName(product.getName()) == 0)
      {
        create(product);
      }
      else
      {
        update(product);
      }
    }
  }

  @Override
  public Stats getStats()
  {
    final String createdCountQuery = String.format(QueryConstant.CREATED_OR_UPDATED_PRODUCT_COUNT_QUERY, QueryConstant.CREATED_DATE);
    logger.debug("Query to get createdCount: " + createdCountQuery);
    final int createdCount = jdbcTemplate.queryForObject(createdCountQuery, Integer.class);

    final String updatedCountQuery = String.format(QueryConstant.CREATED_OR_UPDATED_PRODUCT_COUNT_QUERY, QueryConstant.UPDATED_DATE);
    logger.debug("Query to get updatedCount: " + updatedCountQuery);
    final int updatedCount = jdbcTemplate.queryForObject(updatedCountQuery, Integer.class);

    return new Stats(createdCount, updatedCount);
  }

  private int findProductByName(String name)
  {
    logger.debug("Query to findProductByName: " + QueryConstant.SELECT_PRODUCT + " params :  " + name);
    return jdbcTemplate.queryForList(QueryConstant.SELECT_PRODUCT, new Object[]{name}).size();
  }

  private void create(Product product)
  {
    logger.debug("Query to create product: " + QueryConstant.INSERT_PRODUCT + " params :  " + product.getName(), product.getDescription(),
            product.getProvider(), product.isAvailable(), product.getMeasurementUnits(), new Date());

    jdbcTemplate.update(QueryConstant.INSERT_PRODUCT, product.getName(), product.getDescription(), product.getProvider(), product.isAvailable(),
            product.getMeasurementUnits(), new Date());
  }

  private void update(Product product)
  {
    logger.debug("Query to update product: " + QueryConstant.INSERT_PRODUCT + " params :  " + product.getDescription(), product.getProvider(),
            product.isAvailable(), product.getMeasurementUnits(), new Date(), product.getName());

    jdbcTemplate.update(QueryConstant.UPDATE_PRODUCT, product.getDescription(), product.getProvider(), product.isAvailable(),
            product.getMeasurementUnits(), new Date(), product.getName());
  }

  public class ProductRowMapper implements RowMapper<Product>
  {
    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException
    {
      return new Product(rs.getString(QueryConstant.NAME),
              rs.getString(QueryConstant.DESCRIPTION),
              rs.getString(QueryConstant.PROVIDER),
              rs.getBoolean(QueryConstant.AVAILABLE),
              rs.getString(QueryConstant.MEASUREMENT_UNITS));
    }
  }

}
