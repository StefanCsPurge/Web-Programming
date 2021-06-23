package jspapp.controller;

import jspapp.domain.Order;
import jspapp.model.DBManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductController extends HttpServlet {
    private static String name_prefix = "";
    public static int noOfOrders = 0;
    private final List<Order> orders = new ArrayList<>();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(request.getParameter("add_submit")!=null && request.getParameter("add_submit").length() > 0){
            String name = request.getParameter("prod_name");
            String descr = request.getParameter("prod_description");
            RequestDispatcher rd = request.getRequestDispatcher("/homepage.jsp");

            DBManager dbmanager = new DBManager();
            dbmanager.addProduct(name, descr);

            rd.forward(request, response);
        }
        else if(request.getParameter("order_submit")!=null && request.getParameter("order_submit").length() > 0){
            String name = request.getParameter("p_name");
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            String user = (String) request.getSession().getAttribute("user");

            RequestDispatcher rd;
            DBManager dbmanager = new DBManager();
            Integer prodId = dbmanager.getProductId(name);
            if(prodId!=null){
                noOfOrders++;
                orders.add(new Order(user,prodId,quantity));
                rd = request.getRequestDispatcher("/orderOrNot.jsp");
            }
            else
                rd = request.getRequestDispatcher("/homepage.jsp");
            rd.forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(request.getParameter("search_prod")!=null && request.getParameter("search_prod").length() > 0){
            name_prefix = request.getParameter("prod_name");
            RequestDispatcher rd = request.getRequestDispatcher("/homepage.jsp");
            rd.forward(request, response);
        }
        else if(request.getParameter("finalize_submit")!=null && request.getParameter("finalize_submit").length() > 0){
            RequestDispatcher rd = request.getRequestDispatcher("/homepage.jsp");
            DBManager dbmanager = new DBManager();
            orders.forEach(dbmanager::addOrder);
            orders.clear();
            noOfOrders = 0;
            rd.forward(request, response);
        }
        else if(request.getParameter("cancel_submit")!=null && request.getParameter("cancel_submit").length() > 0){
            RequestDispatcher rd = request.getRequestDispatcher("/homepage.jsp");
            orders.clear();
            noOfOrders = 0;
            rd.forward(request, response);
        }
    }

    public static String getProductsByPrefix(){
        String sql = "SELECT * FROM products WHERE name LIKE '" + name_prefix + "%'";

        StringBuilder content = new StringBuilder("<table border=1><tr><th>ID</th><th>Name</th><th>Description</th></tr>");
        try {
            DBManager dbmanager = new DBManager();
            PreparedStatement stmt = dbmanager.getCon().prepareStatement(sql);
            ResultSet result = stmt.executeQuery();
            while (result.next()){
                content.append("<tr><td>").append(result.getString("id")).append("</td>")
                        .append("<td>").append(result.getString("name")).append("</td>")
                        .append("<td>").append(result.getString("description")).append("</td>")
                        .append("</tr>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        content.append("</table>");
        name_prefix = "";
        return content.toString();
    }
}
