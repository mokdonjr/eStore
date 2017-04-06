package kr.ac.hansung.cse.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.ac.hansung.cse.model.Product;

@Repository
public class ProductDao {

	/* 1. JDBC Template�� DataSource�� ���� �޴´�. */
	private JdbcTemplate jdbcTemplateObject; // DAO��ü�� JdbcTemplate��ü�� Ȱ��.
	
	@Autowired
	public void setDataSource(DataSource dataSource) { // JdbcTemplate�� DataSource�� ���Թ��� ��ü.
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}
	
	/* 2. */
	public List<Product> getProducts(){ // ��� ���ڵ带 ��ȸ
		String sqlStatement = "select * from product";
		return jdbcTemplateObject.query(sqlStatement, new ProductMapper());
//		return jdbcTemplateObject.query(sqlStatement, new RowMapper<Product>() {
//			@Override
//			public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
//				Product product = new Product();
//				product.setId(rs.getInt("id"));
//				product.setName(rs.getString("name"));
//				product.setCategory(rs.getString("category"));
//				product.setPrice(rs.getInt("price"));
//				product.setManufacturer(rs.getString("manufacturer"));
//				product.setUnitInStock(rs.getInt("unitInStock"));
//				product.setDescription(rs.getString("description"));
//				return product;
//			}
//		});
	}

	/* �����ͺ��̽��� insert */ // ���߿� Hibernate�� ��ü
	public boolean addProduct(Product product) {
		String name = product.getName();
		String category = product.getCategory();
		int price = product.getPrice();
		String manufacturer = product.getManufacturer();
		int unitInStock = product.getUnitInStock();
		String description = product.getDescription();
		String imageFileName = product.getImageFileName(); // DB�� imageFileName�� ����
		
		String sqlStatement = "insert into product "
				+ "(name, category, price, manufacturer, unitInStock, description, imageFileName) "
				+ "values (?,?,?,?,?,?,?)";
		return jdbcTemplateObject.update(sqlStatement, new Object[] {
			name, category, price, manufacturer, unitInStock, description, imageFileName
			}) == 1; // update()�޼���� �ݿ��� ���ڵ� ������ ����
	}

	public boolean deleteProduct(int id) {
		String sqlStatement = "delete from product where id=?";
		return jdbcTemplateObject.update(sqlStatement, new Object[] {id}) == 1;
	}

	public Product getProductById(int id) {
		String sqlStatement = "select * from product where id=?";
		return jdbcTemplateObject.queryForObject(sqlStatement, new Object[] {id}, new ProductMapper()); // query�� ���� ��ü, queryForObject�� �ϳ��� ��ü
	}

	public boolean editProduct(Product product) {
		String sqlStatement = "update product set name=?, category=?, price=?, manufacturer=?, unitInStock=?"
				+ ", description=?, imageFileName=? where id=?";
		return jdbcTemplateObject.update(sqlStatement, new Object[] {product.getName(), product.getCategory(), product.getPrice(), product.getManufacturer()
				, product.getUnitInStock(), product.getDescription(), product.getImageFileName(), product.getId() }) == 1;
	}
}
