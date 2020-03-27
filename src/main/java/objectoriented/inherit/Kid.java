package objectoriented.inherit;

/**
 * @author 李昭
 */
public class Kid extends ManKind {
    private int yearsOld;
    public void setYearsOld(int yearsOld)
    {
        this.yearsOld = yearsOld;
    }
    public void printAge()
    {
        System.out.println(yearsOld);
    }
}
class KidTest{
    public static void main(String[] args) {
        Kid someKid = new Kid();
        someKid.setYearsOld(20);
        someKid.printAge();
        someKid.setSex(1);
        someKid.setSalary(200);
        someKid.manOrWoman();
        someKid.employee();
    }
}