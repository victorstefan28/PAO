package Task2345;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RemoveOpType {
    public static void main(String[] args) {
        // Example usage: delete entries where operation_type is 'Integer'
        deleteEntriesByOperationType("Integer");
    }

    public static void deleteEntriesByOperationType(String operationType) {
        String sqlDelete = "DELETE FROM smarterCalculatorResults WHERE operation_type = ?";

        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/laborator", "audiolib", "123456");
             PreparedStatement pstmt = connection.prepareStatement(sqlDelete))
        {
            pstmt.setString(1, operationType);
            int affectedRows = pstmt.executeUpdate();


            if (affectedRows > 0) {
                System.out.println("Successfully deleted " + affectedRows + " records where operation_type is '" + operationType + "'.");
            } else {
                System.out.println("No records found with operation_type '" + operationType + "'.");
            }
        } catch (SQLException e) {
            System.out.println("Error occurred during deletion: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
