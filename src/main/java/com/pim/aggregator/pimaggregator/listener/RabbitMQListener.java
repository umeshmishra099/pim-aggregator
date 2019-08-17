package com.pim.aggregator.pimaggregator.listener;

import com.fasterxml.jackson.databind.*;
import com.pim.aggregator.pimaggregator.entity.Product;
import com.pim.aggregator.pimaggregator.manager.ProductManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RabbitMQListener
{
  private final ObjectMapper configure = new ObjectMapper()
          .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
          .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);

  private static final Logger logger = LoggerFactory.getLogger(RabbitMQListener.class);

  private final ProductManager productManager;

  @Autowired
  public RabbitMQListener(ProductManager productManager)
  {
    this.productManager = productManager;
  }

  @RabbitListener(queues = "product-information-queue")
  public void receive(Message message)
  {
    final String body = new String(message.getBody());
    try
    {
      final Product product = configure.readValue(body, Product.class);

      logger.info("Product received : " + product.toString());

      productManager.save(product);
    }
    catch (IOException e)
    {
      logger.error("Error in parsing message body" + body);
    }
  }
}
