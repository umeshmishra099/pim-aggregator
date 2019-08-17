package com.pim.aggregator.pimaggregator.persistence;

import com.pim.aggregator.pimaggregator.ProductTestUtil;
import com.pim.aggregator.pimaggregator.entity.Product;
import com.pim.aggregator.pimaggregator.response.Stats;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductPersistenceManagerImplTest
{

  @InjectMocks
  ProductPersistenceManagerImpl productPersistenceManager;

  @Mock
  JdbcTemplate jdbcTemplate;

  @Test
  public void whenGetProductsCalledThenReturnAllProductsInDatabase()
  {
    when(jdbcTemplate.query(anyString(), any(ProductPersistenceManagerImpl.ProductRowMapper.class)))
            .thenReturn(ProductTestUtil.getProducts());

    final List<Product> products = productPersistenceManager.getProducts();

    assertEquals(3, products.size());

    verify(jdbcTemplate, times(1)).query(anyString(), any(ProductPersistenceManagerImpl.ProductRowMapper.class));
  }

  @Test
  public void givenProductWhenSaveCalledThenVerifyUpdateCalledOnce()
  {
    when(jdbcTemplate.queryForList(anyString(), anyString())).thenReturn(new ArrayList<>());
    productPersistenceManager.save(ProductTestUtil.getProduct());

    verify(jdbcTemplate, times(1)).queryForList(anyString(), anyString());
    verify(jdbcTemplate, times(0)).update(anyString(), anyString(), anyString(), anyString(), anyString(), any(Date.class), anyString());
  }

  @Test
  public void whenStatCalledThenVerifyStatsReturned()
  {
    when(jdbcTemplate.queryForObject(anyString(), eq(Integer.class))).thenReturn(10);
    final Stats stats = productPersistenceManager.getStats();
    assertEquals(10, stats.getNumberOfProductsCreated());
    assertEquals(10, stats.getNumberOfProductsUpdated());
  }

}