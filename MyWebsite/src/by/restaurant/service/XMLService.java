package by.restaurant.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.Format;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import by.restaurant.bean.Dish;
import by.restaurant.bean.constant.Category;
import by.restaurant.dao.DAOException;

public class XMLService {
	
	public static List<Dish> parse(String resourceName) throws DAOException{

		List<Dish> dishes = new ArrayList<Dish>();

		XMLInputFactory inputFactory = XMLInputFactory.newInstance();
		try {
			InputStream input = new FileInputStream(resourceName);
			XMLStreamReader reader = inputFactory.createXMLStreamReader(input);
			dishes = process(reader);

		} catch (XMLStreamException e) {
			throw new DAOException("Error during parsing(STAX)");
		} catch (FileNotFoundException e) {
			throw new DAOException("Error during getting file!");
		}
		return dishes;
	}

	private static List<Dish> process(XMLStreamReader reader) throws XMLStreamException {
		
		List<Dish> dishes = new ArrayList<Dish>();
		Set<String> ingredients = null;
		Dish dish = null;
		RestaurantTagName elementName = null;
		
		while (reader.hasNext()) {
			int type = reader.next();
			switch (type) {
			case XMLStreamConstants.START_ELEMENT:
				elementName = RestaurantTagName.getElementTagName(reader.getLocalName());
				switch (elementName) {
				case DISH:
					dish = new Dish();
					String name = reader.getAttributeValue(null, "name");
					String picture = reader.getAttributeValue(null, "picture");
					String amount = reader.getAttributeValue(null, "amount");

					dish.setName(name);
					dish.setPicture(picture);
					dish.setAmount(amount);

					break;

				case CATEGORY:
					dish.setCategory(Category.valueOf(reader.getAttributeValue(null, "name")));
					break;

				case INGREDIENTS:
					ingredients = new HashSet<>();

					break;

				case INGREDIENT:
					ingredients.add((reader.getAttributeValue(null, "name")));
					break;
				}
				break;

			case XMLStreamConstants.CHARACTERS:
				String text = reader.getText().trim();
				if (text.isEmpty()) {
					break;
				}
				switch (elementName) {

				case PRICE:
					dish.setPrice(Double.parseDouble(text));
					break;
				}

				break;

			case XMLStreamConstants.END_ELEMENT:
				elementName = RestaurantTagName.getElementTagName(reader.getLocalName());
				switch (elementName) {

				case INGREDIENTS:
					dish.setIngredients(ingredients);
					break;

				case DISH:
					dishes.add(dish);
				}
			}
		}
		return dishes;
	}

	public static void createXMLDocument(List<Dish> dishes) throws ParserConfigurationException, TransformerConfigurationException{
	     
		// Создаем документ
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document xmlDoc = docBuilder.newDocument();
        // Создаем корневой элемент
        Element root = xmlDoc.createElement("restaurant");
        // Добавляем корневой элемент в документ
        xmlDoc.appendChild(root);
        
		for(Dish dish: dishes) {

        
        // Создаем элемент head и добавляем ему атрибут
        Element dishElement = xmlDoc.createElement("dish");
        root.appendChild(dishElement);
        dishElement.setAttribute("id", String.valueOf(dish.getId()));
        dishElement.setAttribute("name", dish.getName());
        dishElement.setAttribute("picture", dish.getPicture());
        dishElement.setAttribute("amount", dish.getAmount());
        
        Element priceElement = xmlDoc.createElement("price");
        priceElement.appendChild(xmlDoc.createTextNode(String.valueOf(dish.getPrice())));
        dishElement.appendChild(priceElement);
        
        Element categoryElement = xmlDoc.createElement("category");
        categoryElement.appendChild(xmlDoc.createTextNode(dish.getCategory().name()));
        dishElement.appendChild(categoryElement);
        
        if(dish.getIngredients() != null) {
        Element ingredientsElement = xmlDoc.createElement("ingredients");
        for(String ingredient: dish.getIngredients()) {
            Element ingredientElement = xmlDoc.createElement("ingredient");
            ingredientsElement.appendChild(ingredientElement);
            ingredientElement.setAttribute("name", ingredient);
            dishElement.appendChild(ingredientsElement);

        }
        }
        }
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer tr = tf.newTransformer();
        DOMSource s = new DOMSource(xmlDoc);
        StreamResult result = new StreamResult(new File("D:\\xml\\allDishes.xml"));
        try {
			tr.transform(s, result);
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
