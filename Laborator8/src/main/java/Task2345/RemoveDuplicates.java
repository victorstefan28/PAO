package Task2345;

import java.sql.*;

public class RemoveDuplicates {
    public static void main(String[] args) {
        removeDuplicates();
    }
    public static void removeDuplicates() {
        String findDuplicates = "SELECT operation_type, left_operand, right_operand, operation, result, COUNT(*) FROM smarterCalculatorResults " +
                "GROUP BY operation_type, left_operand, right_operand, operation, result " +
                "HAVING COUNT(*) > 1";

        try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/laborator", "audiolib", "123456");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(findDuplicates)) {

            while (rs.next()) {
                deleteDuplicateEntries(rs.getString("operation_type"), rs.getString("left_operand"),
                        rs.getString("right_operand"), rs.getString("operation"),
                        rs.getString("result"));
            }
        } catch (SQLException e) {
            System.out.println("Error occurred during duplicate removal: " + e.getMessage());
            e.printStackTrace();
        }
    }
    private static void deleteDuplicateEntries(String type, String left, String right, String operation, String result) {
        String sqlDelete = "DELETE FROM smarterCalculatorResults WHERE id NOT IN (" +
                "SELECT MIN(id) FROM smarterCalculatorResults WHERE operation_type = ? AND left_operand = ? AND " +
                "right_operand = ? AND operation = ? AND result = ? GROUP BY operation_type, left_operand, right_operand, operation, result)";

        try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/laborator", "audiolib", "123456");
             PreparedStatement pstmt = conn.prepareStatement(sqlDelete)) {
            pstmt.setString(1, type);
            pstmt.setString(2, left);
            pstmt.setString(3, right);
            pstmt.setString(4, operation);
            pstmt.setString(5, result);
            int affectedRows = pstmt.executeUpdate();

            System.out.println("Deleted " + affectedRows + " duplicate records for operation " + operation);
        } catch (SQLException e) {
            System.out.println("Error in deleting duplicate entries: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
