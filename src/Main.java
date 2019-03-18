import sun.security.util.AuthResources_zh_CN;
import repos.RepoManager;

public class Main {

    public static void main(String[] args) {
        RepoManager repoHandler=new RepoManager();
        //create with correct json format = true
        repoHandler.register("item1","{'test':'this'}",1);
        System.out.println(repoHandler.retrieve("item1"));
        //create with correct xml format = true
        repoHandler.register("item2","<?xml version=\"1.0\" encoding=\"UTF-8\"?><app hash='nv' name='Tech' package='1.0' version='13' filesize='200' create_date='01-03-1987' upate_date='07-09-2013' ><url><itemName>\"abcdef\"</itemName></url></app>",2);
        System.out.println(repoHandler.retrieve("item2"));
        //create with wrong json format = false
        repoHandler.register("item3","'test':'this'}",1);
        System.out.println(repoHandler.retrieve("item3"));
        //create with wrong XML format = false
        repoHandler.register("item4","<script type=\"text/javascript\" charset=\"utf-8\" id=\"zm-extension\"/><to>Tove</to><from>Jani</from><heading>Reminder</heading><body>Don't forget me this weekend!</body></note>",2);
        System.out.println(repoHandler.retrieve("item4"));
        //get record type
        System.out.println(repoHandler.getType("item1"));
        System.out.println(repoHandler.getType("item2"));
        //remove a record
        repoHandler.deregister("item1");
        System.out.println(repoHandler.retrieve("item1"));
    }
}
