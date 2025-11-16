package com.project.product_management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.math.BigDecimal;          // ✅ ADD THIS IMPORT

import com.project.dbinfo.DatabaseConnection;

public class AddProductDAO {

    public int insertProduct(ProductBean pb) {
        int rowCount = 0;

        try (Connection con = DatabaseConnection.connect()) {

            // ✅ always mention column names
            String sql = "INSERT INTO product121 (pcode, pname, pcompany, pprice, pqty) VALUES (?,?,?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setString(1, pb.getP_code());
            pstmt.setString(2, pb.getP_name());
            pstmt.setString(3, pb.getP_company());

            // ✅ convert String → numeric for Postgres
            BigDecimal price = new BigDecimal(pb.getP_price());
            pstmt.setBigDecimal(4, price);

            int qty = Integer.parseInt(pb.getP_qty());
            pstmt.setInt(5, qty);

            rowCount = pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rowCount;
    }
}
