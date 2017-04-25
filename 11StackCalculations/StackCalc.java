import java.util.Stack;

public class StackCalc {

    public static double eval(String s) {
        String[] tokens = s.split(" ");
        Stack<String> values = new Stack<String>();
        for (String str : tokens) {
            if (isOperator(str)) {
                String val = operate(str, values.pop(), values.pop());
                values.add(val);
            } else {
                values.push(str);
            }
        }
        return Double.parseDouble(values.pop());
    }

    private static boolean isOperator(String s) {
        return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/") || s.equals("%");
    }

    private static String operate(String op, String a, String b) {
        double p = Double.parseDouble(a);
        double q = Double.parseDouble(b);
        switch (op) {
            case "+":
                return q+p+"";
            case "-":
                return q-p+"";
            case "*":
                return q*p+"";
            case "/":
                return q/p+"";
            case "%":
                return q%p+"";
        }
        return "no";
    }
}
