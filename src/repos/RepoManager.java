package repos;

import java.io.StringReader;
import java.util.ArrayList;

import org.xml.sax.InputSource;
import org.json.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


//initialize singleton class
class Archive
{
    // static variable single_instance of type Singleton
    private static Archive instance = null;

    // initialize array
    ArrayList<Item> items;


    // private constructor restricted to this class
    private Archive()
    {
        //define array
        this.items = new ArrayList<Item>();
    }

    //https://stackoverflow.com/questions/10174898/how-to-check-whether-a-given-string-is-valid-json-in-java/10174938
    //json xml validation
    public boolean isJSONValid(String json) {
        try {
            new JSONObject(json);
        } catch (JSONException ex) {
                return false;
        }
        return true;
    }

    public boolean isXMLValid(String xml) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setValidating(false);
            factory.setNamespaceAware(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            builder.parse(new InputSource(new StringReader(xml)));
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean validateCreate(Item item){
        if(item.getItemType()==1){
            if(isJSONValid(item.getItemContent())){
                return true;
            }else{
                return false;
            }
        }else if(item.getItemType()==2){
            if(isXMLValid(item.getItemContent())){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
    public void create(Item item){
        if(validateCreate(item)) {
            //add data to string and validate wether name already exist
            Item obj = items.stream().filter(filteredItem -> filteredItem.getItemName().equals(item.getItemName())).findFirst().orElse(null);
            if (obj == null) {
                items.add(item);
            }
        }
    }

    //find item by name
    public Item findByName(String name) {
        return items.stream().filter(item -> name.equals(item.getItemName())).findFirst().orElse(null);
    }

    //find item id
    public void removeItem(String name) {
        Item obj= items.stream().filter(item -> name.equals(item.getItemName())).findFirst().orElse(null);
        items.remove(obj);
    }

    public int findType(String name){
        Item obj= items.stream().filter(item -> name.equals(item.getItemName())).findFirst().orElse(null);
        if(obj.getItemType()==1) {
            return 1;
        }else if(obj.getItemType()==2){
            return 2;
        }else{
            return 0;
        }
    }

    // static method to create instance of Singleton class
    public static Archive getInstance()
    {
        if (instance == null)
            instance = new Archive();
        return instance;
    }
}

public class RepoManager {
    Archive x = Archive.getInstance();

    public void initialize(){};

    public void register(String itemName, String itemContent, int itemType){
        x.create(new Item(itemName,itemContent,itemType));
    }

    public void deregister(String itemName){
        x.removeItem(itemName);
    }

    public Item retrieve(String itemName){
        try {
            return x.findByName(itemName);
        }
        catch (Exception e){
            return null;
        }
    }

    public int getType(String itemName){
        return x.findType(itemName);
    }

    public Item getIndex(int index){
        try {
            return x.items.get(index);
        }
        catch(Exception e){
            return null;
        }
    }
}

class Item {
    private String itemName;
    private String itemContent;
    private int itemType;

    public Item(String n, String a, int c){

        itemName = n;
        itemContent = a;
        itemType = c;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemContent() {
        return itemContent;
    }

    public void setItemContent(String itemContent) {
        this.itemContent = itemContent;
    }

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    @Override
    public String toString() {
        return "Item Name: " + itemName +", " + "Item Content: "+ itemContent+", "+ "Item Type: "+itemType;
    }
}