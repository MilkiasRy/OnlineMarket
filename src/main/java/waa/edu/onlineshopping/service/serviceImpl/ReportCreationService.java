package waa.edu.onlineshopping.service.serviceImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import waa.edu.onlineshopping.domain.*;
import waa.edu.onlineshopping.dto.ReportDto;
import waa.edu.onlineshopping.dto.TableDto;
import waa.edu.onlineshopping.repository.CartItemRepository;
import waa.edu.onlineshopping.service.*;

import java.security.Principal;
import java.util.*;

@Service
public class ReportCreationService {

    @Autowired
    CredentialService credentialService;
    @Autowired
    BuyerService buyerService;
    @Autowired
    CartItemRepository cartItemRepository;
    @Autowired
    CartService cartService;
    public ReportDto createReport(Principal principal,Long id) {
        Credential credential = credentialService.findByEmail(principal.getName());
        Buyer buyer = buyerService.findByCredential(credential);

        Orders orders=new Orders();
        orders.setId(id);
        List<CartItem> cartItemList =cartItemRepository.findByOrder(orders);
        ReportDto report = new ReportDto(buyer.getFirstName(), "Thanks You for Shopping with us");
        System.out.println("+++++++++++++++++++"+ report);
        Collection<TableDto> table = new ArrayList<>();
         for(CartItem cartItem:cartItemList){
             System.out.println("############");

             System.out.println("############");
             TableDto tableDto=new TableDto(cartItem.getQuantity(),cartItem.getTotalPrice(),cartItem.getProduct().getDiscountPrice(),cartItem.getProduct().getName());
             table.add(tableDto);
         }
                report.setTable(table);
        return report;
    }
}
