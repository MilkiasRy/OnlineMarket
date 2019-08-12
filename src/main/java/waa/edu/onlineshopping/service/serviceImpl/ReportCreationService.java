package waa.edu.onlineshopping.service.serviceImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import waa.edu.onlineshopping.domain.Buyer;
import waa.edu.onlineshopping.domain.CartItem;
import waa.edu.onlineshopping.domain.Orders;
import waa.edu.onlineshopping.domain.User;
import waa.edu.onlineshopping.dto.ReportDto;
import waa.edu.onlineshopping.dto.TableDto;
import waa.edu.onlineshopping.repository.CartItemRepository;
import waa.edu.onlineshopping.service.BuyerService;
import waa.edu.onlineshopping.service.CartItemService;
import waa.edu.onlineshopping.service.CartService;
import waa.edu.onlineshopping.service.UserService;

import java.security.Principal;
import java.util.*;

@Service
public class ReportCreationService {
    @Autowired
    UserService userService;
    @Autowired
    BuyerService buyerService;
    @Autowired
    CartItemRepository cartItemRepository;
    @Autowired
    CartService cartService;
    public ReportDto createReport(Principal principal,Long id) {
        User user  = userService.findUserByEmail(principal.getName());
        Buyer buyer= buyerService.findById(user.getBuyer().getId());
        Orders orders=new Orders();
        orders.setId(id);
        List<CartItem> cartItemList =cartItemRepository.findByOrder(orders);
        ReportDto report = new ReportDto(buyer.getUser().getName(), "Thanks You for Shopping with us");
        System.out.println("+++++++++++++++++++"+ report);
        Collection<TableDto> table = new ArrayList<>();
         for(CartItem cartItem:cartItemList){
             System.out.println("############");
             System.out.println(cartItem.getQuantity()+""+cartItem.getProduct().getName());
             System.out.println(cartItem.getTotalPrice()+""+cartItem.getProduct().getDiscountPrice());
             System.out.println("############");
             TableDto tableDto=new TableDto(cartItem.getQuantity(),cartItem.getTotalPrice(),cartItem.getProduct().getDiscountPrice(),cartItem.getProduct().getName());
             table.add(tableDto);
         }
                report.setTable(table);
        return report;
    }
}
