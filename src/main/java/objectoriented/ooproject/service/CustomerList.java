package objectoriented.ooproject.service;

import objectoriented.ooproject.bean.Customer;


@SuppressWarnings("all")
public class CustomerList {

    private Customer[] customers;       //用来保存用户的数组
    private int total;              //保存客户数量

    /**
     * 用构造器来初始化用户数量数组
     *
     * @param totalCustomer
     */
    public CustomerList(int totalCustomer) {
        customers = new Customer[totalCustomer];
    }

    /**
     * 添加客户
     *
     * @param customer
     * @return
     */
    public boolean addCustomer(Customer customer) {
        if (total >= customers.length)
        {
            return false;       //客户已满
        }
        else
        {
            customers[total++] = customer;
            return true;
        }
    }

    /**
     * 修改客户信息
     *
     * @param index
     * @param model
     * @return
     */
    public boolean modifyCustomer(int index, Customer model) {
        if (index < 0 || index > total)
        {
            return false;
        }
        else
        {
            customers[index] = model;
            return true;
        }
    }

    /**
     * 删除客户信息
     *
     * @param index
     * @return
     */
    public boolean deleteCustomer(int index) {
        if (index < 0 || index >= total)
        {
            return false;
        }
        else
        {
            for (int i = index; index < total - 1; ++index)
            {
                customers[i] = customers[i + 1];
            }
            customers[--total] = null;
            return true;
        }
    }

    public Customer[] getAllCustomers() {
        Customer[] cus = new Customer[total];
        for (int i = 0; i < total; ++i)
        {
            cus[i] = customers[i];
        }
        return cus;
    }

    public Customer getCustomer(int index) {
        if (index < 0 || index >= total)
        {
            return null;
        }
        return customers[index];
    }

    public int getTotal() {
        return total;
    }

}
