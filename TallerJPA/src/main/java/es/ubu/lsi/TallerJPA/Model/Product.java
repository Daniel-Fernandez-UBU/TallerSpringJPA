package es.ubu.lsi.TallerJPA.Model;

import jakarta.persistence.*;

// TODO: Auto-generated Javadoc
/**
 * The Class Product.
 */
@Entity
@Table(name = "products")
public class Product {
	
    /** The identifier. */
    @Id
    @Column(name = "identifier")
    private String identifier;
    
    /** The brand. */
    @Column(name = "brand", length = 100)
    private String brand;
    
    /** The description. */
    @Column(name = "description", length = 200)
    private String description;
    
    /** The Quantity. */
    @Column(name = "quantity")
    private int quantity;
    
    /** The brand. */
    @Column(name = "expirationDate", length = 30)
    private String expirationDate;
    
    /** The Price. */
    @Column(name = "price")
    private float price;


	/**
	 * Gets the brand.
	 *
	 * @return the brand
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * Sets the brand.
	 *
	 * @param brand the new brand
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}

	/**
	 * Gets the identifier.
	 *
	 * @return the identifier
	 */
	public String getIdentifier() {
		return identifier;
	}

	/**
	 * Sets the identifier.
	 *
	 * @param identifier the new identifier
	 */
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the quantity.
	 *
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Sets the quantity.
	 *
	 * @param quantity the new quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * Gets the expiration date.
	 *
	 * @return the expiration date
	 */
	public String getExpirationDate() {
		return expirationDate;
	}

	/**
	 * Sets the expiration date.
	 *
	 * @param expirationDate the new expiration date
	 */
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	/**
	 * Gets the price.
	 *
	 * @return the price
	 */
	public float getPrice() {
		return price;
	}

	/**
	 * Sets the price.
	 *
	 * @param price the new price
	 */
	public void setPrice(float price) {
		this.price = price;
	}
	
	
	
    
    
    

}
