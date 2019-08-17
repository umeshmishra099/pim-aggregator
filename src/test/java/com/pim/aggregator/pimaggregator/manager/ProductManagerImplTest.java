package com.pim.aggregator.pimaggregator.manager;

import com.pim.aggregator.pimaggregator.ProductTestUtil;
import com.pim.aggregator.pimaggregator.entity.Product;
import com.pim.aggregator.pimaggregator.persistence.ProductPersistenceManager;
import com.pim.aggregator.pimaggregator.response.Stats;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductManagerImplTest
{

  @InjectMocks
  ProductManagerImpl productManager;

  @Mock
  ProductPersistenceManager productPersistenceManager;

  @Test
  public void givenProductWhenSaveCalledThenVerifySaveCalled()
  {
    doNothing().when(productPersistenceManager).save(any(Product.class));

    productManager.save(ProductTestUtil.getProduct());

    verify(productPersistenceManager, times(1)).save(any(Product.class));
  }

  @Test
  public void getProducts()
  {
    when(productPersistenceManager.getProducts()).thenReturn(ProductTestUtil.getProducts());
    final List<Product> products = productManager.getProducts();

    assertEquals(3, products.size());
    verify(productPersistenceManager, times(1)).getProducts();
  }

  @Test
  public void getStats()
  {
    when(productPersistenceManager.getStats()).thenReturn(new Stats(20, 10));
    final Stats stats = productPersistenceManager.getStats();
    assertEquals(20, stats.getNumberOfProductsCreated());
    assertEquals(10, stats.getNumberOfProductsUpdated());
  }
}