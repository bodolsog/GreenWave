package pl.bodolsog.greenwave.model;

import org.apache.commons.beanutils.ResultSetIterator;
import org.neo4j.graphdb.*;
import pl.bodolsog.greenwave.model.Dao;

public class Crosses implements Dao<Cross> {
    private GraphDatabaseService db;

    public Crosses(GraphDatabaseService db){
        this.db = db;
    }

    @Override
    public Cross read(long id) {
        Cross r = null;
        try(Transaction tx = db.beginTx()) {
            Node n = db.getNodeById(id);
            r = new Cross();
            r.setId(n.getId());

            tx.success();
        } catch ( NotFoundException e) {
            return null;
        }
        return r;
    }

    @Override
    public boolean create(Cross cross) {
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
