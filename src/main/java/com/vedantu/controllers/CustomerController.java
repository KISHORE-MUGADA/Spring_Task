package com.vedantu.controllers;

import com.vedantu.daos.AbstractMongoClientFactory;
import com.vedantu.daos.CartMongoDAO;
import com.vedantu.daos.CustomerMongoDAO;
import com.vedantu.daos.EmployeeDAO;
import com.vedantu.daos.EmployeeMongoDAO;
import com.vedantu.daos.OrdersMongoDAO;
import com.vedantu.daos.ProductsMongoDAO;
import com.vedantu.enums.Status;
import com.vedantu.models.CartItem;
import com.vedantu.models.CartMongo;
import com.vedantu.models.CustomerMongo;
import com.vedantu.models.Employee;
import com.vedantu.models.EmployeeMongo;
import com.vedantu.models.OrderItem;
import com.vedantu.models.OrdersMongo;
import com.vedantu.models.ProductsMongo;
import com.vedantu.requests.CartReq;
import com.vedantu.requests.CustomerReq;
import com.vedantu.requests.EmployeeReq;
import com.vedantu.requests.OrdersReq;
import com.vedantu.requests.ProductsReq;
import com.vedantu.utils.LogFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.servlet.tags.Param;

@RestController
@RequestMapping("test1")
public class CustomerController {

	@Autowired
	private CustomerMongoDAO customerMongoDAO;

	@Autowired
	private ProductsMongoDAO productsMongoDAO;

	@Autowired
	private CartMongoDAO cartMongoDAO;

	@Autowired
	private OrdersMongoDAO ordersMongoDAO;
	
	@Autowired
	private LogFactory logFactory;

	private final Logger logger = logFactory.getLogger(CustomerController.class);

