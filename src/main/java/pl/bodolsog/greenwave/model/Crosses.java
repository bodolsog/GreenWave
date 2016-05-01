package pl.bodolsog.greenwave.model;

import netscape.javascript.JSObject;
import org.apache.commons.beanutils.ResultSetIterator;
import org.neo4j.graphdb.*;
import pl.bodolsog.greenwave.model.Dao;

public class Crosses implements Dao<Cross> {
    private GraphDatabaseService db;

    public Crosses(GraphDatabaseService db){
        this.db = db;
    }

    @Override
    public Cross read(long id) throws NotFoundException{
        try(Transaction tx = db.beginTx()) {
            Node node = db.getNodeById(id);
            Cross cross = new Cross(node.getId(), node.getProperties( "lat", "lng" ));
            tx.success();
            return cross;
        } catch ( NotFoundException e) {
            throw new NotFoundException();
        }
    }

    @Override
    public long create(Cross cross) {
        try(Transaction tx = db.beginTx()){
            Node node = db.createNode(Nodes.CROSS);
            setAllProperties(node, cross);
            tx.success();
            return node.getId();
        } catch (NullPointerException e ){
            throw new NullPointerException("Id not exists.");
        }
    }

    public long create(JSObject marker) {
        return create(mapToCross(marker));
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

    @Override
    public Cross update(Cross cross) {
        try(Transaction tx = db.beginTx()) {
            Node node = db.getNodeById(cross.getId());
            setAllProperties(node, cross);
            tx.success();
        } catch (Exception e){
            e.printStackTrace();
            return new Cross();
        }
        return new Cross();
    }

    public Cross update(JSObject marker) {
        return mapToCross(marker);
    }

    private void setAllProperties(Node node, Cross cross){
        try {
            node.setProperty("lat", cross.getLat());
        } catch ( NullPointerException e ){
            node.removeProperty("lat");
        }

        try {
            node.setProperty("lng", cross.getLng());
        } catch ( NullPointerException e ){
            node.removeProperty("lng");
        }
    }

    private Cross mapToCross(JSObject JSMarker){
        Cross cross = new Cross();

        // Id
        try {
            cross.setId(Long.valueOf(JSMarker.getMember("id").toString()));
        } catch ( NumberFormatException nfe){
        } catch ( Exception e){
            System.out.println("Unexpected error in marker id conversion.");
            e.printStackTrace();
        }

        // Lat, lng.
        try {
            JSObject position = (JSObject) JSMarker.call("getPosition");
            cross.setLat((double) position.call("lat"));
            cross.setLng((double) position.call("lng"));
        } catch ( Exception e ){
            System.out.println("Unexpected error in marker lat/lng conversion.");
            e.printStackTrace();
        }

        return cross;
    }
}
