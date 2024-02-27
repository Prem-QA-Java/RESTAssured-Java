package utilities;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class xmlFileReadAndWrite {

	public String body;

	DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder docBuilder;
	Document doc;

	TransformerFactory transformerFactory = TransformerFactory.newInstance();

	Logger logger = Logger.getLogger(getClass());

	/*
	 * Change properties is used to change the values of the property
	 */
	public void changeProperties(String carouselType_name_as_fileNamed, String[] property, String[] value) {
		for (int j = 0; j < property.length; j++) {
			try {

				File xmlFile = new File("src/test/resources/XML's/" + carouselType_name_as_fileNamed + "Carousel.xml");
				body = xmlFile.toString();

				docBuilder = docBuilderFactory.newDocumentBuilder();
				doc = docBuilder.parse(xmlFile);

				NodeList nodeList = doc.getElementsByTagName("ns3:property");
				for (int i = 0; i < nodeList.getLength(); i++) {
					Element element = (Element) nodeList.item(i);
					if (element.getAttribute("name").equals(property[j])) {
						element.setAttribute("value", value[j]);
						break;
					}
				}

				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(xmlFile);
				transformer.transform(source, result);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void addPromitions(String carouselType_name_as_fileNamed, String[] position, String[] contentId) {

		for (int j = 0; j < position.length; j++) {
			ArrayList<String> names = new ArrayList<String>();
			try {
				File xmlFile = new File("src/test/resources/XML's/" + carouselType_name_as_fileNamed + "Carousel.xml");
				body = xmlFile.toString();

				DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
				Document doc = docBuilder.parse(xmlFile);

				NodeList nodeLists = doc.getElementsByTagName("ns3:properties");
				for (int i = 0; i < nodeLists.getLength(); i++) {
					Element element = (Element) nodeLists.item(i);
					names.add(i, element.getAttribute("name"));
				}

				nodeLists = doc.getElementsByTagName("ns3:properties");
				/*
				 * This if condition is used if there are no ns3:properties are available then
				 * it create n3:properties tag and then create promotions
				 */
				if (nodeLists.getLength() > 0) {
					for (int i = 0; i < nodeLists.getLength(); i++) {
						Element element = (Element) nodeLists.item(i);
						/*
						 * If we already have promotional place holders then if execute else else will
						 * execute In If content condition with the already existing place holder extra
						 * promotions will get add In else the all promotional place holders will get
						 * added
						 */
						if (names.contains("promotionalPlaceHolders")) {
							if (element.getAttribute("name").equals("promotionalPlaceHolders")) {
								Element newNode = doc.createElement("ns3:properties");
								newNode.setAttribute("id", "3033234");
								newNode.setAttribute("nmae", "placeHolder");
								newNode.setAttribute("type", "promotionalPlaceHolders");
								element.appendChild(newNode);

								Element newNode1 = doc.createElement("ns3:property");
								newNode1.setAttribute("id", "4527");
								newNode1.setAttribute("name", "position");
								newNode1.setAttribute("type", "promotionalPlaceHolders");
								newNode1.setAttribute("value", position[j]);
								newNode.appendChild(newNode1);

								Element newNode2 = doc.createElement("ns3:property");
								newNode2.setAttribute("id", "54324");
								newNode2.setAttribute("name", "contentId");
								newNode2.setAttribute("type", "promotionalPlaceHolders");
								newNode2.setAttribute("value", contentId[j]);
								newNode.appendChild(newNode2);

								Element newNode3 = doc.createElement("ns3:property");
								newNode3.setAttribute("id", "58851");
								newNode3.setAttribute("name", "property");
								newNode3.setAttribute("type", "promotionalPlaceHolders");
								newNode3.setAttribute("value", "default");
								newNode.appendChild(newNode3);

								Element newNode4 = doc.createElement("ns3:property");
								newNode4.setAttribute("id", "63378");
								newNode4.setAttribute("name", "propertyValue");
								newNode4.setAttribute("type", "promotionalPlaceHolders");
								newNode4.setAttribute("value", "default");
								newNode.appendChild(newNode4);

								Element newNode5 = doc.createElement("ns3:property");
								newNode5.setAttribute("id", "67905");
								newNode5.setAttribute("name", "userSegmentRules");
								newNode5.setAttribute("type", "promotionalPlaceHolders");
								newNode.appendChild(newNode5);
								break;
							}
						} else {
							nodeLists = doc.getElementsByTagName("properties");
							Element elementProperties = (Element) nodeLists.item(i);
							Element newChildPromotional = doc.createElement("ns3:properties");
							newChildPromotional.setAttribute("id", "17800");
							newChildPromotional.setAttribute("name", "promotionalPlaceHolders");
							newChildPromotional.setAttribute("type", "promotionalPlaceHolders");
							elementProperties.appendChild(newChildPromotional);
							nodeLists = doc.getElementsByTagName("ns3:properties");
							for (int i1 = 0; i1 < nodeLists.getLength(); i1++) {
								Element element1 = (Element) nodeLists.item(i1);
								if (element1.getAttribute("name").equals("promotionalPlaceHolders")) {
									Element newNode = doc.createElement("ns3:properties");
									newNode.setAttribute("id", "3033234");
									newNode.setAttribute("name", "placeHolder");
									newNode.setAttribute("type", "promotionalPlaceHolders");
									element1.appendChild(newNode);

									Element newNode1 = doc.createElement("ns3:property");
									newNode1.setAttribute("id", "4527");
									newNode1.setAttribute("name", "position");
									newNode1.setAttribute("type", "promotionalPlaceHolders");
									newNode1.setAttribute("value", position[j]);
									newNode.appendChild(newNode1);

									Element newNode2 = doc.createElement("ns3:property");
									newNode2.setAttribute("id", "54324");
									newNode2.setAttribute("name", "contentId");
									newNode2.setAttribute("type", "promotionalPlaceHolders");
									newNode2.setAttribute("value", contentId[j]);
									newNode.appendChild(newNode2);

									Element newNode3 = doc.createElement("ns3:property");
									newNode3.setAttribute("id", "58851");
									newNode3.setAttribute("name", "property");
									newNode3.setAttribute("type", "promotionalPlaceHolders");
									newNode3.setAttribute("value", "default");
									newNode.appendChild(newNode3);

									Element newNode4 = doc.createElement("ns3:property");
									newNode4.setAttribute("id", "63378");
									newNode4.setAttribute("name", "propertyValue");
									newNode4.setAttribute("type", "promotionalPlaceHolders");
									newNode4.setAttribute("value", "default");
									newNode.appendChild(newNode4);

									Element newNode5 = doc.createElement("ns3:property");
									newNode5.setAttribute("id", "67905");
									newNode5.setAttribute("name", "userSegmentRules");
									newNode5.setAttribute("type", "promotionalPlaceHolders");
									newNode.appendChild(newNode5);
									break;
								}
							}
							break;
						}
					}
				} else {
					nodeLists = doc.getElementsByTagName("properties");
					Element elementProperties = (Element) nodeLists.item(0);
					Element newChildPromotional = doc.createElement("ns3:properties");
					newChildPromotional.setAttribute("id", "17800");
					newChildPromotional.setAttribute("name", "promotionalPlaceHolders");
					newChildPromotional.setAttribute("type", "promotionalPlaceHolders");
					elementProperties.appendChild(newChildPromotional);
					nodeLists = doc.getElementsByTagName("ns3:properties");
					for (int i1 = 0; i1 < nodeLists.getLength(); i1++) {
						Element element1 = (Element) nodeLists.item(i1);
						if (element1.getAttribute("name").equals("promotionalPlaceHolders")) {
							Element newNode = doc.createElement("ns3:properties");
							newNode.setAttribute("id", "3033234");
							newNode.setAttribute("name", "placeHolder");
							newNode.setAttribute("type", "promotionalPlaceHolders");
							element1.appendChild(newNode);

							Element newNode1 = doc.createElement("ns3:property");
							newNode1.setAttribute("id", "4527");
							newNode1.setAttribute("name", "position");
							newNode1.setAttribute("type", "promotionalPlaceHolders");
							newNode1.setAttribute("value", position[j]);
							newNode.appendChild(newNode1);

							Element newNode2 = doc.createElement("ns3:property");
							newNode2.setAttribute("id", "54324");
							newNode2.setAttribute("name", "contentId");
							newNode2.setAttribute("type", "promotionalPlaceHolders");
							newNode2.setAttribute("value", contentId[j]);
							newNode.appendChild(newNode2);

							Element newNode3 = doc.createElement("ns3:property");
							newNode3.setAttribute("id", "58851");
							newNode3.setAttribute("name", "property");
							newNode3.setAttribute("type", "promotionalPlaceHolders");
							newNode3.setAttribute("value", "default");
							newNode.appendChild(newNode3);

							Element newNode4 = doc.createElement("ns3:property");
							newNode4.setAttribute("id", "63378");
							newNode4.setAttribute("name", "propertyValue");
							newNode4.setAttribute("type", "promotionalPlaceHolders");
							newNode4.setAttribute("value", "default");
							newNode.appendChild(newNode4);

							Element newNode5 = doc.createElement("ns3:property");
							newNode5.setAttribute("id", "67905");
							newNode5.setAttribute("name", "userSegmentRules");
							newNode5.setAttribute("type", "promotionalPlaceHolders");
							newNode.appendChild(newNode5);
							break;
						}
					}
					break;
				}
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(xmlFile);
				transformer.transform(source, result);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void deletePromotions(String carouselType_name_as_fileNamed) {
		try {
			File xmlFile = new File("src/test/resources/XML's/" + carouselType_name_as_fileNamed + "Carousel.xml");
			body = xmlFile.toString();

			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(xmlFile);

			NodeList nodeLists;

			nodeLists = doc.getElementsByTagName("properties");

			for (int i = 0; i < nodeLists.getLength(); i++) {
				Element parentElement = (Element) doc.getElementsByTagName("properties").item(i);
				NodeList childNodes = parentElement.getChildNodes();
				for (int j = 0; j <= childNodes.getLength(); j++) {
					Node childElement = childNodes.item(j);

					try {
						if (childElement.getAttributes().getNamedItem("name").toString()
								.equals("name=\"promotionalPlaceHolders\"")) {
							parentElement.removeChild(childElement);
							break;
						}
					} catch (Exception e) {

					}
				}
			}

			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(xmlFile);
			transformer.transform(source, result);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void changeStatus(String carouselType_name_as_fileNmaed, String status_new_published) {
		try {
			File fi = new File("src/test/resources/XML's/" + carouselType_name_as_fileNmaed + "Carousel.xml");
			DocumentBuilderFactory dfac = DocumentBuilderFactory.newInstance();
			DocumentBuilder dbul = dfac.newDocumentBuilder();
			Document doc = dbul.parse(fi);

			NodeList nodeList = doc.getElementsByTagName("carouselStatus");
			for (int i = 0; i < nodeList.getLength(); i++) {
				Element element = (Element) nodeList.item(i);
				element.setTextContent(status_new_published);
			}
			TransformerFactory tansfac = TransformerFactory.newInstance();
			Transformer trans = tansfac.newTransformer();
			DOMSource dom = new DOMSource(doc);
			StreamResult results = new StreamResult(fi);
			trans.transform(dom, results);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void contentType(String carouselType_name_as_fileNamed, String[] contentType) {

		for (int j = 0; j < contentType.length; j++) {
			ArrayList<String> names = new ArrayList<String>();
			try {
				File xmlFile = new File("src/test/resources/XML's/" + carouselType_name_as_fileNamed + "Carousel.xml");
				body = xmlFile.toString();

				DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
				Document doc = docBuilder.parse(xmlFile);

				NodeList nodeLists = doc.getElementsByTagName("ns3:properties"); // This loop is used to get all the
																					// properties tag names
				for (int i = 0; i < nodeLists.getLength(); i++) {
					Element element = (Element) nodeLists.item(i);
					names.add(i, element.getAttribute("name"));
				}

				nodeLists = doc.getElementsByTagName("ns3:properties");

				if (nodeLists.getLength() > 0) {
					for (int i = 0; i < nodeLists.getLength(); i++) {
						Element element = (Element) nodeLists.item(i);
						if (names.contains("ContentType")) {
							if (element.getAttribute("name").equals("ContentType")) {
								Element newNode = doc.createElement("ns3:property");
								newNode.setAttribute("id", "3033234");
								newNode.setAttribute("name", "ContentType");
								newNode.setAttribute("type", "text");
								newNode.setAttribute("value", contentType[j]);
								element.appendChild(newNode);
								break;
							}
						} else {
							nodeLists = doc.getElementsByTagName("properties");
							Element elementProperties = (Element) nodeLists.item(i);
							Element newChildPromotional = doc.createElement("ns3:properties");
							newChildPromotional.setAttribute("id", "17800");
							newChildPromotional.setAttribute("name", "ContentType");
							newChildPromotional.setAttribute("type", "ContentType");
							elementProperties.appendChild(newChildPromotional);
							nodeLists = doc.getElementsByTagName("ns3:properties");
							for (int i1 = 0; i1 < nodeLists.getLength(); i1++) {
								Element element1 = (Element) nodeLists.item(i1);
								if (element1.getAttribute("name").equals("ContentType")) {
									Element newNode = doc.createElement("ns3:property");
									newNode.setAttribute("id", "3033234");
									newNode.setAttribute("name", "ContentType");
									newNode.setAttribute("type", "text");
									newNode.setAttribute("value", contentType[j]);
									element1.appendChild(newNode);
									break;
								}
							}
							break;
						}
					}

				} else {
					nodeLists = doc.getElementsByTagName("properties");
					Element elementProperties = (Element) nodeLists.item(0);
					Element newChildPromotional = doc.createElement("ns3:properties");
					newChildPromotional.setAttribute("id", "17800");
					newChildPromotional.setAttribute("name", "ContentType");
					newChildPromotional.setAttribute("type", "ContentType");
					elementProperties.appendChild(newChildPromotional);
					nodeLists = doc.getElementsByTagName("ns3:properties");
					for (int i1 = 0; i1 < nodeLists.getLength(); i1++) {
						Element element1 = (Element) nodeLists.item(i1);
						if (element1.getAttribute("name").equals("ContentType")) {
							Element newNode = doc.createElement("ns3:property");
							newNode.setAttribute("id", "3033234");
							newNode.setAttribute("name", "ContentType");
							newNode.setAttribute("type", "text");
							newNode.setAttribute("value", contentType[j]);
							element1.appendChild(newNode);
							break;
						}
					}
				}

				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(xmlFile);
				transformer.transform(source, result);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void carouselAdding(String carouselType_name_as_fileNamed, String[] carouselId, String[] postion) {

		for (int j = 0; j < postion.length; j++) {
			ArrayList<String> names = new ArrayList<String>();
			try {
				File xmlFile = new File("src/test/resources/XML's/" + carouselType_name_as_fileNamed + "Carousel.xml");
				body = xmlFile.toString();

				changeProperties(carouselType_name_as_fileNamed, new String[] { "NumberofContentsinCarousel" },
						new String[] { String.valueOf(postion.length) });

				DocumentBuilder documentBuilder = docBuilderFactory.newDocumentBuilder();
				Document doc = documentBuilder.parse(xmlFile);

				NodeList nodeLists = doc.getElementsByTagName("ns3:properties");
				for (int i = 0; i < nodeLists.getLength(); i++) {
					Element element = (Element) nodeLists.item(i);
					names.add(i, element.getAttribute("name"));
				}

				nodeLists = doc.getElementsByTagName("ns3:properties");

				if (nodeLists.getLength() > 0) {
					for (int i = 0; i < nodeLists.getLength(); i++) {
						Element element = (Element) nodeLists.item(i);

						if (names.contains("CustomContents")) {
							if (element.getAttribute("name").equals("CustomContents")) {
								Element newNode = doc.createElement("ns3:properties");
								newNode.setAttribute("id", "3033234");
								newNode.setAttribute("nmae", "content");
								newNode.setAttribute("type", "CustomContents");
								element.appendChild(newNode);

								Element newNode1 = doc.createElement("ns3:property");
								newNode1.setAttribute("id", "4527");
								newNode1.setAttribute("name", "position");
								newNode1.setAttribute("type", "CustomContents");
								newNode1.setAttribute("value", postion[j]);
								newNode.appendChild(newNode1);

								Element newNode2 = doc.createElement("ns3:property");
								newNode2.setAttribute("id", "54324");
								newNode2.setAttribute("name", "carouselId");
								newNode2.setAttribute("type", "CustomContents");
								newNode2.setAttribute("value", carouselId[j]);
								newNode.appendChild(newNode2);
								break;
							}
						} else {
							nodeLists = doc.getElementsByTagName("properties");
							Element elementProperties = (Element) nodeLists.item(i);
							Element newChildPromotional = doc.createElement("ns3:properties");
							newChildPromotional.setAttribute("id", "17800");
							newChildPromotional.setAttribute("name", "CustomContents");
							newChildPromotional.setAttribute("type", "CustomContents");
							elementProperties.appendChild(newChildPromotional);
							nodeLists = doc.getElementsByTagName("ns3:properties");
							for (int i1 = 0; i1 < nodeLists.getLength(); i1++) {
								Element element1 = (Element) nodeLists.item(i1);
								if (element1.getAttribute("name").equals("CustomContents")) {
									Element newNode = doc.createElement("ns3:properties");
									newNode.setAttribute("id", "3033234");
									newNode.setAttribute("nmae", "content");
									newNode.setAttribute("type", "CustomContents");
									element1.appendChild(newNode);

									Element newNode1 = doc.createElement("ns3:property");
									newNode1.setAttribute("id", "4527");
									newNode1.setAttribute("name", "position");
									newNode1.setAttribute("type", "CustomContents");
									newNode1.setAttribute("value", postion[j]);
									newNode.appendChild(newNode1);

									Element newNode2 = doc.createElement("ns3:property");
									newNode2.setAttribute("id", "54324");
									newNode2.setAttribute("name", "carouselId");
									newNode2.setAttribute("type", "CustomContents");
									newNode2.setAttribute("value", carouselId[j]);
									newNode.appendChild(newNode2);
									break;
								}
							}
							break;
						}
					}
				} else {
					nodeLists = doc.getElementsByTagName("properties");
					Element elementProperties = (Element) nodeLists.item(0);
					Element newChildPromotional = doc.createElement("ns3:properties");
					newChildPromotional.setAttribute("id", "17800");
					newChildPromotional.setAttribute("name", "CustomContents");
					newChildPromotional.setAttribute("type", "CustomContents");
					elementProperties.appendChild(newChildPromotional);
					nodeLists = doc.getElementsByTagName("ns3:properties");
					for (int i1 = 0; i1 < nodeLists.getLength(); i1++) {
						Element element1 = (Element) nodeLists.item(i1);
						if (element1.getAttribute("name").equals("CustomContents")) {
							Element newNode = doc.createElement("ns3:properties");
							newNode.setAttribute("id", "3033234");
							newNode.setAttribute("nmae", "content");
							newNode.setAttribute("type", "CustomContents");
							element1.appendChild(newNode);

							Element newNode1 = doc.createElement("ns3:property");
							newNode1.setAttribute("id", "4527");
							newNode1.setAttribute("name", "position");
							newNode1.setAttribute("type", "CustomContents");
							newNode1.setAttribute("value", postion[i1]);
							newNode.appendChild(newNode1);

							Element newNode2 = doc.createElement("ns3:property");
							newNode2.setAttribute("id", "54324");
							newNode2.setAttribute("name", "carouselId");
							newNode2.setAttribute("type", "CustomContents");
							newNode2.setAttribute("value", carouselId[i1]);
							newNode.appendChild(newNode2);
							break;
						}
					}
				}
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(xmlFile);
				transformer.transform(source, result);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public void deleteCarousels(String carouselType_name_as_fileNamed) {
		try {
			File xmlFile = new File("src/test/resources/XML's/" + carouselType_name_as_fileNamed + "Carousel.xml");
			body = xmlFile.toString();

			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(xmlFile);

			NodeList nodeLists;
			nodeLists = doc.getElementsByTagName("properties");

			for (int i = 0; i < nodeLists.getLength(); i++) {
				Element parentElement = (Element) doc.getElementsByTagName("properties").item(i);
				NodeList childNodes = parentElement.getChildNodes();
				for (int j = 0; j <= childNodes.getLength(); j++) {
					Node childElement = childNodes.item(j);

					try {
						if (childElement.getAttributes().getNamedItem("name").toString()
								.equals("name=\"CustomContents\"")) {
							parentElement.removeChild(childElement);
							break;
						}
					} catch (Exception e) {

					}
				}
			}

			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(xmlFile);
			transformer.transform(source, result);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void changeInResponse(String response) {
		try {
			docBuilder = docBuilderFactory.newDocumentBuilder();
			doc = docBuilder.parse(response);
			System.out.println(doc.getElementsByTagName("title"));
			System.out.println(doc.getElementsByTagName("h1"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
