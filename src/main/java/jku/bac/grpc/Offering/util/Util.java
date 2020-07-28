package jku.bac.grpc.Offering.util;

import jku.bac.grpc.Offering.entity.GRPCItem;
import jku.bac.grpc.server.grpcserver.CaOfItem;
import jku.bac.grpc.server.grpcserver.TransferItem;
import jku.bac.grpc.server.offeringdiscount.OfferingDiscountItem;

import java.util.ArrayList;
import java.util.List;

public class Util {

    public static List<OfferingDiscountItem> GRPCItemToOfferingDiscountItem(List<GRPCItem> GRPCItemList){
        List<OfferingDiscountItem> odItemList = new ArrayList<>();
        for(GRPCItem GRPCItem : GRPCItemList) {
            OfferingDiscountItem odItem = OfferingDiscountItem.newBuilder()
                    .setId(GRPCItem.getId())
                    .setLabel(GRPCItem.getLabel())
                    .setPrize(GRPCItem.getPrize())
                    .setAmount(GRPCItem.getAmount())
                    .build();
            odItemList.add(odItem);
        }
        return odItemList;
    }

    public static List<GRPCItem> OfferingDiscountItemToGRPCItem(List<OfferingDiscountItem> odItemList){
        List<GRPCItem> grpcItemList = new ArrayList<>();
        for(OfferingDiscountItem odItem : odItemList) {
            GRPCItem grpcItem = new GRPCItem();
            grpcItem.setId(odItem.getId());
            grpcItem.setLabel(odItem.getLabel());
            grpcItem.setAmount(odItem.getAmount());
            grpcItem.setPrize(odItem.getPrize());

            grpcItemList.add(grpcItem);
        }
        return grpcItemList;
    }

    public static List<TransferItem> GRPCItemToTransferItem(List<GRPCItem> GRPCItemList){
        List<TransferItem> transferItemList = new ArrayList<>();
        for(GRPCItem GRPCItem : GRPCItemList) {
            TransferItem transferItem = TransferItem.newBuilder()
                    .setId(GRPCItem.getId())
                    .setLabel(GRPCItem.getLabel())
                    .setPrize(GRPCItem.getPrize())
                    .setAmount(GRPCItem.getAmount())
                    .build();
            transferItemList.add(transferItem);
        }
        return transferItemList;
    }

    public static List<GRPCItem> TransferItemToGRPCItem(List<TransferItem> transferItemList){
        List<GRPCItem> grpcItemList = new ArrayList<>();
        for(TransferItem transferItem : transferItemList) {
            GRPCItem grpcItem = new GRPCItem();
            grpcItem.setId(transferItem.getId());
            grpcItem.setLabel(transferItem.getLabel());
            grpcItem.setAmount(transferItem.getAmount());
            grpcItem.setPrize(transferItem.getPrize());

            grpcItemList.add(grpcItem);
        }
        return grpcItemList;
    }

    public static List<GRPCItem> CaOfItemToGRPCItem(List<CaOfItem> caofItemList){
        List<GRPCItem> grpcItemList = new ArrayList<>();
        for(CaOfItem caOfItem : caofItemList){
            GRPCItem grpcItem = new GRPCItem();
            grpcItem.setId(caOfItem.getId());
            grpcItem.setLabel(caOfItem.getLabel());
            grpcItem.setPrize(caOfItem.getPrize());
            grpcItem.setAmount(caOfItem.getAmount());

            grpcItemList.add(grpcItem);
        }
        return grpcItemList;
    }

}
