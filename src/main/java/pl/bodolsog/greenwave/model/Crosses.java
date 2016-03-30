package pl.bodolsog.greenwave.model;

import org.apache.commons.beanutils.ResultSetIterator;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Result;
import org.neo4j.graphdb.Transaction;
import pl.bodolsog.greenwave.model.Dao;

public class Crosses implements Dao<Cross> {
    private GraphDatabaseService db;

    public Crosses(GraphDatabaseService db){
        this.db = db;
    }

    @Override
    public boolean create(Cross object) {
        try(Transaction tx = db.beginTx()){
            db.createNode(Nodes.CROSS);
            tx.success();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(long id) {
        try(Transaction tx = db.beginTx()) {
            Node node = db.getNodeById(id);
            node.delete();
            tx.success();
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
