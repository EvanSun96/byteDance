import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;



private static boolean isXmlDocument(String rtnMsg){

boolean flag = true;
try {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
        builder.parse( new InputSource( new StringReader( rtnMsg )));
} catch (Exception e) {
    flag = false;
}
return flag;
}
