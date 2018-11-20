package neo4j;
import org.neo4j.graphdb.*;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

import model.Case;
import model.Defendant;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
public class createNeo4j {
	private static void registerShutdownHook(final GraphDatabaseService graphDB) {
        // Registers a shutdown hook for the Neo4j instance so that it shuts down nicely
        // when the VM exits (even if you "Ctrl-C" the running example before it's completed)
        /*为了确保neo4j数据库的正确关闭，我们可以添加一个关闭钩子方法 registerShutdownHook。
         *这个方法的意思就是在jvm中增加一个关闭的钩子，
         *当jvm关闭的时候，会执行系统中已经设置的所有通过方法addShutdownHook添加的钩子，
         *当系统执行完这些钩子后，jvm才会关闭。
         *所以这些钩子可以在jvm关闭的时候进行内存清理、对象销毁等操作。*/
        Runtime.getRuntime().addShutdownHook(
                new Thread() {
                    public void run() {
                        //Shutdown the Database
                        System.out.println("Server is shutting down");
                        graphDB.shutdown();
                    }
                }
        );
    }
    public static void delFolder(String folderPath) {
        try {
           delAllFile(folderPath); //删除完里面所有内容
           String filePath = folderPath;
           filePath = filePath.toString();
           java.io.File myFilePath = new java.io.File(filePath);
           myFilePath.delete(); //删除空文件夹
        } catch (Exception e) {
          e.printStackTrace(); 
        }
   }
	public static boolean delAllFile(String path) {
	       boolean flag = false;
	       File file = new File(path);
	       if (!file.exists()) {
	         return flag;
	       }
	       if (!file.isDirectory()) {
	         return flag;
	       }
	       String[] tempList = file.list();
	       File temp = null;
	       for (int i = 0; i < tempList.length; i++) {
	          if (path.endsWith(File.separator)) {
	             temp = new File(path + tempList[i]);
	          } else {
	              temp = new File(path + File.separator + tempList[i]);
	          }
	          if (temp.isFile()) {
	             temp.delete();
	          }
	          if (temp.isDirectory()) {
	             delAllFile(path + "/" + tempList[i]);//先删除文件夹里面的文件
	             delFolder(path + "/" + tempList[i]);//再删除空文件夹
	             flag = true;
	          }
	       }
	       return flag;
	     }
   // public static void main(String[] args) {
    public static void create() { 
	//指定 Neo4j 存储路径
        File file = new File("E:\\neo4j-community-3.5.0-beta02\\data\\databases\\graph.db");
        if(file.exists())
        {
            createNeo4j.delFolder("E:\\neo4j-community-3.5.0-beta02\\data\\databases\\graph.db");	
        }
        //Create a new Object of Graph Database
        GraphDatabaseService graphDB = new GraphDatabaseFactory().newEmbeddedDatabase(file);
        System.out.println("Server is up and Running");
        try(Transaction tx = graphDB.beginTx()){
            /**
             * 新增User节点
             * 添加Lable以区分节点类型
             * 每个节点设置name属性
             */
        	Node[] Cases=new Node[Case.caselist.size()];
        	//Node[] Defendants=new Defendants[Case.caselist.size()];
        	int i=0;
        	List<Defendant> defendantlist=new ArrayList<Defendant>();
        	List<Defendant> defendants=new ArrayList<Defendant>();
        	for(Case c:Case.caselist)
        	{
        		Cases[i]=graphDB.createNode(MyLabels.Case);
        		Cases[i].setProperty("name",c.getCid());
        		i++;
        		defendants=begin.Util.defendantmanage.loadDefendant(c);
        		defendantlist.addAll(defendants);
        	}
        	Node[] Defendants=new Node[defendantlist.size()];
        	int j=0;
        	for(Defendant d:defendantlist)
        	{
        		Defendants[j]=graphDB.createNode(MyLabels.Defendant);
        		Defendants[j].setProperty("name",d.getDname());
        		j++;
        	}
        	Node[] Sex=new Node[2];
        	Sex[0]=graphDB.createNode(MyLabels.Sex);
        	Sex[0].setProperty("name","男");
        	Sex[1]=graphDB.createNode(MyLabels.Sex);
        	Sex[1].setProperty("name","女");
            /**
             * 为user1添加Friend关系
             * 注：Neo4j的关系是有向的箭头，正常来讲Friend关系应该是双向的，
             * 此处为了简单起见，将关系定义成单向的，不会影响后期的查询
             */
        	for(int i1=0;i1<Case.caselist.size();i1++)
        	{
        		for(int j1=0;j1<defendantlist.size();j1++)
        		{
        			if(defendantlist.get(j1).getCid().equals(Case.caselist.get(i1).getCid()))
        			{
        				Defendants[j1].createRelationshipTo(Cases[i1],MyRelationshipTypes.IS_IN);
        			}
        			
        		}
        	}
        	for(int j1=0;j1<defendantlist.size();j1++)
    		{
    			if(defendantlist.get(j1).getSex().equals("男"))
    			{
    				Defendants[j1].createRelationshipTo(Sex[0],MyRelationshipTypes.Sex_IS);
    			}else if(defendantlist.get(j1).getSex().equals("女"))
    			{
    				Defendants[j1].createRelationshipTo(Sex[1],MyRelationshipTypes.Sex_IS);
    			}
    			
    		}	
         /*   user1.createRelationshipTo(user2,MyRelationshipTypes.IS_FRIEND_OF);
            user1.createRelationshipTo(user3,MyRelationshipTypes.IS_FRIEND_OF);
            /**
             * 新增Movie节点
             * 添加Lable以区分节点类型
             * 每个节点设置name属性
             */
        	/*
            Node movie1 = graphDB.createNode(MyLabels.MOVIES);
            movie1.setProperty("name", "Fargo");

            Node movie2 = graphDB.createNode(MyLabels.MOVIES);
            movie2.setProperty("name", "Alien");

            Node movie3 = graphDB.createNode(MyLabels.MOVIES);
            movie3.setProperty("name", "Heat");*/
            /**
             * 为User节点和Movie节点之间添加HAS_SEEN关系, HAS_SEEN关系设置stars属性
             */
        	/*
            Relationship relationship1 = user1.createRelationshipTo(movie1, MyRelationshipTypes.HAS_SEEN);
            relationship1.setProperty("stars", 5);
            Relationship relationship2 = user2.createRelationshipTo(movie3, MyRelationshipTypes.HAS_SEEN);
            relationship2.setProperty("stars", 3);
            Relationship relationship6 = user2.createRelationshipTo(movie2, MyRelationshipTypes.HAS_SEEN);
            relationship6.setProperty("stars", 6);
            Relationship relationship3 = user3.createRelationshipTo(movie1, MyRelationshipTypes.HAS_SEEN);
            relationship3.setProperty("stars", 4);
            Relationship relationship4 = user3.createRelationshipTo(movie2, MyRelationshipTypes.HAS_SEEN);
            relationship4.setProperty("stars", 5);*/

            tx.success();
            System.out.println("Done successfully");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            graphDB.shutdown();    //关闭数据库
        }

        //Register a Shutdown Hook
        registerShutdownHook(graphDB);
    }
}

/**
 * Label类型枚举类
 */
enum MyLabels implements Label {
    Defendant,Case,Sex
}

/**
 * 关系类型枚举类
 */
enum MyRelationshipTypes implements RelationshipType {
    IS_FRIEND_OF, HAS_SEEN,IS_IN,Sex_IS
}


