package com.pim.aggregator.pimaggregator.listener;

import com.pim.aggregator.pimaggregator.entity.Product;
import com.pim.aggregator.pimaggregator.manager.ProductManagerImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.amqp.core.Message;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RabbitMQListenerTest
{

  @InjectMocks
  RabbitMQListener rabbitMQListener;

  @Mock
  ProductManagerImpl productManager;

  @Test
  public void givenMessageOtherThanProductWhenReceiverParseItFailedAndNotSaved()
  {
    Message message = new Message("hello".getBytes(), null);
    rabbitMQListener.receive(message);
    verify(productManager, times(0)).save(any(Product.class));
  }

  @Test
  public void givenMessageProductWhenReceiverParseAndSaved()
  {
    doNothing().when(productManager).save(any(Product.class));
    Message message = new Message("{\"name\":\"product1\",\"description\":\"product 1 desc\",\"provider\":\"product 1 provide\",\"available\":true,\"measurementUnits\":\"PC\"}".getBytes(), null);
    rabbitMQListener.receive(message);
    verify(productManager, times(1)).save(any(Product.class));
  }
}