package objectoriented.ooproject.view;

import objectoriented.ooproject.bean.Customer;
import objectoriented.ooproject.service.CustomerList;
import objectoriented.ooproject.util.util;

@SuppressWarnings("all")
public class CustomerView {
    private static CustomerList list = new CustomerList(10);

    public static void main(String[] args) {
        enterMainMenu();
    }
    /**
     * 主界面
     */
    public static void enterMainMenu()
    {
        boolean flag = true;
        while (flag) {

            System.out.println("-----------------客户管理软件-------------------\n");
            System.out.println("                 1 添加客户\n");
            System.out.println("                 2 修改客户\n");
            System.out.println("                 3 删除客户\n");
            System.out.println("                 4 客户列表\n");
            System.out.println("                 5 退    出\n");
            char c = util.readMenuSelection();
            switch (c) {
                case '1':
                    addNewCustomer();
                    break;
                case '2':
                    modifyCustomer();
                    break;
                case '3':
                    deleteCustomer();
                    break;
                case '4':
                    listAllCustomer();
                    break;
                case '5':
                    System.out.print("是否确认退出?Y/N\n");
                    char aChar = util.readConfirmSelection();
                    if (aChar == 'Y') {
                        flag = false;
                    }
            }
        }
    }

    /**
     * 添加客户操作
     */
    private static void addNewCustomer()
    {
        System.out.println("---------------------添加客户---------------------\n");
        System.out.print("请输入姓名(长度不超过10个字符):");
        String name = util.readString(10);
        System.out.print("\n请输入性别(m/f):");
        char gender = util.readChar();
        System.out.print("\n请输入年龄:");
        int age = util.readInt();
        System.out.print("\n请输入电话:");
        String phone = util.readString(11);
        System.out.print("\n请输入邮箱:");
        String email = util.readString(20);
        Customer customer = new Customer(name,gender,age,phone,email);
        boolean b = list.addCustomer(customer);
        if (b){
            System.out.println("添加成功!!!\n");
        }
        else{
            System.out.println("添加失败!!!\n");
        }

    }

    private static void modifyCustomer()
    {
        System.out.println("--------------------------修改客户信息---------------------------\n");
        Customer customer;
        int id;
        for (;;)
        {
            System.out.println("请输入修改客户编号(-1退出):");
            id = util.readInt();
            if (id == -1){
                return;
            }
            customer = list.getCustomer(id - 1);
            if (customer == null){
                System.out.print("没有该用户!!!");
            }
            else{
                break;
            }

        }
        System.out.print("姓名("+customer.getName()+"):");
        String name = util.readString(customer.getName(), 10);
        System.out.print("\n性别("+customer.getGender()+"):");
        char gender = util.readChar(customer.getGender());
        System.out.print("\n年龄("+customer.getAge()+"):");
        int age = util.readInt();
        System.out.print("\n电话("+customer.getPhone()+"):");
        String phone = util.readString(customer.getPhone(), 11);
        System.out.print("\n邮箱("+customer.getEmail()+"):");
        String email = util.readString(customer.getEmail(), 20);
        Customer cus = new Customer(name,gender,age,phone,email);
        boolean b = list.modifyCustomer(id - 1, cus);
        if (b){
            System.out.println("修改成功!!!");
        }
        else{
            System.out.println("修改失败!!!");
        }
    }
    private static void deleteCustomer()
    {
        System.out.println("----------------------删除客户信息--------------------------");
        int id;
        for (;;)
        {
            System.out.print("请输入待删除客户的编号(-1退出):");
            id = util.readInt();
            if (id == -1){
                return;
            }
            Customer customer = list.getCustomer(id - 1);
            if (customer == null){
                System.out.println("删除客户不存在!!!");
            }
            else{
                break;
            }
        }
        System.out.println("确认是否删除Y/N?");
        char c = util.readConfirmSelection();
        if (c == 'Y')
        {
            list.deleteCustomer(id - 1);
            System.out.println("删除成功!!!");
        }
    }

    /**
     * 显示所有的客户
     */
    private static void listAllCustomer()
    {
        System.out.print("---------------------显示客户列表-----------------------\n");
        int total = list.getTotal();
        if (total == 0)
        {
            System.out.println("没有客户信息!!!\n");
        }
        else
        {
            System.out.print("编号\t姓名\t性别\t年龄\t电话\t\t邮箱\n");
            Customer[] customers = list.getAllCustomers();
            for (int i = 0; i < total; ++i)
            {
                System.out.print((i + 1)+"\t\t"+customers[i].getName()+"\t  "+customers[i].getGender()+"\t\t "
                +customers[i].getAge()+"\t"+customers[i].getPhone()+"\t"+customers[i].getEmail()+"\n");
            }
        }
        System.out.print("-------------------------------------------------------\n");
    }
}