	// addCustomer
	@RequestMapping(value = "/addCustomer", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String addParam(@RequestBody CustomerReq param) throws Exception {

		CustomerMongo e2 = new CustomerMongo();
		e2.setName(param.getName());
		e2.setAddress(param.getAddress());
		e2.setAmount(param.getAmount());
		e2.setContactnumber(param.getContactnumber());
		e2.setCustomerType(param.getCustomerType());
		logger.info(e2);
		customerMongoDAO.create(e2);
		return "Added customer successfully";
	}

	// addAmount
	@RequestMapping(value = "/addAmount", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String addamountParam(@RequestBody CustomerReq param) throws Exception {

		CustomerMongo e2 = customerMongoDAO.getById(param.getId());
		if (param.getAmount() > 0) {
			e2.setAmount(e2.getAmount() + param.getAmount());
		}
		customerMongoDAO.update(e2, null);
		return "added Amount";
	}

	// updateCustomer
	@RequestMapping(value = "/updateCustomer", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String updateParam(@RequestBody CustomerReq param) throws Exception {

		CustomerMongo e2 = customerMongoDAO.getById(param.getId());
		e2.setName(param.getName());
		e2.setAddress(param.getAddress());
		e2.setAmount(param.getAmount());
		e2.setContactnumber(param.getContactnumber());
		e2.setCustomerType(param.getCustomerType());

		customerMongoDAO.update(e2, null);
		return "updated customer successfully";
	}

	// deleteCustomer
	@RequestMapping(value = "/deleteCustomer", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String deleteParam(@RequestBody CustomerReq param) throws Exception {

		CustomerMongo e2 = customerMongoDAO.getById(param.getId());

		customerMongoDAO.delete(e2, null);
		return "deleted customer successfully";
	}

	// addProducts
	@RequestMapping(value = "/addProducts", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String addParam(@RequestBody ProductsReq param) throws Exception {

		ProductsMongo e2 = new ProductsMongo();
		e2.setName(param.getName());
		e2.setWherehouseid(param.getWherehouseid());
		e2.setPrice(param.getPrice());
		e2.setQuantity(param.getQuantity());
		e2.setProductType(param.getProductType());

		productsMongoDAO.create(e2);
		return "Added product successfully";
	}

	// updateProducts
	@RequestMapping(value = "/updateProducts", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String updateParam(@RequestBody ProductsReq param) throws Exception {

		ProductsMongo e2 = productsMongoDAO.getById(param.getId());
		e2.setName(param.getName());
		e2.setWherehouseid(param.getWherehouseid());
		e2.setPrice(param.getPrice());
		e2.setQuantity(param.getQuantity());
		e2.setProductType(param.getProductType());

		productsMongoDAO.update(e2, null);
		return "Updated product successfully";
	}

	// deleteProducts
	@RequestMapping(value = "/deleteProducts", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String deleteParam(@RequestBody ProductsReq param) throws Exception {

		ProductsMongo e2 = productsMongoDAO.getById(param.getId());
		productsMongoDAO.delete(e2, null);
		return "Deleted product successfully";
	}

	// deleteSingleProductFromCart
	@RequestMapping(value = "/deleteCartPid", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String deletecartpidParam(@RequestBody CartReq param) throws Exception {

		CartMongo cartdata = cartMongoDAO.getEntityByCId(param.getCustomerid(), CartMongo.class);
		if (cartdata.getCartitems() != null && !cartdata.getCartitems().isEmpty()) {
			List<CartItem> cartItems = cartdata.getCartitems();

			if (cartItems.contains(param.getCartitems())) {
				cartItems.remove(param.getCartitems());
				cartdata.setCartitems(cartItems);
				cartMongoDAO.create(cartdata);
				return "Cart single product deleted";
			} else {
				return "given product does not exist in cart";
			}
		} else {

			return "This product  not existed in cartitems";
		}
	}

	// addCart
	@RequestMapping(value = "/addCart", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String addcartParam(@RequestBody CartReq param) throws Exception {

		CustomerMongo cc = customerMongoDAO.getEntityById(param.getCustomerid(), CustomerMongo.class);
		ProductsMongo productdata = productsMongoDAO.getEntityById(param.getCartitems().getPid(), ProductsMongo.class);
		if (cc != null) {
			if (productdata != null) {
				CartMongo cart = cartMongoDAO.getEntityByCId(param.getCustomerid(), CartMongo.class);
				if (cart == null) {
					List<CartItem> cartitem = new ArrayList<CartItem>();
					cartitem.add(param.getCartitems());
					CartMongo e2 = new CartMongo();
					e2.setCustomerid(param.getCustomerid());
					e2.setCartitems(cartitem);

					cartMongoDAO.create(e2);
					return "added cart";
				} else {

					CartMongo cart1 = cartMongoDAO.getEntityByCId(param.getCustomerid(), CartMongo.class);

					List<CartItem> cItems = cart1.getCartitems();

					if (cItems != null) {

						if (cItems.contains(param.getCartitems())) {
							for (CartItem cartItem : cItems) {
								if (cartItem.equals(param.getCartitems())) {
									cartItem.setQuantity(cartItem.getQuantity() + param.getCartitems().getQuantity());
									break;
								}
							}
						} else {
							cItems.add(param.getCartitems());
						}
						cart.setCartitems(cItems);

					} else {
						List<CartItem> newcItems = new ArrayList<CartItem>();
						newcItems.add(param.getCartitems());
						cart.setCartitems(newcItems);

					}
					cartMongoDAO.create(cart);
					return "cart updated";

				}
			} else {
				return "product not available in list";
			}
		} else {
			return "Customer Not exits First Create Customer Account then continue";
		}

	}

	// updateCart
	/*
	 * @RequestMapping(value = "/updateCart", method = RequestMethod.POST, consumes
	 * = MediaType.APPLICATION_JSON_VALUE, produces =
	 * MediaType.APPLICATION_JSON_VALUE)
	 * 
	 * @ResponseBody public String updatecartParam(@RequestBody CartReq param)
	 * throws Exception { CartMongo e2 = cartMongoDAO.getById(param.getId());
	 * e2.setCustomerid(param.getCustomerid()); cartMongoDAO.update(e2,null); return
	 * "Updated cart successfully"; }
	 */

	// deleteCart
	@RequestMapping(value = "/deleteCart", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String deletecartParam(@RequestBody CartReq param) throws Exception {

		CartMongo e2 = cartMongoDAO.getById(param.getId());
		e2.setCustomerid(param.getCustomerid());
		cartMongoDAO.delete(e2, null);
		return "Deleted from cart successfully";
	}

	// placeOrder
	@RequestMapping(value = "/placeOrderProper", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String addorderParam(@RequestBody OrdersReq param) throws Exception {

		CartMongo c = cartMongoDAO.getByCustomerId(param.getCustomerid());

		Set<String> ordIds = c.getProductIds();

		List<ProductsMongo> prdts = productsMongoDAO.getProductsFromIds(ordIds);
		Map<String, Integer> cartmap = new HashMap<>();

		for (CartItem cartItem : c.getCartitems()) {
			cartmap.put(cartItem.getPid(), cartItem.getQuantity());
		}

		int temp = 0;
		for (ProductsMongo prdt : prdts) {
			if (prdt.getQuantity() < cartmap.get(prdt.getId())) {
				temp = 1;
				break;
				// throw new RuntimeException("item not in stock for "+prdt.getName());
			}
		}
		if (temp == 0) {
			List<OrderItem> orderitem_list = new ArrayList<OrderItem>();

			for (ProductsMongo prdt1 : prdts) {
				// adding order items
				int cart_quantity = cartmap.get(prdt1.getId());
				orderitem_list.add(new OrderItem(prdt1.getId(), cart_quantity, prdt1.getPrice()));
			}
			// add Order
			OrdersMongo o = new OrdersMongo();
			o.setOrderitems(orderitem_list);
			o.setCustomerid(param.getCustomerid());
			o.setTotalprice(param.getTotalprice());

			// checking amount in customer
			CustomerMongo customer = customerMongoDAO.getById(o.getCustomerid());
			if (o.getTotalprice() <= customer.getAmount()) {

				// subtract the customer amount
				customer.setAmount(customer.getAmount() - o.getTotalprice());
				customerMongoDAO.create(customer);

				for (ProductsMongo prdt1 : prdts) {
					// subtract the quantity
					prdt1.setQuantity(prdt1.getQuantity() - cartmap.get(prdt1.getId()));
					productsMongoDAO.create(prdt1);
				}
				o.setStatus(Status.PAID);
				ordersMongoDAO.create(o);

				// clear the cart
				c.setCartitems(null);
				cartMongoDAO.create(c);
			}
			return "Order placed successfully";

		} else {
			return "Quantity not available";
		}

	}

	// cancelOrder
	@RequestMapping(value = "/cancelOrder", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody

	public String cancelOrder(@RequestBody OrdersReq param) throws Exception {
		OrdersMongo order_obj = ordersMongoDAO.getById(param.getId());
		if (order_obj != null) {
			if (order_obj.getStatus().equals(Status.PAID)) {
				Set<String> ordIds = order_obj.getProductIds();
				List<ProductsMongo> prdts = productsMongoDAO.getProductsFromIds(ordIds);

				// Adding the customer amount
				CustomerMongo customer = customerMongoDAO.getById(order_obj.getCustomerid());
				customer.setAmount(customer.getAmount() + order_obj.getTotalprice());
				customerMongoDAO.create(customer);

				// Adding the quantity
				Map<String, Integer> ordermap = new HashMap<>();
				for (OrderItem orderItem : order_obj.getOrderitems()) {
					ordermap.put(orderItem.getPid(), orderItem.getQuantity());
				}
				for (ProductsMongo prdt1 : prdts) {
					prdt1.setQuantity(prdt1.getQuantity() + ordermap.get(prdt1.getId()));
					productsMongoDAO.create(prdt1);
				}
				// Change the status of order
				order_obj.setStatus(Status.CANCELLED);
				ordersMongoDAO.create(order_obj);
				return "Order Canceled Successfully";
			} else {
				return "Order already canceled";
			}
		} else {
			return "Order not exist";
		}
	}

	// deleteOrderFromDatabaseUsingId
	@RequestMapping(value = "/deleteOrder", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String deleteOrder(@RequestBody OrdersReq param) throws Exception {
		OrdersMongo order_obj = ordersMongoDAO.getById(param.getId());

		ordersMongoDAO.delete(order_obj, null);
		return "Order delete from Database";
	}

	// placeOrder2
	@RequestMapping(value = "/placeOrder", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String adddorderParam(@RequestBody OrdersReq param) throws Exception {

		CartMongo c = cartMongoDAO.getByCustomerId(param.getCustomerid());
		if (c != null) {
			Set<String> ordIds = c.getProductIds();

			List<ProductsMongo> prdts = productsMongoDAO.getProductsFromIds(ordIds);
			Map<String, Integer> cartmap = new HashMap<>();

			for (CartItem cartItem : c.getCartitems()) {
				cartmap.put(cartItem.getPid(), cartItem.getQuantity());
			}

			List<CartItem> citem_list = c.getCartitems();
			List<OrderItem> orderitem_list = new ArrayList<OrderItem>();

			for (ProductsMongo prdt : prdts) {
				if (prdt.getQuantity() < cartmap.get(prdt.getId())) {
					// throw new RuntimeException("item not in stock for "+prdt.getName());
				} else {
					// adding order items
					int cart_quantity = cartmap.get(prdt.getId());
					orderitem_list.add(new OrderItem(prdt.getId(), cart_quantity, prdt.getPrice()));
					// remove from cartitem_list
					// subtractingQuantity
					citem_list.remove(new CartItem(prdt.getId(), cart_quantity));
					prdt.setQuantity(prdt.getQuantity() - cartmap.get(prdt.getId()));
				}
			}

			if (orderitem_list != null && !orderitem_list.isEmpty()) {
				// add Order
				OrdersMongo o = new OrdersMongo();
				o.setOrderitems(orderitem_list);
				o.setCustomerid(param.getCustomerid());
				o.setTotalprice(param.getTotalprice());

				// checking amount in customer
				CustomerMongo customer = customerMongoDAO.getById(o.getCustomerid());
				if (o.getTotalprice() <= customer.getAmount()) {

					// subtract the customer amount
					customer.setAmount(customer.getAmount() - o.getTotalprice());
					customerMongoDAO.create(customer);
					for (ProductsMongo prdt1 : prdts) {
						productsMongoDAO.create(prdt1);
					}
					o.setStatus(Status.PAID);
					ordersMongoDAO.create(o);

					// clear the cart
					if (citem_list != null) {
						c.setCartitems(citem_list);
					} else {
						c.setCartitems(null);
					}
					cartMongoDAO.create(c);
				} else {
					return "Less amount";
				}
				return "Order Placed Successfully";
			} else {
				return "Quantity not avalable for some products";
			}
		} else {
			return "Cart does not exist for this customer";
		}
	}

	@RequestMapping(value = "/placeOrderFromPd", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String addddorderParam(@RequestBody OrdersReq param) throws Exception {

		CustomerMongo cdetails = customerMongoDAO.getById(param.getCustomerid());
		if(cdetails != null) {
		List<OrderItem> orlist = new ArrayList<OrderItem>();
		for (OrderItem orderItem : param.getOrderitems()) {
			ProductsMongo prdata = productsMongoDAO.getEntityById(orderItem.getPid(), ProductsMongo.class);
		if(prdata != null && prdata.getQuantity()>= orderItem.getQuantity()) {
			OrdersMongo ordata = new OrdersMongo();
			orlist.add(new OrderItem(orderItem.getPid(), orderItem.getQuantity(), prdata.getPrice()));
			prdata.setQuantity(prdata.getQuantity() - orderItem.getQuantity());
			cdetails.setAmount(cdetails.getAmount() - prdata.getPrice());
			productsMongoDAO.create(prdata);

			ordata.setCustomerid(param.getCustomerid());
			ordata.setOrderitems(orlist);
			ordata.setTotalprice(param.getTotalprice());
			ordata.setStatus(Status.PAID);
			ordersMongoDAO.create(ordata);
			customerMongoDAO.create(cdetails);
		} else {
			return "Product not available or Product Quanity not available";
		}
		}
		}else {
			    return "Customer not available now";
		      }
		return "order placed successfully";
	  }
	
	@RequestMapping(value = "/getd", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Collection<CustomerMongo> getParam() throws Exception {
		Collection<CustomerMongo>  product_info = customerMongoDAO.getAll();
	return  product_info;
	}
	
	@RequestMapping(value = "/productid/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ProductsMongo productid(@PathVariable("id") String id) throws Exception {

	return productsMongoDAO.getEntityById(id, ProductsMongo.class);
	}
	
	@RequestMapping(value = "/getproducts", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Collection<ProductsMongo> gettParam() throws Exception {
		Collection<ProductsMongo>  product_info = productsMongoDAO.getAll();
	return  product_info;
	}
	
	@RequestMapping(value = "/getc", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Collection<CartMongo> getcParam() throws Exception {
		Collection<CartMongo>  cart_info = cartMongoDAO.getAll();
	return  cart_info;
	}
	
}