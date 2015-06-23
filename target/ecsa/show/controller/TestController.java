package main.webapp.show.controller;

import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/local/orderService")
public class TestController {

	 static Logger LOG = Logger.getLogger( TestController.class.toString());

	

	@ResponseBody
	       @RequestMapping(value = { "/orders/reseller" }, method = { RequestMethod.POST }, consumes = "application/json", headers = { "Content-type=application/json" })
	       public void setOrderStatus(@RequestBody OrderStatusData importedOrder) {
	          
	             LOG.info(importedOrder.getOrderNumber());
	             LOG.info("cazzo");
	}


	}

