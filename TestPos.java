public class TestPos {
    public static void main(String[] args) {
        Position instance1 = new Position('A',1);
        Position instance2 = new Position(0,0);

        System.out.println(instance1.row());
        System.out.println(instance1.column());
        System.out.println(instance1.rowIndex());
        System.out.println(instance1.columnIndex());
        System.out.println(instance1.gridval());

        System.out.println(" ");

        System.out.println(instance2.row());
        System.out.println(instance2.column());
        System.out.println(instance2.rowIndex());
        System.out.println(instance2.columnIndex());
        System.out.println(instance2.gridval());
    }
}