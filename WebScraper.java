import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.FileWriter;
import java.io.IOException;

public class WebScraper {
    public static void main(String[] args) {
        // Example URL (Use a legal scraping sandbox or public site)
        String url = "https://example-ecommerce-site.com/products";
        
        try {
            // 1. Connect to the website
            Document doc = Jsoup.connect(url).get();
            
            // 2. Select product elements (CSS selectors vary by site)
            Elements products = doc.select(".product-card");
            
            FileWriter writer = new FileWriter("products.csv");
            writer.append("Name,Price,Rating\n");

            for (Element product : products) {
                String name = product.select(".product-name").text();
                String price = product.select(".product-price").text();
                String rating = product.select(".product-rating").text();
                
                // 3. Write to CSV
                writer.append(name + "," + price + "," + rating + "\n");
            }
            
            writer.close();
            System.out.println("Data successfully saved to products.csv");
            
        } catch (IOException e) {
            System.out.println("Error connecting to the website: " + e.getMessage());
        }
    }
}