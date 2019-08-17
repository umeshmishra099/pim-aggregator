package com.pim.aggregator.pimaggregator.manager;

import com.pim.aggregator.pimaggregator.entity.Product;
import com.pim.aggregator.pimaggregator.response.Stats;

import java.util.List;

public interface ProductManager
{
  void save(Product product);

  List<Product> getProducts();

  Stats getStats();
}
