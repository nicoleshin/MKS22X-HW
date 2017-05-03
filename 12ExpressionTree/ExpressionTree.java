public class ExpressionTree{
    private char op;
    private double value;
    private ExpressionTree left,right;

    /*TreeNodes are immutable, so no issues with linking them across multiple
     *   *  expressions. The can be constructed with a value, or operator and
     *   2
     *     * sub-ExpressionTrees*/
    public ExpressionTree(double value){
        this.value = value;
        op = '~';
    }
    public ExpressionTree(char op,ExpressionTree l, ExpressionTree r){
        this.op = op;
        left = l;
        right = r;
    }

    public char getOp(){
        return op;
    }

    private double getValue(){
        return value;
    }

    private ExpressionTree getLeft(){
        return left;
    }

    private ExpressionTree getRight(){
        return right;
    }

    private boolean isOp(){
        return hasChildren();
    }
    private boolean isValue(){
        return !hasChildren();
    }

    private boolean hasChildren(){
        return left != null && right != null;
    }

    public static void main(String[] args){
        ExpressionTree a = new ExpressionTree(4.0);
        ExpressionTree b = new ExpressionTree(2.0);

        ExpressionTree c = new ExpressionTree('+',a,b);
        System.out.println(c);
        System.out.println(c.toStringPostfix());
        System.out.println(c.toStringPrefix());
        System.out.println(c.evaluate());

        ExpressionTree d = new ExpressionTree('*',c,new ExpressionTree(3.5));
        System.out.println(d);
        System.out.println(d.toStringPostfix());
        System.out.println(d.toStringPrefix());
        System.out.println(d.evaluate());

        ExpressionTree ex = new ExpressionTree('-',d,new ExpressionTree(1.0));
        System.out.println(ex);
        System.out.println(ex.toStringPostfix());
        System.out.println(ex.toStringPrefix());
        System.out.println(ex.evaluate());

        ex = new ExpressionTree('+',new ExpressionTree(1.0),ex);
        System.out.println(ex);
        System.out.println(ex.toStringPostfix());
        System.out.println(ex.toStringPrefix());
        System.out.println(ex.evaluate());

        ex = new ExpressionTree('/',ex,new ExpressionTree(2.0));
        System.out.println(ex);
        System.out.println(ex.toStringPostfix());
        System.out.println(ex.toStringPrefix());
        System.out.println(ex.evaluate());
    }

    /*return the expression as an infix notation string with
     * parenthesis*/
    /* The sample tree at the top would be: "( 3 + (2 * 10))"
     * */
    public String toString() {
        if (isValue()) {
            return getValue() + "";
        } else {
            return "(" + getLeft() + " " + getOp() + " " + getRight() + ")";
        }
    }

    /*return the expression as a postfix notation string without
     * parenthesis*/
    /* The sample tree would be: "3 2 10 * +"     */
    public String toStringPostfix() {
        String ret;
        if (isValue()) {
            // Value
            ret = getValue() + "";
        } else {
            // Operator
            ret = getLeft().toStringPostfix() + " " + getRight().toStringPrefix() + " ";
            ret += getOp() + "";
        }
        return ret;
    }

    /*return the expression as a prefix notation string
     * without parenthesis*/
    /* The sample tree would be: "+ 3 * 2 10"     */
    public String toStringPrefix() {
        String ret;
        if (isValue()) {
            // Value
            ret = getValue() + "";
        } else {
            // Operator
            ret = getOp() + " ";
            ret += getLeft().toStringPrefix() + " " + getRight().toStringPrefix();
        }
        return ret;
    }

    /*return the value of the expression tree*/
    public double evaluate() {
        if (isValue()) {
            return getValue();
        } else {
            double left = getLeft().evaluate();
            double right = getRight().evaluate();
            switch (getOp()) {
                case '+':
                    return left + right;
                case '-':
                    return left - right;
                case '*':
                    return left * right;
                case '/':
                    return left / right;
                default:
                    return Double.POSITIVE_INFINITY;
            }
        }
    }

    // untested
    public static ExpressionTree postfixToTree(String s) {
        String[] tokens = s.split(" ");
        Stack<ExpressionTree> trees = new Stack<ExpressionTree>();
        for (String str : tokens) {
            if (isOperator(str)) {
                ExpressionTree t = new ExpressionTree(str.charAt(0), trees.pop(), trees.pop());
                trees.push(t);
            } else {
                trees.push(new ExpressionTree(Double.parseDouble(str)));
            }
        }
        return trees.pop();
    }

    private static boolean isOperator(String s) {
        return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/") || s.equals("%");
    }
}
