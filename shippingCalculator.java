public class ShippingCalculator {
    public static void main(String[] args) {
        double orderTotal = 1250.50;
        int stockQuantity = 0;

        double shippingFee = (orderTotal >= 1000.0) ? 0.0 : 150.0;
        
        String itemStatus = (stockQuantity > 0) ? "in stock" : "Out of Stock";

        System.out.println("ShippingFee: PHP " + shippingFee);
        System.out.println("Item Availability: " + itemStatus);
    }
    
}
