package dal;

import model.Products;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO extends DBContext {

    PreparedStatement stm;
    ResultSet rs;
    List<Products> list = new ArrayList<>();

    public List<Products> getAll() {
        try {
            String strSelect = "select * from Products";
            stm = connection.prepareStatement(strSelect);
            rs = stm.executeQuery();
            while (rs.next()) {
                Products em = new Products(rs.getInt("Id"), rs.getString("Name"));
                list.add(em);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public static void main(String[] args) {
        ProductDAO objP = new ProductDAO();
        List<Products> list = objP.getAll();
        for (Products products : list) {
            System.out.println(products.getId() +" : "+ products.getName());

        }
    }
}
