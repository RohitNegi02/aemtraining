
package aemtraining.core.models;

import java.util.ArrayList;
import java.util.List;
 
import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.Property;
import javax.jcr.RepositoryException;
import javax.jcr.Value;
import javax.jcr.ValueFormatException;
 
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
import com.adobe.cq.sightly.WCMUsePojo;
 
public class IssuesMultiBean extends WCMUsePojo{
  
    private final Logger logger = LoggerFactory.getLogger(IssuesMultiBean.class);
  
    private IssueBean iBean = null;
  
    private List<IssueBean> lBean = null;
  
  
    public List<IssueBean> getLBean() {
         return this.lBean;
    }
     
    public void setLBean(List<IssueBean> items) {         
        this.lBean = items; 
    }
 
 
    @Override
    public void activate() throws Exception {
  
        Node currentNode = getResource().adaptTo(Node.class); 
        String currentItem = "iItems";
        logger.info("Current Node : " + currentNode); 
        if(currentNode.hasProperty(currentItem)){ 
            setItems(currentNode, currentItem);  
        } 
    }        
  
    private void setItems(Node currentNode, String tab) throws PathNotFoundException, RepositoryException, ValueFormatException, JSONException {
        Value[] value;
  
        JSONObject jObj; 
        Property currentProperty; 
        lBean = new ArrayList<IssueBean>();
  
        currentProperty = currentNode.getProperty(tab);
        logger.info("Current Property : " + currentProperty );
  
        if(currentProperty.isMultiple()){         
  
            value = currentProperty.getValues();            
  
        }else{
  
            value = new Value[1]; 
            value[0] = currentProperty.getValue();            
  
        }
        logger.info("Current Value : " + value.length );
         
        for (int i = 0; i < value.length; i++) {
  
            jObj = new JSONObject(value[i].getString());            
            iBean = new IssueBean();            
            iBean.setPage(jObj.getString("issueTitle"));            
            iBean.setPath(jObj.getString("path"));
            iBean.setDesc(jObj.getString("issueDesc"));
            logger.info("Current Bean : " +  value[i].getString());
              
            lBean.add(iBean);
         }   
        logger.info("Current json : " + lBean.toString() );
        setLBean(lBean);
        
    }
}