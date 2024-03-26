package com.example.springmq.consumer;

import com.example.springmq.entites.OrderOnline;
import com.example.springmq.entites.Product;
import com.example.springmq.service.BashService;
import com.example.springmq.service.EmailService;
import com.example.springmq.service.impl.OrderServiceImpl;
import com.example.springmq.service.impl.ProductServiceImpl;
import com.example.springmq.util.ConvertUtil;
import jakarta.jms.*;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class SendQueue {

    @Qualifier("jmsTemplate")
    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    OrderServiceImpl orderService;

    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    private EmailService emailService;

    public void sendMq(OrderOnline orderOnline){
       try{
           ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);
           Connection connection = connectionFactory.createConnection();
           connection.start();

           Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
           Queue queue = session.createQueue("order");

           MessageProducer producer = session.createProducer(queue);

           ConvertUtil convertUtil = new ConvertUtil();
           String json = convertUtil.convertObjectToJson(orderOnline);
           BashService bashService = new BashService();
           String message = bashService.encode(json);

           System.out.println("json: " + json);
           System.out.println("Sending message: " + message);
           TextMessage textMessage = session.createTextMessage(message);

           producer.send(textMessage);

           session.close();
           connection.close();
       }catch (Exception e){
           e.printStackTrace();
       }
    }

    @JmsListener(destination = "order", containerFactory = "jmsListenerContainerFactory")
    public void receiveMessage(final Message jsonMessage){
        try{
            if(jsonMessage instanceof TextMessage textMessage){
                String message = textMessage.getText();
                BashService bashService = new BashService();
                String json = bashService.decode(message);
                ConvertUtil convertUtil = new ConvertUtil();
                OrderOnline orderOnline = convertUtil.convertJsonToOrderOnline(json);
                Product product = productService.getProductById(orderOnline.getProduct().getProductId());
                product.setQuantity(product.getQuantity() - orderOnline.getOrderQuantity());
                productService.updateProductDB(product);
                orderService.addOrderDB(orderOnline);
                //Gửi mail start
                String subject = "Cảm ơn bạn đã mua hàng của chúng tôi!";
                String body = "<html><body>"
                        + "<h2>Cảm ơn bạn đã mua hàng!</h2>"
                        + "<p>Xin chào " + orderOnline.getOrderName() + ",</p>"
                        + "<p>Chúng tôi xin gửi lời cảm ơn sâu sắc đến bạn đã mua hàng tại cửa hàng của chúng tôi.</p>"
                        + "<p>Mong rằng bạn sẽ hài lòng với sản phẩm mà bạn đã mua. Nếu bạn có bất kỳ câu hỏi hoặc yêu cầu nào, đừng ngần ngại liên hệ với chúng tôi.</p>"
                        + "<p>Dưới đây là thông tin về đơn hàng của bạn:</p>"
                        + "<p><strong>Tên Khách Hàng:</strong> " + orderOnline.getOrderName() + "</p>"
                        + "<p><strong>Email:</strong> " + orderOnline.getOrderEmail() + "</p>"
                        + "<p><strong>Tên Sản Phẩm:</strong> " + product.getProductName() + "</p>"
                        + "<p><strong>Số Lượng Đặt:</strong> " + orderOnline.getOrderQuantity() + "</p>"
                        + "<p>Xin cảm ơn một lần nữa!</p>"
                        + "<p>Trân trọng,</p>"
                        + "<p>Đội ngũ cửa hàng của chúng tôi</p>"
                        + "</body></html>";
                String emailKh = orderOnline.getOrderEmail();
                emailService.sendEmail(emailKh, body, subject);
                //Gửi mail end
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
