package org.selenide.examples.cucumber.stepDefs;

import java.io.File;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.assertj.core.api.SoftAssertions;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DynamicValueValidationSteps {

	public static Scenario scenario;
	public static SoftAssertions softly ;
	public HashMap<String, String> scenarioValidations = new HashMap<>();
	@Before
	public void setUp(Scenario scenario) {
		this.scenario = scenario;
		 softly = new SoftAssertions();

	}

	
	@Given("user with soap service endpoint")
	public void user_with_soap_service_endpoint() {

		scenario.log("User end point is - xyz");
	}

	@When("user prepare request payload from template {string} using below values")
	public void user_prepare_request_payload_from_template_using_below_values(String templateName, io.cucumber.datatable.DataTable dataTable) {
	    // Write code here that turns the phrase above into concrete actions
	    // For automatic transformation, change DataTable to one of
	    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
	    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
	    // Double, Byte, Short, Long, BigInteger or BigDecimal.
	    //
	    // For other transformations you can register a DataTableType.

//		scenario.log("Preparing request using template "+ templateName );
	    File resourcesDirectory = new File("src/test/resources");
	    File templateFile = new File(resourcesDirectory.getAbsolutePath()+"/payload_templates/"+templateName);
	    DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
		DocumentBuilder b;
		List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
		
		try {
			b = f.newDocumentBuilder();
			Document doc = b.parse(templateFile);
			XPath xPath = XPathFactory.newInstance().newXPath();
			
			for (Map<String, String> columns : rows) {
				// store.addBook(new Book(columns.get("title"), columns.get("author")));
				String nodeValue = columns.get("nodeValue");
				if(nodeValue.equalsIgnoreCase("$publish_date$")) {
					//Dynamically generated date
					 Date dNow = new Date( );
				      SimpleDateFormat ft = 
				      new SimpleDateFormat ("yyyy-MM-dd");
				      nodeValue = ft.format(dNow);
				      scenarioValidations.put("publish_date", nodeValue);
				}
				else if(nodeValue.equalsIgnoreCase("$SID$")) {
					//Dynamically generated value 
					nodeValue = "generated_SID";
				      scenarioValidations.put("SID", "generated_SID");
					
				}else if(nodeValue.equalsIgnoreCase("$Token$")) {
					//Dynamically generated Token may be result of api response 
				}
				
				System.out.println("xmlpath: " + columns.get("xmlpath") + " nodeValue: " +nodeValue);
				Node node = (Node) xPath.compile(columns.get("xmlpath")).evaluate(doc, XPathConstants.NODE);
				node.setTextContent(nodeValue);
			}

			
			Transformer tf = TransformerFactory.newInstance().newTransformer();
			tf.setOutputProperty(OutputKeys.INDENT, "yes");
			tf.setOutputProperty(OutputKeys.METHOD, "xml");
//			tf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

			DOMSource domSource = new DOMSource(doc);
			

			String []tempArray = templateName.split("\\.");
			System.out.println(tempArray.length);
			System.out.println(tempArray);
			
			String  payloadFilePath = resourcesDirectory.getAbsolutePath()+"/payload_templates/temp/"+ tempArray[0] +"_01"+tempArray[1];
			StreamResult sr = new StreamResult(new File(payloadFilePath));
			tf.transform(domSource, sr);
			scenario.attach(Files.readAllBytes(new File(payloadFilePath).toPath()), "text/plain", "request-body");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@When("submitted request")
	public void submitted_request() {
	    // Write code here that turns the phrase above into concrete actions
		scenario.log("TODO: request will be prepared here");
	}

	@Then("response is updated with correct values")
	public void response_is_updated_with_correct_values(io.cucumber.datatable.DataTable dataTable) {
		
		List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
		
		Formatter fmt = new Formatter();  
		scenario.log(fmt.format("%30s %50s %50s %10s\n", "Attribute", "Actual", "Expected","Status").toString());
		fmt.close();
		int i=0;
		for(Map<String,String> row:rows) {
			fmt = new Formatter();  
//			scenario.log(rowFmt .format("%30s %50s %50s %10s\n", "Attribute", "Actual", "Expected","Status").toString());

			String attribute = row.get("Attribute");
			String attributeValue = row.get("AttributeValue");
			System.out.println(">>>>>>> "+ ++i);
			
	//		scenario.log("------> Actual value of" + attribute +" (read from web page) and expected is "+ attributeValue);
			
			   softly.assertThat(Double.valueOf(attributeValue)).as("Attribute %s : ",attribute).isGreaterThan(100);

			   System.out.println("softly.wasSuccess()>>>>>>>>>>>>>>>>>>>>> "+softly.wasSuccess());
			   if(softly.wasSuccess()) {
					scenario.log(fmt .format("%30s %50s %50s %10s\n", attribute, "Actual from api or UI eleent", attributeValue,"PASS").toString());

			   }else
			   {
				   scenario.log(fmt .format("%30s %50s %50s %10s\n", attribute, "Actual from api or UI eleent", attributeValue,"FAIL").toString());
			   }
			   	
		}
		System.out.println("scenarioValidations>>>>"+scenarioValidations.size());
		for (Map.Entry<String, String> entry : scenarioValidations.entrySet()) {
			String attribute = entry.getKey();
			String Expectedval = entry.getValue();
			fmt = new Formatter();  
			// Addd soft assertions for step after reading from UI/parsing API response
			//if(Actual==expected){
			//scenario.log("PASS"
			//else
			//scenario.log("Fail")
			scenario.log(fmt .format("%30s %50s %50s %10s\n", attribute, "Actual from api or UI eleent", Expectedval,"PASS/FAIL").toString());
		}
		softly.assertAll();
	}


}
