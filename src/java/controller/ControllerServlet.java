/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import cart.ShoppingCart;
import entity.Category;
import entity.Product;
import java.io.IOException;
import java.util.Collection;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.CategoryFacade;
import session.ProductFacade;

/**
 *
 * @author dima
 */
@WebServlet(name = "ControllerServlet", 
        loadOnStartup = 1,
        urlPatterns = {"/index",
            "/selectCategory",
            "/searchProduct",
            "/productInfo",
            "/addToCart",
            "/shoppingCart",
            "/removeFromCart",
            "/changeQuantity"
        })
public class ControllerServlet extends HttpServlet {

    @EJB
    private CategoryFacade categoryFacade;
    
    @EJB
    private ProductFacade productFacade;
    
    @Override
    public void init() throws ServletException {

        // store category list in servlet context
        getServletContext().setAttribute("categories", categoryFacade.findAll());
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        String userPath = request.getServletPath();
        Category selectedCategory;
        Collection<Product> categoryProducts;
        Collection<Product> searchProducts;


        // if category page is requested
        if (userPath.equals("/selectCategory")) {
            // get categoryId from request
            int categoryId = Integer.parseInt(request.getQueryString());
            //getServletContext().setAttribute("searchForCategory", categoryId);
            // get selected category
            selectedCategory = categoryFacade.find(categoryId);
            // get all products for selected category
            categoryProducts = selectedCategory.getProductCollection();
            
            // place category products in request scope
            request.setAttribute("categoryProducts", categoryProducts);
            
            userPath = "/index";
        }
        
        else if (userPath.equals("/searchProduct")) {
            String searchString = request.getParameter("searchString");
            
            if(searchString != "") {
                searchProducts = productFacade.search(searchString);
                request.setAttribute("searchProducts", searchProducts);
            }
            
            userPath = "/index";
        }
        
        else if (userPath.equals("/productInfo")) {
            Product selectedProduct;
            selectedProduct = productFacade.find(Integer.parseInt(request.getQueryString()));
            request.setAttribute("selectedProduct", selectedProduct);
            
        }
        
        else if (userPath.equals("/shoppingCart")) {
                        
        }
        

        // use RequestDispatcher to forward request internally
        String url = userPath + ".jsp";

        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        String userPath = request.getServletPath();
        HttpSession session = request.getSession();
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");

        // if addToCart action is called
        if (userPath.equals("/addToCart")) {
            if(cart == null){
                cart = new ShoppingCart();
                session.setAttribute("cart", cart);
            }
            
            String productId = request.getParameter("productId");

            if (!productId.isEmpty()) {

                Product product = productFacade.find(Integer.parseInt(productId));
                cart.addItem(product);
                request.setAttribute("selectedProduct", product);
            }

            userPath = "/productInfo";
        }
        
        else if (userPath.equals("/removeFromCart")) {
            String productId = request.getParameter("productId");
            String quantity = "0";

            Product product = productFacade.find(Integer.parseInt(productId));
            cart.update(product, quantity);
            
            userPath = "/shoppingCart";
        }
        
        else if (userPath.equals("/changeQuantity")) {
            String productId = request.getParameter("productId");
            String quantity = request.getParameter("quantity");

            Product product = productFacade.find(Integer.parseInt(productId));
            cart.update(product, quantity);
            
            userPath = "/shoppingCart";
        }
        
        

        // use RequestDispatcher to forward request internally
        String url = userPath + ".jsp";

        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}