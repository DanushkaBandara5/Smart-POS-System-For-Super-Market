package lk.ijse.dep10.app.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import lk.ijse.dep10.app.business.custom.CustomerBO;
import lk.ijse.dep10.app.business.exception.BusinessException;
import lk.ijse.dep10.app.business.exception.BusinessExceptionType;
import lk.ijse.dep10.app.dto.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;


public class CustomerWSHandler extends TextWebSocketHandler {
    @Autowired
    private CustomerBO customerBO;


    @Autowired
    private ObjectMapper objectMapper;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

        try {
            CustomerDTO customer = customerBO
                    .findCustomerByIdOrContact(message.getPayload().strip());
            String customerJSON = objectMapper.writeValueAsString(customer);
            session.sendMessage(new TextMessage(customerJSON));
        } catch (BusinessException be) {
            if (be.getType() != BusinessExceptionType.RECORD_NOT_FOUND) throw be;
        }
    }
}
