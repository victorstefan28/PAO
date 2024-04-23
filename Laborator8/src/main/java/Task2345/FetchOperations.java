package Task2345;


import java.sql.*;

public class FetchOperations {
    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/laborator", "audiolib", "123456"))
        {
            /// SELECT operation_type, left_operand, right_operand, operation, result FROM smarterCalculatorResults

            String sqlSelect = "SELECT operation_type, left_operand, right_operand, operation, result FROM smarterCalculatorResults";
            try (Statement stmt = connection.createStatement();
                 ResultSet rs = stmt.executeQuery(sqlSelect)) {  // Corrected to use executeQuery for SELECT
                while (rs.next()) {
                    String operationType = rs.getString("operation_type");
                    String leftOperand = rs.getString("left_operand");
                    String rightOperand = rs.getString("right_operand");
                    String operation = rs.getString("operation");
                    String result = rs.getString("result");

                    CalculatorRequest request = new CalculatorRequest(leftOperand, rightOperand, operation);
                    System.out.println(request);


                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
