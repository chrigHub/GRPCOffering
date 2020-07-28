package jku.bac.grpc.Offering.grpcServer;

import io.grpc.Context;
import io.grpc.stub.StreamObserver;
import jku.bac.grpc.Offering.entity.GRPCItem;
import jku.bac.grpc.Offering.service.GRPCOfferingService;
import jku.bac.grpc.Offering.util.Util;
import jku.bac.grpc.server.grpcserver.CartOfferingServiceGrpc;
import jku.bac.grpc.server.grpcserver.CartRequest;
import jku.bac.grpc.server.grpcserver.Response;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@GrpcService
public class CartOfferingServer extends CartOfferingServiceGrpc.CartOfferingServiceImplBase {
    @Autowired
    private GRPCOfferingService offeringService;

    @Override
    public void buyCart(CartRequest request, StreamObserver<Response> responseObserver) {
        Context ctx = Context.current().fork();
        ctx.run(() -> {
            List<GRPCItem> grpcItemList = Util.CaOfItemToGRPCItem(request.getCaOfItemList());
            offeringService.removeItems(grpcItemList);
        });
        responseObserver.onNext(null);
        responseObserver.onCompleted();
    }
}
