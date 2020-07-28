package jku.bac.grpc.Offering.service;

import jku.bac.grpc.Offering.entity.GRPCItem;
import jku.bac.grpc.Offering.grpcClient.OfferingDiscountClient;
import jku.bac.grpc.Offering.repos.GRPCItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GRPCOfferingService {

    @Autowired
    private GRPCItemRepo GRPCItemRepo;
    private OfferingDiscountClient offeringDiscountClient;

    public GRPCOfferingService(OfferingDiscountClient offeringDiscountClient) {
        this.offeringDiscountClient = offeringDiscountClient;
    }

    public void insertItems() throws Exception {
        GRPCItemRepo.deleteAll();
        GRPCItemRepo.save(new GRPCItem("Screw", 0.2, 1000));
        GRPCItemRepo.save(new GRPCItem("Screwdriver", 19.99, 10));
        GRPCItemRepo.save(new GRPCItem("Drill", 199.99, 1));
    }

    public List<GRPCItem> getAllItems() throws Exception {
        //fetching all items
        List<GRPCItem> GRPCItemList = GRPCItemRepo.findAll();
        return GRPCItemList;
    }

    public List<GRPCItem> getDiscount(List<GRPCItem> itemList){
        return offeringDiscountClient.getDiscount(itemList);
    }

    public void removeItems(List<GRPCItem> itemList){
        for(GRPCItem inItem : itemList){
            GRPCItem dbItem = GRPCItemRepo.findByLabel(inItem.getLabel());
            if(dbItem.getAmount() - inItem.getAmount() >= 0){
                dbItem.setAmount(dbItem.getAmount() - inItem.getAmount());
                if(dbItem.getAmount() == 0){
                    GRPCItemRepo.delete(dbItem);
                    offeringDiscountClient.syncDB(dbItem.getLabel());
                    System.out.println("Should remove Item from DB");
                } else {
                    System.out.println("Purchase complete!");
                    GRPCItemRepo.save(dbItem);
                }
            }else{
                System.out.println(inItem.getAmount()+" x "+inItem.getLabel()+" kann nicht gekauft werden. Bestand zu gering!" );
            }

        }
    }

}
