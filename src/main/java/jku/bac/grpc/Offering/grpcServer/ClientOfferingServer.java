package jku.bac.grpc.Offering.grpcServer;

import io.grpc.stub.StreamObserver;
import jku.bac.grpc.Offering.service.GRPCOfferingService;
import jku.bac.grpc.Offering.util.Util;
import jku.bac.grpc.server.grpcserver.ClientOfferingServiceGrpc;
import jku.bac.grpc.server.grpcserver.ClientRequest;
import jku.bac.grpc.server.grpcserver.TransferItem;
import jku.bac.grpc.server.grpcserver.OfferingResponse;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@GrpcService
public class ClientOfferingServer extends ClientOfferingServiceGrpc.ClientOfferingServiceImplBase {
    @Autowired
    private GRPCOfferingService offeringService;

    @Override
    public void getOffering(ClientRequest request, StreamObserver<OfferingResponse> responseObserver){
        List<TransferItem> itemList = null;
        System.out.println("Offering Server received!");
        try {
            itemList = Util.GRPCItemToTransferItem(offeringService.getAllItems());
            itemList = Util.GRPCItemToTransferItem(offeringService.getDiscount(Util.TransferItemToGRPCItem(itemList)));
        }catch (Exception e){
            e.printStackTrace();
        }
        OfferingResponse response = OfferingResponse.newBuilder()
                .addAllTransferItems(itemList)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
