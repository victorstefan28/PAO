package Task1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;


public class Task1 {
    public static List<CalculatorResult> testOperations() {
        String[] arguments = {"3", "+", "5", "10.5", "*", "4.5", "true", "AND", "false"};
        return foo(arguments);
    }

    public static List<CalculatorResult> foo(String[] args) {
        List<CalculatorResult> calculationResults = SmarterCalculator.calculate(args);
        return calculationResults;
//        for (CalculatorResult result : calculationResults) {
//            CalculatorRequest request = result.getRequest();
//            return result.computeResult();
//            // System.out.println("Operation: " + request + " has result " + result.computeResult());
//        }
    }
    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/laborator", "audiolib", "123456");
         Statement stmt = connection.createStatement()) {
            System.out.println("Connection established");

            String sqlCreate = "CREATE TABLE IF NOT EXISTS smarterCalculatorResults (" +
                        "id SERIAL PRIMARY KEY," +
                        "operation_type VARCHAR(20) NOT NULL," +
                        "left_operand VARCHAR(254) NOT NULL," +
                        "right_operand VARCHAR(254) NOT NULL," +
                        "operation VARCHAR(5) NOT NULL," +
                        "result VARCHAR(255) NOT NULL)";

            stmt.execute(sqlCreate);
            System.out.println("Table checked/created successfully!");
            for(CalculatorResult result : testOperations()) {
                CalculatorRequest request = result.getRequest();
                String sqlInsert = "INSERT INTO smarterCalculatorResults (operation_type, left_operand, right_operand, operation, result) VALUES ('" +
                        request.getRequestType() + "', '" + request.leftOperand() + "', '" + request.rightOperand() + "', '" + request.operation() + "', '" + result.computeResult() + "')";
                System.out.println(sqlInsert);
                stmt.execute(sqlInsert);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
