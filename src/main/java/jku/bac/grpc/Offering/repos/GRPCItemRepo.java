package jku.bac.grpc.Offering.repos;


import jku.bac.grpc.Offering.entity.GRPCItem;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GRPCItemRepo extends MongoRepository<GRPCItem, String> {
    public GRPCItem findByLabel(String label);
}
