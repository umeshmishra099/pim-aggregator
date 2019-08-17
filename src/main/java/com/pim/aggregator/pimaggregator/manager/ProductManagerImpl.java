package com.pim.aggregator.pimaggregator.manager;

import com.pim.aggregator.pimaggregator.entity.Product;
import com.pim.aggregator.pimaggregator.persistence.ProductPersistenceManager;
import com.pim.aggregator.pimaggregator.response.Stats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductManagerImpl implements ProductManager
{
  private final ProductPersistenceManager productPersistenceManager;

  @Autowired
  public ProductManagerImpl(ProductPersistenceManager productPersistenceManager)
  {
    this.productPersistenceManager = productPersistenceManager;
  }

  @Override
  public void save(Product product)
  {
    productPersistenceManager.save(product);
  }

  @Override
  public List<Product> getProducts()
  {
    return productPersistenceManager.getProducts();
  }

  @Override
  public Stats getStats()
  {
    return productPersistenceManager.getStats();
  }
}
