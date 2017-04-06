package kr.ac.hansung.cse.model;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Product {
	
	private int id;
	
	/* Data Validation */
	@NotEmpty(message="product name must not be null")
	private String name;
	
	private String category;
	
	@Min(value=0, message="product price must not be less then zero")
	private int price;
	
	@NotEmpty(message="product manufacturer must not be null")
	private String manufacturer;
	
	@Min(value=0, message="product unit in stock must not be less then zero")
	private int unitInStock;
	
	private String description;
	
	/* ��������, ���������̸�, ������������ ������ ��ü�� 
	 * Performance������ DB�� ���������� �ʴ´�. 
	 * �̹��� ������ �����Ѵ�. (���� �̸��� DB�� �����Ѵ�) */
	private MultipartFile productImage;
	private String imageFileName;
}
