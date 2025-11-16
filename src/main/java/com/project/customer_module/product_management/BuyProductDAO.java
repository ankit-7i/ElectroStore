package com.project.customer_module.product_management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.math.BigDecimal;   // added for numeric price

import com.project.dbinfo.DatabaseConnection;
import com.project.product_management.ProductBean;

public class BuyProductDAO {

    public ProductBean getProduct(String pcode) {

        ProductBean pb = null;

        try {
            Connection con = DatabaseConnection.connect();

            PreparedStatement pstmt =
                con.prepareStatement("SELECT pcode, pname, pcompany, pprice, pqty FROM product121 WHERE pcode = ?");
            pstmt.setString(1, pcode);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {

                pb = new ProductBean();
                
                // SAME as your original code for strings
                pb.setP_code(rs.getString("pcode"));
                pb.setP_name(rs.getString("pname"));
                pb.setP_company(rs.getString("pcompany"));

                // IMPORTANT FIX for PostgreSQL numeric columns
                BigDecimal price = rs.getBigDecimal("pprice");
                pb.setP_price(price.toString());      // Keep string for JSP/Servlet compatibility

                int qty = rs.getInt("pqty");
                pb.setP_qty(String.valueOf(qty));    // Keep string for JSP/Servlet
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return pb;
    }
}
