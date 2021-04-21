package com.investigatingsoftware.app;

import org.testng.annotations.*;
import org.testng.Assert;
import javax.xml.parsers.*;
import javax.xml.xpath.*;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.IOException;
import org.xml.sax.SAXException;


public class TestXMLFile {
    
    Document doc = null;

    @BeforeMethod
    public void getDoc()
    {
        try {
            File fXmlFile = new File("staff.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(fXmlFile);
        } catch(ParserConfigurationException e) {
            e.printStackTrace();
            Assert.fail();
        } catch (SAXException e) {
            e.printStackTrace();
            Assert.fail();

        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail();

        }
    }

    @Test
    public void checkXMLGeneral()
    {
        System.out.println("XML Test 1 running");

        NodeList nList = doc.getElementsByTagName("staff");
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                
                Element elem = (Element) nNode;

                System.out.println("\nCurrent Element :" + elem.getAttribute("id"));
                Assert.assertTrue(Integer.parseInt(elem.getAttribute("id")) >1000);
                Assert.assertTrue(Integer.parseInt(elem.getAttribute("id")) <2002);
                Node node1 = elem.getElementsByTagName("firstname").item(0);
                System.out.println(node1.getTextContent());
                Assert.assertTrue(node1.getTextContent().length() >= 2);
            }
        } // for
    }

    @DataProvider(name = "idAndLastNames")
    public static Object[][] idAndLastNames() {
       return new Object[][] {{1001, "mook kim"}, {2001, "yin fong"}};
    }
    @Test(dataProvider = "idAndLastNames")
    public void checkXMLIDAndLastName(int id, String lastName)
    {
        XPathFactory xPathfactory = XPathFactory.newInstance();
        XPath xpath = xPathfactory.newXPath();

        try {
            XPathExpression expr = xpath.compile("//staff[@id='" + id + "']/lastname/text()");

            System.out.println(expr.evaluate(doc, XPathConstants.STRING) + " " + lastName);
            Assert.assertEquals(expr.evaluate(doc, XPathConstants.STRING), lastName);

        } catch (Exception e) {
            Assert.fail();
        }
    }

    @DataProvider(name = "idAndFirstNames")
    public static Object[][] idAndFirstNames() {

        
       return new Object[][] {{1001, "mook kim"}, {2001, "yin fong"}};
    }
    @Test(dataProvider = "idAndLastNames")
    public void checkXMLIDAndFirstName(int id, String lastName)
    {
        XPathFactory xPathfactory = XPathFactory.newInstance();
        XPath xpath = xPathfactory.newXPath();

        try {
            XPathExpression expr = xpath.compile("//staff[@id='" + id + "']/lastname/text()");

            System.out.println(expr.evaluate(doc, XPathConstants.STRING) + " " + lastName);
            Assert.assertEquals(expr.evaluate(doc, XPathConstants.STRING), lastName);

        } catch (Exception e) {
            Assert.fail();
        }
    }

}
