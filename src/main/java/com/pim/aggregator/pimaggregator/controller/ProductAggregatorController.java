package com.pim.aggregator.pimaggregator.controller;

import com.pim.aggregator.pimaggregator.entity.Product;
import com.pim.aggregator.pimaggregator.manager.ProductManager;
import com.pim.aggregator.pimaggregator.response.Stats;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/product")
public class ProductAggregatorController
{
  private static final Logger logger = LoggerFactory.getLogger(ProductAggregatorController.class);
  private final ProductManager productManager;

  @Autowired
  public ProductAggregatorController(ProductManager productManager)
  {
    this.productManager = productManager;
  }

  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public List<Product> getProducts()
  {
    logger.info("Fetching all product details");
    return productManager.getProducts();
  }

  @RequestMapping(value = "/stats", method = RequestMethod.GET)
  public Stats getStats()
  {
    logger.info("Fetching  product stats");
    return productManager.getStats();
  }
}
