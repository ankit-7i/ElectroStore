package com.project.product_management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.math.BigDecimal;

import com.project.dbinfo.DatabaseConnection;

public class UpdateProductDAO {

    public int updateProduct(ProductBean pb) {

        int rowCount = 0;

        try {
            Connection con = DatabaseConnection.connect();

            PreparedStatement pstmt = con.prepareStatement(
                "UPDATE product121 SET pprice = ?, pqty = ? WHERE pcode = ?"
            );

            // PostgreSQL numeric conversion
            BigDecimal price = new BigDecimal(pb.getP_price());
            pstmt.setBigDecimal(1, price);

            int qty = Integer.parseInt(pb.getP_qty());
            pstmt.setInt(2, qty);

            pstmt.setString(3, pb.getP_code());

            rowCount = pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rowCount;
    }
}
