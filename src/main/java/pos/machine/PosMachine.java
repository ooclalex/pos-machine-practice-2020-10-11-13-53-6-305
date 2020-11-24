package pos.machine;

import java.util.*;

public class PosMachine {
    // P: 3 mins
    // D: 3 mins
    // C: everything worked well
    // A: no improvement
    public String printReceipt(List<String> barcodes) {
        LinkedHashMap<String, Integer> hashMapItems = getQuantity(barcodes);
        ArrayList<ArrayList<Object>> listReceipt = getPrice(hashMapItems);
        getSubtotal(listReceipt);

        return generateReceipt(listReceipt);
    }

    // P: 5 mins
    // D: 5 mins
    // C: everything worked well
    // A: no improvement
    private String generateReceiptItem(ArrayList<Object> listItems) {
        return "Name: " + listItems.get(0) + ", Quantity: " + listItems.get(1) +
                ", Unit price: " + listItems.get(2) + " (yuan), Subtotal: " +
                listItems.get(3) + " (yuan)\n";
    }

    // P: 5 mins
    // D: 5 mins
    // C: everything worked well
    // A: no improvement
    private String generateReceipt(ArrayList<ArrayList<Object>> listReceipt) {
        int sum = 0;
        StringBuilder s = new StringBuilder("***<store earning no money>Receipt***\n");
        for (ArrayList<Object> objects : listReceipt) {
            s.append(generateReceiptItem(objects));
            sum += (int) objects.get(3);
        }
        s.append("----------------------\n");
        s.append("Total: ").append(sum).append(" (yuan)\n");
        s.append("**********************");
        return s.toString();
    }

    // P: 10 mins
    // D: 15 mins
    // C: need to specify type in the arraylist listReceipt
    // A: learn more about how arraylist works
    private void getSubtotal(ArrayList<ArrayList<Object>> listReceipt) {
        for (ArrayList<Object> objects : listReceipt) {
            int quantity = (int) objects.get(1);
            int price = (int) objects.get(2);
            objects.add(price * quantity);
        }
    }

    // P: 10 mins
    // D: 10 mins
    // C: everything worked well
    // A: no improvement
    private ArrayList<ArrayList<Object>> getPrice(LinkedHashMap<String, Integer> listReceipt) {
        ArrayList<ArrayList<Object>> listReceipts = new ArrayList<ArrayList<Object>>();
        for (Map.Entry<String,Integer> entry : listReceipt.entrySet()) {
            String barcode = entry.getKey();
            int quantity = entry.getValue();
            ArrayList<Object> listItem = new ArrayList<Object>();
            List<ItemInfo> database = ItemDataLoader.loadAllItemInfos();
            for (ItemInfo itemInfo : database) {
                if (itemInfo.getBarcode().equals(barcode)) {
                    listItem.add(itemInfo.getName());
                    listItem.add(quantity);
                    listItem.add(itemInfo.getPrice());
                }
            }
            listReceipts.add(listItem);
        }
        return listReceipts;
    }

    // P: 5 mins
    // D: 10 mins
    // C: had to lookup using linked hashmap instead of hashmap
    // A: learn about the limitations of hashmap
    private LinkedHashMap<String, Integer> getQuantity(List<String> barcodes) {
        LinkedHashMap<String,Integer> hashMapItems = new LinkedHashMap<String,Integer>();
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
