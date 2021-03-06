package DeadClassInterface.Util;

import com.github.javaparser.ast.expr.ObjectCreationExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.List;

public class ObjectDeclarationVisitor extends VoidVisitorAdapter<List<String>> {

    @Override
    public void visit(ObjectCreationExpr n, List<String> arg) {
        super.visit(n, arg);
        arg.add(n.getTypeAsString());
    }
}
