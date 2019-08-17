package com.pim.aggregator.pimaggregator.persistence;

import com.pim.aggregator.pimaggregator.entity.Product;
import com.pim.aggregator.pimaggregator.response.Stats;

import java.util.List;

public interface ProductPersistenceManager
{
  List<Product> getProducts();

  void save(Product product);

  Stats getStats();
}
