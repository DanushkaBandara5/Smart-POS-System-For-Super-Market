package lk.ijse.dep10.app.business.util;


import lk.ijse.dep10.app.dto.CustomerDTO;
import lk.ijse.dep10.app.dto.ItemDTO;
import lk.ijse.dep10.app.entity.Customer;
import lk.ijse.dep10.app.entity.Item;
import lk.ijse.dep10.app.entity.OrderDetail;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class Transformer {

    private final ModelMapper mapper = new ModelMapper();

    public Transformer() {
        mapper.typeMap(ItemDTO.class, OrderDetail.class)
        .addMapping(ItemDTO::getCode,
                (orderDetail, code) -> orderDetail.getOrderDetailPK().setItemCode((String)code));
    }

    public Customer toCustomerEntity(CustomerDTO customerDTO){
        return mapper.map(customerDTO, Customer.class);
    }

    public CustomerDTO fromCustomerEntity(Customer customerEntity){
        return mapper.map(customerEntity, CustomerDTO.class);
    }

    public Item toItemEntity(ItemDTO itemDTO){
        return mapper.map(itemDTO, Item.class);
    }

    public ItemDTO fromItemEntity(Item itemEntity){
        return mapper.map(itemEntity, ItemDTO.class);
    }

    public OrderDetail toOrderDetailEntity(ItemDTO itemDTO){
        return mapper.map(itemDTO, OrderDetail.class);
    }
}
