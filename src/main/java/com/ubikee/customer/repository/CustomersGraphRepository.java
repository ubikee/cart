package com.ubikee.customer.repository;

import com.ubikee.customer.Customer;
import iot.jcypher.database.DBAccessFactory;
import iot.jcypher.database.DBProperties;
import iot.jcypher.database.DBType;
import iot.jcypher.database.IDBAccess;
import iot.jcypher.graph.GrNode;
import iot.jcypher.graph.GrRelation;
import iot.jcypher.graph.Graph;
import iot.jcypher.query.result.JcError;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author jeroldan
 */
public class CustomersGraphRepository implements CustomersRepository {

    Properties props = new Properties();
    IDBAccess remote;
    
    {
        props.setProperty(DBProperties.SERVER_ROOT_URI, "http://localhost:7474");
        remote = DBAccessFactory.createDBAccess(DBType.REMOTE, props, "neo4j", "mlmdlpm");
    }
    
    @Override
    public void register(Customer customer) {
        
        Graph graph = Graph.create(remote);
        GrNode customerNode = graph.createNode();
        customerNode.addLabel("CUSTOMER");
        customerNode.addProperty("CUC", customer.id);
        customerNode.addProperty("name", customer.id);
        
        GrNode cartNode = graph.createNode();
        cartNode.addLabel("CART");
        cartNode.addProperty("items", new ArrayList<>());
        
        GrRelation rel = graph.createRelation("IS_OWNER", customerNode, cartNode);
        rel.addProperty("site", "Hipercor");
        
        List<JcError> errors = graph.store();
        
        if (errors.isEmpty()) {
            errors.stream().forEach(error -> {
                System.out.println(error.getMessage());
            });
        }
    }
}
