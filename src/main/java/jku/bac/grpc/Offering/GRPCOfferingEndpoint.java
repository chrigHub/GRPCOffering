package jku.bac.grpc.Offering;

import jku.bac.grpc.Offering.service.GRPCOfferingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GRPCOfferingEndpoint {
    @Autowired
    GRPCOfferingService grpcOfferingService;

    @GetMapping("/resetDB")
    public String resetDB() throws Exception{
        grpcOfferingService.insertItems();
        return "Offering database has been reset";
    }
}
