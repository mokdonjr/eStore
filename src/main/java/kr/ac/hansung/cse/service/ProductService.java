package kr.ac.hansung.cse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.hansung.cse.dao.ProductDao;
import kr.ac.hansung.cse.model.Product;

@Service
public class ProductService {

	/* Controller -> Service호출 -> DAO이용해 DB접근 */
	@Autowired
	private ProductDao productDao;
	
	public List<Product> getProducts(){
		return productDao.getProducts();
	}
	
	public boolean addProduct(Product product) {
		return productDao.addProduct(product);
	}

	public boolean deleteProductById(int id) {
		return productDao.deleteProduct(id);
	}

	public Product getProductById(int id) {
		return productDao.getProductById(id);
	}

	public boolean editProduct(Product product) {
		return productDao.editProduct(product);
	}
}
