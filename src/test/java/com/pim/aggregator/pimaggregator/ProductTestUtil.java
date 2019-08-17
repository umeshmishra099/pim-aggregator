package com.pim.aggregator.pimaggregator;

import com.pim.aggregator.pimaggregator.entity.Product;
import org.springframework.mock.web.MockHttpServletResponse;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ProductTestUtil
{
  public static Product getProduct()
  {
    return new Product("product1", "product 1 desc", "product 1 provide", true, "PC");
  }

  public static List<Product> getProducts()
  {
    return Arrays.asList(new Product("product1", "product 1 desc", "product 1 provide", true, "PC"),
            new Product("product2", "product 2 desc", "product 2 provide", true, "PC"),
            new Product("product3", "product 3 desc", "product 3 provide", false, "PC"));
  }

  public static void validate(MockHttpServletResponse mockHttpServletResponse, String response) throws UnsupportedEncodingException
  {
    assertEquals(200, mockHttpServletResponse.getStatus());
    assertEquals(response, mockHttpServletResponse.getContentAsString());
  }
}
