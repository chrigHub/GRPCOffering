package jku.bac.grpc.Offering.grpcClient;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import jku.bac.grpc.Offering.entity.GRPCItem;
import jku.bac.grpc.Offering.util.Util;
import jku.bac.grpc.server.offeringdiscount.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class OfferingDiscountClient {

    private static ManagedChannel channel;

    StreamObserver<Null> syncObserver = new StreamObserver<Null>() {
        @Override
        public void onNext(Null aNull) {
            System.out.println("checkout onNext");
        }

        @Override
        public void onError(Throwable throwable) {
            System.out.println("sync on Error");
            channel.shutdown();
            throwable.printStackTrace();
        }

        @Override
        public void onCompleted() {
            System.out.println("sync on complete!");
            channel.shutdown();
        }
    };

    public List<GRPCItem> getDiscount(List<GRPCItem> list){
        System.out.println("Offering Client starts sending!");
        List<OfferingDiscountItem> itemList = Util.GRPCItemToOfferingDiscountItem(list);
        this.channel = initChannel();
        OfferingDiscountServiceGrpc.OfferingDiscountServiceBlockingStub stub = OfferingDiscountServiceGrpc.newBlockingStub(channel);
        OfferingRequest offeringRequest = OfferingRequest.newBuilder()
                .addAllTransferItems(itemList)
                .build();
        DiscountResponse discountResponse = stub.getDiscount(offeringRequest);
        channel.shutdown();
        return Util.OfferingDiscountItemToGRPCItem(discountResponse.getTransferItemsList());
    }

    public void syncDB(String itemLabel){
        System.out.println("Removing " + itemLabel + " from Discount becuase of sync!");
        this.channel = initChannel();
        OfferingDiscountServiceGrpc.OfferingDiscountServiceStub stub = OfferingDiscountServiceGrpc.newStub(channel);
        Sync sync = Sync.newBuilder()
                .setItemLabel(itemLabel)
                .build();
        stub.syncDB(sync, syncObserver);
    }

    private static ManagedChannel initChannel(){
        return ManagedChannelBuilder.forAddress("localhost", 9093)
                .usePlaintext()
                .build();
    }
}
