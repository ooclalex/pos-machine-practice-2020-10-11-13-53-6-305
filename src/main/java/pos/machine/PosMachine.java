package pos.machine;

import java.util.*;

public class PosMachine {
    public String printReceipt(List<String> barcodes) {
        HashMap<String, Integer> hashMapItems = getQuantity(barcodes);
        ArrayList listReceipt = getPrice(hashMapItems);
        listReceipt = getSubtotal(listReceipt);

        return generateReceipt(listReceipt);
    }

    private String generateReceipt(ArrayList listReceipt) {
    }

    private ArrayList getSubtotal(ArrayList listReceipt) {
    }

    private ArrayList getPrice(HashMap<String, Integer> listReceipt) {
        ArrayList listReceipts = new ArrayList();
        for (Map.Entry<String,Integer> entry : listReceipt.entrySet()) {
            String barcode = entry.getKey();
            int quantity = entry.getValue();
            String name = "";

        }
        return listReceipts;
    }

    private HashMap<String, Integer> getQuantity(List<String> barcodes) {
        HashMap<String,Integer> hashMapItems = new HashMap<String,Integer>();
        for (String barcode : barcodes) {
            if (hashMapItems.containsKey(barcode)) {
                hashMapItems.put(barcode, hashMapItems.get(barcode) + 1);
            } else {
                hashMapItems.put(barcode, 1);
            }
        }
        return hashMapItems;
    }


}
