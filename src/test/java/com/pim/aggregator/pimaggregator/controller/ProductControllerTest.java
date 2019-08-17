package com.pim.aggregator.pimaggregator.controller;

import com.pim.aggregator.pimaggregator.ProductTestUtil;
import com.pim.aggregator.pimaggregator.manager.ProductManager;
import com.pim.aggregator.pimaggregator.response.Stats;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductAggregatorController.class)
public class ProductControllerTest
{

  @Autowired
  private MockMvc mvc;

  @MockBean
  ProductManager productManager;

  @Test
  public void givenURLWhenGetProductListCalledThenProductListReturned() throws Exception
  {
    when(productManager.getProducts()).thenReturn(ProductTestUtil.getProducts());
    final MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.get("/product/list")).andReturn().getResponse();
    ProductTestUtil.validate(response,
            "[{\"name\":\"product1\",\"description\":\"product 1 desc\",\"provider\":\"product 1 provide\",\"available\":true,\"measurementUnits\":\"PC\"},{\"name\":\"product2\",\"description\":\"product 2 desc\",\"provider\":\"product 2 provide\",\"available\":true,\"measurementUnits\":\"PC\"},{\"name\":\"product3\",\"description\":\"product 3 desc\",\"provider\":\"product 3 provide\",\"available\":false,\"measurementUnits\":\"PC\"}]");
  }

  @Test
  public void givenURLWhenGetStatsCalledThenProductListReturned() throws Exception
  {
    when(productManager.getStats()).thenReturn(new Stats(10, 5));
    final MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.get("/product/stats")).andReturn().getResponse();
    ProductTestUtil.validate(response, "{\"numberOfProductsCreated\":10,\"numberOfProductsUpdated\":5}");
  }
}