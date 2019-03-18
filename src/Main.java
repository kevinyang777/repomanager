import sun.security.util.AuthResources_zh_CN;
import repos.RepoManager;

public class Main {

    public static void main(String[] args) {
        RepoManager repoHandler=new RepoManager();
        repoHandler.register("acaca","{'test':'this'}",1);
        repoHandler.register("acacas","<?xml version=\"1.0\" encoding=\"UTF-8\"?><app hash='nv' name='Tech' package='1.0' version='13' filesize='200' create_date='01-03-1987' upate_date='07-09-2013' ><url><itemName>\"abcdef\"</itemName></url></app>",2);
        System.out.println("index0"+repoHandler.getIndex(0));
        System.out.println("index1"+repoHandler.getIndex(1));
    }
}
